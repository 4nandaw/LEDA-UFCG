package problems;

import util.Util;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		if (array.length > 0 && x != null) {
			quickSort(array, 0, array.length - 1);
			return recursiveFloor(array, x, null, 0, array.length - 1);
		}
		return null;
	}
	
	public Integer recursiveFloor(Integer[] array, Integer x, Integer floor, int leftIndex, int rightIndex) {
		if (!(leftIndex > rightIndex || leftIndex < 0 || rightIndex >= array.length || array.length == 0)) {
			int middle = (leftIndex + rightIndex) / 2;

			if (array[middle].compareTo(x) == 0) {
				return array[middle];
			}
			if (array[middle].compareTo(x) > 0) {
				return recursiveFloor(array, x, floor, leftIndex, middle - 1);
			}
			if (array[middle].compareTo(x) < 0) {
				return recursiveFloor(array, x, array[middle], middle + 1, rightIndex);
			}

		}
		return floor;
	}

	public int partition(Integer[] array, int leftIndex, int rightIndex) {
		int pivot = leftIndex;

		int indice = leftIndex;
		
		for (int j = leftIndex + 1; j <= array.length; j++) {
			if (array[j].compareTo(array[indice]) <= 0) {
				i++;
				Util.swap(array, i, j);
			}
		}
		Util.swap(array, leftIndex, i);

		return i;
	}
	
	public void quickSort(Integer[] array, int leftIndex, int rightIndex) {
		if (!(leftIndex > rightIndex || leftIndex < 0 || rightIndex > array.length || array.length == 0)) {
			int pivotIndex = partition(array, leftIndex, rightIndex);
			quickSort(array, leftIndex, pivotIndex - 1);
			quickSort(array, pivotIndex + 1, rightIndex);
		}
	}
}
