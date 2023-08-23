package orderStatistic;

public class OrderStatisticsSelectionImpl<T extends Comparable<T>> implements OrderStatistics<T> {

	/**
	 * Esta eh uma implementacao do calculo da estatistica de ordem seguindo a estrategia 
	 * de usar o selection sem modificar o array original. Note que seu algoritmo vai 
	 * apenas aplicar sucessivas vezes o selection ate encontrar a estatistica de ordem 
	 * desejada sem modificar o array original. 
	 * 
	 * Restricoes:
	 * - Voce NÃO pode modificar o aray original
	 * - Voce NÃO pode usar memória extra: nenhum array auxiliar deve ser criado e utilizado.
	 * - Você NÃO pode usar métodos já prontos de manipulação de arrays
	 * - Voce NÃO pode encontrar a k-esima estatistica de ordem por contagem de
	 *   elementos maiores/menores, mas sim aplicando sucessivas selecoes (selecionar um elemento
	 *   usando a ideia do selection sort).
	 * - Considere que k varia de 1 a N 
	 * - Voce DEVE usar recursão para resolver o problema.
	 * - Você pode implementar métodos auxiliares, desde que seja apenas nesta classe.
	 * - Os métodos auxiliares NÃO PODEM ter mais que três parâmetros.
	 *
	 * Dica: procure pensar como usar a ideia do selection sort para resolver esse problema,
     *       identifique que métodos você precisará para resolver o problema 
	 *       e pense no template da recursão em cada método que voce vai implementar.
	 */
	@Override
	public T getOrderStatistics(T[] array, int k) {
		if (!(k >= array.length || k < 1 || array.length == 0)) {
			int leftIndex = 0;
			int rightIndex = array.length - 1;

			int small = smallestElem(array, leftIndex, rightIndex);
			if (leftIndex < rightIndex && leftIndex < k) {
				int smallest = smallestElem(array, leftIndex, small);
				small = smallest;
				leftIndex++;
			}
			
			return array[small];
		}
		return null;
	}

	public int smallElem(T[] array, int leftIndex, int rightIndex) {
		int smallest = leftIndex;

		if (leftIndex < rightIndex) {
			int small = smallestElem(array, leftIndex + 1, rightIndex);
			if (array[small].compareTo(array[smallest]) < 0) {
				smallest = small;
			}
		}

		return smallest;
	}

	public int smallestElem(T[] array, int leftIndex, int small) {
		int rightIndex = array.length - 1;
		int smallest = small;

		if (leftIndex < rightIndex) {
			int min = smallestElem(array, leftIndex + 1, rightIndex);
			if (array[min].compareTo(array[smallest]) < 0) {
				smallest = small;
			}
		}

		return smallest;
	}
}