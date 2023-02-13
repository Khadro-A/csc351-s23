import java.util.Comparator;
import java.util.ArrayList;

public class Main {

    /** Largest array to be considered in any of the experiments */
    static int maxsize = 1000000;

    public static void main(String[] args) {

        /** Primary means for running an experiment and recording the results in a file. */
        Analyzer analyzer = new Analyzer();

        /** Array to be sorted */
        AlphaNumeric[] array;

        /** List of files that hold arrays with different orderings used with Analyzer. */
        ArrayList<String> files = new ArrayList<>();

        /** List of algorithms used with Analyzer. */
        ArrayList<Sorter<AlphaNumeric>> algos;


        System.out.println("\n___________________ CREATING A SERIES OF FILES");
         
        // Makes the files and returns an array of filenames
        makeFilesOfArrays();
        // ^^^^^^ YOU NEED ONLY MAKE THESE FILES 1 TIME! Comment out once files exist.

        /** The call above makes all of these files. Include whichever files you want to experiment with. */
        files.add("reversed.txt");
        files.add("sorted.txt");
        files.add("random1.txt");
        files.add("random2.txt");
        files.add("mixed1.txt");
        files.add("mixed2.txt");

        // Running a couple of experiments with 1 array and 1 algorithm
        analyzer.analyze("sorted.txt",new Insertion<AlphaNumeric>(AlphaNumeric.orderNumeric));
        analyzer.analyze("reversed.txt",new Counting<AlphaNumeric>(AlphaNumeric.numberGetter));
         
        // ________________________________________________________________________________
        // ________________________________________________________________________________
        //   Below is used to test your algorithms to make sure they are working.
        /* 
        print(array);
        System.out.println("\n___________________ SORTING WITH RADIX");

        array = ArrayMaker.makeArray(ArrayMaker.ArrayOrder.REVERSED,10);
        print(array);
        Radix<AlphaNumeric> radixNumber = new Radix<>(AlphaNumeric.numberGetter);
        radixNumber.sort(array);
        print(array);
        
        System.out.println("\n___________________ SORTING WITH COUNTING");
        
        // Make it again so it is not sorted
        array = ArrayMaker.makeArray(ArrayMaker.ArrayOrder.REVERSED,10);
        print(array);
        Counting<AlphaNumeric> countingNumber = new Counting<>(AlphaNumeric.numberGetter);
        countingNumber.sort(array);
        print(array);

        System.out.println("\n___________________ SORTING WITH INSERTION (alpha)");
        
        // Make an array in which the numbers are increasing, but alpha is in decreasing sorted order
        array = ArrayMaker.makeArray(ArrayMaker.ArrayOrder.SORTED,10);
        print(array);
        Insertion<AlphaNumeric> insertionAlpha = new Insertion<>(AlphaNumeric.orderAlpha);
        insertionAlpha.sort(array);
        print(array);

        System.out.println("\n___________________ SORTING WITH INSERTION (number, random)");
        
        // Make an array with random values
        array = ArrayMaker.makeArray(ArrayMaker.ArrayOrder.RANDOM,10);
        print(array);
        Insertion<AlphaNumeric> insertionNumber = new Insertion<>(AlphaNumeric.orderNumeric);
        insertionNumber.sort(array);
        print(array);
        
        */
    } // end main

    public static void print(Object[] array) {
        for (Object el : array) {
            System.out.print(el+" ");
        }
        System.out.println();
    }

    /** Create a collection of arrays, each stored in a separate file */
    public static void makeFilesOfArrays() {

        /** Array created then saved to file */
        AlphaNumeric[] array;

        array = ArrayMaker.makeArray(ArrayMaker.ArrayOrder.REVERSED,maxsize);
        FileIO.writeFile("reversed.txt",array);

        array = ArrayMaker.makeArray(ArrayMaker.ArrayOrder.SORTED,maxsize);
        FileIO.writeFile("sorted.txt",array);

        array = ArrayMaker.makeArray(ArrayMaker.ArrayOrder.RANDOM,maxsize);
        FileIO.writeFile("random1.txt",array);

        array = ArrayMaker.makeArray(ArrayMaker.ArrayOrder.RANDOM,maxsize);
        FileIO.writeFile("random2.txt",array);

        array = ArrayMaker.makeArray(ArrayMaker.ArrayOrder.MIXED,maxsize);
        FileIO.writeFile("mixed1.txt",array);

        array = ArrayMaker.makeArray(ArrayMaker.ArrayOrder.MIXED,maxsize);
        FileIO.writeFile("mixed2.txt",array);
    }
}
