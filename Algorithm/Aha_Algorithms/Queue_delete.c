// 问题描述：
// 一串数据，删除第一个，第二个放到最后一位，再重复上述步骤，直至删除最后一个，最后打印删除的数字
// 631758924 -> 615947283

#include <stdio.h>

int main()
{
    int q[102] = {0, 6, 3, 1, 7, 5, 8, 9, 2, 4};
    int head, tail;

    head = 1;
    tail = 10;

    while (head < tail)
    {
        printf("%d ", q[head]);
        head++;
        q[tail] = q[head];
        tail++;
        head++;
    }
    getchar();
    getchar();
    return 0;
}
