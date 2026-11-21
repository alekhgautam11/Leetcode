import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<List<Integer>> initialTree = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            initialTree.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            initialTree.get(parent[i]).add(i);
        }

     
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        int[] newParent = Arrays.copyOf(parent, n);
        assignNewParents(0, initialTree, s, lastSeen, newParent);

        List<List<Integer>> finalTree = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            finalTree.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            finalTree.get(newParent[i]).add(i);
        }

        int[] subtreeSize = new int[n];
        computeSubtreeSizes(0, finalTree, subtreeSize);
        return subtreeSize;
    }

    private void assignNewParents(
            int node,
            List<List<Integer>> tree,
            String s,
            int[] lastSeen,
            int[] newParent
    ) {
        int charIdx = s.charAt(node) - 'a';
        int previousAncestor = lastSeen[charIdx];

        if (previousAncestor != -1 && node != 0) {
            newParent[node] = previousAncestor;
        }

        lastSeen[charIdx] = node;

        for (int child : tree.get(node)) {
            assignNewParents(child, tree, s, lastSeen, newParent);
        }

        lastSeen[charIdx] = previousAncestor;
    }

    private int computeSubtreeSizes(int node, List<List<Integer>> tree, int[] subtreeSize) {
        int size = 1;
        for (int child : tree.get(node)) {
            size += computeSubtreeSizes(child, tree, subtreeSize);
        }
        subtreeSize[node] = size;
        return size;
    }
}