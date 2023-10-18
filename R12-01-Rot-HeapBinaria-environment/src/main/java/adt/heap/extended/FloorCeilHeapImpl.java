package adt.heap.extended;

import java.util.Comparator;

import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

	public FloorCeilHeapImpl(Comparator<Integer> comparator) {
		super(comparator);
	}

	@Override
	public Integer floor(Integer[] array, double numero) {
		Integer floor = null;
		if (array != null) {
			for (Integer value : array) {
				insert(value);
			}
			floor = floor(numero, floor);
		}
		return floor;

	}

	private Integer floor(double numero, Integer floor) {
		Integer root = extractRootElement();

		if (root != null) {
			if (numero >= root && (floor == null || root >= floor)) {
				floor = floor(numero, root);
			} else {
				floor = floor(numero, floor);
			}
		}
		return floor;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		Integer ceil = null;
		if (array != null) {
			for (Integer value : array) {
				insert(value);
			}
			ceil = ceil(numero, ceil);
		}
		return ceil;
	}

	private Integer ceil(double numero, Integer ceil) {
		Integer root = extractRootElement();
		if (root != null) {
			if (numero <= root && (ceil == null || root <= ceil)) {
				ceil = ceil(numero, root);
			} else {
				ceil = ceil(numero, ceil);
			}
		}
		return ceil;
	}
}
