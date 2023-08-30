package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (!(leftIndex >= rightIndex || rightIndex >= array.length || array.length == 0)) {
			int lower = array[leftIndex];
			int higher = array[leftIndex];
			for (int i = leftIndex; i <= rightIndex; i++) {
				if (array[i] > higher) {
					higher = array[i];
				} else if (array[i] < lower) {
					lower = array[i];
				}
			}

			Integer[] frequency = new Integer[higher + 1];

			for (int i = leftIndex; i <= rightIndex; i++) {
				frequency[array[i] - lower]++;
			}

			for (int i = 1; i < frequency.length; i++) {
				frequency[i] = frequency[i] + frequency[i - 1];
			}

			Integer[] result = new Integer[array.length];

			for (int i = 0; i < array.length; i++) {
				result[i] = array[i];
			}

			for (int i = rightIndex; i >= leftIndex; i--) {
				frequency[result[i] - lower]--;
				array[frequency[result[i] - lower] + leftIndex] = result[i];
				
				// result[frequency[array[i] - 1] - 1] = array[i];
				// frequency[array[i] - 1]--;
			}
			
		}
	}

}

// Descobre o min e o max
// max - min = tamanho do array de contagem
// para alocar no array => elemento - min = posição