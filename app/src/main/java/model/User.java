package model;

/**
 * Created by Hoi on 10/16/2017.
 */

public class User {
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public User(String userName, String password, String level, String unit, String status) {

        UserName = userName;
        Password = password;
        Level = level;
        Unit = unit;
        Status = status;
    }

    public User(String userName, String level, String unit) {
        UserName = userName;
        Level = level;
        Unit = unit;
    }

    public User(String userName, String password) {
        UserName = userName;
        Password = password;
    }

    public User(String userName, int id) {
        UserName = userName;
        Id = id;
    }

    public int getId() {

        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    String UserName;
    String Password;
    String Level;
    String Unit;
    String Status;



    int Id ;

    @Override
    public String toString() {
        return getUserName();
    }
}
