public class Ticket {
    private int row;
    private int seat;
    private double price;
    private String ticket_ID;
    private String email;
    private Person person;

    /**
     * -------------------------Task 10-------------------------
     * Constructor creates a new ticket object
     *
     * @param Ticket_ID Contains the ticket ID
     * @param Row       Contains the row number
     * @param Seat      Contains the seat number
     * @param Price     Contains the price of the ticket
     * @param email     Contains the email of the person
     * @param Person    Contains the person object
     */
    public Ticket(String Ticket_ID, int Row, int Seat, double Price, String email, Person Person) {
        this.ticket_ID = Ticket_ID;
        this.row = Row;
        this.seat = Seat;
        this.price = Price;
        this.email = email;
        this.person = Person;
    }


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getTicket_ID() {
        return ticket_ID;
    }

    public void setTicket_ID(String ticket_ID) {
        this.ticket_ID = ticket_ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * -------------------------Task 11-------------------------
     * This method is called to print the ticket details of a person as soon as he purchases a ticket.
     *
     * @return String of all the details of the person and ticket
     */
    public String print() {
        return "Ticket ID: " + ticket_ID + ", " +
                "Name: " + person.getName() + ", " +
                "Surname: " + person.getSurname() + ", " +
                "Email: " + person.getEmail() + ", " +
                "row:" + row + ", " +
                "seat:" + seat + ", " +
                "price:" + price;
    }

    @Override
    public String toString() {
        return "Ticket ID: " + ticket_ID + ",  " +
                "Name: " + person.getName() + ", " +
                "Surname: " + person.getSurname() + ",  " +
                "Email: " + person.getEmail() + ",  " +
                "row:" + row + ", " +
                "seat:" + seat + ", " +
                "price:" + price;
    }
}