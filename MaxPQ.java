//
// Title: P1 PQ
// Author: Joseph Soukup
// Email: jsoukup2@wisc.edu
// Due: 2/5/2018
// Known bugs: Unable to add more items than the initial array size, remove can be buggy
//

/**
 * This class implements the PQADT to create a PQ using a max heap.
 */
public class MaxPQ<E extends Comparable<E>> implements PriorityQueueADT<E> {
    private E[] items; // This is the array used to store the items
    private static final int INITIAL_SIZE = 10;  // This is the size of the array
    private int itemCount; // This keeps track of how many items are in the array

    /**
     * This constructor creates the priority queue.
     */
    public MaxPQ() {
        this.items = (E[]) new Comparable[INITIAL_SIZE];
        this.itemCount = 0;
    }

    /**
     * Checks if the priority queue has any
     * elements and returns true if no elements,
     * false otherwise.
     *
     * @return true if no elements in queue, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        boolean empty = true;
        if (itemCount != 0)
            empty = false;
        return empty;   
    }
    
    /**
     * return the parent index of the given child index
     * 
     * @param childIndex
     * @return parent index
     */
    private int getParent(int childIndex) {
        return childIndex / 2;
    }

    /**
     * return the left child index of the given parent index
     * 
     * @param parentIndex
     * @return left child index
     */
    private int getLeftChild(int parentIndex) {
        return parentIndex * 2;
    }

    /**
     * return the right child index of the given parent index
     * 
     * @param parentIndex
     * @return right child index
     */
    private int getRightChild(int parentIndex) {
        
        return parentIndex * 2 + 1;
    }
    
    /**
     * swaps the Item references at itemOneIndex and itemTwoIndex in the items array
     * 
     * @param itemOneIndex
     * @param itemTwoIndex
     */
    private void swap(int itemOneIndex, int itemTwoIndex) {
        E temp = items[itemOneIndex];
        items[itemOneIndex] = items[itemTwoIndex];
        items[itemTwoIndex] = temp;
    }

    /**
     * Propagates the max-heap order property from the node at position index,
     * up through it's ancestor nodes. Assumes that only the node at position
     * index may be in violation of this property. This is useful when adding
     * a new item to the bottom of the heap.
     * 
     * @param index the index of the item
     */
    private void insertHelper(int index) {
        if (items[getParent(index)] != null && items[index] != null) {
            if (items[getParent(index)].compareTo(items[index]) < 0) {
                swap(index, getParent(index));
                insertHelper(getParent(index));
            }
        }
    }
    
    /**
     * Adds a data item to the priority queue.
     * Reorders all the other data items in the
     * queue accordingly.
     *
     * If the size is equal to the capacity of the
     * priority queue, double the capacity and then
     * add the new item.
     *
     * @param item the item to add
     * @throws IllegalArgumentException if item is null
     */
    public void insert(E item) {
        // Add the given item to the items array and perform the necessary
        // actions to maintain the min-heap properties.
        if (itemCount == items.length) {
            throw new IllegalStateException("WARNING: Item not added.  This vending machine is already filled to capacity.");
        }
        items[++itemCount] = item;
        int current = itemCount;
        insertHelper(current);
    }

    /**
     * Returns the highest priority item in the priority queue.
     *
     * MinPriorityQueue => it will return the smallest valued element.
     * MaxPriorityQueue => it will return the largest valued element.
     *
     * @return the highest priority item in the priority queue.
     * @throws EmptyQueueException if priority queue is empty.
     */
    @Override
    public E getMax() throws EmptyQueueException {
        if (itemCount == 0)
            throw new EmptyQueueException();
        return items[1];
    }
    
    /**
     * Propagates the max-heap order property from the node at position index,
     * down through it's highest priority descendant nodes. Assumes that the
     * children of the node at position index conform to this heap property.
     * This is useful when removing an item from the top of the heap.
     * 
     * @param index the index of the item
     */
    private void removeHelper(int index) {
        int i = index;
        if (getLeftChild(i) < itemCount && getRightChild(i) < itemCount) {
            int bigger = i;
            int left = getLeftChild(i);
            int right = getRightChild(i);
            if (items[left] != null && items[right] != null) {
                if (items[left].compareTo(items[right]) > 0 && items[left].compareTo(items[i]) > 0) {
                    bigger = right;
                }
                if (items[right].compareTo(items[left]) > 0 && items[right].compareTo(items[i]) > 0) {
                    bigger = left;
                }
                swap(i, bigger);
                removeHelper(bigger);
            }
            if (items[left] == null && items[right] == null) {
                swap(i, bigger);
            }
            if (items[left] != null || items[right] != null) {
                if (items[left] != null) {
                    bigger = right;
                }
                if (items[right] != null) {
                    bigger = left;
                }
                swap(i, bigger);
                removeHelper(bigger);
            }
        }
    }

    /**
     * Returns and removes the highest priority item in the priority queue.
     * Reorders all the other data items in the
     * queue accordingly.
     *
     * MinPriorityQueue => it will return and remove the smallest valued element.
     * MaxPriorityQueue => it will return and remove the largest valued element.
     *
     * @return the highest priority item in the priority queue.
     * @throws EmptyQueueException if priority queue is empty.
     */
    @Override
    public E removeMax() throws EmptyQueueException {
        if (itemCount == 0)
            throw new EmptyQueueException();
        E popped = items[1];
        items[1] = items[itemCount--];
        removeHelper(1);
        return popped;
    }

    /**
     * Returns the number of elements in the priority queue.
     *
     * @return number of elements in the queue.
     */
    @Override
    public int size() {
        return itemCount + 1;
    }
}