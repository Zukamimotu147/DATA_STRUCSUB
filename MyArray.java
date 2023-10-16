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
                case 8:
                    System.out.println("Good Bye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (option != 8);
    }



    static void add() {
        if (count < arr.length) {
            int remaining = arr.length - count;
            System.out.println("Enter the elements up to " + remaining);
    
            for (int i = 0; i <= size; i++) {
                // This will check if the user entered the same size in the resize().
                if (size == count) {
                    return;
                }
    
                try {
                    System.out.print("Enter Element: ");
                    int input = scan.nextInt();
                    arr[count] = input;
                    count++;
    
                    // Ask the user to add another value after entering the element first.
                    System.out.println("\nDo you want to add another value?");
                    System.out.println("[1] Yes\n[2] No");
                    System.out.print("Option: ");
    
                    int option = scan.nextInt();
    
                    if (option != 1) {
                        return;
                    }
    
                    if (count == arr.length) {
                        while (true) {
                            System.out.println("====================");
                            System.out.println("The array is full.");
                            System.out.println("====================");
                            System.out.println("Current size is " + count);
                            System.out.println("Do you want to resize the array?");
                            System.out.println("[1] Yes\n[2] No");
                            System.out.print("Option: ");
    
                            String resizeOption = scan.next();
    
                            if (resizeOption.equalsIgnoreCase("1")) {
                                resize();
                                break;
                            } else if (resizeOption.equalsIgnoreCase("2")) {
                                return;
                            } else {
                                System.out.println("Invalid choice.");
                            }
                            return;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("============================================");
                    System.out.println("Invalid input. Please enter a valid number.");
                    System.out.println("============================================");
                    scan.next(); 
                }
            }
            System.out.println("Elements added successfully."); 
        } else {
            System.out.println("===============");
            System.out.println("Array is full.");
            System.out.println("===============");

            while (true) {
                System.out.println("\nCurrent size is " + size);
                System.out.println("Do you want to resize the array?");
                System.out.println("[1] Yes\n[2] No");
                System.out.print("Option: ");

                String resizeOption = scan.next();
                            
                if(resizeOption.equalsIgnoreCase("1")) {
                    resize();
                    break;
                } else if (resizeOption.equalsIgnoreCase("2")) {
                    return;
                } else {
                    System.out.println("Invalid choice.");
                }
                
            }
            
        } 
    }

    static void resize() {

        try {

            System.out.print("Enter new size for the array: ");
            int newSize = scan.nextInt();
            int[] newArr = new int[newSize];

            for (int i = 0; i < count; i++) {
                if (newSize < size) {
                    System.out.println("=========================================");
                    System.out.println("Cannot input less than the current size.");
                    System.out.println("=========================================");
                    return;
                } else {
                    newArr[i] = arr[i];
                }
                
            }

            arr = newArr;
            size = newSize;

            if (size != count) {
                System.out.println("=============================================");
                System.out.println("Array has been resized successfully to size " + newSize);
                System.out.println("=============================================");
            } else {
                System.out.println("=========================================================");
                System.out.println("You have entered the same array size. Please try again.");
                System.out.println("=========================================================");
                return;
            }

        } catch (Exception e) {
            System.out.println("============================================");
            System.out.println("Invalid input. Please enter a valid number.");
            System.out.println("============================================");
            scan.next();

        }
    }

    static void view() {
        if(count > 0) {
            System.out.println("Array elements: " + Arrays.toString(Arrays.copyOf(arr, count)));
        } else {
            System.out.println("===============");
            System.out.println("Array is empty.");
            System.out.println("===============");
        }
    }

    static void remove() {
        if(count > 0) {
            try {
            
                System.out.print("Enter the index to remove element (0 to " + (count - 1) + "): ");
                int indexRemove = scan.nextInt();
                if(indexRemove >= 0 && indexRemove < count) {
                    for (int i = indexRemove; i < count; i++) {
                        if (indexRemove == i) {
                            arr[indexRemove] = 0;
                        }
                    }
                    size--;
                    System.out.println("Element removed successfully.");
                } else {
                    System.out.println("Invalid index.");
                }

            } catch (Exception e) {
                System.out.println("============================================");
                System.out.println("Invalid input. Please enter a valid number.");
                System.out.println("============================================");
                scan.next(); 
            }
            
        } else {
            System.out.println("===============");
            System.out.println("Array is empty.");
            System.out.println("===============");
        }
    }

    static void search() {
        if (count > 0) {

            try {

                System.out.print("Enter an element to search: ");
                int elementSearch = scan.nextInt();

                int temp = 0;
                for (int i = 0; i < count; i++) {
                    if(arr[i] == elementSearch) {
                        temp++;
                    }
                }

                if (temp == 0) {
                    System.out.println("==================");
                    System.out.println("Invalid element.");
                    System.out.println("==================");
                    return;
                }

                for (int i = 0; i < count; i++) {
                    if(arr[i] == elementSearch) {
                        System.out.println("The element is located at index " + i);
                    }
                }

            } catch (Exception e) {
                System.out.println("============================================");
                System.out.println("Invalid input. Please enter a valid number.");
                System.out.println("============================================");
                scan.next();
            }
        } else {
            System.out.println("===============");
            System.out.println("Array is empty.");
            System.out.println("===============");
        }
    }

    static void sort() {
        if (count > 0) {

            try {

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
                    default:
                        System.out.println("Invalid Input.");
                }

            } catch (Exception e) {
                System.out.println("============================================");
                System.out.println("Invalid input. Please enter a valid number.");
                System.out.println("============================================");
                scan.next();
            }
        } else {
            System.out.println("===============");
            System.out.println("Array is empty.");
            System.out.println("===============");
        }
    }

    static void edit() {
        if(count > 0) {

            try {

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

            } catch (Exception e) {
                System.out.println("============================================");
                System.out.println("Invalid input. Please enter a valid number.");
                System.out.println("============================================");
                scan.next();
            }
        } else {
            System.out.println("===============");
            System.out.println("Array is empty.");
            System.out.println("===============");
        }
    }

    static int count() {
        System.out.println("\nThe count is: " + count);
        return count;
    }
}