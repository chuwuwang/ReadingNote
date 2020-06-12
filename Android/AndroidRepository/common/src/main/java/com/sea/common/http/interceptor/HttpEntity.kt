package com.sea.common.http.interceptor

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.sea.common.BR
import java.io.Serializable

class HttpEntity : BaseObservable(), Serializable {

    private val serialVersionUID = 3475562060660977192L

    @get:Bindable
    var tookMillis: Long = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.tookMillis)
        }

    @get:Bindable
    var requestTime: Long = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.requestTime)
        }

    @get:Bindable
    var responseTime: Long = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.responseTime)
        }

    @get:Bindable
    var protocol: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.protocol)
        }

    @get:Bindable
    var tlsVersion: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.tlsVersion)
        }

    @get:Bindable
    var cipherSuite: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.cipherSuite)
        }

    @get:Bindable
    var requestUrl: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.requestUrl)
        }

    @get:Bindable
    var requestPath: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.requestPath)
        }

    @get:Bindable
    var requestHost: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.requestHost)
        }

    @get:Bindable
    var requestScheme: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.requestScheme)
        }

    @get:Bindable
    var requestMethod: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.requestMethod)
        }

    @get:Bindable
    var requestInquireParameter: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.requestInquireParameter)
        }

    @get:Bindable
    var requestInquireParameterMap: HashMap<String, String> = HashMap()
        set(value) {
            field = value
            notifyPropertyChanged(BR.requestInquireParameterMap)
        }

    @get:Bindable
    var requestBody: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.requestBody)
        }

    @get:Bindable
    var requestBodyIsPlaintext = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.requestBodyIsPlaintext)
        }

    @get:Bindable
    var requestHeaders: HashMap<String, String> = HashMap()
        set(value) {
            field = value
            notifyPropertyChanged(BR.requestHeaders)
        }

    @get:Bindable
    var requestContentLength: Long = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.requestContentLength)
        }

    @get:Bindable
    var requestContentType: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.requestContentType)
        }

    @get:Bindable
    var responseCode = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.responseCode)
        }

    @get:Bindable
    var responseMessage: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.responseMessage)
        }

    @get:Bindable
    var responseBody: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.responseBody)
        }

    @get:Bindable
    var responseBodyIsPlaintext = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.responseBodyIsPlaintext)
        }

    @get:Bindable
    var responseHeaders: HashMap<String, String> = HashMap()
        set(value) {
            field = value
            notifyPropertyChanged(BR.responseHeaders)
        }

    @get:Bindable
    var responseContentLength: Long = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.responseContentLength)
        }

    @get:Bindable
    var responseContentType: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.responseContentType)
        }

}