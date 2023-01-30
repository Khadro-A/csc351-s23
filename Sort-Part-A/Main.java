public class Main {

    public static void main(String[] args) {

        AlphaNumeric[] array;

        System.out.println("\n___________________ SORTING WITH RADIX");
        
        array = ArrayMaker.makeArray(ArrayMaker.ArrayOrder.REVERSED,10);
        print(array);

        Radix<AlphaNumeric> radixNumber = new Radix<>(AlphaNumeric.numberGetter);
        radixNumber.sort(array);
        System.out.println("Operations count "+radixNumber.getCount());
        print(array);

        System.out.println("\n___________________ SORTING WITH COUNTING");
        
        array = ArrayMaker.makeArray(ArrayMaker.ArrayOrder.REVERSED,10);
        print(array);

        Counting<AlphaNumeric> countingNumber = new Counting<>(AlphaNumeric.numberGetter);
        countingNumber.sort(array);
        System.out.println("Operations count "+countingNumber.getCount());
        print(array);

        System.out.println("\n___________________ SORTING WITH INSERTION (alpha)");
        
        // Make an array in which the numbers are increasing, but alpha is in decreasing sorted order
        array = ArrayMaker.makeArray(ArrayMaker.ArrayOrder.SORTED,10);
        print(array);

        Insertion<AlphaNumeric> insertionAlpha = new Insertion<>(AlphaNumeric.orderAlpha);
        insertionAlpha.sort(array);
        System.out.println("Operations count "+insertionAlpha.getCount());
        print(array);

        System.out.println("\n___________________ SORTING WITH INSERTION (number, random)");
        
        // Make an array in which the numbers are increasing, but alpha is in decreasing sorted order
        array = ArrayMaker.makeArray(ArrayMaker.ArrayOrder.RANDOM,10);
        print(array);

        Insertion<AlphaNumeric> insertionNumber = new Insertion<>(AlphaNumeric.orderNumeric);
        insertionNumber.sort(array);
        System.out.println("Operations count "+insertionNumber.getCount());
        print(array);

    } // end main

    public static void print(Object[] array) {
        for (Object el : array) {
            System.out.print(el+" ");
        }
        System.out.println();
    }
}
