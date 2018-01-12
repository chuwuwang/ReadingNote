#include <stdio.h>

struct note
{
    int x;
    int y;  // 纵坐标
    int f;
    int s;  // 步数
};

int main ()
{
    struct note que[2501];

    int a[51][51] = {0};
    int book[51][51] = {0};

    // 定义一个用于表示走的方向的数组
    int next[4][2] =
    {
        {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    int head;
    int tail;

    int n;
    int m;

    scanf("%d %d", &n, &m);
    for (int i = 1; i <= n; i++)
        for (int j = 1; j <= m; ++j)
            scanf("%d", &a[i][j]);

    int startX;
    int startY;
    int p;
    int q;
    scanf("%d %d %d %d", &startX, &startY, &p, &q);

    head = 1;
    tail = 1;
    // 往队列插入迷宫入口坐标
    que[tail].x = startX;
    que[tail].y = startY;
    que[tail].f = 0;
    que[tail].s = 0;
    tail++;
    book[startX][startY] = 1;

    int flag = 0;   // 用来标记是否到达目标点，0表示还没有到达，1表示到达
    int tx;
    int ty;
    // 当队列不为空时循环
    while (head < tail)
    {
        // 枚举四个方向
        for (int i = 0; i <= 3; ++i)
        {
            // 计算下一个点的坐标
            tx = que[head].x + next[i][0];
            ty = que[head].y + next[i][1];
            // 判断是否越界
            if (tx < 1 || tx > n || ty < 1 || ty > m)
                continue;
			// a[tx][ty] == 0 表示又障碍物
            if (a[tx][ty] == 0 &&  book[tx][ty] == 0)
            {
                // 把这个点标记为走过
                book[tx][ty] = 1;
                // 插入新的点到队列
                que[tail].x = tx;
                que[tail].y = ty;
                que[tail].f = head;
                que[tail].s= que[head].s + 1;
                tail++;
            }
            // 如果到达目标 停止扩展
            if (tx == p && ty == q)
            {
                flag = 1;
                break;
            }
        }
        if (flag == 1)
            break;
        head++;
    }

    printf("%d", que[tail -1].s);
    getchar();
    getchar();
    return 0;
}
