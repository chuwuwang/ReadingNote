// 快速排序
#include <stdio.h>

int a[101], n;

void quick_sort(int left, int right)
{
    int i, j, t, temp;
    if(left > right)
    {
        return;
    }

    temp = a[left]; // temp中存的就是基准数
    i = left;
    j = right;

    while(i != j)
    {
        // 顺序很重要，先要从右往左找
        while(a[j] >= temp && i < j)
        {
            j--;
        }
        // 再从左往右找
        while(a[i] <= temp && i < j)
        {
            i++;
        }
        // 交换两个数在数组中的位置
        if(i < j)
        {
            // 当哨兵i和哨兵j没有相遇时
            t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }

    // 最终将基准数归位
    a[left] = a[i];
    a[i] = temp;

    // 继续处理左边的，这是一个递归的过程
    quick_sort(left, i - 1);

    // 继续处理右边的，这是一个递归的过程
    quick_sort(i + 1, right);
}

int main()
{
    int i, j, t;
    scanf("%d", &n);
    for (i = 1; i <= n; ++i)
    {
        scanf("%d", &a[i]);
    }

    quick_sort(1, n);

    for (i = 1; i <= n; ++i)
    {
        printf("%d ", a[i]);
    }

    getchar();
    getchar();
    return 0;
}
