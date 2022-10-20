package tracker;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;

public class StudentSort {
    ArrayList<Data> userData;
    ArrayList<DataStats> temp;

    ArrayList<Graduate> graduateArrayList = new ArrayList<>();

    final double JAVA_POINTS = 600;
    final double DSA_POINTS = 400;
    final double DATABASE_POINTS = 480;
    final double SPRING_POINTS = 550;
    public StudentSort(ArrayList<Data> userData) {
        this.userData = userData;
        temp = new ArrayList<>();
        getSortedById();
    }
    private void sortTempListId() {
        temp.sort(Comparator.comparing(DataStats::getId).reversed());

    }


    public void getSortedById() {
        sortTempListId();
        for (Data userDatum : userData) {

            temp.add(new DataStats(userDatum.getId(), userDatum.getJavaScore(), userDatum.getDsaScore(),
                    userDatum.getDatabasesScore(), userDatum.getSpringScore()));

        }

    }
    private void JavaSort(){
        temp.sort(Comparator.comparing(DataStats::getJavaScore).reversed());
    }
    private void DsaSort(){
        temp.sort(Comparator.comparing(DataStats::getDsaScore).reversed());
    }
    private void DatabasesSort(){
        temp.sort(Comparator.comparing(DataStats::getDatabasesScore).reversed());
    }
    private void SpringSort(){
        temp.sort(Comparator.comparing(DataStats::getSpringScore).reversed());
    }
    public void invokeSortedJava(){


        JavaSort();
        System.out.println("Java\n" +
                "id    points    completed");
        for (DataStats dataStats : temp)
            if(dataStats.getJavaPercentage().compareTo(BigDecimal.ZERO) == 0) {
                continue;
            }
        else {
                System.out.println(dataStats.getId() + " " + dataStats.getJavaScore() + "        "
                            + dataStats.getJavaPercentage() + "%");
            }

    }
    public void invokeSortedDsa(){

        DsaSort();
        System.out.println("DSA\n" +
                "id    points    completed");
        for (DataStats dataStats : temp)
            if(dataStats.getDsaPercentage().compareTo(BigDecimal.ZERO) == 0) {
                continue;
            }
            else {
                System.out.println( dataStats.getId() + " " + dataStats.getDsaScore() + "        "
                        + dataStats.getDsaPercentage()+"%");
            }

    }
    public void invokeSortedDatabases(){

        DatabasesSort();
        System.out.println("Databases\n" +
                "id    points    completed");
        for (DataStats dataStats : temp)
            if(dataStats.getDatabasePercentage().compareTo(BigDecimal.ZERO) == 0) {
                continue;
            }
            else {
                System.out.println( dataStats.getId() + " " + dataStats.getDatabasesScore() + "        "
                        + dataStats.getDatabasePercentage()+"%");
            }

    }
    public void invokeSortedSpring(){

        SpringSort();
        System.out.println("Spring\n" +
                "id    points    completed");
        for (DataStats dataStats : temp)
            if(dataStats.getSpringPercentage().compareTo(BigDecimal.ZERO) == 0) {
                continue;
            }
            else {
                System.out.println(dataStats.getId() + " " + dataStats.getSpringScore() + "        "
                        + dataStats.getSpringPercentage() + "%");
            }
    }

    private void generateGraduatedStudents() {
        for (Data userDatum : userData) {
            if (userDatum.getJavaScore() == JAVA_POINTS) {
                graduateArrayList.add(new Graduate(userDatum.email,userDatum.getName() + " " + userDatum.getLastName(),"Java"));
            }
            if (userDatum.getDsaScore() == DSA_POINTS) {
                graduateArrayList.add(new Graduate(userDatum.getEmail(), userDatum.getName() + " " + userDatum.getLastName(), "DSA"));
            }
            if (userDatum.getDatabasesScore() == DATABASE_POINTS) {
                graduateArrayList.add(new Graduate(userDatum.getEmail(), userDatum.getName() + " " + userDatum.getLastName(),"Databases"));
            }
            if (userDatum.getSpringScore() == SPRING_POINTS) {
                graduateArrayList.add(new Graduate(userDatum.getEmail(), userDatum.getName() + " " + userDatum.getLastName(), "Spring"));
            }
        }
    }
    private boolean checkIfNotified(ArrayList<Graduate> notified,String email,String course) {
        boolean result = true;
        for (Graduate graduate : notified) {
            if (graduate.getEmail().equals(email) && graduate.getCourseName().equals(course)) {
                result = false;
                break;
            }
        }
        return result;
    }
    public void invokeGraduateStudents(ArrayList<Graduate> notified) {
        int notifiedNum = 0;
        ArrayList<String> previousTemp = new ArrayList<>();
        if(userData.size() > 0) {
            generateGraduatedStudents();
            if (graduateArrayList.size() > 0) {
                for (Graduate graduate : graduateArrayList) {
                    if (checkIfNotified(notified, graduate.getEmail(), graduate.getCourseName())) {
                        System.out.printf("""
                                        To: %s
                                        Re: Your Learning Progress
                                        Hello, %s! You have accomplished our %s course!
                                        """,
                                graduate.getEmail(), graduate.getUserName(), graduate.getCourseName());
                        notified.add(new Graduate(graduate.getEmail(), graduate.getUserName(), graduate.getCourseName()));
                        previousTemp.add(graduate.getEmail());
                        notifiedNum = notifiedNumber(previousTemp);
                    }
                }
            }
        }

        System.out.printf("Total %d students have been notified.\n",notifiedNum);
    }

    private int notifiedNumber(ArrayList<String> notifiedNum) {
        int tempNotifiedNum = 1;
        String temp = notifiedNum.get(0);
        for(int i = 1; i < notifiedNum.size();i++) {
            if (!notifiedNum.get(i).equals(temp)){
                temp = notifiedNum.get(i);
            }
        }
        return tempNotifiedNum;
    }
}
