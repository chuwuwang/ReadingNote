//
// Created by Lee64 on 20.5.6.
//
#include <stdio.h>
#include <string.h>
#include <malloc.h>
#include "../common/logs.h"

// 为了使桶排序更加高效，我们需要做到这两点：
//     1. 在额外空间充足的情况下，尽量增大桶的数量
//     2. 使用的映射函数能够将输入的 N 个数据均匀的分配到 K 个桶中
// 平均时间复杂度：O(M + N)
// 最佳时间复杂度：O(M + N)
// 最差时间复杂度：O(N ^ 2)
// 空间复杂度：O(M * N)
// 稳定性：稳定

char *bucket_sort(int num, int size) {
    size_t len = sizeof(char) * num;
    char buff[1000];
    LOG_E("len = %d", len);
    LOG_E("num = %d", num);
    LOG_E("size = %d", size);
    int i, j, bucket[size];
    for (i = 0; i < size + 1; i++) {
        bucket[i] = 0;
    }
    for (i = 0; i < num; i++) {
        int value = (i + 3) * 3;
        bucket[value]++; // 进桶
        LOG_E("value = %d", value);
    }
    for (i = 0; i < size + 1; i++) {
        for (j = 1; j <= bucket[i]; j++) {
            char temp[4];
            sprintf(temp, "%d", i);
            LOG_E("temp = %s", temp);
            strcat(buff, temp);
        }
    }
    char *ptr = buff;
    return ptr;
}