package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (validation(array, leftIndex, rightIndex)) {
			boolean swapped = true;

        	while (swapped) {
            	swapped = false;
            	for (int i = leftIndex; i < rightIndex; i++) {
                	if (array[i].compareTo(array[i + 1]) > 0) {
                    	Util.swap(array, i, i + 1);
                    	swapped = true;
                	}
            	}
			}
		}
	}

	private boolean validation(T[] array, int leftIndex, int rightIndex) {
		boolean isValid = true;

		if (array == null || array.length == 0) {
			isValid = false;
		} else if ((leftIndex >= rightIndex) || (leftIndex < 0) || (rightIndex <= 0)) {
			isValid = false;
		} else if ((rightIndex > array.length - 1) || leftIndex >= array.length) {
			isValid = false;
		}

		return isValid;
	}
}
