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

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
