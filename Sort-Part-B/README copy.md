## Sorting Project Part A

<hr>

**This is an independent project. You are to implement your own code. You may not copy code off the web. You may not email your code to a classmate. You may not let other's see your code so that they can copy it. You may not use TutorMe without my consent - it is likely to be a waste of your time anyway.**

You may and should copy the algorithms from the textbook! Just remember to convert to 0-based indexing. You may look for examples of the Function class and the Comparator class to understand how they fit into this project. You may look at any documentation of Java to help with syntax.

Please do not be shy about asking me questions. This is a lot of code and I am happy to walk you through any parts that you do not understand. I can typically debug code pretty fast - tooting my own horn :-) -- but really, I'm fast, just ask when you are stuck!

<hr>

**In this comparative algorithm project**, you will be implementing sorting algorithms then comparing their runtime efficiency. Each algorithm will be used to sort a collection of arrays of increasing size. The arrays will be either in increasing sorted order, decreasing sorted order, or random order to begin with. The efficiency of the algorithm will be assessed based on the actual time it takes to run the algorithm and counting the total number of iterations/comparisons executed. The final deliverable is a report that presents and discusses the results.

<hr>

**In Part B**, you will conduct a series of experiments using the `Analyzer` class that gathers data about the number of operations (i.e. iterations) and time in milliseconds required to sort a variety of arrays. Arrays are first created and saved to a file. In the Analyzer, arrays of increasing size, read from the file, are sorted using the specified algorithm(s). The array is read fresh from the file each time "n" is increased to be as efficient as possible with memory. Each time an array is sorted, the results are saved to the results file.

There are many configurations for calling Analyzer.analyze(). These include:
- `public void analyze(String filename, Sorter<AlphaNumeric> algorithm)`
- `public void analyze(ArrayList<String> files, ArrayList<Sorter<AlphaNumeric>> algorithms)`
- `public void analyze(String filename, ArrayList<Sorter<AlphaNumeric>> algorithms)`
- `public void analyze(ArrayList<String> files, Sorter<AlphaNumeric> algorithm)`

You have flexibility in what you will be testing, but there are requirements to meet regarding experiments to run. 

**First, determine the maximum sized array that your system can handle.** 

> My laptop sorted a 500,000 element array that was in reverse order in about 110 minutes using Insertion Sort. It sorted a 250,000 element array in reverse order in about 25 minutes. It "sorted" a 500,000 element array that was in sorted order in less than a second.

1. Compare 3 sorting algorithms, all sorting the same array in a particular order (e.g. SORTED). Make the maximum "n" as large as possible.

1. Compare 3 sorting algorithms, all sorting the same array in a particular order (but different than the one above). Make the maximum "n" as large as possible.

1. Compare 3 sorting algorithms, all sorting the same array in a particular order (but different than the two above). Make the maximum "n" as large as possible.

1. Compare a single algorithm sorting 3 arrays with different orderings.

1. Design your own experiment.

> **Please create a back-up of your data once it has been saved to the results file. You do not want to spend a couple of hours collecting data only to accidentally overwrite it!**

The primary requirement for this project is a report of your comparative study. This is a technical report written in a professional voice. Describe all the experiments that you ran including the algorithm, array ordering, and maximum size of the array for each comparison. **Present your data in graphs.** You can open the results file in Google sheets or in Microsoft Excel and use those to produce the graphs. Be sure to label the axes and title the graphs. JUSTIFY your results by discussing the T(n) equations for each of the algorithms or the general asymptotic bounds of the algorithm. Relate those equations and/or bounds to the graphs.

<hr>

**In Part A**, you will implement the algorithms _Insertion_, _Counting_, and _Shell_ sort. The textbook provides the algorithm for the first 2, but do remember that it uses 1-based indexing, not 0-based. As part of your implementation of an algorithm, count the number of comparisons or iterations that are executed. There is a `count` variable in each _Sorter_ that should be reset to 0 at the start of the algorithm and incremented for each iteration. 

