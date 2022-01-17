package it.unisa.agency_formation.utils;

public class Check {

    public static boolean checkEmail(String email) {
        if (email == null) {
            return false;
        }
        String regular = "^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
        return email.matches(regular);
    }

    public static boolean checkPwd(String pwd) {
        if (pwd == null) {
            return false;
        }
        //String regular = "^.*(?=.*[a-z A-Z])(?=.*\\d)(?=.*[!#$%&? \"]).*$";
        String regularSimple = "^[A-Za-z0-9]{3,16}$";
        return pwd.matches(regularSimple);
    }
    public static boolean checkName(String name) {
        if (name == null) {
            return false;
        }
        String regular = "^[A-Za-z]{2,32}$";
        return name.matches(regular);
    }
    public static boolean checkSurname(String surname) {
        if (surname == null) {
            return false;
        }
        String regular = "^[A-Za-z]{2,32}$";
        return surname.matches(regular);
    }
    /*----------check per la creazione di un team----------*/
    public static boolean checkProjectName(String projectName) {
        String regular = "^[A-Za-z0-9 ’]+$";
        return projectName.matches(regular);
    }
    public static boolean checkDescription(String desc) {
        String regular = "^[A-Za-z ’]+$";
        return desc.matches(regular);
    }
    public static boolean checkTeamName(String teamName) {
        String regular = "^[A-Za-z ’]+$";
        return teamName.matches(regular);
    }
    public static boolean checkCompetence(String competence) {
        String regular = "^[A-Za-z]+^[#+.]+$";
        return competence.matches(regular);
    }
}
