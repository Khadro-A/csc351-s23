import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;

public class Analyzer {

    /** start time in nanoseconds */
    private long startNS;
    /** end time in nanoseconds */
    private long endNS;

    /** array size ranges */
    // Using the smaller sizes to determine if everything is working before trying the large dataset
    private int[] sizes = { 100, 1000, 10000, 50000};
    //private int[] sizes = { 100, 1000, 10000, 50000, 100000, 250000, 500000, 1000000 };

    /** File for storing results */
    String filename = "results.csv";

    /** Default constructor. Uses results.csv to store the results. */
    public Analyzer() {}

    /** Constructor to specify the file in which results will be stored. */
    public Analyzer(String f) {
        filename = f;
    }

    /** One array + One algorithm */
    public void analyze(String filename, Sorter<AlphaNumeric> algorithm) {

        // Extract information to be stored in the results to know which experiment was run.
        // getClass() returns "class Insertion", so remove the "class " part at the beginning.
        String algo = algorithm.getClass().toString().substring(6);
        // Remove the file extension
        String arrayType = filename.substring(0,filename.length()-4);

        // Sort the array in the associated file, creating a larger and larger array each time
        for (int i=0; i<sizes.length; i++) {
            // Determine "n" for this iteration
            int size = sizes[i];
            // Start the clock
            startNS=System.nanoTime();
            // Read size values. If size > length of the file, it returns null, so break out
            AlphaNumeric[] array = FileIO.readFile(filename,size);
            if (array==null) {
                break;
            }
            // Complete the sort. Notice that this is always calling the in-place version.
            // If the algo is not an in-place algorithm, extra time will be spent copying the array
            // to make it function as an in-place algorithm. It does not impact the operations count.
            // If instead, you would like to use the non-in-place version do ...
            // AlphaNumeric[] sortedArray = new AlphaNumeric[array.length];
            // algorithm.sort(array,sortedArray);
            algorithm.sort(array);

            // record the time. get total ops count.
            endNS=System.nanoTime();
            long timeNS = endNS - startNS;
            long operations = algorithm.getCount();

            // save to the file
            String results = algo+","+arrayType+","+size+","+operations+","+(long)(timeNS/1000/1000);
            saveResults(results);
        }
    }    

    /** List of arrays + List of algorithms */
    public void analyze(ArrayList<String> files, ArrayList<Sorter<AlphaNumeric>> algorithms) {
        // Try each combination
        for (String arrayFile : files) {
            for (Sorter<AlphaNumeric> algo: algorithms) {
                analyze(arrayFile,algo);
            }
        }
    }

    /** One array + List of algorithms */
    public void analyze(String filename, ArrayList<Sorter<AlphaNumeric>> algorithms) {
        // Create an array so the other function can be called
        ArrayList<String> files = new ArrayList<>();
        files.add(filename);
        analyze(files,algorithms);
    }

    /** List of arrays + One algorithm */
    public void analyze(ArrayList<String> files, Sorter<AlphaNumeric> algorithm) {
        // Create an array so the other function can be called
        ArrayList<Sorter<AlphaNumeric>> algos = new ArrayList<>();
        algos.add(algorithm);
        analyze(files,algos);
    }
    

    /** Save results of one array of fixed size with one algorithm */
    private void saveResults(String toWrite) {
        // Write file using "try with resources". This will APPEND.
        // When try-ing with resources, the system will manage the
        // closing of the file if anything goes wrong.
        try ( FileWriter writer = new FileWriter(new File(filename),true)) {
            writer.write(toWrite+"\n");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error writing to file.");
            e.printStackTrace();
        }
    }
}
