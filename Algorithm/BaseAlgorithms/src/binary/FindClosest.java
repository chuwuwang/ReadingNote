package binary;

/**
 * 找数组中距离最近得一个元素
 */
public class FindClosest {

    public static void main(String[] args) {
        int[] array = {1, 2, 2, 2, 4, 6, 8, 8, 10};
        int index = new FindClosest().findClosest(array, 7);
        System.out.println("index:" + index);
    }

    public int findClosest(int[] array, int target) { // O(Log2N) O(1)
        if (array == null || array.length == 0) return -1;
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        // post processing
        if (Math.abs(target - array[left]) <= Math.abs(target - array[right])) {
            return left;
        } else {
            return right;
        }
    }


}
