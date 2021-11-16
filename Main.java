import java.util.ArrayList;
import java.util.Random;

public class Main {

	
	public static void main(String[] args) {
		Random rand = new Random();
		ArrayList<Integer> datSet = new ArrayList<Integer>();
		for (int i = 0; i < 100; i++) {
			datSet.add(rand.nextInt(100));
			System.out.print(datSet.get(i) + " ");
		}
		ArrayList<Integer> insertSorted = insertSort(datSet);
		ArrayList<Integer> bSorted = bSort(datSet);
		ArrayList<Integer> mergeSorted = mergeSort(datSet, 5);
		System.out.print("\n \n");
		for (int i = 0; i < 100; i++) {
			System.out.print(mergeSorted.get(i) + " ");
		}

	}
	
	public static ArrayList<Integer> insertSort(ArrayList<Integer> unsorted) {
		ArrayList<Integer> sorted = new ArrayList();
		sorted.add(unsorted.get(0));
		for (int i = 1; i < unsorted.size(); i++) {
			int j = 0;
			while(true) {
				if(j == sorted.size()) {
					sorted.add(unsorted.get(i));
					break;
				}
				else if (unsorted.get(i) < sorted.get(j)){
					sorted.add(j, unsorted.get(i));
					break;
				}
				else {
				j++;
				}
			}
		
		}
		return sorted;
	}
	
	public static ArrayList<Integer> bSort(ArrayList<Integer> unsorted) {
		ArrayList<Integer> sorted = new ArrayList();
		sorted.add(unsorted.get(0));
		boolean direction = true;
		boolean found;
		for(int i = 1; i< unsorted.size(); i++) {
			int position = sorted.size()/2;
			int divider = 2;
			found = false;
			while (!found) {
				if (direction) {
					while(true) {
						if(unsorted.get(i) >= sorted.get(position)) {
							if(position==sorted.size() - 1) {
								sorted.add(unsorted.get(i));
								found=true;
								break;
							}
							else if(unsorted.get(i) <= sorted.get(position+1)) {
								sorted.add(position + 1, unsorted.get(i));
								found=true;
								break;
							}
							else {
								if((double) sorted.size()/divider < (double) 1/(sorted.size())) {
									position++;
								}
								else {
									divider *= 2;
									position = position + sorted.size()/divider;
								}
							}
						}
						else {
							direction=false;
							break;
						}
					}
				}
				else {
					while(true) {
						if (position == 0) {
							if (unsorted.get(i) <= sorted.get(position)) {
								sorted.add(0, unsorted.get(i));
								found=true;
								break;
							}
							else {
								direction=true;
								break;
							}
						}
						else if(unsorted.get(i)<sorted.get(position)) {
							if((double) sorted.size()/divider < (double) 1/(sorted.size())) {
								position--;
							}
							else {
								divider *= 2;
								position = position - (sorted.size()/divider);
							}
							
						}
						else {
							direction=true;
							break;
						}
					}
				}
			}
			
		}
		
		
		return sorted;
		
	}
	
	public static ArrayList mergeSort(ArrayList<Integer> unsorted_array, int k) {
		ArrayList<Integer> sublists = new ArrayList();
		boolean unevenK = false;
		if (k == 1) {
			bSort(unsorted_array);
			return insertSort(unsorted_array);
		}
		else if (k > 1 && k%2 == 0) {
			ArrayList<Integer> toSort1 = new ArrayList<Integer>();
			ArrayList<Integer> toSort2 = new ArrayList<Integer>();
			toSort1.addAll(unsorted_array.subList(0, unsorted_array.size()/2));
			toSort2.addAll(unsorted_array.subList(unsorted_array.size()/2, unsorted_array.size()));
			sublists.addAll(mergeSort(toSort1, k/2));
			sublists.addAll(mergeSort(toSort2, k/2));
		}
		else {
			unevenK=true;
			ArrayList<Integer> toSort1 = new ArrayList<Integer>();
			ArrayList<Integer> toSort2 = new ArrayList<Integer>();
			toSort1.addAll(unsorted_array.subList(0, unsorted_array.size()/k));
			toSort2.addAll(unsorted_array.subList(unsorted_array.size()/k, unsorted_array.size()));
			sublists.addAll(mergeSort(toSort1, 1));
			sublists.addAll(mergeSort(toSort2, k-1));
		}

		ArrayList<Integer> merged = new ArrayList<Integer>();
		ArrayList<Integer> split1 = new ArrayList();
		ArrayList<Integer> split2 = new ArrayList();
		if(unevenK) {
			split1.addAll(sublists.subList(0, unsorted_array.size()/k));
			split2.addAll(sublists.subList(unsorted_array.size()/k, unsorted_array.size()));
		}
		else {
			split1.addAll(sublists.subList(0, unsorted_array.size()/2));
			split2.addAll(sublists.subList(unsorted_array.size()/2, unsorted_array.size()));	
		}
		for(int i = 0; i<unsorted_array.size(); i++) {
			if (split1.get(split1.size()-1) > split2.get(split2.size()-1)) {
				merged.add(0, split1.get(split1.size()-1));
				split1.remove(split1.size()-1);
				if(split1.isEmpty()) {
					int stop = split2.size();
					for(int j = 0; j < stop; j++) {
						merged.add(0, split2.get(split2.size()-1));
						split2.remove(split2.size()-1);
					}
					break;
				}
			}
			else {
				merged.add(0, split2.get(split2.size()-1));
				split2.remove(split2.size()-1);
				if(split2.isEmpty()) {
					int stop = split1.size();
					for(int j = 0; j < stop; j++) {
						merged.add(0, split1.get(split1.size()-1));
						split1.remove(split1.size()-1);
					}
					break;
				}
			}
		}
		return merged;
	}

}