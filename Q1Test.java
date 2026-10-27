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
     for (int pre = 0; pre < 3; pre++) {
            for (int attempt = 0; attempt < 2; attempt++) {
                int in = (pre + 1 + attempt) % 3;
                int post = (pre + 2 - attempt) % 3;

                int[] preorder = arrays[pre];
                int[] inorder = arrays[in];
                int[] postorder = arrays[post];

                System.out.println(
                    "\nAssume t" + (pre + 1) + " is preorder, t" +
                    (in + 1) + " is inorder, t" +
                    (post + 1) + " is postorder"
                );

                int root = preorder[0];
                int rootIndex = -1;

                for (int i = 0; i < inorder.length; i++) {
                    if (inorder[i] == root) {
                        rootIndex = i;
                        break;
                    }
                }

                // These conditions prevent impossible array slices.
                if (rootIndex == -1 ||
                    preorder.length != inorder.length ||
                    postorder.length != inorder.length) {
                    System.out.println("Cannot split this combination.");
                    continue;
                }

                int leftSize = rootIndex;
                int n = preorder.length;

                System.out.println("Root: " + root);

                printSplit(
                    "Preorder",
                    Arrays.copyOfRange(preorder, 1, 1 + leftSize),
                    Arrays.copyOfRange(preorder, 1 + leftSize, n)
                );

                printSplit(
                    "Inorder",
                    Arrays.copyOfRange(inorder, 0, rootIndex),
                    Arrays.copyOfRange(inorder, rootIndex + 1, n)
                );

                printSplit(
                    "Postorder",
                    Arrays.copyOfRange(postorder, 0, leftSize),
                    Arrays.copyOfRange(postorder, leftSize, n - 1)
                );

                System.out.println(
                    "Postorder's assumed root: " + postorder[n - 1]
                );
            }
        }
    }
}
