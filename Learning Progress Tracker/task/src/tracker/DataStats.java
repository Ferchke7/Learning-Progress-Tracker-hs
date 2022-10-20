package tracker;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DataStats {
    int id;
    int javaScore;
    int dsaScore;
    int databasesScore;
    int springScore;

    final double JAVA_POINTS = 600;
    final double DSA_POINTS = 400;
    final double DATABASE_POINTS = 480;
    final double SPRING_POINTS = 550;

    double javaPercentage;
    double dsaPercentage;
    double databasePercentage;
    double springPercentage;

    public DataStats(int id, int javaScore, int dsaScore, int databasesScore, int springScore) {
        this.id = id;
        this.javaScore = javaScore;
        this.dsaScore = dsaScore;
        this.databasesScore = databasesScore;
        this.springScore = springScore;
        this.javaPercentage = (double)javaScore * 100 / JAVA_POINTS;
        this.dsaPercentage = ((double)dsaScore * 100 / DSA_POINTS);
        this.databasePercentage = ((double)databasesScore * 100/DATABASE_POINTS);
        this.springPercentage = ((double)springScore * 100 / SPRING_POINTS);

    }

    public int getJavaScore() {
        return javaScore;
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

    public int getId() {
        return id;
    }

    public BigDecimal getJavaPercentage() {
        return new BigDecimal(javaPercentage).setScale(1,RoundingMode.HALF_UP);
    }

    public BigDecimal getDsaPercentage() {
        return new BigDecimal(dsaPercentage).setScale(1,RoundingMode.HALF_UP);
    }

    public BigDecimal getDatabasePercentage() {
        return new BigDecimal(databasePercentage).setScale(1,RoundingMode.HALF_UP);
    }

    public BigDecimal getSpringPercentage() {
        return new BigDecimal(springPercentage).setScale(1,RoundingMode.HALF_UP);
    }


}
