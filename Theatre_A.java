import java.util.*;
import java.io.*;


public class Theatre_A {
    public static void main(String[] args) throws IOException {
        //boolean repeat = true;
        System.out.println("Welcome to the New Theatre!");
        Scanner sc = new Scanner(System.in);
        int[] row1 = new int[12];
        int[] row2 = new int[16];
        int[] row3 = new int[20];
        //System.out.println(Arrays.toString(row1));

        loop:
        while (true) {
            System.out.println("\n--------------------------------------");
            System.out.println("Please Select an option:");
            System.out.println("""
                    1) Buy a ticket
                    2) Print Seating Area
                    3) Cancel Ticket
                    4) List available seats
                    5) Save to file
                    6) Load from file
                    7) Print ticket information and total price
                    8) Sort tickets by price
                    0) Quit Program
                    --------------------------------------""");
            System.out.print("Enter your option:");
            String option = sc.next();

            switch (option) {
                case "0":
                    System.out.println("\nQuiting program");
                    break loop;

                case "1":
                    buy_ticket(row1, row2, row3);
                    break;

                case "2":
                    print_seating_area(row1, row2, row3);
                    break;

                case "3":
                    cancel_ticket(row1, row2, row3);
                    break;

                case "4":
                    show_available(row1, row2, row3);
                    break;

                case "5":
                    save(row1, row2, row3);
                    break;

                case "6":
                    load(row1, row2, row3);
                    break;

                default:
                    System.err.println("Invalid option.. Enter a valid option number.\n");
                    break;
            }
        }
    }

    /**
     * This method is used to book a ticket by the user.
     *
     * @param row1 array row1 is used to store the seat info of row 1.
     * @param row2 array row2 is used to store the seat info of row 2.
     * @param row3 array row3 is used to store the seat info of row 3.
     */
    private static void buy_ticket(int[] row1, int[] row2, int[] row3) {
        int row_no;
        boolean start = true;
        boolean repeat = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("\nBUY TICKETS\n");
        while (start) {
            try {
                repeat = true;
                System.out.print("Enter row number: ");
                row_no = sc.nextInt();
                if (row_no == 1 || row_no == 2 || row_no == 3) {

                    if (row_no == 1) {
                        buy_ticket_main(row1, 12);

                    } else if (row_no == 2) {
                        buy_ticket_main(row2, 16);

                    } else {
                        buy_ticket_main(row3, 20);
                    }
                } else {
                    System.out.println();
                    System.err.println("The row you entered is invalid. Please enter a row number from 1-3.");
                    repeat = false;
                    System.out.println();
                }


            } catch (Exception e) {
                System.err.println("The value you entered is not a valid input.");
                sc.next();
            }
            if (repeat) {
                System.out.println();
                System.out.print("Do you want to book another seat?(y/n) ");
                char option = sc.next().charAt(0);
                option = Character.toLowerCase(option);
                if (option == 'y') {
                    System.out.println();
                } else if (option == 'n') {
                    //System.out.println(Ticket.print);
                    start = false;
                } else {
                    System.out.println("Invalid option.. Enter (y/n).\n");
                }
            }
        }
    }


    private static void buy_ticket_main(int[] row, int no_of_seats) {
        int seat_no;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter seat number: ");
        seat_no = input.nextInt();

        if (seat_no > no_of_seats) {
            System.out.println("Sorry..There are only " + no_of_seats + " seats available in this row." +
                    " Choose another seat number.");
            buy_ticket_main(row, no_of_seats);
        } else {
            if (row[seat_no - 1] == 0) {
                row[seat_no - 1] = 1;
                System.out.print("Seat has been booked successfully!");
            } else {
                System.out.print("Sorry this seat is taken. Please choose a different seat.");
                System.out.println();
                buy_ticket_main(row, no_of_seats);
            }
        }
    }

    /**
     * This method is used to cancel a booked ticket.
     *
     * @param row1 array row1 is used to store the seat info of row 1.
     * @param row2 array row2 is used to store the seat info of row 2.
     * @param row3 array row3 is used to store the seat info of row 3.
     */
    private static void cancel_ticket(int[] row1, int[] row2, int[] row3) {
        int row_no;
        int seat_no;
        Scanner sc = new Scanner(System.in);
        System.out.println("CANCEL TICKETS\n");
        while (true) {
            try {
                System.out.print("\nEnter row number: ");
                row_no = sc.nextInt();
                if (row_no == 1 || row_no == 2 || row_no == 3) {
                    System.out.print("Enter seat number: ");
                    seat_no = sc.nextInt();
                    if (row_no == 1)
                        cancel_seat(row1, seat_no, 12);
                    else if (row_no == 2)
                        cancel_seat(row2, seat_no, 16);
                    else
                        cancel_seat(row3, seat_no, 20);
                } else
                    System.out.println("The row you entered is incorrect. Enter a row number between 1 and 3.");
            } catch (Exception e) {
                System.err.println("Please enter a valid row/seat number.");
                sc.next();
            }
            System.out.print("Do you want to cancel another ticket? (y/n): ");
            char repeat = sc.next().charAt(0);
            if (Character.toLowerCase(repeat) == 'y') {
                System.out.println();
            } else if (Character.toLowerCase(repeat) == 'n') {
                break;
            } else
                System.err.println("Invalid response. Please enter y/n...\n");

        }
    }


