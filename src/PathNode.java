import java.util.ArrayList;

/**
 * Class to build nodes that will build Heap objects
 *
 * @author Julia Januchowski
 * @author Brett Dale
 * @version 1.0 (11/21/19)
 */
public class PathNode implements Comparable{

    /** An ArrayList of vertex IDs ordered by appearance in the path. */
    private ArrayList<Number> path;
    /** Reference to the left child. */
    private PathNode left;
    /** Reference to the right child. */
    private PathNode right;
    /** Reference to the parent. */
    private PathNode parent;
    /** Reference to the node directly to the left on the same tree level. */
    private PathNode generationLeft;
    /** Reference to the node directly to the right on the same tree level */
    private PathNode generationRight;
    /** True if the node is last in the level. */
    private boolean isLevelEnd;
    /** True if the node is the right-most node in the last level. */
    private boolean isLastNode;

    /**
     * Constructor for PathNode objects
     */
    public PathNode(){
        this.left = null;
        this.right = null;
        this.parent = null;
        this.generationLeft = null;
        this.generationRight = null;
        this.isLevelEnd = false;
        this.isLastNode = false;
    }

    /**
     * Compare method for PathNode objects
     * @param other other PathNode to compare to
     * @return 1 if paramter is less than this, 0 if they are equal, -1 if this is less than parameter
     */
    @Override
    public int compareTo(Object other) {
        if (other instanceof PathNode){
            return Integer.compare(this.path.size(), ((PathNode) other).path.size());
        }else{
            throw new ClassCastException("compareTo; parameter passed is not PathNode");
        }
    }

    /**
     * Equals method for PathNode objects
     * @param other other PathNode to check for equality
     * @return true if they are equal, false otherwise
     */
    @Override
    public boolean equals(Object other){
        boolean status = true;
        if (other instanceof PathNode){
            for (Number num : ((PathNode) other).path){
                if (!this.path.contains(num)) {
                    status = false;
                }
            }
        }
        return status;
    }

    /**
     * Getter method for path field
     * @return path field
     */
    public ArrayList<Number> getPath() {
        return path;
    }

    /**
     * Setter method for path field
     * @param path path to be set
     */
    public void setPath(ArrayList<Number> path) {
        this.path = path;
    }

    /**
     * Getter method for left field
     * @return
     */
    public PathNode getLeft() {
        return left;
    }

    /**
     * Setter method for left field
     * @param left left field to be set
     */
    public void setLeft(PathNode left) {
        this.left = left;
    }

    /**
     * Getter method for right field
     * @return right field
     */
    public PathNode getRight() {
        return right;
    }

    public void setRight(PathNode right) {
        this.right = right;
    }

    /**
     * Getter method for Parent field
     * @return Parent field
     */
    public PathNode getParent() {
        return parent;
    }

    /**
     * Setter method for Parent field
     * @param parent parent to be set
     */
    public void setParent(PathNode parent) {
        this.parent = parent;
    }

    /**
     * Setter method for isLevelEnd field
     * @param levelEnd boolean to be set
     */
    public void setLevelEnd(boolean levelEnd) {
        isLevelEnd = levelEnd;
    }

    /**
     * Getter method for Generation Left field
     * @return generationLeft field
     */
    public PathNode getGenerationLeft() {
        return generationLeft;
    }

    /**
     * Setter method for generationLeft field
     * @param generationLeft Node to be set to generationLeft
     */
    public void setGenerationLeft(PathNode generationLeft) {
        this.generationLeft = generationLeft;
    }

    /**
     * Getter method for generation right
     * @return generationRight field
     */
    public PathNode getGenerationRight() {
        return generationRight;
    }

    /**
     * Setter method for generationRight field
     * @param generationRight Node to be set to generation Right
     */
    public void setGenerationRight(PathNode generationRight) {
        this.generationRight = generationRight;
    }
}