import java.io.FileNotFoundException;

/**
 * This class runs the project and builds the Binary Tree
 *
 * @author Julia Januchowski
 * @author Brett Dale
 * @version 1.0 (11/21/19)
 */
public class Driver {

    public static void main(String[] args) {
        try {
            Heap heap = new Heap();
            heap.readPaths(args[2]);
        }catch (FileNotFoundException fnfe){
            System.out.println("main; file not found");
        }
    }
}