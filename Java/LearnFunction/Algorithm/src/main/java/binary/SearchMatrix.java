package binary;

/**
 * array: m * n
 * time: log(number of elements) -log(m * n)
 * space:O(1)
 */
public class SearchMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12}
        };
        int[] index = new SearchMatrix().searchMatrix(matrix, 7);
        System.out.println("index:" + index[0] + " " + index[1]);
    }

    /**
     * O(Log2 M x N) O(1)
     */
    public int[] searchMatrix(int[][] matrix, int target) {
        int[] res = {-1, -1};
        int num_row = matrix.length;
        int num_col = matrix[0].length;
        int left = 0;
        int right = num_row * num_col - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == matrix[mid / num_col][mid % num_col]) {
                res[0] = mid / num_col;
                res[1] = mid % num_col;
                return res;
            } else if (matrix[mid / num_col][mid % num_col] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

}
