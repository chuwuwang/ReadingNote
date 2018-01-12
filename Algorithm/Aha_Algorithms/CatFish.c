#include <stdio.h>

struct queue
{
    int data[1000];
    int head;
    int tail;
};

struct stack
{
    int data[10];
    int top;
};

int main()
{
    struct queue q1, q2;
    struct stack s;
    int book[10];
    int i, t;

    // 初始化队列
    q1.head = 1;
    q1.tail = 1;
    q2.head = 1;
    q2.tail = 1;

    // 初始化栈
    s.top = 0;
    // 初始化用来标记数组，用来标记哪些牌已经在桌上
    for (i = 0; i <= 9; ++i)
        book[i] = 0;

    // 依次向队列插入6个数
    for (i = 1; i <= 6; ++i)
    {
        scanf("%d", &q1.data[q1.tail]);
        q1.tail++;
    }
    for (i = 1; i <= 6; ++i)
    {
        scanf("%d", &q2.data[q2.tail]);
        q2.tail++;
    }

    while (q1.head < q1.tail && q2.head < q2.tail)
    {
        t = q1.data[q1.head];
        // 判断当前打出的牌是否已经在桌子上了
        if (book[t] == 0)
        {
            q1.head++;
            s.top++;
            s.data[s.top] = t;
            book[t] = 1;
        }
        else
        {
            q1.head ++;
            q1.data[q1.tail] = t;
            q1.tail++;
            while (s.data[s.top] != t)
            {
                book[s.data[s.top]] = 0;
                q1.data[q1.tail] = s.data[s.top];
                q1.tail++;
                s.top--;
            }
        }
    }

    t = q2.data[q2.head];
    if (book[t] == 0)
    {
        q2.head++;
        s.top++;
        s.data[s.top] = t;
        book[t] = 1;
    }
    else
    {
        q2.head++;
        q2.data[q2.tail] = t;
        q2.tail++;
        while (s.data[s.top] != t)
        {
            book[s.data[s.top]] = 0;
            q2.data[q2.tail] = s.data[s.top];
            q2.tail++;
            s.top--;
        }
    }

    if (q2.head == q2.tail)
    {
        printf("小哼Win\n");
        printf("小哼当前手中的牌是：");
        for (i = q1.head; i <= q1.tail - 1; ++i)
            printf("%d ", q1.data[i]);
        if (s.top > 0)
        {
            printf("\n桌面上的牌是：");
            for (i = 1; i <= s.top; ++i)
                printf("%d ", s.data[i]);
        }
        else
            printf("\n桌面上已经没牌了");
    }
    else
    {
        printf("小哈Win\n");
        printf("小哈当前手中的牌是：");
        for (i = q2.head; i <= q2.tail - 1; ++i)
            printf("%d ", q2.data[i]);
        if (s.top > 0)
        {
            printf("\n桌面上的牌是：");
            for (i = 1; i <= s.top; ++i)
                printf("%d ", s.data[i]);
        }
        else
            printf("\n 桌面上已经没牌了");
    }


    getchar();
    getchar();

    return 0;

}


