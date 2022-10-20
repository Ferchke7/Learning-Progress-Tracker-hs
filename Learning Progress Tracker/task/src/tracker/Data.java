package tracker;

public class Data {
    String name;
    String lastName;
    String email;
    int id;
    int javaScore;
    int dsaScore;
    int databasesScore;
    int springScore;

    final double JAVA_POINTS = 600;
    final double DSA_POINTS = 400;
    final double DATABASE_POINTS = 480;
    final double SPRING_POINTS = 550;
    public int getId() {
        return id;
    }

    public int getDsaScore() {
        return dsaScore;
    }

    public int getDatabasesScore() {
        return databasesScore;
    }

    public int getSpringScore() {
        return springScore;
    }

    public int getJavaScore() {
        return javaScore;
    }

    public void setJavaScore(int javaScore) {
        if (javaScore + this.javaScore <= JAVA_POINTS) {
            this.javaScore += javaScore;
        }
    }

    public void setDsaScore(int dsaScore) {
        if (dsaScore + this.dsaScore <= DSA_POINTS) {
            this.dsaScore += dsaScore;
        }
    }

    public void setDatabasesScore(int databasesScore) {
        if (databasesScore + this.databasesScore <= DATABASE_POINTS) {
            this.databasesScore += databasesScore;
        }
    }

    public void setSpringScore(int springScore) {
        if (springScore + this.springScore <= SPRING_POINTS) {
            this.springScore += springScore;
        }
    }


    public Data(String name,String lastName,String email,int id) {
        this.name = name;
        
        this.email = email;
        this.lastName = lastName;
        this.id = id;
    }


    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getLastName() {
        return lastName;
    }
}
