package com.sea.common.http.interceptor;

import android.content.Context;
import android.util.Log;

import com.sea.common.http.interceptor.ui.HttpLogsHelper;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.CipherSuite;
import okhttp3.Connection;
import okhttp3.Handshake;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.TlsVersion;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;
import okio.GzipSource;

public class CaptureOkHttpInterceptor implements Interceptor {

    private static final String TAG = "CaptureOkHttp";
    private static final Charset UTF8 = Charset.forName("UTF-8");

    private Context context;

    public CaptureOkHttpInterceptor(Context ctx) {
        context = ctx;
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        HttpEntity entity = new HttpEntity();

        Request request = chain.request();
        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;

        String requestStartMessage = "-->";

        String methodString = request.method();
        requestStartMessage += " " + methodString;
        entity.setRequestMethod(methodString);

        HttpUrl httpUrl = request.url();

        String hostString = httpUrl.host();
        entity.setRequestHost(hostString);

        String urlString = httpUrl.toString();
        requestStartMessage += " " + urlString;
        entity.setRequestUrl(urlString);

        String schemeString = httpUrl.scheme();
        entity.setRequestScheme(schemeString);

        String pathString = httpUrl.encodedPath();
        entity.setRequestPath(pathString);

        long requestTimeMillis = System.currentTimeMillis();
        entity.setRequestTime(requestTimeMillis);

        Connection connection = chain.connection();
        if (connection != null) {
            Protocol protocol = connection.protocol();
            requestStartMessage += " " + protocol;
        }

        if (hasRequestBody) {
            long contentLength = requestBody.contentLength();
            requestStartMessage += " (" + contentLength + " byte body)";
        }
        log(requestStartMessage);

        long requestContentLength = 0L;
        if (hasRequestBody) {
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                String contentTypeString = contentType.toString();
                log("Content-Type: " + contentTypeString);
                entity.setRequestContentType(contentTypeString);
            }
            long contentLength = requestBody.contentLength();
            if (contentLength != -1L) {
                requestContentLength = contentLength;
                log("Content-Length: " + requestContentLength);
                entity.setRequestContentLength(contentLength);
            }
        }

        Headers requestHeaders = request.headers();
        HashMap<String, String> requestHeadersMap = new HashMap<>();
        for (int i = 0; i < requestHeaders.size(); i++) {
            String name = requestHeaders.name(i);
            String value = requestHeaders.value(i);
            requestHeadersMap.put(name, value);
            boolean b1 = "Content-Type".equalsIgnoreCase(name);
            boolean b2 = "Content-Length".equalsIgnoreCase(name);
            if (b1 || b2) {
                // continue;
            }
            log("Request Header: " + name + ": " + value);
        }
        entity.setRequestHeaders(requestHeadersMap);

        String queryParameterString = httpUrl.encodedQuery();
        if (queryParameterString != null) {
            entity.setRequestInquireParameter(queryParameterString);
        }

        Set<String> queryParameterNames = httpUrl.queryParameterNames();
        if (queryParameterNames != null && queryParameterNames.size() > 0) {
            HashMap<String, String> queryParameterMap = new HashMap<>();
            for (String name : queryParameterNames) {
                String parameter = httpUrl.queryParameter(name);
                queryParameterMap.put(name, parameter);
                log(name + ": " + parameter);
            }
            entity.setRequestInquireParameterMap(queryParameterMap);
        }

