package MinSpanTree;

class PriorityQueue {
	private Edge[] heap;
	private int size = 0;
	
	public PriorityQueue(int size) {
		heap = new Edge[size];
	}
	
	public void insert(Edge e) {
		size++;
		perculateUp(e);
	}
	
	public Edge deleteMin() {
		return perculateDown();
	}
	
	public Edge getElement(int i) {
		return heap[i];
	}
	
	public int size() {
		return this.size;
	}
	
	private void perculateUp(Edge ele) {
		int i = size;
		while(i/2 > 0 && ele.compareTo(heap[i/2]) < 0) {
				heap[i] = heap[i/2];
				i /= 2;
		}
		
		heap[i] = ele;
	}
	
	private Edge perculateDown() {
		int hole = 1;
		Edge min = heap[1];
		heap[hole] =  heap[size];
		size--;
		
		int child;
		Edge tmp = heap[hole];
		
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
		
		return min;
	}
}