package Heap;

import java.util.Arrays;

public class Heap<T extends Comparable<? super T>> {
	private T[] heap;
	private int size = 0;
	
	@SuppressWarnings("unchecked")
	public Heap(int size, T... dummy) {
		heap = Arrays.copyOf(dummy, size+1);
	}
	
	public void insert(T ele) {
		size++;
		perculateUp(ele);
	}
	
	public void deleteMin() {
		perculateDown();
	}
	
	public T getElement(int i) {
		return heap[i];
	}
	
	public int size() {
		return this.size;
	}
	
	private void perculateUp(T ele) {
		int i = size;
		while(i/2 > 0 && ele.compareTo(heap[i/2]) < 0) {
				heap[i] = heap[i/2];
				i /= 2;
		}
		
		heap[i] = ele;
	}
	
	private void perculateDown() {
		int hole = 1;
		//T min = heap[1];
		heap[hole] =  heap[size];
		size--;
		
		int child;
		T tmp = heap[hole];
		
		while(hole * 2 <= size) {
			child = hole * 2;
			
			if(child != size && heap[child+1].compareTo(heap[child]) < 0) {
				child += 1;
			}
			
			if(heap[child].compareTo(tmp) < 0) {
				heap[hole] = heap[child];
				hole = child;
			} else {
				break;
			}
		}
		
		heap[hole] =  tmp;
	}
}
