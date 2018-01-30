package sort;

public class Test {
	
	public static void main(String[] args) {
		int[] array = {1, 5 , 6, 4, 1, 9, 7, 3, 5, 2};
		QuickSort quickSort = new QuickSort();
		int[] sort = quickSort.quickSort(array);
		for (int i = 0; i < sort.length; i++) {
			System.out.print(sort[i] + " ");
		}
	}
	
}
