// 火柴棍等式
// 最大24根
// a + b = c
// 求有多少中组合方式
#include <stdio.h>

int fun(int x)
{
    // 用来计数的变量
    int num = 0;
    int f[10] = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};

    // 如果商不为0的话，这个数一定是两位数
    while (x / 10 != 0)
    {

        num += f[x % 10];
        x = x / 10;
    }
    num += f[x];
    // 返回需要火柴的总根数
    return num;
}

int main()
{
    int a, b, c, m, i, sum = 0;
    scanf("%d", &m);

    // 开始枚举a和b
    for (a = 0; a <= 1111; a++)
    {
        for (b = 0; b <= 1111; ++b)
        {
            c = a + b;
            if (fun(a) + fun(b) + fun(c) == m - 4)
            {
                printf("%d + %d = %d \n", a, b, c);
                sum++;
            }
        }
    }
    printf("total:%d", sum);
    getchar();
    getchar();
    return 0;
}
