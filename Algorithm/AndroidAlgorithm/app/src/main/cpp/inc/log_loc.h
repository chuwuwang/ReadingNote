//
// Created by Lee64 on 20.5.6.
//

#ifndef ANDROID_ALGORITHM_LOG_LOC_H
#define ANDROID_ALGORITHM_LOG_LOC_H

// 引入log头文件
#include  <android/log.h>
// log标签
#define TAG "LZ"
#define LOG_I(...) __android_log_print(ANDROID_LOG_INFO, TAG, __VA_ARGS__)
#define LOG_D(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
#define LOG_E(...) __android_log_print(ANDROID_LOG_ERROR, TAG, __VA_ARGS__)

#endif //ANDROID_ALGORITHM_LOG_LOC_H