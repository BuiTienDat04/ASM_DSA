package com.mycompany.asm;

import java.util.ArrayList;
import java.util.Scanner;

public class StdMgmt {
    static ArrayList<Std> stList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean run = true;

        while (run) {
            System.out.println("Select function:");
            System.out.println("1. Add Students");
            System.out.println("2. Edit Students");
            System.out.println("3. Delete Students");
            System.out.println("4. Sort students");
            System.out.println("5. Search students");
            System.out.println("6. View student list");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addSt(sc);
                    break;
                case 2:
                    editSt(sc);
                    break;
                case 3:
                    delSt(sc);
                    break;
                case 4:
                    sortSt();
                    break;
                case 5:
                    searchSt(sc);
                    break;
                case 6:
                    dispSt();
                    break;
                case 0:
                    run = false;
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }

        sc.close();
    }

    private static void addSt(Scanner sc) {
        int num = 0;
        do {
            System.out.print("Enter the number of students: ");
            String input = sc.nextLine();

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter the number of students.");
                continue;
            }

            try {
                num = Integer.parseInt(input);
                if (num <= 0) {
                    System.out.println("The number of students must be greater than zero. Please enter again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number of students.");
            }
        } while (num <= 0);

        for (int i = 0; i < num; i++) {
            System.out.println("Enter info for student " + (i + 1) + ":");

            String id, n;
            double s;

            do {
                System.out.print("ID: ");
                id = sc.nextLine();
                if (id.isEmpty()) {
                    System.out.println("ID cannot be empty. Please enter again.");
                } else if (isDuplicateId(id)) {
                    System.out.println("This ID already exists. Please enter a different ID.");
                    id = ""; // Reset ID to trigger the loop
                }
            } while (id.isEmpty());

            do {
                System.out.print("Name: ");
                n = sc.nextLine();
                if (n.isEmpty()) {
                    System.out.println("Name cannot be empty. Please enter again.");
                }
            } while (n.isEmpty());

            while (true) {
                System.out.print("Score (0-10): ");
                String input = sc.nextLine();

                if (input.isEmpty()) {
                    System.out.println("Score cannot be empty. Please enter again.");
                    continue;
                }

                try {
                    s = Double.parseDouble(input);

                    if (s < 0 || s > 10) {
                        System.out.println("Score must be between 0 and 10. Please enter again.");
                        continue;
                    }

                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid score. Please enter a number.");
                }
            }

            stList.add(new Std(id, n, s));
            System.out.println("Student added successfully!");
        }
    }

    private static boolean isDuplicateId(String id) {
        for (Std st : stList) {
            if (st.getId().equals(id)) {
                return true; 
            }
        }
        return false; 
    }

    private static void editSt(Scanner sc) {
    System.out.print("Enter the student ID to edit: ");
    String id = sc.nextLine();

    for (Std st : stList) {
        if (st.getId().equals(id)) {
            String newN;
            double newS;

            // Update name
            do {
                System.out.print("New name: ");
                newN = sc.nextLine();
                if (newN.isEmpty()) {
                    System.out.println("Name cannot be empty. Please enter again.");
                }
            } while (newN.isEmpty());

            // Update score
            while (true) {
                System.out.print("New score (0-10): ");
                String input = sc.nextLine();
                if (input.isEmpty()) {
                    System.out.println("Score cannot be empty. Please enter again.");
                    continue;
                }

                try {
                    newS = Double.parseDouble(input);
                    if (newS >= 0 && newS <= 10) {
                        break; // Score is valid, exit loop
                    } else {
                        System.out.println("Score must be between 0 and 10. Please enter again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid score. Please enter a valid number.");
                }
            }

            st.setN(newN); // Use setter to update name
            st.setS(newS); // Use setter to update score
            System.out.println("Student edited successfully!");
            return;
        }
    }

    System.out.println("No student found with ID: " + id);
}

        private static void delSt(Scanner sc) {
            System.out.print("Enter the student ID to delete: ");
            String id = sc.nextLine();
            for (Std st : stList) {
                if (st.getId().equals(id)) {
                    stList.remove(st);
                    System.out.println("Student deleted successfully!");
                    return;
                }
            }
            System.out.println("No student found with ID: " + id);
        }

    private static void sortSt() {
        quickSort(0, stList.size() - 1);
//        mergeSort(0, stList.size() - 1);
        System.out.println("Sorted student list by score.");
    }

    static void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    private static int medianOfThree(int low, int high) {
        int mid = low + (high - low) / 2;

        if (stList.get(low).getS() > stList.get(mid).getS()) {
            swap(low, mid);
        }
        if (stList.get(low).getS() > stList.get(high).getS()) {
            swap(low, high);
        }
        if (stList.get(mid).getS() > stList.get(high).getS()) {
            swap(mid, high);
        }

        swap(mid, high);
        return high;
    }

    private static int partition(int low, int high) {
        int pivotIndex = medianOfThree(low, high);
        double pivot = stList.get(pivotIndex).getS();
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (stList.get(j).getS() > pivot) {
                i++;
                swap(i, j);
            }
        }

        swap(i + 1, high);
        return i + 1;
    }

    private static void swap(int i, int j) {
        Std temp = stList.get(i);
        stList.set(i, stList.get(j));
        stList.set(j, temp);
    }
    
    private static void mergeSort(int left, int right) {
    if (left < right) {
        int mid = left + (right - left) / 2;

        // Divide the list into two halves
        mergeSort(left, mid);
        mergeSort(mid + 1, right);

        // Merge the sorted halves
        merge(left, mid, right);
    }
}

    private static void merge(int left, int mid, int right) {
        // Sizes of the two subarrays
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays
        ArrayList<Std> leftArray = new ArrayList<>(n1);
        ArrayList<Std> rightArray = new ArrayList<>(n2);

        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++) {
            leftArray.add(stList.get(left + i));
        }
        for (int j = 0; j < n2; j++) {
            rightArray.add(stList.get(mid + 1 + j));
        }

        // Merge the two subarrays
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray.get(i).getS() <= rightArray.get(j).getS()) {
                stList.set(k, leftArray.get(i));
                i++;
            } else {
                stList.set(k, rightArray.get(j));
                j++;
            }
            k++;
        }

        // Copy the remaining elements of the left array
        while (i < n1) {
            stList.set(k, leftArray.get(i));
            i++;
            k++;
        }

        // Copy the remaining elements of the right array
        while (j < n2) {
            stList.set(k, rightArray.get(j));
            j++;
            k++;
        }
    }

    private static void searchSt(Scanner sc) {
        System.out.print("Enter the student ID to search: ");
        String id = sc.nextLine();
        for (Std st : stList) {
            if (st.getId().equals(id)) {
                System.out.println("Student found: " + st);
                return;
            }
        }
        System.out.println("No student found with ID: " + id);
    }

    private static void dispSt() {
        if (stList.isEmpty()) {
            System.out.println("No students available.");
        } else {
            System.out.println("List of students:");
            for (Std st : stList) {
                System.out.println(st);
            }
        }
    }
}
