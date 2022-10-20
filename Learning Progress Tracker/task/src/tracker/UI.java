package tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UI {

    ArrayList<Data> userData = new ArrayList<>();

    ArrayList<Graduate> notifiedUsers = new ArrayList<>();
    Statistics stats = new Statistics(userData);
    int id = 10000;

    private boolean isMailExist(String email){
        boolean res = false;
        for (Data userDatum : userData) {
            if (userDatum.getEmail().equals(email)) {
                res = true;
                break;
            }
        }
        return res;
    }
    private boolean checkGradeCorrect(String[] input) {
        boolean res = true;
        for(int i = 1; i < input.length;i++) {
            if(!Pattern.matches(".*\\d.*",input[i])){
                res = false;
                break;
            }
        }
        return res;
    }
    private boolean isIdExist(int id){
        boolean res = false;
        for(Data userDatum: userData) {
            if (userDatum.getId() == id) {
                res = true;
                break;
            }
        }
        return res;
    }
    private int findId(int searchingId){
        int index = 0;
        for(int i = 0; i < userData.size();i++) {
            if(userData.get(i).getId() == searchingId) {
                index = i;
                break;
            }
        }
        return index;
    }
    private boolean userNameChecker(String name) {
        return Pattern.matches("[a-zA-z]+([ '-][a-zA-Z]+)*", name);
    }
    private boolean lastNameChecker(String[] lastName) {
        boolean result = true;
        for(int i = 1; i <= lastName.length-2;i++) {
            if(!Pattern.matches("[a-zA-z]+([ '-][a-zA-Z]+)*", lastName[i])) {
                result = false;
            }
        }

        return result;
    }
    private boolean isCommandLowerCase(String str) {
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            if (!Character.isLowerCase(c))
                return false;
        }
        return true;
    }
    private boolean emailChecker(String email) {
        return Pattern.matches("^[^@]+@[^@]+\\.[^@]+$", email);
    }
    private void showList(){
        if(userData.size() == 0) {
            System.out.println("No students found");
        }
        else {
            System.out.println("Students:");
            for (Data userDatum : userData) {
                System.out.println(userDatum.id);
            }
        }
    }

    private void updatePoints(int id,int javaScore,int dsaScore,int databaseScore,int springScore){
            if(isIdExist(id)){
                if(javaScore >= 0 && dsaScore >= 0 && databaseScore >= 0 && springScore >= 0) {

                    userData.get(findId(id)).setJavaScore(javaScore);
                    userData.get(findId(id)).setDsaScore(dsaScore);
                    userData.get(findId(id)).setDatabasesScore(databaseScore);
                    userData.get(findId(id)).setSpringScore(springScore);
                    stats.numOfStudent(javaScore,dsaScore,databaseScore,springScore);
                    System.out.println("Points updated.");

                }
                else {
                    System.out.println("Incorrect points format.");
                }
            }
            else {
                System.out.println("No student is found for id=" + id);
            }
    }
    private void addPoints() {
        System.out.println("Enter an id and points or 'back' to return:");
        while(true) {

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] points = input.split(" ");
            if("back".equals(input)) {
                break;
            }
            if(points[0].matches(".*[a-zA-Z].")){
                System.out.println("No student is found for id=" + points[0]);
            }
            if(input.contains("-") || !checkGradeCorrect(points) ||
                    points.length != 5) {
                System.out.println("Incorrect points format.");
            }
            else if (input.split(" ").length == 5) { //checks if length is exactly size of 5

                if(Pattern.matches(".*\\d.*",points[0]) && checkGradeCorrect(points)) {
                    int[] values = Arrays.stream(points)
                            .mapToInt(Integer::parseInt)
                            .toArray();
                    updatePoints(values[0], values[1], values[2], values[3], values[4]);

                }
            }
        }
    }
    private void addStudents(){
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter student credentials or 'back' to return:");

        while(true) {

            String[] userCommand;
            userCommand = scan.nextLine().split(" ");

            if ("back".equals(userCommand[0])) {
                System.out.println("Total " + userData.size() +" students have been added.");
                break;
            }

            if(userCommand.length <= 2 || userCommand[0].isBlank() && isCommandLowerCase(userCommand[0])) {
                System.out.println("Incorrect credentials.");
            }

            else if (!userNameChecker(userCommand[0]) || userCommand[0].length() <= 1) {
                System.out.println("Incorrect first name.");
            }
            else if(!lastNameChecker(userCommand) || userCommand[1].length() <= 1) {
                System.out.println("Incorrect last name.");
            }
            else if (!emailChecker(userCommand[userCommand.length-1])) {
                System.out.println("Incorrect email.");
            }

            else if(userNameChecker(userCommand[0]) && emailChecker(userCommand[userCommand.length-1])) {
                if(isMailExist(userCommand[userCommand.length-1])) {
                    System.out.println("This email is already taken.");
                }
                else {
                    userData.add(new Data(userCommand[0], userCommand[1], userCommand[userCommand.length - 1], id));
                    System.out.println("The student has been added.");
                    id++;
                }
            }

        }
    }
    private void findStudent(){
        System.out.println("Enter an id or 'back' to return:");
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String idToSearch = scanner.next();
            if ("back".equals(idToSearch)) {
                break;
            }
            if (isIdExist(Integer.parseInt(idToSearch))) {
                System.out.printf("%d points: Java=%d; DSA=%d; Databases=%d; Spring=%d",
                        userData.get(findId(Integer.parseInt(idToSearch))).getId(),
                        userData.get(findId(Integer.parseInt(idToSearch))).getJavaScore(),
                        userData.get(findId(Integer.parseInt(idToSearch))).getDsaScore(),
                        userData.get(findId(Integer.parseInt(idToSearch))).getDatabasesScore(),
                        userData.get(findId(Integer.parseInt(idToSearch))).getSpringScore());
            } else {
                System.out.println("No student is found for id=" + idToSearch);
            }
        }
    }

    private void statistics() {
        Scanner scanner = new Scanner(System.in);
        StudentSort studentSort = new StudentSort(userData);
        System.out.println("Type the name of a course to see details or 'back' to quit:");

        System.out.println("Most popular: " + (userData.size() > 0 ? stats.returnMostPopular() : "n/a"));
        System.out.println("Least popular: " + (userData.size() > 0 ? stats.returnLeastPopular() : "n/a"));
        System.out.println("Highest activity: " + (userData.size() > 0 ? stats.invokeMostActivity() : "n/a"));
        System.out.println("Lowest activity: " + (userData.size() > 0 && stats.leastActivityCounter() != 0
                ? stats.leastActivityInvoce() : "n/a"));
        System.out.println("Easiest course: " + (userData.size() > 0 ? stats.easiestCourse() : "n/a"));
        System.out.println("Hardest course: " + (userData.size() > 0 ? stats.hardestCourse() : "n/a"));

        while (true) {
            String input = scanner.next().toLowerCase();

            if ("back".equals(input)) {
                break;
            } else {

                switch (input) {
                    case "java" -> studentSort.invokeSortedJava();
                    case "dsa" -> studentSort.invokeSortedDsa();
                    case "databases" -> studentSort.invokeSortedDatabases();
                    case "spring" -> studentSort.invokeSortedSpring();
                    default -> System.out.println("Unknown course.");
                }
            }
        }
    }

    public void userInterface() {
        Scanner scanner = new Scanner(System.in);
        int num = 1;
        System.out.println("Learning Progress Tracker");
        while (num != 0) {

            num = commandNum(scanner.nextLine());
            switch (num) {
                case 0 -> System.out.println("Bye!");
                case 1 -> System.out.println("No input.");
                case 2 -> System.out.println("Enter 'exit' to exit the program");
                case 3 -> addStudents();
                case 6 -> showList();
                case 7 -> addPoints();
                case 8 -> findStudent();
                case 9 -> statistics();
                case 10 -> {
                    StudentSort studentSort = new StudentSort(userData);
                    studentSort.invokeGraduateStudents(notifiedUsers);
                }
                default -> System.out.println("Unknown command!");
            }

        }
    }
    private Integer commandNum(String command) {
        if ("exit".equals(command)) {
            return 0;
        }
        if("back".equals(command)) {
            return 2;
        }
        if (command.isBlank()) {
            return 1;
        }
        if ("add students".equals(command)) {
            return 3;
        }
        if ("help".equals(command)) {
            return 4;
        }
        if("list".equals(command)) {
            return 6;
        }
        if("add points".equals(command)) {
            return 7;
        }
        if("find".equals(command)) {
            return 8;
        }
        if("statistics".equals(command)) {
            return 9;
        }
        if("notify".equals(command)) {
            return 10;
        }
        return 5;
    }
}