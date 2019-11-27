import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// FIXME: 11/21/2019 description sucks
/**
 * Class to build Heap object in order to heapify
 *
 * @author Julia Januchowski
 * @author Brett Dale
 * @version 1.0 (11/21/19)
 */
public class Heap {

    /** Temporary storage for the paths starting at tempPath[1]. */
    private ArrayList<PathNode> tempPath = new ArrayList<>();
    /** top of Heap*/
    private PathNode top;

    /**
     * Reads inputFile given at the command line and places the contents of each line into the
     * path field found in each PathNode object. The order is the same as found in the text file.
     * Adds the PathNode object to tempPath starting at tempPath[1].
     *
     * @param inputFile Name of the input file to be read.
     * @throws FileNotFoundException if the input file cannot be found.
     */
    public void readPaths(String inputFile) throws FileNotFoundException{
        try {
            File file = new File(inputFile);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                PathNode node = new PathNode();
                ArrayList<Integer> temp = new ArrayList<>();
                scanner.useDelimiter(" ");
                while (scanner.hasNext()){
                    String num = scanner.next();
                    System.out.println(num);
                    //temp.add((int) num);
                }
            }
        }catch (FileNotFoundException fnfe){
            System.out.println("readPaths; File passed not found");
        }catch (NumberFormatException nfe){
            System.out.println("readPaths; File has invalid characters");
        }
    }

    /**
     * Recursively builds a complete binary tree. Places PathNode objects in tempPath into a
     * complete binary tree in order of appearance in the text file. The left child of a parent
     * located at tempPath[index] is found at tempPath[2 * index] and the right child is found at
     * tempPath[(2 * index) + 1].
     *
     * @param index Index of the current node in tempPath.
     * @param parent Parent of the current node.
     * @return A reference to the node just placed in the tree.
     */

    public PathNode buildCompleteTree(int index, int parent) {
        return null;
    }

    /**
     * Recursive method that sets isLevelEnd.
     * @param root Root of the subtree.
     */
    public void setLevelEnd(PathNode root){

    }

    /**
     * Recursive method that sets the "generation" link of PathNode objects from left-to-right.
     * generation is a term I use to indicate nodes on the same level (these may be siblings or
     * cousins)
     * @param root Root of the subtree.
     */
    public void setGenerationLinks(PathNode root){

    }

    /**
     * Prints the path lengths from left-to-right at each level in the tree in the form specified
     * by the instructions.
     * @param root Root of the whole tree to begin printing from.
     */
    public void printTreeLevels(PathNode root){

    }

    public ArrayList<PathNode> getTempPath() {
        return tempPath;
    }

    public void setTempPath(ArrayList<PathNode> tempPath) {
        this.tempPath = tempPath;
    }

    private void addNode(PathNode node){

    }

    private void swap(PathNode node){

    }
}