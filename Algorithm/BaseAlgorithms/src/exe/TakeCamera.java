package exe;

import java.util.ArrayList;
import java.util.List;

// 集体拍照
// 1.每排人数为N/K（向下取整），多出来的人数全部站在最后一排
// 2.后排所有人的个子都不比前排任何人矮
// 3.每排中最高者站中间（中间位置为m/2+1，其中m为该排人数，除法向下取整）
// 4.每排其他人以中间人为轴，按身高非增序，先右后左交替入队站在中间人的两侧（例如5人身高为190、188、
// 186、175、170，则队形为175、188、190、186、170。这里加入你面对拍照者，所以你的左边是中间人的右边）
// 5.若多人身高相同，则按名字的字典升序排序。这里保证无重名
// 6.现在给定一组拍照人，请编写出他们的队形

// 输入格式：
// N：总人数（<=10000） K：总排数（<=10）

// 输出格式：
// 输出拍照的队形。
// 即K排人名，其间以空格分隔，行末不得有多余空格。
// 注意：假设你面对拍照者，后排的人输出在上方，前排输出在下方。

// 输入
// 10 3
// Tom 188 Mike 170 Eva 168 Tim 160 Joe 190 Ann 168 Bob 175
// Nick 186 Amy 160 John 159

// 输出
// Bob Tom Joe Nick
// Ann Mike Eva
// Time Amy John

public class TakeCamera {

    public static void main(String[] args) {

        List<People> list = new ArrayList<>();
        People people = new People("Tom", 188);
        list.add(people);
        people = new People("Mike", 170);
        list.add(people);
        people = new People("Eva", 168);
        list.add(people);
        people = new People("Tim", 160);
        list.add(people);
        people = new People("Joe", 190);
        list.add(people);
        people = new People("Ann", 168);
        list.add(people);
        people = new People("Bob", 175);
        list.add(people);
        people = new People("Nick", 186);
        list.add(people);
        people = new People("Amy", 160);
        list.add(people);
        people = new People("John", 159);
        list.add(people);

        new TakeCameraSort().sort(3, list);
    }
}

class TakeCameraSort {

    public void sort(int column, List<People> list) {
        int size = list.size();
        for (int i = 0; i < column; i++) {

            // 计算最后一行的人数
            int temp;
            if (i == 0) {
                temp = size / column + size % column;
            } else {
                temp = size / column;
            }

            // 初始化每一行的人数
            List<People> tempList = new ArrayList<>();
            for (int j = 0; j < temp; j++) {
                People people = list.get(j);
                people.temp = j;
                tempList.add(people);
            }

            // 获取最小值
            People min = min(tempList);

            for (int j = temp; j < list.size(); j++) {
                People people = list.get(j);
                if (people.height > min.height) {
                    // 更换list中的最小值
                    people.temp = min.temp;
                    tempList.remove(min.temp);
                    tempList.add(people.temp, people);

                    // // 获取最小值
                    min = min(tempList);
                }
            }

            // 对每一行进行排序
            People[] peoples = rowSort(tempList);

            // 打印
            for (int j = 0; j < peoples.length; j++) {
                People p = peoples[j];
                // 去除最后一行的空格
                if (j == peoples.length - 1) {
                    System.out.print(p.name);
                } else {
                    System.out.print(p.name + " ");
                }
                list.remove(p);
            }
            System.out.println();
        }
    }

    /**
     * 对每一行进行排序
     */
    private People[] rowSort(List<People> list) {
        int size = list.size();
        int left = 1;
        int right = 1;
        int max = size / 2;
        People[] temp = new People[size];
        for (int i = 0; i < size; i++) {
            People people = max(list);
            if (i == 0) {
                temp[max] = people;
            } else if (i % 2 == 0) {
                temp[max + right] = people;
                right++;
            } else {
                temp[max - left] = people;
                left++;
            }
            list.remove(people);
        }
        return temp;
    }

    /**
     * 获取最大值
     */
    private People max(List<People> peoples) {
        People max = peoples.get(0);
        for (People p : peoples) {
            if (max.height < p.height) {
                max = p;
            } else if (max.height == p.height) {
                int compare = max.name.compareTo(p.name);
                if (compare > 0)
                    max = p;
            }
        }
        return max;
    }

    /**
     * 获取最小值
     */
    private People min(List<People> peoples) {
        People min = peoples.get(0);
        for (People p : peoples) {
            if (min.height > p.height) {
                min = p;
            } else if (min.height == p.height) {
                int compare = min.name.compareTo(p.name);
                if (compare > 0)
                    min = p;
            }
        }
        return min;
    }

}

class People {

    String name;
    int height;
    int temp;   // 临时位置

    public People(String name, int height) {
        this.name = name;
        this.height = height;
    }

}
