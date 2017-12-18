// 回文数字

#include <stdio.h>
#include <string.h>

int main()
{
    char a[101], s[101];
    int i, len, mid, next, top;
    gets(a);            // 读入一行字符串
    len = strlen(a);    // 求字符串的长度
    mid = len / 2 - 1;  // 求字符串的中点

    top = 0;            // 栈的初始化

    // 将mid前的字符一次入栈
    for (i = 0; i <= mid; ++i)
        s[++top] = a[i];

    // 判断字符串的长度是奇数还是偶数，并找出需要进行字符匹配的起始下标
    if (len % 2 == 0)
        next = mid + 1;
    else
        next = mid + 2;

    // 开始匹配
    for (i = next; i <= len - 1; ++i)
    {
        if (a[i] != s[top])
            break;
        top--;
    }

    if (top == 0)
        printf("YES");
    else
        printf("NO");

    getchar();
	getchar();

    return 0;

}
