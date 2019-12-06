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

    //cant assume any fields so set default values, no parameters
    public PathNode(){
        this.left = null;
        this.right = null;
        this.parent = null;
        this.generationLeft = null;
        this.generationRight = null;
        this.isLevelEnd = false;
        this.isLastNode = false;
    }

    //compares sizes
    @Override
    public int compareTo(Object other) {
        if (other instanceof PathNode){
            return Integer.compare(this.path.size(), ((PathNode) other).path.size());
        }else{
            throw new ClassCastException("compareTo; parameter passed is not PathNode");
        }
    }

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

    public void copy(PathNode node){
        this.parent = node.parent;
        this.path = node.path;
        this.left = node.left;
        this.right = node.right;
        this.generationLeft = node.generationLeft;
        this.generationRight = node.generationRight;
        this.isLevelEnd = node.isLevelEnd;
        this.isLastNode = node.isLastNode;
    }

    public ArrayList<Number> getPath() {
        return path;
    }

    public void setPath(ArrayList<Number> path) {
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

    public PathNode getGenerationLeft() {
        return generationLeft;
    }

    public void setGenerationLeft(PathNode generationLeft) {
        this.generationLeft = generationLeft;
    }

    public PathNode getGenerationRight() {
        return generationRight;
    }

    public void setGenerationRight(PathNode generationRight) {
        this.generationRight = generationRight;
    }

    public boolean isLeaf(){
        return this.right == null && this.left == null;
    }

    public boolean isMiddle(){
        return ((this.right != null || this.left != null) && this.parent != null);
    }
}