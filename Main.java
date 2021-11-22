import java.util.ArrayList;
import java.util.Random;

public static void main(String[] args) {
		Random rand = new Random();
		int[] datSet = new int[100];
		for (int i = 0; i < 100; i++) {
			datSet[i] = rand.nextInt(100);
			//System.out.print(" " + datSet[i]);
		}
		int[] arrayCopy = Arrays.copyOf(datSet,  datSet.length);
		
		//========PRINTING===========
		//Print out the unsorted data set:
		System.out.print("Un-sorted: [");
        for(int i = 0; i<datSet.length;i++) {
        	System.out.print(datSet[i] + ",");
        }
        System.out.print("] \n");
        
        //Print out the insertionsorted data set:
		System.out.print("i Sorted: [");
        for(int i = 0; i<datSet.length;i++) {
        	System.out.print(insertionSort(datSet, datSet.length)[i] + ",");
        }
        
        System.out.print("] \n");
		
        
        //Print out the bsorted data set:
        datSet = Arrays.copyOf(arrayCopy, arrayCopy.length);
        
		System.out.print("b Sorted: [");
        for(int i = 0; i<datSet.length;i++) {
        	System.out.print(bSort(datSet, datSet.length)[i] + ",");
        }
        System.out.print("] \n\n");
		
        
      //===========MergeSort #1=========================
        datSet = Arrays.copyOf(arrayCopy, arrayCopy.length);
        long t1 = System.nanoTime();
        int [] outArray1 = mergeSort_1(datSet, 5);
        long t2 = System.nanoTime();
        long performance1 = t2-t1;
        System.out.println("Time for mergeSort with insertionSort:" + performance1 + "ns");
        
        
        System.out.print("Merge Sorted: [");
        for(int i = 0; i<datSet.length;i++) {
        	System.out.print(outArray1[i] + ",");
        }
        System.out.print("] \n");
        
        //==========MergeSort #2========================
        datSet = Arrays.copyOf(arrayCopy, arrayCopy.length);
        long t3 = System.nanoTime();
        int [] outArray2 = mergeSort_2(datSet, 5);
        long t4 = System.nanoTime();
        long performance2 = t4-t3;
        System.out.println("Time for mergeSort with bSort:" + performance2 + "ns");
        
        
        System.out.print("bMerge Sorted: [");
        for(int i = 0; i<datSet.length;i++) {
        	System.out.print(outArray2[i] + ",");
        }
        System.out.print("] \n");
        
        
        //Get averages for each input size
        /*int inputSize = 1000; //Define input size here
        long time_ns = 0;
        int numberOfMeasurements = 10000;
        int k = 7;
        
        for(int i=0;i<numberOfMeasurements;i++) {
        	//Generate a random sequence of numbers
        	Random rand2 = new Random();
    		int[] dataSet = new int[inputSize];
    		for (int j = 0; j < dataSet.length; j++) {
    			dataSet[j] = rand2.nextInt(100);
    			//System.out.print(" " + datSet[i]);
    		}
    		
    		//Do the measurements and save the time in time_ns
    		long t1 = System.nanoTime();
            int [] outArray1 = mergeSort_2(dataSet, k);
            long t2 = System.nanoTime();
            time_ns += t2-t1;
            //System.out.println("Time for mergeSort with insertionSort:" + performance1 + "ns");
        }
        
        //Print out the average time
        System.out.println("Time for mergeSort2: " + time_ns/numberOfMeasurements/1000 + " microseconds");*/
	}
	
	
	static int[] insertionSort(int array[], int array_length)
    {
		
        // The base case
        if (array_length <= 1) {
            return array;
        }
    
        // Sort first n-1 elements
        insertionSort( array, array_length-1 );
    
        //Put last element at correct location
        int last = array[array_length-1];
        int j = array_length-2;
    
        //Move elements of array > key
        while (j >= 0 && array[j] > last) {
        	array[j+1] = array[j];
            j--;
        }
        
        //Put the last one where it should be in array
        array[j+1] = last;
        
        return array;
    }
	
	
	static int binarySearch(int array[], int key, int low, int high) {
		if (high <= low) {
			return (key > array[low]) ?
            (low + 1) : low;
		}
		int mid = (low + high) / 2;

		if (key == array[mid]) {
			return mid + 1;
		}
		if (key > array[mid]) {   
			return binarySearch(array, key, mid + 1, high);
		}
		return binarySearch(array, key, low, mid - 1);
	}

	
	static int[] bSort(int array[], int n)
	{
		int newIndex; 
		int j; 
		int key;

		for (int i = 1; i < n; ++i)
		{
			j = i - 1;
			key = array[i];

			// find location where selected should be inseretd
			newIndex = binarySearch(array, key, 0, j);

			// Shift elements right
			while (j >= newIndex)
			{
				array[j + 1] = array[j];
				j--;
				}
			array[j + 1] = key;
			}
		return array;
		}

	
	static int[] mergeSort_1(int[] array, int k) {
		
		
		int n = array.length;
		k = n/k;
		
		int[] result = new int[n];
		int[] merge1 = new int[n];
		int[] merge2 = new int[n];
		
		//The backbone of the recursive algorithm, after splitting the array into parts it returns a sorted subArray
		if (k==1) {
			return insertionSort(array, n);
		}
		//Whenever there needs to be an unneven splitting of the array, 1 array will be created seperately as well 
		//as a subArray with an even number of further splits.
		else if (k % 2 == 1) {
			//creates a subarray that contains a k:th of the original array (rounded down + the rest). 
			int[] subArray1 = new int[n/k + (n % k)];
			for (int i = 0; i < n / k + (n % k); i++) {
				subArray1[i] = array[i];
			}
			//creates a subarray that contains whatever was left from the original array that wasn't already copied
			//by the previous subarray
			int[] subArray2 = new int[n - n / k - n % k];
			for (int i = 0; i < n - n / k - n % k; i++) {
				subArray2[i] = array[i + n / k + n % k];
			}
			//adds the subArrays to the appropriate arrays for later merging. 
			merge1 = mergeSort_1(subArray1, 1);
			merge2 = mergeSort_1(subArray2, k-1);
		}
		//The else is called whenever there is an even splitting of the code, splitting the array into 2 equal parts
		//that'll be split further an equal number of times. 
		else {
			//Creates 2 subarrays containing a half of the original array's contents each
			int[] subArray1 = new int[n / 2];
			int[] subArray2 = new int[n / 2];
			for (int i = 0; i < n / 2; i++) {
				subArray1[i] = array[i];
				subArray2[i] = array[i + n / 2];
			}
			//adds the subArrays to the appropriate arrays for later merging. 
			merge1 = mergeSort_1(subArray1, k/2);
			merge2 = mergeSort_1(subArray2, k/2);
		}
		
		//counter to keep track of the index of the integers to be compared
		int indexCount1 = 0;
		int indexCount2 = 0;
		//Merges the 2 subarrays
		for (int i = 0; i < n; i++) {
			//compares the lowest unadded value of each list and adds the lowest to the end of a resulting list
			if (merge1[indexCount1] <= merge2[indexCount2]) {
				result[i] = merge1[indexCount1];
				indexCount1++;
				//When 1 list is depleted then the rest of the other list is simply added to the end. 
				if (merge1.length == indexCount1) {
					i++;
					while (i < n) {
						result[i] = merge2[indexCount2];
						indexCount2++;
						i++;
					}
				}
			}
			else if (merge1[indexCount1] >= merge2[indexCount2]) {
				result[i] = merge2[indexCount2];
				indexCount2++;
				if (merge2.length == indexCount2) {
					i++;
					while (i < n) {
						result[i] = merge1[indexCount1];
						indexCount1++;
						i++;
					}
				}
			}
			
		}
		
		return result;
	}
	
	static int[] mergeSort_2(int[] array, int k) {
	
	
		int n = array.length;
		k = n/k;
	
		int[] result = new int[n];
		int[] merge1 = new int[n];
		int[] merge2 = new int[n];
	
		//The backbone of the recursive algorithm, after splitting the array into parts it returns a sorted subArray
		if (k==1) {
			return bSort(array, array.length);
			}
		//Whenever there needs to be an unneven splitting of the array, 1 array will be created seperately as well 
		//as a subArray with an even number of further splits.
		else if (k % 2 == 1) {
			//creates a subarray that contains a k:th of the original array (rounded down + the rest). 
			int[] subArray1 = new int[n/k + (n % k)];
			for (int i = 0; i < n / k + (n % k); i++) {
			subArray1[i] = array[i];
			}
			//creates a subarray that contains whatever was left from the original array that wasn't already copied
			//by the previous subarray
			int[] subArray2 = new int[n - n / k - n % k];
			for (int i = 0; i < n - n / k - n % k; i++) {
				subArray2[i] = array[i + n / k + n % k];
				}
			//adds the subArrays to the appropriate arrays for later merging. 
			merge1 = mergeSort_2(subArray1, 1);
			merge2 = mergeSort_2(subArray2, k-1);
			}
		//The else is called whenever there is an even splitting of the code, splitting the array into 2 equal parts
		//that'll be split further an equal number of times. 
		else {
			//Creates 2 subarrays containing a half of the original array's contents each
			int[] subArray1 = new int[n / 2];
			int[] subArray2 = new int[n / 2];
			for (int i = 0; i < n / 2; i++) {
				subArray1[i] = array[i];
				subArray2[i] = array[i + n / 2];
				}
			//adds the subArrays to the appropriate arrays for later merging. 
			merge1 = mergeSort_2(subArray1, k/2);
			merge2 = mergeSort_2(subArray2, k/2);
			}
	
		//counter to keep track of the index of the integers to be compared
		int indexCount1 = 0;
		int indexCount2 = 0;
		//Merges the 2 subarrays
		for (int i = 0; i < n; i++) {
			//compares the lowest unadded value of each list and adds the lowest to the end of a resulting list
			if (merge1[indexCount1] <= merge2[indexCount2]) {
				result[i] = merge1[indexCount1];
				indexCount1++;
				//When 1 list is depleted then the rest of the other list is simply added to the end. 
				if (merge1.length == indexCount1) {
					i++;
					while (i < n) {
						result[i] = merge2[indexCount2];
						indexCount2++;
						i++;
					}
				}
			}
			else if (merge1[indexCount1] >= merge2[indexCount2]) {
				result[i] = merge2[indexCount2];
				indexCount2++;
				if (merge2.length == indexCount2) {
					i++;
					while (i < n) {
						result[i] = merge1[indexCount1];
						indexCount1++;
						i++;
					}
				}
			}
		
		}
	
		return result;
	}

}