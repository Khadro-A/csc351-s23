import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class FileIO {

    /** Read all the AlphaNumeric elements in the file
     * 
     * @param filename is file of space delimited values 
     * @return AlphaNumeric array
     */
    public static AlphaNumeric[] readFile(String filename) {
        // passing null for size to indicate read all in the file
        return readFile(filename, null);
    }

    /** Read as many elements in the file as indicated by size.
     * 
     * @param filename is file with space delimited values  
     * @param size The number of elements to read from the file
     * 
     * @return AlphaNumeric array of specified size containing values from specified file
     */    
    public static AlphaNumeric[] readFile(String filename, Integer size) {
        
        AlphaNumeric[] array = null;
        
        // track where to insert into lines
        int index = 0;

        // Read file using "try with resources"
        // When try-ing with resources, the system will manage the
        // closing of the file if anything goes wrong.
        try ( Scanner scanner = new Scanner(new File(filename))) {
            // First line contains number of elements in the file
            int maxsize = scanner.nextInt();
            if (maxsize < size) {
                return null;
            }
            if (size == null) {
                size = maxsize;
            } 
            array = new AlphaNumeric[size];
            while (scanner.hasNext() && index < size) {
                array[index] = new AlphaNumeric(scanner.next(),scanner.nextInt());
                index++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error reading file.");
            e.printStackTrace();
        }
        return array;
    }

    /** Create a file of AlphaNumerics. It will have "size" alphanumeric elements
     * 
     * @param filename File to write to -- this OVERWRITES the file!
     * @param size Number of random faces to store.
    */
    public static void writeFile(String filename, AlphaNumeric[] array) {
        
        // Write file using "try with resources"
        // When try-ing with resources, the system will manage the
        // closing of the file if anything goes wrong.
        try ( FileWriter writer = new FileWriter(new File(filename))) {
            writer.write(array.length+"\n");

            for (AlphaNumeric an : array) {
                // add line to array (used to instantiate a face later)
                writer.write(an.alpha()+" "+ an.number()+" ");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error reading file.");
            e.printStackTrace();
        }
    }
}
