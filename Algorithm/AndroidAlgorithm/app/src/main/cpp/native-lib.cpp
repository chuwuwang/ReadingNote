#include <jni.h>
#include <string>

#include "common/logs.h"
#include "sort/bucket_sort.h"

extern "C"
JNIEXPORT jstring JNICALL
Java_com_sea_algorithm_MainActivity_stringFromJNI(JNIEnv *env, jobject /* this */) {
    std::string hello = "Hello from C++";
    const char *ptr = hello.c_str();

    LOG_E("Hello world JNI");

    return env->NewStringUTF(ptr);
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_sea_algorithm_sort_BucketSort_bucketSort(JNIEnv *env, jobject, jint num, jint size) {
    int c_num = num;
    int c_size = size;
    char *ptr = bucket_sort(c_num, c_size);
    return env->NewStringUTF(ptr);
}