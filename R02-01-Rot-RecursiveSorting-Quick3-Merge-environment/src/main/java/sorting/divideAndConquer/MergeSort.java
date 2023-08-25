package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (!(leftIndex >= rightIndex || leftIndex < 0 || rightIndex > array.length || array.length <= 1)) {
			int middleIndex = (leftIndex + rightIndex) / 2;

			sort(array, leftIndex, middleIndex);
			sort(array, middleIndex + 1, rightIndex);
			merge(array, leftIndex, middleIndex, rightIndex);
		}
	}

	private void merge(T[] array, int leftIndex, int middleIndex, int rightIndex) {
		T[] temp = (T[]) new Comparable[array.length];
		for (int i = leftIndex; i <= rightIndex; i++) {
			temp[i] = array[i];
		}

		int left = leftIndex;
		int right = middleIndex + 1;
		int j = leftIndex;
		
		while (left <= middleIndex && right <= rightIndex) {
			if (temp[left].compareTo(temp[right]) < 0) {
				array[j] = temp[left];
				left++;
			} else {
				array[j] = temp[right];
				right++;
			}

			j++;
		}

		while (left <= middleIndex) {
			array[j] = temp[left];
			j++;
			left++;
		}

		while (right <= rightIndex) {
			array[j] = temp[right];
			j++;
			right++;
		}
	}
}