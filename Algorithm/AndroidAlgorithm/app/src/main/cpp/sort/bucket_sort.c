//
// Created by Lee64 on 20.5.6.
//
#include <stdio.h>

#include "../common/logs.h"

// 时间复杂度是O(M+N)
int bucket_sort() {
    int i, j, num, score, bucket[100];
    for (i = 0; i < 101; i++) {
        bucket[i] = 0;
    }
    num = 8;
    for (i = 0; i < num; i++) {
        score = (i + 1) * 5;
        // 进桶
        bucket[score]++;
    }
    for (i = 101; i > 0; i--) {
        for (j = 1; j <= bucket[i]; j++) {

        }
    }
    return 1;
}
