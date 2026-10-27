public class Q1Test {
     public static void main(String[] args) {

          String result, traversal1, traversal2, traversal3;

          traversal1 = "1-2";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "3-2-1";
          traversal3 = "2-3-1";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "3-1-2-5-4";
          traversal2 = "2-1-4-5-3";
          traversal3 = "1-2-3-4-5";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "10-20-30-40-50";
          traversal2 = "20-10-30-40-50";
          traversal3 = "10-50-40-30-20";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();
     } 

      // write your codes here
     public static void verify(String t1, String t2, String t3) {
        String[] inputs = {t1, t2, t3};
        int[][] arrays = new int[3][];

        for (int i = 0; i < 3; i++) {
            String[] parts = inputs[i].split("-");
            arrays[i] = new int[parts.length];

            for (int j = 0; j < parts.length; j++) {
                arrays[i][j] = Integer.parseInt(parts[j]);
            }
        }

        for (int pre = 0; pre < 3; pre++) {
            for (int attempt = 0; attempt < 2; attempt++) {
                int in = (pre + 1 + attempt) % 3;
                int post = (pre + 2 - attempt) % 3;

                // Inorder must be strictly increasing for a BST.
                if (!isSorted(arrays[in])) {
                    continue;
                }

                if (sameTree(arrays[pre], arrays[in], arrays[post])) {
                    String[] orders = new String[3];
                    orders[pre] = "Preorder";
                    orders[in] = "Inorder";
                    orders[post] = "Postorder";

                    System.out.println(
                        "Traversal 1 - " + orders[0] +
                        ", Traversal 2 - " + orders[1] +
                        ", Traversal 3 - " + orders[2]
                    );
                    return;
                }
            }
        }

        System.out.println("Invalid traversals");
    }

    private static boolean sameTree(
            int[] preorder, int[] inorder, int[] postorder) {

        int n = preorder.length;

        if (inorder.length != n || postorder.length != n) {
            return false;
        }

        // Three empty traversals describe the same empty subtree.
        if (n == 0) {
            return true;
        }

        int root = preorder[0];

        if (postorder[n - 1] != root) {
            return false;
        }

        int rootIndex = -1;

        for (int i = 0; i < n; i++) {
            if (inorder[i] == root) {
                rootIndex = i;
                break;
            }
        }

        if (rootIndex == -1) {
            return false;
        }

        int leftSize = rootIndex;

        // copyOfRange includes the start index, excludes the end index.
        int[] preLeft = java.util.Arrays.copyOfRange(
            preorder, 1, 1 + leftSize
        );
        int[] preRight = java.util.Arrays.copyOfRange(
            preorder, 1 + leftSize, n
        );

        int[] inLeft = java.util.Arrays.copyOfRange(
            inorder, 0, rootIndex
        );
        int[] inRight = java.util.Arrays.copyOfRange(
            inorder, rootIndex + 1, n
        );

        int[] postLeft = java.util.Arrays.copyOfRange(
            postorder, 0, leftSize
        );
        int[] postRight = java.util.Arrays.copyOfRange(
            postorder, leftSize, n - 1
        );

        return sameTree(preLeft, inLeft, postLeft)
            && sameTree(preRight, inRight, postRight);
    }

    private static boolean isSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] <= array[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
