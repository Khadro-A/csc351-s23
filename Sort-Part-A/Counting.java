import java.util.function.Function;

/** Counting Sort assumes key of type Integer */
public class Counting<T> implements Sorter<T> {

  /** Extracts the key from an object in the array */
  Function<T,Integer> keyGetter = null;

  /** Max Value in the array to be sorted */
  Integer maxValue = null;

  /** Counter of loop iterations */
  int count = 0;

  /** Default empty constructor. */
  public Counting() {}

  /** Constructor for Counting
   * 
   * @param order Comparator to establish ordering of array elements.
   */
  public Counting(Function<T,Integer> getter) {
    keyGetter = getter;
  }

    /** Constructor for Counting with known max value
   * 
   * @param order Comparator to establish ordering of array elements.
   */
  public Counting(Function<T,Integer> getter, Integer maximum) {
    keyGetter = getter;
    this.maxValue = maximum;
  }

  /** Sorts specified array using Counting Sort. Inplace version of the sorter.
   * 
   * @param array Array to be sorted.
   */
  public void sort(T[] array) {

    // Counting is not an in-place sorting algorithm.
    // Therefore, need to sort, then copy the new sorted array back into the original

    @SuppressWarnings("unchecked")
    T[] sorted = (T[]) new Object[array.length];

    sort(array,sorted);
    for (int i=0; i<array.length; i++) {
      array[i] = sorted[i];
    }
  } // end sort(T[])

  /**  Sorts specified array, placing results in outArray. Not inplace sorter.
   * @param inArray Values to be sorted. inArray remains untouched in process.
   * @param outArray Contains sorted values of inArray upon completion.
   */
  public void sort(T[] inArray, T[] outArray) {
    
    if (maxValue==null) {
      maxValue = findMax(inArray);
    }

    // Reset count back to 0
    count = 0;

    // _________________________________________________________________
    // TODO ___________ COMPLETE COUNTING SORT BELOW ___________________
    // ________________   IMPORTANT TO COMMENT YOUR CODE _______________
    // _____ BEWARE OF THE CONVERSION FROM 1-based to 0-based indexing _
  
    


    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^    

  } // end sort(T[], T[])

  private Integer findMax(T[] array) {
    Integer max = keyGetter.apply(array[0]);
    for (T element : array) {
      Integer valueOf = keyGetter.apply(element);
      if (valueOf > max) {
        max = valueOf;
      }
    }
    return max;
  } // end findMax()

  public void setKeyGetter(Function<T,Integer> getter) {
    keyGetter = getter;
  }

  public int getCount() {
    return count;
  }  
} // end class Countin
