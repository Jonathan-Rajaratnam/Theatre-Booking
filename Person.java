public class Person {
    private String name;
    private String surname;
    private String email;

    public Person(String name, String Surname, String email) {
        this.name = name;
        this.surname = Surname;
        this.email = email;
        System.out.println(name + " " + surname + " " + email);
    }
}
