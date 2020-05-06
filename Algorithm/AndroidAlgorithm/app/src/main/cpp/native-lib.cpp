#include <jni.h>
#include <string>

#include "inc/add.h"
#include "common/logs.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_sea_algorithm_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {

    add();

    std::string hello = "Hello from C++ XXX";
    const char *p = hello.c_str();
    p = "123456";

    LOG_E("Hello world");

    return env->NewStringUTF(p);
}