package p13xx;

class Problem1379 {
    public static void main(String[] args) throws InterruptedException {
        new Problem1379().test().getTargetCopy(null, null, null);
    }

    public Solution test() {
        return new Solution();
    }

    class TreeNode {
        int val;

        TreeNode left;

        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
            if (original == null) {
                return null;
            }

            if (original == target) {
                return cloned;
            }

            final TreeNode result = getTargetCopy(original.left, cloned.left, target);
            if (result != null) {
                return result;
            }

            return getTargetCopy(original.right, cloned.right, target);
        }
    }
}

