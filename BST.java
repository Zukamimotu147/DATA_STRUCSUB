package DATASTRUCsubject;

import java.util.Scanner;

class Node {
    int data;
    Node left;
    Node right;
}

class BinaryTree {
    public Node createNewNode (int val) {
        Node node = new Node();
        node.data = val;
        node.left = null;
        node.right = null;
        return node;
    }

    public Node insert (Node node, int val) {
        if(node == null) {
            return createNewNode(val);
        }

        if(val < node.data) {
            node.left = insert(node.left, val);
        } else if (val > node.data) {
            node.right = insert(node.right, val);
        }

        return node;
    }

    public Node delete (Node node, int val) {
        if (node == null) {
            return null;
        }

        if (val < node.data) {
            node.left = delete (node.left, val);
        } else if (val > node.data) {
            node.right = delete (node.right, val);
        } else {
            if (node.left == null || node.right == null) {
                Node temp = null;
                temp = node.left == null ? node.right : node.left;
                
                if (temp == null) {
                    return null;
                } else {
                    return node;
                }
            } else {
                Node successor = getSuccessor(node);
                node.data = successor.data;

                node.right = delete(node.right, successor.data);
                return node;
            }
        }
        return node;
    }

    public Node getSuccessor (Node node) {
        if (node == null) {
             return null;
        }

        Node temp = node.right;

        while (temp.left != null) {
            temp = temp.left;
        }

        return temp;
    }

    public boolean find (Node node, int val) {
        if(node == null) {
            return false;
        }
    
        boolean isPresent = false;
        
        while(node != null) {
            if(val < node.data) {
                node = node.left;
            } else if(val > node.data) {
                node = node.right;
            } else {
                isPresent = true;
                break;
            }
        }
        
        return isPresent;
    }

    public void inorder(Node node) {
        if (node == null) {
        return;
        }
    
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    public void preorder(Node node) {
        if(node == null) {
            return;
        }
        
        System.out.print(node.data + " ");
        preorder(node.left);
        preorder(node.right);
    }

    public void postorder(Node node) {
        if(node == null) {
            return;
        }
    
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data+ " ");
    }
}

public class BST {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BinaryTree binaryTree = new BinaryTree();
        Node root = null;
        
        System.out.println("Enter a series of integers (enter non-integer to stop): ");

        while (scan.hasNextInt()) {
            int data = scan.nextInt();
            root = binaryTree.insert(root, data);
        }

        int option = 0;
        do {
            scan.nextLine();
            System.out.println("\n[1] Insert\n[2] Delete\n[3] Search\n[4] Traverse\n[5] Exit");
            System.out.print("Option: ");
            option = scan.nextInt();

            switch (option) {
                case 1:
                    System.out.print("\nEnter a value to insert from the binary tree: ");
                    int valueToInsert = scan.nextInt();
                    System.out.println("Number " + valueToInsert + " has been inserted.");
                    root = binaryTree.insert(root, valueToInsert);
                    break;
                case 2:
                    System.out.print("\nEnter a value to delete from the binary tree: ");
                    int valueToDelete = scan.nextInt();
                    System.out.println("Number " + valueToDelete + " has been deleted.");
                    root = binaryTree.delete(root, valueToDelete);
                    break;
                case 3:
                    System.out.print("\nEnter a value to find in the binary tree:");
                    int valueToFind = scan.nextInt();
                    boolean found = binaryTree.find(root, valueToFind);
            
                    if (found) {
                        System.out.println(valueToFind + " is found in the binary tree.");
                    } else {
                        System.out.println(valueToFind + " is not found in the binary tree.");
                    }
                    break;
                case 4:
                    System.out.println("\n[1] InOrder Traversal\n[2] PreOrder Traversal\n[3] PostOrder Traversal\n[4] Back");
                    System.out.print("Option: ");
                    option = scan.nextInt();

                    switch (option) {
                        case 1:
                            System.out.println("In-order traversal of the binary tree:");
                            binaryTree.inorder(root);
                            break;
                        case 2:
                            System.out.println("\nPre-order traversal of the binary tree:");
                            binaryTree.preorder(root);
                            break;
                        case 3:
                            System.out.println("\nPost-order traversal of the binary tree:");
                            binaryTree.postorder(root);
                            break;
                        case 4:
                            break;
                        default:
                            System.out.println("Invalid Choice");
                            break;
                    }
                    break;
                case 5:
                    System.out.println("Good Bye");
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;

            } 
        } while (option != 5);

        scan.close();
    }
}
