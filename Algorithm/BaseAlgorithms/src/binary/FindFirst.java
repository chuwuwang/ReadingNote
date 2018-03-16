package binary;

// 查找元素出现的第一个位置

public class FindFirst {

    public static void main(String[] args) {
        int[] array = {1, 2, 2, 2, 10};
        int index = new FindFirst().findFirst(array, 2);
        System.out.println("index:" + index);
    }

    public int findFirst(int[] array, int target) { // O(Log2N) O(1)
        if (array == null || array.length == 0) return -1;

        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] < target) {
                left = mid;
            } else if (array[mid] >= target) {
                right = mid;
            }
        }

        // post processing
        if (array[left] == target) return left;
        if (array[right] == target) return right;

        return -1;
    }

}
