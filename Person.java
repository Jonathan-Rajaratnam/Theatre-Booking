public class Person {

    private String name;
    private String surname;
    private String email;

    /**
     * -------------------------Task 9-------------------------
     * Constructor creates a new person object
     *
     * @param name    Name of the person
     * @param surname Surname of the person
     * @param email   Email of the person
     */
    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    /*
    All setters and getters have been created for the variables
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}

