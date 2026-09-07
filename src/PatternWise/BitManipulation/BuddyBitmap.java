package PatternWise.BitManipulation;
import java.util.Arrays;

public class BuddyBitmap {
    /**
     * Q:  You are given a complete binary tree ( represented as an array of bits(0 or 1 ))
     * where leafs represent the lowest -level memory blocks 
     * 0: free
     * 1: allocated 
     *  The task is to impleemnt two operations 
     * 1. set_bit(offset, length): mark legth bits starting from offset as 1 , set all descendents to 1 , and update 
     *  ancestor nodes upward
     * 2. clear_bit(ooset, length) : Mark length bits starting from offset as 0, set all descendents to 0, and update 
     * ancestor nodes upward
     * 
     * tree representation:
     * for o-indexed array  representing a binary tree 
     * left child i = 2i + 1
     * right child i = 2i+ 2
     * parent node = (i-1)/2 
     * 
     * 
     * rough works:
     * 
     * for N node tree 
     * number if internal nodes = N-1
     * and leaf offset 0 ( k=0) = Array index (N-1) + 0 
     * leaf offset 1 ( k =1)  = array index(N -1) + 1
     * leaf offset 2 ( k= 2) = array of index(N-1) + 2
     * 
     * so for i , leaf offset = (N-1) + (offset + i)
    */
  

        private final int numLeaves;
        private final int[] tree; // 0 = free, 1 = allocated
    
        public BuddyBitmap(int numLeaves) {
            if ((numLeaves & (numLeaves - 1)) != 0) {
                throw new IllegalArgumentException("Number of leaves must be a power of 2.");
            }
            this.numLeaves = numLeaves;
            this.tree = new int[2 * numLeaves - 1]; // Default initialized to 0
        }
    
        /**
         * Set 'length' bits starting at leaf 'offset' to 1.
         * Updates descendants downward and ancestors upward.
         */
        public void setBit(int offset, int length) {
            validateBounds(offset, length);
            
            for (int i = 0; i < length; i++) {
                int leafIdx = (numLeaves - 1) + (offset + i);
                if (tree[leafIdx] == 1) continue; // Early exit if already set
    
                tree[leafIdx] = 1;
                updateDownward(leafIdx, 1);
                updateUpward(leafIdx);
            }
        }
    
        /**
         * Clear 'length' bits starting at leaf 'offset' to 0.
         * Updates descendants downward and ancestors upward.
         */
        public void clearBit(int offset, int length) {
            validateBounds(offset, length);
    
            for (int i = 0; i < length; i++) {
                int leafIdx = (numLeaves - 1) + (offset + i);
                if (tree[leafIdx] == 0) continue; // Early exit if already free
    
                tree[leafIdx] = 0;
                updateDownward(leafIdx, 0);
                updateUpward(leafIdx);
            }
        }
    
        /**
         * Upward Propagation (Pull Invariant): 
         * Recomputes parent = left & right up to the root.
         * Stops early if a parent's value does not change.
         */
        private void updateUpward(int nodeIndex) {
            int curr = nodeIndex;
            while (curr > 0) {
                int parent = (curr - 1) / 2;
                int left = 2 * parent + 1;
                int right = 2 * parent + 2;
    
                int newValue = tree[left] & tree[right];
                
                // Early Exit Optimization: Ancestors won't change if parent stays the same
                if (tree[parent] == newValue) {
                    break;
                }
    
                tree[parent] = newValue;
                curr = parent;
            }
        }
    
        /**
         * Downward Propagation (Push Invariant):
         * Recursively sets the node and all of its sub-children to val.
         */
        private void updateDownward(int nodeIndex, int val) {
            if (nodeIndex >= tree.length) return;
    
            tree[nodeIndex] = val;
            updateDownward(2 * nodeIndex + 1, val); // Left child
            updateDownward(2 * nodeIndex + 2, val); // Right child
        }
    
        private void validateBounds(int offset, int length) {
            if (offset < 0 || length <= 0 || offset + length > numLeaves) {
                throw new IndexOutOfBoundsException("Invalid offset or length range.");
            }
        }
    
        public void printTree() {
            System.out.println("Tree Array State: " + Arrays.toString(tree));
        }
    
        public int getRoot() {
            return tree[0];
        }
    public static void main(String[] args) {
        
    }
    
}
