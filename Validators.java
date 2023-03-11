import java.util.*;
import java.util.regex.*;


public class Validators {
//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        System.out.print("Enter email address: ");
//        String email = input.next();
//        if (!checkEmail(email)) {
//            System.out.println("Email is invalid");
//            main(null);
//        }
//    }


    /**
     * This method checks if the email entered is of the format username@abc.com
     *
     * @return email if the email is valid
     */
    public String CheckEmail() {//String email) {
        boolean a;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter email address: ");
        String email = input.next();
        String emailCheck = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailCheck);
        if (email == null) {
            System.out.println("Email is invalid");
            //return false;
        } else {
            a = pattern.matcher(email).matches();
            if (!a) {
                System.err.println("Email is invalid");
                System.out.println();
                CheckEmail();
            }
        }
        return email;
    }

    public String Check_FirstNames() {
        boolean check;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first name: ");
        String f_name = input.nextLine();
        String nameCheck = "^[A-Za-z]{2,29}+$";

        Pattern pattern = Pattern.compile(nameCheck);
        if (f_name == null) {
            System.err.println("Name is invalid");
            //return false;
        } else {
            check = pattern.matcher(f_name).matches();
            if (!check) {
                System.err.println("Name is invalid");
                System.out.println();
                Check_FirstNames();
            }
        }
        return f_name;
    }

    public String Check_LastNames() {
        boolean check;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter surname: ");
        String surname = input.next();
        String nameCheck = "^[A-Za-z]{2,29}+$";

        Pattern pattern = Pattern.compile(nameCheck);
        if (surname == null) {
            System.err.println("Surname is invalid");
            //return false;
        } else {
            check = pattern.matcher(surname).matches();
            if (!check) {
                System.err.println("Surname is invalid");
                System.out.println();
                Check_LastNames();
            }
        }
        return surname;
    }
}
