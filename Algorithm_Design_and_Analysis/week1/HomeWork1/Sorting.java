import java.util.Arrays;


public class Sorting {
	
	/**
	 * Implement the mergesort function, which should sort the array of
	 * integers in place
	 * 
	 * You will probably want to use helper functions here, as described in the lecture recordings
	 * (ex. merge(), a helper mergesort function)
	 * @param arr
	 */
	public static void mergeSort(CompareInt[] arr) {
		//TODO
         //idea - split into 2 lists, then merge these 2 lists while comparing
		sort(arr, arr.length);
	}
    
    public static void sort(CompareInt[] arr, int high) {
		if (arr.length <= 1) return;
        int mid = high / 2;
        CompareInt[] l = new CompareInt[mid];
        CompareInt[] r = new CompareInt[high - mid];
 
        for (int i = 0; i < mid; i++) {
            l[i] = arr[i];
        }
        for (int i = mid; i < high; i++) {
            r[i - mid] = arr[i];
        }
        sort(l, mid);
        sort(r, arr.length - mid);
 
        merge(arr, l, r, mid, arr.length - mid);
// 		System.out.pritnln(Arrays.toString(arr));
	}
    
    public static void merge(CompareInt[] arr, CompareInt[] left, CompareInt[] right, int low, int high) {
		int i = 0; int j = 0; int k = 0;
		
		while (i < low && j < high) {
			if (less(left[i], right[j])) {//comparing splitted arrays
				arr[k++] = left[i++];
			} else {
				arr[k++] = right[j++];
			}
		}
		
		while (i < low) {
			arr[k++] = left[i++];
		}
		while (j < high) {
			arr[k++] = right[j++];
		}
	}
    
    // is x <= y ?
	public static boolean less(CompareInt x, CompareInt y) {
		return x.compareTo(y) <= 0;
	}
    
	
	/**
	 * Implement the quickSelect
	 * 
	 * Again, you will probably want to use helper functions here
	 * (ex. partition(), a helper quickselect function)
	 */
    
    // swap arr[i] and arr[j]
	public static void swap(CompareInt[] arr, int i, int j) {
		CompareInt tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
    
    public static int partition(CompareInt[] arr, int lo, int hi) {
		CompareInt pivot = arr[lo];
		int i = lo; int j = hi+1;
		while (true) {
			while (less(arr[++i], pivot)) // find next i that a[i] > pivot
				if (i == hi) break;
			
			while (less(pivot, arr[--j])) // find next j that a[j] < pivot
				if (j == lo) break;
			
			if (i >= j) break;
			swap(arr, i, j);
		}
		
		swap(arr, lo, j);
		return j;
	}
    
    public static CompareInt qselect(CompareInt[] array, int k){
        int low = 0;
        int high = array.length -1;
        if(low == high) return array[low];
        int pivotDist;
        while(high != low){
            pivotDist = partition(array, low, high);
            if (pivotDist == k) return array[k];
            if(k < pivotDist){
                high = pivotDist - 1;
            }
            else{
                low = pivotDist + 1;
            }
        }
        return array[k];
    }
    
    public static CompareInt quick(CompareInt[] arr, int k, int lo, int hi) {
		if (lo == hi) return arr[lo];
		
		
		int pivot = partition(arr, lo, hi);
		System.out.println("k = " + k + " pivot = " + pivot);
		if (pivot == k) {
			return arr[k];
		}
		
		if (pivot < k)
			return quick(arr, k, pivot + 1, hi);
		else 
			return quick(arr, k, lo, pivot - 1);
	}
    
	public static CompareInt quickSelect(int k, CompareInt[] arr) {
		//TODO
        return qselect(arr, k-1);
//         return quick(arr, k-1, 0, arr.length - 1);
	}

}
