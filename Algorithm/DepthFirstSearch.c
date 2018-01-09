#include <stdio.h>

int a[10], book[10], total = 0;

void depthFirstSearch (int step)
{
    if (step == 10)
    {
        _Bool ok = a[1] *100 + a[2] * 10 + a[3] + a[4] * 100 + a[5] * 10 + a[6] == a[7] * 100 + a[8] * 10 + a[9];
        if (ok)
        {
            total ++;
            printf("%d%d%d + %d%d%d = %d%d%d\n", a[1], a[2], a[3], a[4], a[5], a[6], a[7], a[8], a[9]);
        }
        return;
    }
    for (int i = 1; i <= 9; i++)
    {
        if (book[i] == 0)
        {
            a[step] = i;
            book[i] = 1;

            depthFirstSearch(step + 1);

            book[i] = 0;
        }
    }
    return;
}

int main ()
{
    depthFirstSearch(1);

    printf("total = %d", total / 2);
    getchar();
    getchar();
    return 0;
}


