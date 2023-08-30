package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala.
 *
 * Procure evitar desperdício de memória: AO INVÉS de alocar o array de contadores
 * com um tamanho arbitrariamente grande (por exemplo, com o maior valor de entrada possível),
 * aloque este array com o tamanho sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Seu algoritmo deve assumir que o array de entrada nao possui numeros negativos,
 * ou seja, possui apenas numeros inteiros positivos e o zero.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (!(leftIndex >= rightIndex || leftIndex < 0 || rightIndex >= array.length || array.length == 0)) {
			int higher = array[leftIndex];
			for (int i = leftIndex; i <= rightIndex; i++) {
				if (array[i] > higher) {
					higher = array[i];
				}
			}

			Integer[] frequency = new Integer[higher + 1];

			for (int i = 0; i < frequency.length; i++) {
				frequency[i] = 0;
			}

			for (int i = leftIndex; i <= rightIndex; i++) {
				frequency[array[i]]++;
			}

			for (int i = 0; i < frequency.length - 1; i++) {
				frequency[i + 1] += frequency[i];
			}

			Integer[] result = new Integer[rightIndex - leftIndex + 1];

			for (int i = rightIndex; i >= leftIndex; i--) {
				int elem = array[i];

				frequency[elem]--;

				result[frequency[elem]] = elem;
			}

			for (int i = leftIndex; i <= rightIndex; i++) {
				array[i] = result[i - leftIndex];
			}
		}
	}
}
