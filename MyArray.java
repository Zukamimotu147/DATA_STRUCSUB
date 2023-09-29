package DATASTRUCsubject;

import java.util.*;

public class MyArray {

    static int[] arr;
    static int count;
    static int size;
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        
        System.out.println("=========ARRAY MENU=========");
        System.out.print("Create Array size: ");
        
        size = scan.nextInt();
        arr = new int[size]; // create size of array
        
        System.out.println("The size of array is: " + arr.length);

        int option;
        do {

            System.out.println("(Menu)\n [1] Add Element\n [2] View Array\n [3] Remove Element \n [4] Search Element\n [5] Sort Array\n [6] Edit Element\n [7] Count Element\n [8] Exit");
            System.out.print("Option: " );
            option = scan.nextInt();

            switch(option) {
                case 1:
                    add();
                    break;
                case 2:
                    view();
                    break;
                case 3:
                    remove();
                    break;
                case 4:
                    search();
                    break;
                case 5:
                    sort();
                    break;
                case 6:
                    edit();
                    break;
                case 7:
                    count();
                    break;
                default:
                    System.out.println("Good Bye!");
                    break;
            }
        } while (option != 8);
    }



    static void add() {
        if(count < arr.length) {
            int remaining = arr.length - count;
            System.out.println("Enter the elements up to " + remaining);

            for(int i = 0; i < size; i++) {
                System.out.print("Enter Element (enter \"Stop\" to stop adding): ");
                int input = scan.nextInt();
                
                if (input == -1) {
                    break;
                } else if (count > input) {
                    System.out.println("Cannot add more element");
                    break;
                }

                arr[count] = input;
                count++;
            }
            System.out.println("Elements added successfully."); 
        } else {
            System.out.println("Array is full.");
        } 
    }

    static void view() {
        if(count > 0) {
            System.out.println("Array elements: " + Arrays.toString(Arrays.copyOf(arr, count)));
        } else {
            System.out.println("Array is empty.");
        }
    }

    static void remove() {
        if(count > 0) {
            System.out.print("Enter the index to remove element (0 to " + (count - 1) + "): ");
            int indexRemove = scan.nextInt();
            if(indexRemove >= 0 && indexRemove < count) {
                for (int i = indexRemove; i < count - 1; i++) {
                    arr[i] = arr[i + 1];
                }
                count--;
                System.out.println("Element removed successfully.");
            } else {
                System.out.println("Invalid index.");
            }
        } else {
            System.out.println("Array is empty.");
        }
    }

    static void search() {
        if (count > 0) {
            System.out.print("Enter an element to search: ");
            int elementSearch = scan.nextInt();

            int dups = 0;
            for (int i = 0; i < count; i++) {
                if(arr[i] == elementSearch) {
                    dups++;
                }
            }

            if (dups == 0) {
                System.out.println("Invalid element.");
                return;
            }

            for (int i = 0; i < count; i++) {
                if(arr[i] == elementSearch) {
                    System.out.println("The element is located at " + i);
                }
            }

        } else {
            System.out.println("Array is empty.");
        }
    }

    static void sort() {
        if (count > 0) {
            System.out.println("Choose sorting order");
            System.out.println("[1] Ascending\n[2] Descending");
            System.out.print("Option: ");

            int sortOption = scan.nextInt();
            switch(sortOption) {
                case 1:
                    for (int i = 0; i < count; i++) {
                        for(int j = 1; j < count - i; j++) {
                            if(arr[j] < arr[j - 1]) {
                                int temp = arr[j];
                                arr[j] = arr[j - 1];
                                arr[j - 1] = temp;
                            }
                        }  
                    }
                    System.out.println("Array sorted in ascending order.");
                    break;
                case 2:
                    for (int i = 0; i < count; i++) {
                        for(int j = 1; j < count - i; j++) {
                            if(arr[j] > arr[j - 1]) {
                                int temp = arr[j];
                                arr[j] = arr[j - 1];
                                arr[j - 1] = temp;
                            }
                        }  
                    } 
                    System.out.println("Array sorted in descending order.");
                    break;   
            }
        } else {
            System.out.println("Array is empty.");
        }
    }

    static void edit() {
        if(count > 0) {
            System.out.print("Enter the index to edit element (0 to " + (count - 1) + "): ");
            int indexEdit = scan.nextInt();
            if(indexEdit >= 0 && indexEdit < count) {
                System.out.print("Enter new element: ");
                int newElement = scan.nextInt();
                arr[indexEdit] = newElement;
                System.out.println("Element edited successfully.");
            } else {
                System.out.println("Invalid Index.");
            }
        } else {
            System.out.println("Array is empty.");
        }
    }

    static int count() {
        System.out.println("The count is: " + count);
        return count;
    }

}