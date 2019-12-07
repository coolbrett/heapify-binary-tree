import java.io.FileNotFoundException;

/**
 * This class runs the project and builds the Binary Tree
 *
 * @author Julia Januchowski
 * @author Brett Dale
 * @version 1.0 (11/21/19)
 */
public class Driver {
    /**
     * creates a heap and calls go to create the heap with that text file
     * @param args input file
     */
    public static void main(String[] args) {
        try {
            Heap heap = new Heap();
            heap.go(args[2]);
        }catch (IllegalArgumentException eae){
            System.out.println("main: no file was passed");
        } catch (ArrayIndexOutOfBoundsException eae){
            System.out.println("main: no file was passed");
        }

    }
}
