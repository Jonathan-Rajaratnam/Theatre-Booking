public class Ticket {
    private int row;
    private int seat;
    private int price;
    private Person person;

    public Ticket(int Row, int Seat, int Price, Person Person) {
        this.row = Row;
        this.seat = Seat;
        this.price = Price;
        this.person = Person;


    }

    private void printTicket() {
        System.out.println("Ticket: " + row + seat + price + person);
    }
}