    private static void cancel_seat(int[] row, int seat_no, int no_of_seats) {
        if (seat_no < no_of_seats) {
            if (row[seat_no - 1] == 1) {
                row[seat_no - 1] = 0;
                System.out.println("The ticket has been cancelled");
            } else
                System.out.println("Currently selected seat is already free.");
        } else {
            System.out.println("The seat you currently selected is incorrect. Please try again.");
        }
    }

    private static void print_seating_area(int[] row1, int[] row2, int[] row3) {
        System.out.println("""
                \nTHIS IS THE SEATING LAYOUT

                       ***********
                       *  STAGE  *
                       ***********
                       """);
        print_seats(row1, 12, 6, 5);
        print_seats(row2, 16, 4, 7);
        print_seats(row3, 20, 2, 9);
        System.out.println();
    }

    /**
     * This method is used to print the seating layout of the theatre.
     *
     * @param row_no       This array stores the seat info of a particular row.
     * @param no_of_seats  This variable stores the number of seats in a particular row.
     * @param space_before This variable stores the number of spaces before the first seat in a row.
     * @param space_after  This variable stores the number of spaces after the last seat in a row.
     */
    private static void print_seats(int[] row_no, int no_of_seats, int space_before, int space_after) {
        // This method prints the proper seating layout of the theatre with spaces between seats
        for (int i = 0; i < space_before; i++)
            System.out.print(" ");
        for (int seats_in_row = 0; seats_in_row < no_of_seats; seats_in_row++) {
            if (seats_in_row == space_after) {
                if (row_no[seats_in_row] == 0)
                    System.out.print("O");
                else
                    System.out.print("X");
                System.out.print(" ");
            } else {
                if (row_no[seats_in_row] == 0)
                    System.out.print("O");
                else
                    System.out.print("X");
            }
        }
        System.out.println();
    }

    /**
     * This method is used to show the available seats in the theatre.
     *
     * @param row1 array row1 is used to store the seat info of row 1.
     * @param row2 array row2 is used to store the seat info of row 2.
     * @param row3 array row3 is used to store the seat info of row 3.
     */
    private static void show_available(int[] row1, int[] row2, int[] row3) {
        print_available(row1, 1, 12);
        print_available(row2, 2, 16);
        print_available(row3, 3, 20);

    }

    /**
     * This method is used to print the available seats in a particular row.
     *
     * @param row         This array stores the seat info of a particular row.
     * @param row_no      This variable stores the row number.
     * @param no_of_seats This variable stores the number of seats in a particular row.
     */
    private static void print_available(int[] row, int row_no, int no_of_seats) {
        System.out.print("Row " + row_no + ": ");
        for (int i = 1; i <= no_of_seats; i++) {
            if (row[i - 1] == 0) {
                try {
                    if (row[i] == 0) {
                        System.out.print(i + ", ");
                    }
                } catch (Exception e) {
                    System.out.print(i + ".");
                }
            } else if (i == no_of_seats)
                System.out.print(i - 1 + ".");
        }
        System.out.println();
    }


    /**
     * This method is used to write the seating information and booked seats to a file.
     *
     * @param row1 contains the seats booked in row 1
     * @param row2 contains the seats booked in row 2
     * @param row3 contains the seats booked in row 3
     */
    private static void save(int[] row1, int[] row2, int[] row3) {
        try {
            FileWriter Write = new FileWriter("row_info.txt");
            BufferedWriter BWrite = new BufferedWriter(Write);
            StringBuilder row1_csv = new StringBuilder();   //the use of StringBuilder here is more appropriate
            StringBuilder row2_csv = new StringBuilder();  //as it is more efficient than using a += operator to append
            StringBuilder row3_csv = new StringBuilder(); // characters to a string,  as that creates a new string
            // each time the loop iterates which could create performance issues...


            for (int j : row1)
                row1_csv.append(j).append(","); //
            BWrite.write(row1_csv.toString());
            BWrite.newLine();

            for (int i : row2)
                row2_csv.append(i).append(",");
            BWrite.write(row2_csv.toString());
            BWrite.newLine();

            for (int i : row3)
                row3_csv.append(i).append(",");
            BWrite.write(row3_csv.toString());
            BWrite.close();

            System.out.println("The seating data has been successfully saved!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while saving to file...");
        }
    }

    private static void load(int[] row1, int[] row2, int[] row3) throws IOException {
        FileReader Reader = new FileReader("row_info.txt");
        BufferedReader bufferRead = new BufferedReader(Reader);
        String line = bufferRead.readLine();
        String[] arr;
        String[] arr2;
        String[] arr3;

        try {
            arr = line.split(",");
            line = bufferRead.readLine();
            arr2 = line.split(",");
            line = bufferRead.readLine();
            arr3 = line.split(",");
            bufferRead.close();
            Reader.close();

            for (int i = 0; i < arr.length; i++) {
                row1[i] = Integer.parseInt(arr[i]);
            }

            for (int i = 0; i < arr2.length; i++) {
                row2[i] = Integer.parseInt(arr2[i]);
            }

            for (int i = 0; i < arr3.length; i++) {
                row3[i] = Integer.parseInt(arr3[i]);
            }

        } catch (Exception e) {
            System.out.println("An error occurred while reading from file...");
        }
    }
}
