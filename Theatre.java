/**
 * This class is used to create a theatre object that will be used to book tickets.
 * The class contains the main method and all the methods that are used to book tickets and carry put other functions.
 * The class also contains the arrays that will be used to store the seat info.
 * The class also contains the arraylist that will be used to store the person objects.
 * The class also contains the arraylist that will be used to store the ticket objects.
 *
 * @author Jonathan Rajaratnam
 * @version 1.0
 * @since 2023-02-15
 * @last updated 2023-06-06
 */


import java.util.*;
import java.io.*;


public class Theatre {
    static ArrayList<Person> Persons = new ArrayList<>();
    static ArrayList<Ticket> Tickets_Sold = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //boolean repeat = true;
        System.out.println("Welcome to the New Theatre!");
        Scanner sc = new Scanner(System.in);
        /*This is used to create the 3 arrays that will contain the seats */
        int[] row1 = new int[12];
        int[] row2 = new int[16];
        int[] row3 = new int[20];

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
                    cancel_tickets(row1, row2, row3);
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
                case "7":
                    show_ticket_info();
                    break;
                case "8":
                    sort_tickets();
                    break;
                default:
                    System.err.println("Invalid option.. Enter a valid option number.\n");
                    System.out.println();
                    break;
            }
        }
    }


    /**
     * --------------------------------Task 3 & 12-------------------------------
     * This method is used to book a ticket by the user.
     *
     * @param row1 array row1 is used to store the seat info of row 1.
     * @param row2 array row2 is used to store the seat info of row 2.
     * @param row3 array row3 is used to store the seat info of row 3.
     */
    private static void buy_ticket(int[] row1, int[] row2, int[] row3) {
        int count = 1;
        int row_no;
        int seat_no = 0;
        int price;
        int tickets_needed = 0;
        String ticket_ID;
        Scanner input = new Scanner(System.in);
        System.out.println("\nBUY TICKETS\n");
        System.out.println();
        System.out.println("These are the available seats in each row.");
        show_available(row1, row2, row3);

        Person person = create_person();
        String email = person.getEmail();

        tickets_needed = getTicketsNeeded(tickets_needed, input);

        while (count <= tickets_needed) {
            boolean row_check = true;
            boolean seat_check = true;
            boolean price_check = true;

            System.out.println("Ticket " + count);
            while (row_check) {
                try {

                    System.out.print("Enter row number: ");
                    row_no = input.nextInt();
                    if (row_no == 1 || row_no == 2 || row_no == 3) {
                        row_check = false;
                        while (seat_check) {
                            try {
                                System.out.print("\nEnter seat number: ");
                                seat_no = input.nextInt();
                                if (row_no == 1) {
                                    if (buy_ticket_main(row1, seat_no, 12)) {
                                        count++;
                                        seat_check = false;
                                    }
                                } else if (row_no == 2) {
                                    if ((buy_ticket_main(row2, seat_no, 16))) {
                                        count++;
                                        seat_check = false;
                                    }
                                } else {
                                    if (buy_ticket_main(row3, seat_no, 20)) {
                                        count++;
                                        seat_check = false;
                                    }
                                }
                            } catch (InputMismatchException e) {
                                System.err.println("The value you entered is invalid... Please enter a number.");
                                input.next();
                            }
                        }

                        while (price_check) {
                            try {
                                System.out.print("\nEnter ticket price you prefer: ");
                                price = input.nextInt();
                                ticket_ID = person + "-" + row_no + "-" + seat_no;
                                add_tickets(row_no, seat_no, price, ticket_ID, email, person);
                                price_check = false;
                            } catch (InputMismatchException e) {
                                System.err.println("Invalid Value... Please enter a valid price");
                                input.next();
                            }
                        }

                    } else {
                        System.out.println();
                        System.err.println("Invalid row number");
                    }

                } catch (InputMismatchException e) {
                    System.err.println("The value you entered is not a valid input.");
                    System.out.println();
                    input.next();
                }
            }
        }
        System.out.println("\nThese are all the ticket you bought:");
        //Prints all the tickets bought by current customer
        for (int i = Tickets_Sold.size() - tickets_needed; i < Tickets_Sold.size(); i++) {
            System.out.println(Tickets_Sold.get(i));
        }
    }


    /**
     * --------------------------------Task 9-------------------------------
     * This method is used to get person details and create the person object
     *
     * @return person object
     */
    private static Person create_person() {
        System.out.println();
        System.out.println("Please Enter your details...");
        String first_name = Validators.Check_FirstNames();
        String surname = Validators.Check_LastNames();
        String email = Validators.CheckEmail();
        System.out.println();

        //This creates the person object who bought the ticket
        Person person = new Person(first_name, surname, email);
        person.setName(first_name);
        person.setSurname(surname);
        person.setEmail(email);
        Persons.add(person); //This adds the person object to the arraylist Persons
        return person;
    }


    /**
     * This method asks the user how many tickets they need and checks if the input is valid
     *
     * @param tickets_needed number of tickets needed by the user
     * @param input          scanner object
     * @return tickets_needed
     */
    private static int getTicketsNeeded(int tickets_needed, Scanner input) {
        boolean check = true;

        while (check) {
            try {
                System.out.println("How many tickets do you need?");
                tickets_needed = input.nextInt();

                if (tickets_needed > 0 && tickets_needed <= 5) {
                    System.out.println("Are you sure you want to buy " + tickets_needed + " tickets? (Y/N)");
                    char confirm = input.next().charAt(0);

                    if (confirm == 'Y' || confirm == 'y') {
                        check = false;
                    } else if (confirm == 'N' || confirm == 'n') {
                        System.out.println("Please enter the number of tickets you need.");
                    } else {
                        System.err.println("Invalid Value... Please enter Y or N");
                    }
                    //check = false;
                } else if (tickets_needed > 5) {
                    System.out.println("You can only buy a maximum of 5 tickets at a time.");
                    System.out.println();
                } else {
                    System.err.println("Invalid Value... Please enter a number greater than 0");
                    System.out.println();
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid Value... Please enter a number");
                input.next();
            }
        }
        return tickets_needed;
    }


    /**
     * This method creates a ticket object and adds the object to the Tickets arraylist
     *
     * @param row_no    contains the row no that the ticket is booked in
     * @param seat_no   contains the seat the user booked
     * @param price     contains the price of the ticket
     * @param ticket_ID Unique ID identifying the ticket
     * @param email     email address of the person buying the ticket
     * @param person    contains person object
     */
    private static void add_tickets(int row_no, int seat_no, double price, String ticket_ID, String email, Person person) {
        Ticket ticket = new Ticket(ticket_ID, row_no, seat_no, price, email, person); //creates a ticket object
        ticket.setTicket_ID(ticket_ID);
        ticket.setEmail(email);
        ticket.setRow(row_no);
        ticket.setSeat(seat_no);
        ticket.setPrice(price);
        ticket.setPerson(person);
        Tickets_Sold.add(ticket); //adds the ticket object to the Tickets arraylist
        System.out.println(ticket.print()); //prints the currently purchased ticket info
        System.out.println();
    }


    /**
     * This method is called to book the tickets and assign the seats in the theatre
     *
     * @param row         contains the row array of the seat being booked
     * @param seat_no     contains the seat number being booked
     * @param no_of_seats is the number of seats available in each row
     * @return true if the seat has been booked successfully.
     */
    private static boolean buy_ticket_main(int[] row, int seat_no, int no_of_seats) {
        if (seat_no > no_of_seats || seat_no < 1) {
            System.err.println("\nSorry..There are only " + no_of_seats + " seats available in this row." +
                    " Enter another seat number..");
            System.out.println();
            return false;

        } else {
            if (row[seat_no - 1] == 0) {
                row[seat_no - 1] = 1;
                System.out.println("Seat has been booked successfully!");
                return true;
            } else {
                System.out.println("Sorry this seat is taken. Please choose a different seat...");
                System.out.println();
                return false;
            }
        }
    }


    /**
     * ----------------------------Task 5 & 12----------------------------------
     *
     * @param row1 array row1 is used to store the seat info of row 1.
     * @param row2 array row2 is used to store the seat info of row 2.
     * @param row3 array row3 is used to store the seat info of row 3.
     */
    private static void cancel_tickets(int[] row1, int[] row2, int[] row3) {
        boolean flag = true;
        boolean ask_again = true;
        Scanner input = new Scanner(System.in);
        System.out.println("CANCEL TICKETS\n");
        //System.out.println("Enter the email: ");
        String email = Validators.CheckEmail();
        while (flag) {
            System.out.println();
            System.out.println("The tickets booked by you are: ");
            for (Ticket i : Tickets_Sold) {
                if (i.getEmail().equalsIgnoreCase(email)) {
                    System.out.println(i);
                    flag = false;
                }
            }
            if (flag) {
                System.out.println("No tickets booked by this email");
                ask_again = false;
            } else {
                cancel_seats(row1, row2, row3);

            }

            while (ask_again) {
                System.out.println("Do you want to cancel another ticket? (Y/N)");
                char choice = input.next().charAt(0);
                if (choice == 'Y' || choice == 'y') {
                    flag = true;
                    ask_again = false;
                    System.out.println();
                } else if (choice == 'N' || choice == 'n') {
                    ask_again = false;
                } else
                    System.out.println("Invalid input... Enter Y/N");
            }
        }
    }


    /**
     * This method includes the part to cancel a ticket and restore the seat as free after cancellation.
     *
     * @param row1 array row1 is used to store the seat info of row 1.
     * @param row2 array row2 is used to store the seat info of row 2.
     * @param row3 array row3 is used to store the seat info of row 3.
     */
    private static void cancel_seats(int[] row1, int[] row2, int[] row3) {
        int row_no;
        int seat_no;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the ticket ID of the ticket you want to cancel: ");
        String ticket_ID = input.next();
        //An iterator was used to avoid concurrent modification exception as my solution was to remove
        // the list element from the arraylist as soon as the ticket was cancelled and using a for loop this
        // cannot be achieved
        for (Ticket i : Tickets_Sold) {
            if (i.getTicket_ID().equalsIgnoreCase(ticket_ID)) {    //seat_no and row_no were not taken as input
                row_no = i.getRow();                              //as the ticket ID is unique and can be used to
                seat_no = i.getSeat();                           //identify the seat and row number and therefore
                if (row_no == 1) {                               //the user does not have to enter the seat and row.
                    row1[seat_no - 1] = 0;
                } else if (row_no == 2) {
                    row2[seat_no - 1] = 0;
                } else if (row_no == 3) {
                    row3[seat_no - 1] = 0;
                }
                System.out.println("Ticket has been cancelled successfully!");
                Tickets_Sold.remove(i);
            } else {
                System.out.println("Invalid ticket ID");
            }
            break;

        }
    }


    /**
     * ------------------------------Task 4--------------------------------
     * This method is used to call method that prints the seating layout of the theatre.
     *
     * @param row1 This array stores the seat info of row 1.
     * @param row2 This array stores the seat info of row 2.
     * @param row3 This array stores the seat info of row 3.
     */
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
                    if (row[i - 1] == 0) {
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
     * -----------------------------Task 7-------------------------------
     * This method is used to save the seat info of the theatre to a file.
     * After saving the seat info to a file it will call save tickets and save persons to store
     * the ticket info and person info to a file.
     *
     * @param row1 This array contains the seat info of row 1.
     * @param row2 This array contains the seat info of row 2.
     * @param row3 This array contains the seat info of row 3.
     */
    private static void save(int[] row1, int[] row2, int[] row3) {
        try {
            FileWriter write_row = new FileWriter("row_info.txt");
            BufferedWriter buff_write_row = new BufferedWriter(write_row); /*Buffered-writer is better in this case as
            there is a need to write multiple arrays to a file thus is increases efficiency by reducing the number of
            writes made to a file and
            */

            StringBuilder row1_string = new StringBuilder();
            StringBuilder row2_string = new StringBuilder();
            StringBuilder row3_string = new StringBuilder();
            /* The use of StringBuilder here is more appropriate  as it is more efficient
            than using a += operator to append characters to a string,  as that creates a new string
            each time the loop iterates which could create performance issues...*/


            for (int j : row1)
                row1_string.append(j).append(","); //
            buff_write_row.write(row1_string.toString());
            buff_write_row.newLine();

            for (int i : row2)
                row2_string.append(i).append(",");
            buff_write_row.write(row2_string.toString());
            buff_write_row.newLine();

            for (int i : row3)
                row3_string.append(i).append(",");
            buff_write_row.write(row3_string.toString());
            buff_write_row.close();

            save_person();

            System.out.println("The seating data has been successfully saved!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while saving to file...");
        }
    }


    /**
     * This method is used to write the ticket information to a file.
     */
    private static void save_tickets_info() {
        try {
            FileWriter write_row = new FileWriter("ticket_info.txt");
            BufferedWriter buff_ticket_write = new BufferedWriter(write_row);
            for (Ticket ticket : Tickets_Sold) {
                buff_ticket_write.write(ticket.getTicket_ID() + "," + (ticket.getRow()) + ","
                        + (ticket.getSeat()) + "," +
                        (ticket.getPrice()) + "," + ticket.getEmail() + "," + (ticket.getPerson()));
                buff_ticket_write.newLine();
            }
            buff_ticket_write.close();
            System.out.println("The ticket data has been successfully saved!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while saving to file...");
        }
    }


    private static void save_person() {
        try {
            FileWriter write_person = new FileWriter("person_info.txt");
            BufferedWriter buff_person_write = new BufferedWriter(write_person);
            for (Person person : Persons) {
                buff_person_write.write(person.getName() + "," + person.getSurname() + "," + person.getEmail());
                buff_person_write.newLine();
            }
            buff_person_write.close();
            save_tickets_info();
            System.out.println("The person data has been successfully saved!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while saving to file...");
        }

    }


    /**
     * ---------------------------Task 8-----------------------------------
     * This method reads data from the file and loads the bought tickets into the arrays
     *
     * @param row1 array row1 is used to store the seat info of row 1.
     * @param row2 array row2 is used to store the seat info of row 2.
     * @param row3 array row3 is used to store the seat info of row 3.
     * @throws IOException if the file is not found.
     */
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
            System.out.println("The seating data has been successfully loaded!");
            load_person();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("An error occurred while reading seating info from file...");
        }
    }


    /**
     * This method is used to load the person info from a file.
     *
     * @throws IOException if the file is not found or if the data could not be saved.
     */
    private static void load_person() throws IOException {
        FileReader Reader = new FileReader("person_info.txt");
        BufferedReader bufferRead = new BufferedReader(Reader);
        String line = bufferRead.readLine();
        String[] arr;
        try {
            while (line != null) {
                arr = line.split(",");
                Person person = new Person(arr[0], arr[1], arr[2]);
                Persons.add(person);
                line = bufferRead.readLine();
            }
            System.out.println("Person info has successfully been loaded!");
            load_tickets();
            bufferRead.close();
            Reader.close();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("An error occurred while reading person info from file...");
        }
    }


    /**
     * This method is used to load the ticket info from a file.
     *
     * @throws IOException if the file is not found or if the data could not be saved.
     */
    private static void load_tickets() throws IOException {
        //Scanner input = new Scanner(System.in);
        FileReader Reader = new FileReader("ticket_info.txt");
        BufferedReader bufferRead = new BufferedReader(Reader);
        String line = bufferRead.readLine();
        String[] arr;
        try {
            while (line != null && !(line.equals(""))) {
                arr = line.split(",");
                for (Person i : Persons) {
                    if (arr[4].equals(i.getEmail())) {
                        Ticket ticket = new Ticket((arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]),
                                Double.parseDouble(arr[3]), arr[4], i);
                        ticket.setTicket_ID(arr[0]);
                        ticket.setRow(Integer.parseInt(arr[1]));
                        ticket.setSeat(Integer.parseInt(arr[2]));
                        ticket.setPrice(Double.parseDouble(arr[3]));
                        ticket.setEmail(arr[4]);
                        ticket.setPerson(i);
                        Tickets_Sold.add(ticket);
                    }
                }
                line = bufferRead.readLine();
            }
            System.out.println("Ticket info has successfully been loaded!");
            bufferRead.close();
            Reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while reading ticket info from file...");
        }
    }


    /**
     * --------------------------------Task 13------------------------------------
     * This method finds the total value of the tickets bought by a person and prints all the ticket
     * info with the total
     */
    private static void show_ticket_info() {
        String email;
        for (Person i : Persons) {
            email = i.getEmail();
            double total_tkts_price = 0;
            boolean flag = false;
            if (Tickets_Sold.isEmpty()) {
                System.out.println("No tickets have been sold yet!");
                break;
            }else{
                System.out.println("Name: " + i.getName() + "\t" + "Surname: " + i.getSurname() + "\t" + "Email: " + i.getEmail());
                for (Ticket j : Tickets_Sold) {
                    if (j.getEmail().equals(email)) {
                        total_tkts_price += j.getPrice();
                        flag = true;
                        System.out.printf("%-32s", "Ticket ID: " + j.getTicket_ID() + "\t " + "Row: " + j.getRow() + "\t " + "Seat: " + j.getSeat() +
                                "\t " + "Price: " + j.getPrice() + "\n");
                    }
                }
                if (!flag) {
                    continue;
                } else
                    System.out.println("Total value of tickets bought by " + i.getName() + " " + i.getSurname() + " is " + total_tkts_price);

                System.out.println();
            }
        }
    }


    /**
     * ----------------------------------Task 14------------------------------------
     * * This method sorts the tickets using a selection sort algorithm based on the price in ascending order.
     */
    private static void sort_tickets() {
        System.out.println("Tickets sorted by price");
        int minIndex;
        Ticket temp;
        for (int tickets = 0; tickets < Tickets_Sold.size(); tickets++) {
            minIndex = tickets;
            for (int ticket = tickets + 1; ticket < Tickets_Sold.size(); ticket++) {
                if (Tickets_Sold.get(ticket).getPrice() < Tickets_Sold.get(minIndex).getPrice()) {
                    minIndex = ticket;
                }
                temp = Tickets_Sold.get(tickets);
                Tickets_Sold.set(tickets, Tickets_Sold.get(minIndex));
                Tickets_Sold.set(minIndex, temp);
            }
        }
        for (Ticket i : Tickets_Sold) {
            System.out.println(i);
        }
    }

}
