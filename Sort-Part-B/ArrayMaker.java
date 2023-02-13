import java.util.Random;

public class ArrayMaker {

    public static enum ArrayOrder {
        REVERSED, SORTED, RANDOM, MIXED
    };

    private static Random random = new Random();

    public static AlphaNumeric[] makeArray(ArrayOrder order, int size) {

        if (order == ArrayOrder.REVERSED) {
            return makeReversed(size);
        } else if (order == ArrayOrder.SORTED) {
            return makeSorted(size);
        } else if (order == ArrayOrder.RANDOM) {
            return makeRandom(size);
        } else if (order == ArrayOrder.MIXED) {
            return makeMixed(size);
        }
        return null;
    }

    private static AlphaNumeric[] makeReversed(int size) {

        AlphaNumeric[] array = new AlphaNumeric[size];

        // numbers will be in reverse sorted order
        // alpha will be in forward sorted order
        array[0] = new AlphaNumeric("aaaaaa",size);
        for (int i=1; i<size; i++) {
            array[i] = new AlphaNumeric(
            AlphaNumeric.nextAlpha(array[i-1].alpha(),false), 
            array[i-1].number()-1);
        }
        return array;
    }

    private static AlphaNumeric[] makeSorted(int size) {

        AlphaNumeric[] array = new AlphaNumeric[size];

        // numbers will be in increasing sorted order
        // alpha will be in reverse sorted order
        array[0] = new AlphaNumeric("zzzzzz", 0);
        for (int i = 1; i < size; i++) {
            array[i] = new AlphaNumeric(
                    AlphaNumeric.nextAlpha(array[i - 1].alpha(), true),
                    array[i - 1].number() + 1);
        }
        return array;
    } // end makeSorted
    
    private static AlphaNumeric[] makeRandom(int size) {

        AlphaNumeric[] array = new AlphaNumeric[size];

        // numbers will be in reverse sorted order
        // alpha will be in forward sorted order
        for (int i = 0; i < size; i++) {
            array[i] = new AlphaNumeric();
        }
        return array;
    } // end makeRandom()

    private static AlphaNumeric[] makeMixed(int size) {

        AlphaNumeric[] array = makeSorted(size);

        // Randomly rearrange about 40% with a mix of 60% sorted / 40% random 
        int fortypercent = (int)(size*.40);
        for (int i=0; i<fortypercent; i++) {
            // Swap randomly selected elements
            int index1 = random.nextInt(size);
            int index2 = random.nextInt(size);

            AlphaNumeric temp = array[index1];
            array[index1] = array[index2];
            array[index2] = temp;
        }
        return array;
    } // end makeMixed
}