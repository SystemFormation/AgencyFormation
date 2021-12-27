package it.unisa.agency_formation.utils;

public class Check {

    public Check() {
    }

    public static boolean checkEmail(String email){
        String regular = "^(.+)@(\\S+) $";
        return email.matches(regular);
    }
    public static boolean checkPwd(String pwd){
        String regular = "^.(?=.[a-z A-Z])(?=.\\d)(?=.[!#$%&? \"]).*$";
        return pwd.matches(regular);
    }

    public static boolean checkName(String name){
        String regular = "^[A-Za-z ’]+$";
        return name.matches(regular);
    }
    public static boolean checkSurname(String surname){
        String regular = "^[A-Za-z ’]+$";
        return surname.matches(regular);
    }


}
