package sort;

/**
 * 快速排序
 *
 */
public class QuickSort {
	
	// the real index for pivot
	private int quickSelect(int[] array, int left, int right) {
		int pivot = array[right];
		int i = left;
		int j = right - 1;
		while (i <= j) {
			if (array[i] < pivot) {
				i++;
			} else if (array[j] >= pivot) {
				j--;
			} else {
				swap(array, i, j);
				i++;
				j--;
			}
		}
		swap(array, i, right);
		return i;
	}

	public int[] quickSort(int[] array) {
		if (array == null || array.length == 0) {
			return array;
		}
		quickSort(array, 0, array.length - 1);
		return array;
	}

	private void quickSort(int[] array, int left, int right) {
		// base case
		if (left >= right) {
			return;
		}
		int pivotIndex = quickSelect(array, left, right);
		quickSort(array, left, pivotIndex - 1);
		quickSort(array, pivotIndex + 1, right);
	}

	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
