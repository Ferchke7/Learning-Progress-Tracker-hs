package tracker;

public class Graduate {
    String email;
    String userName;
    String courseName;

    public Graduate(String email, String userName, String courseName) {
        this.email = email;
        this.userName = userName;
        this.courseName = courseName;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getCourseName() {
        return courseName;
    }
}
