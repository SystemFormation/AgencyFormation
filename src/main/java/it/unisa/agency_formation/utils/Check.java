package it.unisa.agency_formation.utils;

public class Check {

    public Check() {
    }

    public static boolean checkEmail(String email){
        String regular = "^[A-z0-9\\.\\+_-]+@[A-z0-9\\._-]+\\.[A-z]{2,6}$";
        return email.matches(regular);
    }
    public static boolean checkPwd(String pwd){
        //String regular = "^.*(?=.*[a-z A-Z])(?=.*\\d)(?=.*[!#$%&? \"]).*$";
        String regularSimple="^[A-Za-z0-9]+$";
        return pwd.matches(regularSimple);
    }

    public static boolean checkName(String name){
        String regular = "^[A-Za-z ’]+$";
        return name.matches(regular);
    }
    public static boolean checkSurname(String surname){
        String regular = "^[A-Za-z ’]+$";
        return surname.matches(regular);
    }
    /*----------check per la creazione di un team----------*/
    public static boolean checkProjectName(String projectName){
        String regular = "^[A-Za-z0-9 ’]+$";
        return projectName.matches(regular);
    }
    public static boolean checkDescription(String desc){
        String regular = "^[A-Za-z ’]+$";
        return desc.matches(regular);
    }
    public static boolean checkTeamName(String teamName){
        String regular = "^[A-Za-z ’]+$";
        return teamName.matches(regular);
    }
    public static boolean checkCompetence(String competence){
        String regular = "^[A-Za-z]+^[#+.]+$";
        return competence.matches(regular);
    }

}
