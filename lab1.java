#Lab 1


#Function insertion_sort(array unsorted list), returns array sorted list:
# Make copy of unsorted list
# For-loop trough list from index 0 to end
# 	get key of current element
#   save index of current element - 1
#	while-loop while j >=0 and j:th of unsorted list copy is larger than key
#		set (list current index+1) to (list current index)
#		decrease current index by 1
#	set set (list current index+1) to key
#return copy of list, now sorted
		
public int[] insertion_sort(int[] unsorted_list) {
	
}

#Function bSort(array unsorted list), returns array sorted list:
# Make copy of unsorted list
# For-loop trough list from index 0 to end
#	get key of current element
#	save lowest index (0) in var 'low'
#   save highest index (current index) in var 'high'
#	while-loop on condition (low < high)
#		Get middle of list by floor of (high+low)/2, save in 'middle'
#		if 'middle' is larger or equal to key, 'middle' is the new 'high'
#		Otherwise, 'low' is 'middle' + 1
#	pop current index from copied list
#	insert 'high' and key into copied list
# return copied list, now sorted
