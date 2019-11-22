import java.util.ArrayList;

/**
 * Class to build nodes that will build the Binary Trees
 *
 * @author Julia Januchowski
 * @author Brett Dale
 * @version 1.0 (11/21/19)
 */
public class PathNode implements Comparable {

    /** An ArrayList of vertex IDs ordered by appearance in the path. */
    private ArrayList<Integer> path;
    /** Reference to the left child. */
    private PathNode left;
    /** Reference to the right child. */
    private PathNode right;
    /** Reference to the parent. */
    private PathNode parent;
    /** Reference to the node directly to the right on the same tree level. */
    private PathNode generation; //left to right sibling or cousin
    /** True if the node is last in the level. */
    private boolean isLevelEnd;
    /** True if the node is the right-most node in the last level. */
    private boolean isLastNode;

    public PathNode(PathNode left, PathNode right, PathNode parent, PathNode generation,
                    boolean isLevelEnd, boolean isLastNode){
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.generation = generation;
        this.isLevelEnd = isLevelEnd;
        this.isLastNode = isLastNode;
    }

    public PathNode(){
        this.left = null;
        this.right = null;
        this.parent = null;
        this.generation = null;
        this.isLevelEnd = false;
        this.isLastNode = false;
    }

    @Override
    public int compareTo(Object other) {
        //remember to check instanceof
        return 0;
    }

    public ArrayList<Integer> getPath() {
        return path;
    }

    public void setPath(ArrayList<Integer> path) {
        this.path = path;
    }

    public PathNode getLeft() {
        return left;
    }

    public void setLeft(PathNode left) {
        this.left = left;
    }

    public PathNode getRight() {
        return right;
    }

    public void setRight(PathNode right) {
        this.right = right;
    }

    public PathNode getParent() {
        return parent;
    }

    public void setParent(PathNode parent) {
        this.parent = parent;
    }

    public PathNode getGeneration() {
        return generation;
    }

    public void setGeneration(PathNode generation) {
        this.generation = generation;
    }

    public boolean isLevelEnd() {
        return isLevelEnd;
    }

    public void setLevelEnd(boolean levelEnd) {
        isLevelEnd = levelEnd;
    }

    public boolean isLastNode() {
        return isLastNode;
    }

    public void setLastNode(boolean lastNode) {
        isLastNode = lastNode;
    }
}