        boolean isUnknownEncoding = bodyHasUnknownEncoding(requestHeaders);
        if (requestBody == null) {
            log("--> END " + methodString);
        } else if (isUnknownEncoding) {
            log("--> END " + methodString + " (encoded body omitted)");
        } else {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);

            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }

            log("");
            boolean isPlaintext = isPlaintext(buffer);
            if (isPlaintext) {
                String requestBodyString = buffer.readString(charset);
                log(requestBodyString);
                log("--> END " + methodString + " (" + requestContentLength + " byte body)");
                entity.setRequestBody(requestBodyString);
                entity.setRequestBodyIsPlaintext(true);
            } else {
                log("--> END " + methodString + " (" + requestContentLength + " byte body omitted)");
            }
        }

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////

        Response response;
        long startNs = System.nanoTime();
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            e.printStackTrace();
            log("<-- HTTP FAILED: " + e);
            throw e;
        }
        long endNs = System.nanoTime();
        long tookMs = TimeUnit.NANOSECONDS.toMillis(endNs - startNs);
        entity.setTookMillis(tookMs);

        long responseTimeMillis = System.currentTimeMillis();
        entity.setResponseTime(responseTimeMillis);

        ResponseBody responseBody = response.body();

        Protocol protocol = response.protocol();
        String protocolString = protocol.toString();
        entity.setProtocol(protocolString);

        Handshake handshake = response.handshake();
        if (handshake != null) {
            TlsVersion tlsVersion = handshake.tlsVersion();
            String tlsVersionName = tlsVersion.name();
            log("tlsVersion: " + tlsVersionName);
            entity.setTlsVersion(tlsVersionName);

            CipherSuite cipherSuite = handshake.cipherSuite();
            String cipherSuiteName = cipherSuite.javaName();
            log("cipherSuite: " + cipherSuiteName);
            entity.setCipherSuite(cipherSuiteName);

            List<Certificate> peerCertificates = handshake.peerCertificates();
            for (Certificate certificate : peerCertificates) {
                if (certificate instanceof X509Certificate) {
                    X509Certificate x509Certificate = (X509Certificate) certificate;
                    // String x509CertificateString = x509Certificate.toString();
                    // log("x509Certificate: " + x509CertificateString);
                    Principal subjectDN = x509Certificate.getSubjectDN();
                    if (subjectDN != null) {
                        String name = subjectDN.getName();
                        log("peerCertificates subjectDN: " + name);
                    }
                }
            }
            List<Certificate> localCertificates = handshake.localCertificates();
            for (Certificate certificate : localCertificates) {
                if (certificate instanceof X509Certificate) {
                    X509Certificate x509Certificate = (X509Certificate) certificate;
                    Principal subjectDN = x509Certificate.getSubjectDN();
                    if (subjectDN != null) {
                        String name = subjectDN.getName();
                        log("localCertificates subjectDN: " + name);
                    }
                }
            }
        }

        long responseContentLength = responseBody.contentLength();
        String bodySize = responseContentLength != -1L ? responseContentLength + " byte" : "unknown-length";

        String responseStartMessage = "<--";

        int codeString = response.code();
        responseStartMessage += " " + codeString;
        entity.setResponseCode(codeString);

        String messageString = response.message();
        if (messageString != null && messageString.length() > 0) {
            responseStartMessage += " " + messageString;
        }
        entity.setResponseMessage(messageString);

        responseStartMessage += " " + urlString;

        responseStartMessage += " (" + tookMs + "ms)";

        log(responseStartMessage);

        Headers responseHeaders = response.headers();
        HashMap<String, String> responseHeadersMap = new HashMap<>();
        for (int i = 0; i < responseHeaders.size(); i++) {
            String name = responseHeaders.name(i);
            String value = responseHeaders.value(i);
            responseHeadersMap.put(name, value);
            log(name + ": " + value);
        }
        entity.setResponseHeaders(responseHeadersMap);

        boolean promisesBody = HttpHeaders.promisesBody(response);
        boolean hasUnknownEncoding = bodyHasUnknownEncoding(responseHeaders);
        if (promisesBody == false) {
            log("<-- END HTTP");
        } else if (hasUnknownEncoding) {
            log("<-- END HTTP (encoded body omitted)");
        } else {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.getBuffer();

            long gzippedLength = 0L;
            String contentEncoding = responseHeaders.get("Content-Encoding");
            boolean equalsIgnoreCase = "gzip".equalsIgnoreCase(contentEncoding);
            if (equalsIgnoreCase) {
                gzippedLength = buffer.size();
                GzipSource gzippedResponseBody = null;
                try {
                    Buffer cloneBuffer = buffer.clone();
                    gzippedResponseBody = new GzipSource(cloneBuffer);
                    buffer = new Buffer();
                    buffer.writeAll(gzippedResponseBody);
                } finally {
                    if (gzippedResponseBody != null) {
                        gzippedResponseBody.close();
                    }
                }
            }

            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
                String contentTypeString = contentType.toString();
                entity.setResponseContentType(contentTypeString);
            }

            boolean isPlaintext = isPlaintext(buffer);
            if (isPlaintext) {
                if (responseContentLength != 0L) {
                    log("");
                    String responseBodyString = buffer.clone().readString(charset);
                    log(responseBodyString);
                    entity.setResponseBody(responseBodyString);
                }
                entity.setResponseBodyIsPlaintext(true);
                if (gzippedLength != 0L) {
                    log("<-- END HTTP (" + buffer.size() + " byte, " + gzippedLength + " gzipped-byte body)");
                } else {
                    log("<-- END HTTP (" + buffer.size() + " byte body)");
                }
            } else {
                log("");
                log("<-- END HTTP (binary " + buffer.size() + " byte body omitted)");
            }
            long size = buffer.size();
            entity.setResponseContentLength(size);
        }

        HttpLogsHelper.saveHttpLog(context, entity);

        return response;
    }

    private boolean bodyHasUnknownEncoding(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null &&
                contentEncoding.equalsIgnoreCase("gzip") == false &&
                contentEncoding.equalsIgnoreCase("identity") == false;
    }

    private boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                boolean exhausted = prefix.exhausted();
                if (exhausted) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                boolean bool = Character.isISOControl(codePoint) && Character.isWhitespace(codePoint) == false;
                if (bool) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void log(String log) {
        int size = 4000;
        long length = log.length();
        if (length > size) {
            while (log.length() > size) {
                String substring = log.substring(0, size);
                log = log.replace(substring, "");
                Log.d(TAG, substring);
            }
        }
        Log.d(TAG, log);
    }

}