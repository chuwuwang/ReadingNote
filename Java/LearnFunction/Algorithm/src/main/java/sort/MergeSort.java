package sort;

/**
 * 归并排序
 */
public class MergeSort {

    public void mergeSort(int[] array) {
        if (array == null || array.length <= 1) return;

        int[] helper = new int[array.length];

        mergeSort(array, 0, array.length - 1, helper);
    }

    public void mergeSort(int[] array, int left, int right, int[] helper) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;

        mergeSort(array, left, mid, helper);

        mergeSort(array, mid + 1, right, helper);

        for (int i = left; i < right; i++) {
            helper[i] = array[i];
        }

        int l = left, r = mid + 1, k = left;
        while (l <= left && r <= right) {
            boolean bool = helper[l] < helper[r];
            if (bool) {
                array[k++] = helper[l++];
            } else {
                array[k++] = helper[r++];
            }
        }

        while (l <= mid) {
            array[k++] = helper[l++];
        }
    }


}
