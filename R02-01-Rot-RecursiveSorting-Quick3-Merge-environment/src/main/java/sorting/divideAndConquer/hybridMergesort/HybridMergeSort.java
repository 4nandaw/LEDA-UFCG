package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 *   que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada chamada
 *   interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 *   INSERTIONSORT_APPLICATIONS são incrementados.
 * - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (!(leftIndex >= rightIndex || leftIndex < 0 || leftIndex >= array.length || rightIndex > array.length || array.length == 0)) {
			if (rightIndex > SIZE_LIMIT) {
				insertionSort(array, leftIndex, rightIndex);
			} else {
				if (leftIndex < rightIndex) {
					int middleIndex = (leftIndex + rightIndex) / 2;

					sort(array, leftIndex, middleIndex);
					sort(array, middleIndex + 1, rightIndex);
					mergeSort(array, leftIndex, middleIndex, rightIndex);
				}
			}
		}
	}

	private void insertionSort(T[] array, int leftIndex, int rightIndex) {
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			T key = array[i];
			int j = i - 1;

			while (j >= leftIndex && key.compareTo(array[j]) < 0) {
				Util.swap(array, j + 1, j);
				j--;
			}

			array[j + 1] = key;
		}
	}

	private void mergeSort(T[] array, int leftIndex, int middleIndex, int rightIndex) {
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
