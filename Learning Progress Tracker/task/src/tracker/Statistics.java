package tracker;

import java.util.ArrayList;

public class Statistics {
    ArrayList<Data> userData;

    int javaCount = 0;
    int dsaCount = 0;
    int databaseCount = 0;
    int springCount = 0;

    int javaStudents = 0;
    int dsaStudents = 0;
    int dbStudents = 0;
    int springStudents = 0;
    public Statistics(ArrayList<Data> userData) {
        this.userData = userData;
    }
    private void findNumOfStudnet() {
        for (Data userDatum : userData) {
            if (userDatum.getJavaScore() > 0) javaStudents++;
            if (userDatum.getDsaScore() > 0) dsaStudents++;
            if (userDatum.getDatabasesScore() > 0) dbStudents++;
            if (userDatum.getSpringScore() > 0) springStudents++;
        }
    }
    private void dropNums() {
        javaStudents = 0;
        dsaStudents = 0;
        dbStudents = 0;
        springStudents = 0;
    }
    public int findLeastStudentCourse(){
        return Math.min(Math.min(javaStudents,dsaStudents),Math.min(dbStudents,springStudents));
    }
    private int findMostPopularCourse(){
        return Math.max(Math.max(javaStudents,dsaStudents),Math.max(dbStudents,springStudents));
    }
    public String returnMostPopular() {
        findNumOfStudnet();
        ArrayList<String> res = new ArrayList<>();
        int min = findLeastStudentCourse();
        int max =findMostPopularCourse();
        if(javaStudents > min || javaStudents == max) {res.add("Java");}
        if(dsaStudents > min  || dsaStudents == max) {res.add("DSA");}
        if(dbStudents > min || dsaStudents == max) {res.add("Databases");}
        if(springStudents > min || springStudents == max) {res.add("Spring");}
        String commaSeparated = res.toString();
        commaSeparated =
                commaSeparated.replace("[","")
                        .replace("]","");
        dropNums();
        return commaSeparated;
    }
    public String returnLeastPopular() {
        findNumOfStudnet();
        ArrayList<String> res = new ArrayList<>();
        int min = findLeastStudentCourse();
        if(min == findMostPopularCourse()) {
            return "n/a";
        }
        else
            if (javaStudents == min) {
                res.add("Java");
            }
            if (dsaStudents == min) {
                res.add("DSA");
            }
            if (dbStudents == min) {
                res.add("Databases");
            }
            if (springStudents == min) {
                res.add("Spring");
            }
            String commaSeparated = res.toString();
            commaSeparated =
                    commaSeparated.replace("[", "")
                            .replace("]", "");
            dropNums();
            return commaSeparated;

    }
    public void numOfStudent(int javaCount, int dsaCount, int databaseCount, int springCount){
        if(javaCount > 0) {this.javaCount++;}
        if(dsaCount > 0) {this.dsaCount++;}
        if(databaseCount > 0) {this.databaseCount++;}
        if(springCount > 0) {this.springCount++;}
    }

    private String mostActivity(int num, ArrayList<String> res){
        if(javaCount == num) {res.add("Java");}
        if(dsaCount == num) {res.add("DSA");}
        if(databaseCount == num) {res.add("Databases");}
        if(springCount == num) {res.add("Spring");}
        String commaSeparated = res.toString();
        commaSeparated =
                commaSeparated.replace("[","")
                        .replace("]","");
        return commaSeparated;//+ String.format(" Counts%d %d %d %d",javaCount,dsaCount,databaseCount,springCount)
    }
    private String leastActivity(int num, ArrayList<String> res){
        if(javaCount == num) {res.add("Java");}
        if(dsaCount == num) {res.add("DSA");}
        if(databaseCount == num) {res.add("Databases");}
        if(springCount == num) {res.add("Spring");}
        String commaSeparated = res.toString();
        commaSeparated =
                commaSeparated.replace("[","")
                        .replace("]","");
        return commaSeparated;//+ String.format(" Counts%d %d %d %d",javaCount,dsaCount,databaseCount,springCount)
    }
    private String activityCounter(int num, ArrayList<String> res,int javaCount,int dsaCount,int databaseCount,int springCount){
        if(javaCount == num) {res.add("Java");}
        if(dsaCount == num) {res.add("DSA");}
        if(databaseCount == num) {res.add("Databases");}
        if(springCount == num) {res.add("Spring");}
        String commaSeparated = res.toString();
        commaSeparated =
                commaSeparated.replace("[","")
                        .replace("]","");
        return commaSeparated;
    }


    public String invokeMostActivity(){
        int min = Math.max(Math.max(javaCount,dsaCount),Math.max(databaseCount,springCount));
        ArrayList<String> res = new ArrayList<>();
        return mostActivity(min,res);
    }
    public int leastActivityCounter() {
        return  Math.min(Math.min(javaCount,dsaCount),Math.min(databaseCount,springCount)) ==
                Math.max(Math.max(javaCount,dsaCount),Math.max(databaseCount,springCount)) ?
                0 : Math.min(Math.min(javaCount,dsaCount),Math.min(databaseCount,springCount)) ;
    }
    public String leastActivityInvoce(){
        int min = leastActivityCounter();
        ArrayList<String> res = new ArrayList<>();
        return leastActivity(min,res);
    }



    public String easiestCourse(){
        findNumOfStudnet();
        int java = 0,dsa = 0,database = 0,spring = 0;
        for (Data userDatum : userData) {
            java += userDatum.getJavaScore();
            dsa += userDatum.getDsaScore();
            database += userDatum.getDatabasesScore();
            spring += userDatum.getSpringScore();
        }
        if(java > 0) java = java/javaStudents;
        if(dsa>0) dsa = dsa/dsaStudents;
        if(database>0)database = database/dbStudents;
        if(spring>0)spring = spring/springStudents;
        int minAct = Math.max(Math.max(java,dsa),Math.max(database,spring));
        ArrayList<String> res = new ArrayList<>();
        dropNums();
        return activityCounter(minAct,res,java,dsa,database,spring);
    }

    public String hardestCourse(){
        findNumOfStudnet();
        int java = 0,dsa = 0,database = 0,spring = 0;
        for (Data userDatum : userData) {
            java += userDatum.getJavaScore();
            dsa += userDatum.getDsaScore();
            database += userDatum.getDatabasesScore();
            spring += userDatum.getSpringScore();
        }
        if(java > 0) java = java/javaStudents;
        if(dsa>0) dsa = dsa/dsaStudents;
        if(database>0)database = database/dbStudents;
        if(spring>0)spring = spring/springStudents;
        int maxAct = Math.min(Math.min(java,dsa),Math.min(database,spring));
        ArrayList<String> res = new ArrayList<>();
        dropNums();
        return activityCounter(maxAct,res,java,dsa,database,spring);
    }

}
