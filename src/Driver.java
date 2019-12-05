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
            //tempPath starts at 1, start point has no parent so 0 is handled accordingly in method
            heap.buildCompleteTree(1);
            heap.setLevelEnd(heap.getRoot());
            heap.setGenerationLinks(heap.getRoot());
            heap.heapify(heap.getRoot());
        }catch (FileNotFoundException fnfe){
            System.out.println("main; file not found");
        }
    }
}