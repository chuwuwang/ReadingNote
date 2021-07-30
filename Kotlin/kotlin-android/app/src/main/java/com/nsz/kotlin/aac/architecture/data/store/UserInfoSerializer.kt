package com.nsz.kotlin.aac.architecture.data.store

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.kotlin.demo.proto.LoginInfo
import java.io.InputStream
import java.io.OutputStream

object UserInfoSerializer : Serializer<LoginInfo.Login> {

    override val defaultValue: LoginInfo.Login
        get() = LoginInfo.Login.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): LoginInfo.Login {
        try {
            return LoginInfo.Login.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", e)
        }
    }

    override suspend fun writeTo(t: LoginInfo.Login, output: OutputStream) {
        t.writeTo(output)
    }

}