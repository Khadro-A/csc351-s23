import java.util.Comparator;

/** Insertion sort with variable steps to implement ShellSort */
public class Insertion<T> implements Sorter<T> {

  /** Establishes ordering of type T */
  private Comparator<T> orderBy;

  /** Step size of ShellSort */
  private int step=1;

  /** Counter of compare operations */
  int count = 0;

  /** Constructor for ShellSort 
   * 
   * @param order Comparator to establish ordering of array elements.
   * @param step Iteration interval for shell sort
   */
  public Insertion(Comparator<T> order, int step) {
    orderBy = order;
    this.step = step;
  }

  /** Constructor for traditional Insertion Sort
   * 
   * @param order Comparator to establish ordering of array elements.
   */
  public Insertion(Comparator<T> order) {
    // Insertion Sort is Shell Sort with Iteration interval of 1.
    this(order,1);
  }

  /** Sorts specified array using Insertion Sort. Inplace sorter.
   * 
   * @param array Array to be sorted.
   */
  public void sort(T[] array) {

    // Once Insertion Sort is working, implement ShellSort
    
    // Reset count back to 0
    count = 0;

    // _________________________________________________________________
    // TODO ___________ COMPLETE INSERTION SORT BELOW __________________
    // ________________   IMPORTANT TO COMMENT YOUR CODE _______________
  


    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

  } // end sort(T[])

  /**  Sorts specified array, placing results in outArray. Not inplace sorter.
   * @param inArray Values to be sorted. inArray remains untouched in process.
   * @param outArray Contains sorted values of inArray upon completion.
   */
  public void sort(T[] inArray, T[] outArray) {

    // Insertion sort is an in-place sorter.
    // To preserve original array values, copy the inArray to the outArray. 
    // Then sort inplace on the outArray, leaving inArray untouched.
    for (int i=0; i<inArray.length; i++) {
      outArray[i] = inArray[i];
    }

    sort(outArray);
  } // end sort(T[],T[])

  public void setComparator(Comparator<T> order) {
    orderBy = order;
  }

  public int getCount() {
    return count;
  }
} // end class Insertion
