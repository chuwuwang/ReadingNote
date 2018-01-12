#include <stdio.h>

struct note
{
    int x;
    int y;
};

int main ()
{
    struct note que[2501];
    int head;
    int tail;
    int a[51][51];
    int book[51][51];

    // 定义一个用于表示走的方向的数组
    int next[4][2] =
    {
        {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    int startX;
    int startY;
    int n;
    int m;
    scanf("%d %d %d %d", &n, &m, &startX, &startY);

    for (int i = 1; i <= n; i++)
        for (int j = 1; j <= m; ++j)
            scanf("%d", &a[i][j]);

    int sum;

    // 队列初始化
    head = 1;
    tail = 1;
    que[tail].x = startX;
    que[tail].y = startY;
    tail++;
    book[startX][startY] = 1;
    sum = 1;

    int tx;
    int ty;
    while (head < tail)
    {
        for (int i = 0; i < 3; ++i)
        {
            tx = que[head].x + next[i][0];
            ty = que[head].y + next[i][1];

            if (tx < 1 || tx > n || ty < 1 || ty > m)
                continue;

            if (a[tx][ty] > 0 &&  book[tx][ty] == 0)
            {
                sum++;
                que[tail].x = tx;
                que[tail].y = ty;
                book[tx][ty] = 1;
                tail++;
            }
        }
        head++;
    }

    printf("sum = %d\n", sum);
    getchar();
    getchar();
    return 0;

}
