package sorting.divideAndConquer.quicksort3;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte:
 * 1. Comparar o elemento mais a esquerda, o central e o mais a direita do intervalo.
 * 2. Ordenar os elementos, tal que: A[left] < A[center] < A[right].
 * 3. Adotar o A[center] como pivô.
 * 4. Colocar o pivô na penúltima posição A[right-1].
 * 5. Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6. Aplicar o algoritmo na particao a esquerda e na particao a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends
		AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (!(leftIndex >= rightIndex || leftIndex < 0 || rightIndex > array.length || array.length == 0)) {
			int pivot = partition(array, leftIndex, rightIndex);
			sort(array, leftIndex, pivot - 1);
			sort(array, pivot + 1, rightIndex);
		}
	}

	private int partition(T[] array, int leftIndex, int rightIndex) {
		int pivotIndex = indexPivot(array, leftIndex, rightIndex);
		int i = leftIndex;

		for (int j = leftIndex + 1; j <= rightIndex; j++) {
			if (array[j].compareTo(array[pivotIndex]) <= 0) {
				i++;
				Util.swap(array, i, j);
			}
		}
		
		Util.swap(array, leftIndex, i);
		
		return i;
	}

	private int indexPivot(T[] array, int leftIndex, int rightIndex) {
		int left = leftIndex;
		int middle = (leftIndex + rightIndex) / 2;
		int right = rightIndex;

		if (array[middle].compareTo(array[right]) < 0) {
			Util.swap(array, middle, right);
		}
		if (array[left].compareTo(array[middle]) < 0) {
			Util.swap(array, left, middle);
			if (array[middle].compareTo(array[right]) < 0) {
				Util.swap(array, middle, right);
			}
		}

		return left;
	}
}