// 求1-n的全排列

#include <stdio.h>

int a[100], book[100], n;

void dfs(int step)
{
    if (step == n + 1)
    {
        for (int i = 1; i <= n; i++)
            printf("%d ", a[i]);
        printf("\n");
        return; // 返回之前的一步（最近一次调用dfs函数的地方）
    }
    for (int i = 1; i <= n; i++)
    {
        if (book[i] == 0)
        {
            a[step] = i;
            book[i] = 1;    // 表示已经使用过了

            dfs(step + 1);  // 这里通过函数的递归调用来实现
            book[i] = 0;    // 回收结果
        }
    }
    return;
}

int main()
{
    scanf("%d", &n);
    dfs(1);
    getchar();
    getchar();
    return 0;
}

