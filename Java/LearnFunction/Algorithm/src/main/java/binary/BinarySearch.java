package binary;

// target 6
// 1  3  4  5  6  10
// left           right
// 0              5

// mid

// 查找指定元素的位置

public class BinarySearch {

    public static void main(String[] args) {
        int[] array = {
                1, 3, 4, 5, 6, 10
        };
        int index = new BinarySearch().binarySearch(array, 5);
        System.out.println("index:" + index);
    }

    /**
     * O(Log2N) O(1)
     */
    public int binarySearch(int[] array, int target) {
        if (array == null || array.length == 0) return -1;
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2; // integer may out of bound
            if (array[mid] < target) {
                left = mid + 1;
            } else if (array[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public int binarySearchRecur(int[] array, int target, int left, int right) {
        // base case
        if (left > right) return left;

        int mid = left + (right - left) / 2;
        if (array[mid] < target) {
            return binarySearchRecur(array, target, mid + 1, right);
        } else if (array[mid] > target) {
            return binarySearchRecur(array, target, mid, right - 1);
        } else {
            return mid;
        }
    }

}
