package test;

import model.User;
import service.Database;
import test.utils.Assert;
import utils.DateUtils;

import java.util.UUID;

public class DatabaseTest {

    Database database = new Database();

    public void addUser_ok() {
        int sizeList = database.getAllUsers().size();
        UUID id = UUID.randomUUID();
        User user = new User(id, "firstName", "surname", "email", DateUtils.stringToDate("1963-01-23"), "city");
        database.addUser(user);
        Assert.assertEquals(sizeList + 1, database.getAllUsers().size());
    }

    public void addUser_userAlreadyExists() {
        UUID id = UUID.randomUUID();
        User user = new User(id, "firstName", "surname", "email", DateUtils.stringToDate("1963-01-23"), "city");
        database.addUser(user);
        String exception = null;
        try {
            database.addUser(user);
        } catch (IllegalStateException e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("User with id " + id + " already exists", exception);
    }

    // public void addUser_idMissing()
    // public void addUser_birthdateMissing()
    // public void addUser_emailMissing()
    // public void addUser_firstnameMissing()
    // public void addUser_surnameMissing()
    // public void addUser_cityMissing()


}