> In Insertion Sort, increment the counter (i.e. `++count`) inside the outer loop and the inner loop. In Counting Sort, increment the counter in each of the for loops. Shell Sort uses Counting Sort, so nothing additional is needed.

Each sorting algorithm is a method in a class that implements **the interface `Sorter`**. This structure is in place to assist in the experimental phase of the project. A _Sorter_ uses generics and technically could be used to sort any type of object. However, for Counting, we will assume that only Integer types will be sorted. For testing purposes, all algorithms will sort objects of type `AlphaNumeric`.

### Understanding the Code

**Instantiation and use of the Radix Sorter** is:

```Java
Radix<AlphaNumeric> radixNumber = new Radix<>(AlphaNumeric.numberGetter);
radixNumber.sort(array);
```

The `numberGetter` is of type `Function<AlphaNumeric,Integer>`. When it is applied to an AlphaNumeric object, it will call the getter `number()`. It looks like this ...

```
Integer valueOf = keyGetter.apply(element);
```

In the above example, if the element is an AlphaNumeric object with the values "aab" and 734 and the keyGetter is `AlphaNumeric.numberGetter`, then `_valueOf_ = 734` after this line is executed.

The _Counting Sort_ algorithm will also use this _Function<>` to extract the Integer portion of an AlphaNumeric object.

<hr>

**Instantiation and use of Insertion Sort** looks like this:

```Java
Insertion<AlphaNumeric> insertionAlpha = new Insertion<>(AlphaNumeric.orderAlpha);
insertionAlpha.sort(array);
```

or 

```Java
Insertion<AlphaNumeric> insertionNumeric = new Insertion<>(AlphaNumeric.orderNumeric);
insertionNumeric.sort(array);
```

The `orderAlpha` and `orderNumeric` are of type `Comparator<AlphaNumeric>` which implements the `compare(T,T)` method. In _Insertion Sort_, this Comparator is assigned to the variable _orderBy_. It is used in this way to test if `array[i] > array[i+1]`:

```Java
if (orderBy.compare(array[i], array[i+1]) > 0) {
```

> Notice that the call to sort the array only passes the array, however Counting Sort does not sort in-place. It places the sorted elements into another array. The user has the option to place the sorted elements back into the original array or preserve the ordering of the original and place the sorted elements in a different array. This is managed from inside the Sorter. Note that this might involve the time to copy the array to another array.

<hr>

**Shell Sort** is a variation on Insertion Sort. It has a `step` size, which can be any value. The `step` dictates how many iterations of Insertion Sort are executed and which parts of the original array are sorted at each iteration. For example, with an array of size n=12 and step=3, 

- The first iteration of Insertion Sort will sort array elements at indices 1,4,7,10
- The second iteration will sort array elements at indices 2,5,8,11
- The third iteration will sort array elements at indices 3,6,9,12.
- The final iteration will use the standard Insertion Sort (i.e step size 1) to sort the entire array.

Elements do not need to be moved out of the array to sort the subset of elements. Simply control which elements are being sorted with the step size of the for loop. Recall that the last parameter of a for loop definition controls the step size of the iterator within the loop. Typically, we use i++ for a "step" size of 1, but any step size is acceptable (e.g. i=i+3).

When instantiating an Insertion Sorter, if the step size is not provided in the call to the constructor, then the step size is assumed to be 1 (i.e. the typical Insertion Sort). If the step size is >1, then Shell Sort should be used to sort the array. It might look like this:

```Java
Insertion<AlphaNumeric> insertionNumeric = new Insertion<>(AlphaNumeric.orderNumeric,3);
insertionNumeric.sort(array);
```

Notice that from the user's perspective, there is no difference in the call to sort, only in the instantiation of the Sorter. In the method `sort()`, first test the step size to determine if it should use Insertion or Shell sort. You might add helper functions or manage everything within the sort() method -- your choice!


