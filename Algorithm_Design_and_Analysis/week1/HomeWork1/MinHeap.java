

/**
 * A Heap implementation class
 * 
 * @param heap the array that holds the heap data
 * @param size the number of elements currently stored in the heap
 */
public class MinHeap {
	
	CompareInt[] heap;
	int size;

	/**
	 * Constructs a new heap with maximum capacity n
	 * Remember to index your heap at 1 instead of 0!
	 * @param n the maximum number of elements allowed in the heap
	 */
	public MinHeap(int n) {
		heap = new CompareInt[n+1];
		size = 0;
	}
	
	/**
	 * Adds an element to the heap
	 * 
	 * @param val the value to be added to the heap
	 */
	public void add(CompareInt val) {
        if(heap.length < size){
            print("heap is at maximut capacity");
            throw new IllegalArgumentException("heap is at maximum capacity");
        }
        size++;
        heap[size] = val;
		swim(size);
		return;
	}
    
    public void swim(int k) {//swim operation executes as long as k > 1 and divides it by 2 on every iteration;
// 		print("Swim " + k);
		while (k > 1 && Sorting.less(heap[k],heap[k/2])) {
			Sorting.swap(heap, k, k / 2);
			k /= 2;
		}
	}
    
    public void sink(int k) {//where k - kth node
        //where 2*k - is a position of a left child of node k, 2*k + 1 - is a position of right child of node k
        while (2*k <= size){
            int smallest = 2 * k + 1;
            if (Sorting.less(heap[2*k],heap[2*k + 1])){
                smallest = 2 * k;
            }
            if (Sorting.less(heap[k],heap[smallest])) break;
            Sorting.swap(heap, k, smallest);
            k = smallest;
        }
	}
	
	/**
	 * Extracts the smallest element from the heap
	 */
	public CompareInt extractMin() {
		CompareInt min = heap[1];
		heap[1] = heap[size];
		size--;
		sink(1);
		
		return min;
	}
	
	private void print(String str){
        System.out.println(str);
    }
}
