import java.util.*;
import java.util.regex.*;


public class Validators {

    /**
     * This method checks if the email entered is of the format username@abc.com
     *
     * @return email if the email is valid
     */
    public static String CheckEmail() {//String email) {
        boolean check;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter email address: ");
        String email = input.next();
        email = email.toLowerCase();
        String emailCheck = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailCheck);
        check = pattern.matcher(email).matches();
        if (!check) {
            System.err.println("Email is invalid\n");
            System.out.println();
            return CheckEmail();
        }
        return email;
    }

    public static String Check_FirstNames() {
        boolean check;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first name: ");
        String f_name = input.next();
        f_name = CamelCase(f_name);
        String nameCheck = "^[A-Za-z]{2,29}+$";

        Pattern pattern = Pattern.compile(nameCheck);
        check = pattern.matcher(f_name).matches();
        if (!check) {
            System.err.println("Name is invalid\n");
            System.out.println();
            return Check_FirstNames();
        }
        return f_name;
    }


    public static String Check_LastNames() {
        boolean check;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter surname: ");
        String surname = input.next();
        surname = CamelCase(surname);
        String nameCheck = "^[A-Za-z]{2,29}+$";

        Pattern pattern = Pattern.compile(nameCheck);
        check = pattern.matcher(surname).matches();
        if (!check) {
            System.err.println("Surname is invalid\n");
            System.out.println();
            return Check_LastNames();
        }
        return surname;
    }

    public static String CamelCase(String name) {
        String Letter = name.substring(0, 1).toUpperCase();
        String rest = name.substring(1).toLowerCase();
        return Letter + rest;
    }
}

