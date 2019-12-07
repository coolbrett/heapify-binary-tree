import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class builds Heap objects that store PathNodes from least to greatest with
 * greatest being at the bottom
 *
 * @author Julia Januchowski
 * @author Brett Dale
 * @version 1.0 (11/21/19)
 */
public class Heap {

    /** Temporary storage for the paths starting at tempPath[1]. */
    private ArrayList<PathNode> tempPath = new ArrayList<>();
    /** Root of Heap*/
    private PathNode root;
    /** Level count*/
    private int levels;

    public Heap(){
        this.tempPath.add(null);
        this.root = null;
        this.levels = 1;
    }

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
            scanner.useDelimiter("\n");
            while (scanner.hasNext()) {
                PathNode node = new PathNode();
                ArrayList<Number> temp = new ArrayList<>();
                String string = scanner.next();
                String[] arr = string.split(" ");
                for (String str : arr) {
                    Number num;
                    if (!str.contains(".")) {
                        num = Integer.valueOf(str);
                    } else {
                        num = Double.valueOf(str);
                    }
                    temp.add(num);
                }
                node.setPath(temp);
                tempPath.add(node);
                //System.out.println(tempPath);
                //code below displays data from file being passed to each node
                /*System.out.println("\nStart of node");
                for (Number num : node.getPath()){
                    System.out.print(num);
                }
                System.out.println("\nEnd of node"); */
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
     * @return A reference to the node just placed in the tree.
     */

    public PathNode buildCompleteTree(int index) {
        if (tempPath.size() > 1){
            root = tempPath.get(1);
        }else{
            throw new InputMismatchException("buildCompleteTree; file did not pass any data");
        }

        if (index < tempPath.size() && index > 0) {
            PathNode temp = tempPath.get(index);
            PathNode left;
            PathNode right;

            if ((index * 2) < tempPath.size()) {
                left = tempPath.get(index * 2);
                left.setParent(temp);
                left.getParent().setLeft(left);
            }
            if (((index * 2) + 1) < tempPath.size()) {
                right = tempPath.get((index * 2) + 1);
                right.setParent(temp);
                right.getParent().setRight(right);
            }
            buildCompleteTree((index + 1));
        }
        return root;
    }

    /**
     * Recursive method that sets isLevelEnd.
     * @param root Root of the subtree.
     */
    public void setLevelEnd(PathNode root){
        root.setLevelEnd(true);
        if (root.getLeft() != null){
            this.levels++;
            setLevelEnd(root.getLeft());
        }
    }

    /**
     * Recursive method that sets the "generation" link of PathNode objects from left-to-right.
     * generation is a term I use to indicate nodes on the same level (these may be siblings or
     * cousins)
     * @param root Root of the subtree.
     */
    public void setGenerationLinks(PathNode root){
        if (root.getLeft() != null && root.getRight() != null){
            root.getLeft().setGenerationRight(root.getRight());
            root.getRight().setGenerationLeft(root.getLeft());
            if (root.getGenerationLeft() != null && root.getGenerationLeft().getRight() != null){
                root.getLeft().setGenerationLeft(root.getGenerationLeft().getRight());
                root.getGenerationLeft().getRight().setGenerationRight(root.getLeft());
            }
            if (root.getGenerationRight() != null && root.getGenerationRight().getLeft() != null){
                root.getRight().setGenerationRight(root.getGenerationRight().getLeft());
                root.getGenerationRight().getLeft().setGenerationLeft(root.getRight());
            }
            setGenerationLinks(root.getLeft());
            setGenerationLinks(root.getRight());
        }else {
            if (root.getGenerationLeft() != null && root.getGenerationLeft().getRight() != null){
                root.getLeft().setGenerationLeft(root.getGenerationLeft().getRight());
                root.getGenerationLeft().getRight().setGenerationRight(root.getLeft());
            }
        }
    }

    private void swap(PathNode parent, PathNode child){
        ArrayList<Number> temp = parent.getPath();
        parent.setPath(child.getPath());
        child.setPath(temp);
    }

    /**
     * This method will sort the Heap object in terms of size from smallest to largest with
     * the smallest PathNodes being at the top and largest PathNodes being at the bottom
     * @param root root of the heap object
     */
    public void heapify(PathNode root){
        PathNode leftMost = root;
        while(leftMost.getLeft() != null){
            leftMost = leftMost.getLeft();
        }
        PathNode clPointer = leftMost;
        while(clPointer.getParent() != null){
            //brett edited
            traverseLevel(clPointer.getParent());
            clPointer = clPointer.getParent();
        }
    }

    /**
     * This method will sort the Heap object in terms of size from smallest to largest with
     * the smallest PathNodes being at the top and largest PathNodes being at the bottom
     * @param root root of the heap object
     */
    public void traverseLevel(PathNode parent){
        PathNode imbalance = findImbalances(parent);
        if(imbalance != null){
            swap(parent, imbalance);
            checkSwap(imbalance);
        }
        while(parent.getGenerationRight() != null){
            parent = parent.getGenerationRight();
            imbalance = findImbalances(parent);


            if(imbalance != null){
                swap(parent, imbalance);
                checkSwap(imbalance);

            }
        }
    }

     /**
     * check the current swap just made to see if its new children need to heapified again
     * @param parent the new parent made from the swap
     */
    public void checkSwap(PathNode parent){
        PathNode imbalance = findImbalances(parent);
        if(imbalance != null){
            swap(parent,imbalance);
            checkSwap(imbalance);
        }
    }


    /**
     * This method will find imbalances in the Heap object
     * @param root root of the heap object
     */
    public PathNode findImbalances(PathNode parent){
        PathNode imbalance = null;

        //while(imbalance == null && !root.isLeaf()) {
            if (parent.getLeft() != null && parent.getRight() != null) {
                int compCC = parent.getLeft().compareTo(parent.getRight());
                if (compCC <= 0) {
                    int compCP = parent.getLeft().compareTo(parent);
                    if (compCP < 0) {
                        imbalance = parent.getLeft();
                    } // end if
                } // end if
                else{
                    int compCP = parent.getRight().compareTo(parent);
                    if (compCP < 0) {
                        imbalance = parent.getRight();
                    } // end if
                } // end else if
            } // end if
            else if (parent.getLeft() != null && parent.getRight() == null) {
                int compCP = parent.compareTo(parent.getLeft());
                if (compCP > 0) {
                    imbalance = parent.getLeft();
                }
            } else if (parent.getLeft() == null && parent.getRight() != null) {
                int compCP = parent.compareTo(parent.getRight());
                if (compCP > 0) {
                    imbalance = parent.getRight();
                }
            }
        //}
        return imbalance;
    }

    /**
     * Prints the path lengths from left-to-right at each level in the tree in the form specified
     * by the instructions.
     * @param root Root of the whole tree to begin printing from.
     */
    private void printTreeLevels(PathNode root){
        String result = "";
        result += "Root:\t\t" + pathNodeStr(root) + "\n";
        PathNode currentNode = root;

        int levelNum = 1;
        while(currentNode.getLeft() != null){
            currentNode = currentNode.getLeft();
            result += printCurrentTreeLevel(currentNode, levelNum) + "\n";
            levelNum++;

        }
        System.out.println(result);
    }

    /**
     * Helper method for printTreeLevels. Returns string version of a node including it's path
     * @param currNode the pathNode you wish to get the string value of
     * @return the string value of currNode
     */
    private String pathNodeStr(PathNode currNode){
        String result = "";
        result += (currNode.getPath().size()-1) + "(";
        int pSize = 0;
        for(Number i : currNode.getPath()){
            if(pSize < currNode.getPath().size()-1) {
                result += i + ", ";
            }
            else{
                result += i;
            }
            pSize++;
        }
        result += ")";
        return result;
    }

    /**
     * Helper method for printTreeLevels. Returns string version of a tree level
     * @param currNode a pathNode that represents the leftmost node in the current level of a tree
     * @param levelNum an integer that represents the current level number you are on.
     * @return a formatted string of the current level you are on.
     */
    private String printCurrentTreeLevel(PathNode currNode, int levelNum){
        String result = "Level " + levelNum + ":\t";
        PathNode currentNode = currNode;

        while(currentNode != null){
            if(currentNode.getGenerationRight() != null){
                result+= pathNodeStr(currentNode) + "--> ";
                currentNode = currentNode.getGenerationRight();
            }
            else {
                result+= pathNodeStr(currentNode);
                currentNode = currentNode.getGenerationRight();
            }
        }
        return result;
    }

    public ArrayList<PathNode> getTempPath() {
        return tempPath;
    }

    public void setTempPath(ArrayList<PathNode> tempPath) {
        this.tempPath = tempPath;
    }

    public PathNode getRoot() {
        return root;
    }

    public void setRoot(PathNode root) {
        this.root = root;
    }

    public int getLevels() {
        return levels;
    }

    public void setLevels(int levels) {
        this.levels = levels;
    }

    /*private PathNode getLastNode(PathNode root){
        if (root.getLeft() != null){
            getLastNode(root.getLeft());
        }else{
            while (root.getGenerationRight() != null){
                root = root.getGenerationRight();
            }
        }
        root.setLastNode(true);
        return root;
    }*/

    private PathNode navigateLeft(PathNode root){
        while (root.getLeft() != null){
            root = root.getLeft();
        }
        return root;
    }

    private PathNode getLastNode(PathNode root){
        PathNode temp = root.getGenerationRight();
        while (temp.getGenerationRight() != null){
            temp = temp.getGenerationRight();
        }
        temp.setLastNode(true);
        return temp;
    }
    public void go(String fileName) {
        try {
            this.readPaths("input.txt");
            //tempPath starts at 1, start point has no parent so 0 is handled accordingly in method
            this.buildCompleteTree(1);
            this.setLevelEnd(this.getRoot());
            this.setGenerationLinks(this.getRoot());
            System.out.println("---------- Before Heapify ----------");
            this.printTreeLevels(this.getRoot());
            this.heapify(this.getRoot());
            System.out.println("\n---------- After Heapify ----------");
            this.printTreeLevels(this.getRoot());

        }catch (FileNotFoundException fnfe){
            System.out.println("main; file not found");
        }
    }
}
