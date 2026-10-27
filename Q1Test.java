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

                int[] preorder = arrays[pre];
                int[] inorder = arrays[in];
                int[] postorder = arrays[post];

                int root = preorder[0];
                int rootIndex = find(inorder, root);

                if (rootIndex == -1) {
                    continue;
                }

                boolean valid = true;

                for (int i = 0; i < preorder.length; i++) {
                    int parentIndex = find(inorder, preorder[i]);

                    int left = 2 * i + 1;
                    int right = 2 * i + 2;

                    if (left < preorder.length) {
                        int leftIndex = find(inorder, preorder[left]);

                        if (leftIndex >= parentIndex) {
                            valid = false;
                        }
                    }

                    if (right < preorder.length) {
                        int rightIndex = find(inorder, preorder[right]);

                        if (rightIndex <= parentIndex) {
                            valid = false;
                        }
                    }
                }

                if (postorder[postorder.length - 1] != root) {
                    valid = false;
                }

                if (valid) {
                    String[] orders = new String[3];
                    orders[pre] = "Preorder";
                    orders[in] = "Inorder";
                    orders[post] = "Postorder";

                    for (int i = 0; i < 3; i++) {
                        System.out.println(
                            "Traversal " + (i + 1) + " - " + orders[i]
                        );
                    }
                    return;
                }
            }
        }

        System.out.println("Invalid traversals";
    }
}
