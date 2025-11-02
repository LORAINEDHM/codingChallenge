package test;

import model.Event;
import model.EventTypeEnum;
import model.User;
import service.Database;
import test.utils.Assert;
import utils.DateUtils;

import java.util.UUID;

public class DatabaseTest {

    Database database = new Database();

    private User userBuilder() {
        return new User(UUID.randomUUID(), "firstName", "surname", "email", DateUtils.stringToDate("1963-01-23"), "city");
    }

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
        Assert.assertEquals("User with id '" + id + "' already exists.", exception);
    }

    public void addUser_idNull() {
        User user = new User(null, "firstName", "surname", "email", DateUtils.stringToDate("1963-01-23"), "city");
        String exception = null;
        try {
            database.addUser(user);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("User data incomplete.", exception);
    }

    public void addUser_firstnameNull() {
        User user = new User(UUID.randomUUID(), null, "surname", "email", DateUtils.stringToDate("1963-01-23"), "city");
        String exception = null;
        try {
            database.addUser(user);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("User data incomplete.", exception);
    }

    public void addUser_surnameNull() {
        User user = new User(UUID.randomUUID(), "firstName", null, "email", DateUtils.stringToDate("1963-01-23"), "city");
        String exception = null;
        try {
            database.addUser(user);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("User data incomplete.", exception);
    }

    public void addUser_emailNull() {
        User user = new User(UUID.randomUUID(), "firstname", "surname", null, DateUtils.stringToDate("1963-01-23"), "city");
        String exception = null;
        try {
            database.addUser(user);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("User data incomplete.", exception);
    }

    public void addUser_birthDateNull() {
        User user = new User(UUID.randomUUID(), "firstname", "surname", "email", null, "city");
        String exception = null;
        try {
            database.addUser(user);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("User data incomplete.", exception);
    }

    public void addUser_cityNull() {
        User user = new User(UUID.randomUUID(), "firstname", "surname", "email", DateUtils.stringToDate("1963-01-23"), null);
        String exception = null;
        try {
            database.addUser(user);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("User data incomplete.", exception);
    }

    public void updateUser_userNull() {
        String exception = null;
        Event event = new Event(EventTypeEnum.USER_EMAIL_UPDATED, null);
        try {
            database.updateUser(event);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("Invalid line.", exception);
    }

    public void updateUser_userIdNull() {
        String exception = null;
        User user = userBuilder();
        user.setId(null);
        Event event = new Event(EventTypeEnum.USER_EMAIL_UPDATED, user);
        try {
            database.updateUser(event);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("Invalid line.", exception);
    }

    public void updateUser_userFirstnameNull() {
        String exception = null;
        User user = userBuilder();
        database.addUser(user);
        user.setFirstname(null);
        Event event = new Event(EventTypeEnum.USER_FIRSTNAME_UPDATED, user);
        try {
            database.updateUser(event);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("Attribute value to update is null.", exception);
    }

    public void updateUser_userSurnameNull() {
        String exception = null;
        User user = userBuilder();
        database.addUser(user);
        user.setSurname(null);
        Event event = new Event(EventTypeEnum.USER_SURNAME_UPDATED, user);
        try {
            database.updateUser(event);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("Attribute value to update is null.", exception);
    }

    public void updateUser_userEmailNull() {
        String exception = null;
        User user = userBuilder();
        database.addUser(user);
        user.setEmail(null);
        Event event = new Event(EventTypeEnum.USER_EMAIL_UPDATED, user);
        try {
            database.updateUser(event);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("Attribute value to update is null.", exception);
    }

    public void updateUser_userBirthdateNull() {
        String exception = null;
        User user = userBuilder();
        database.addUser(user);
        user.setBirthdate(null);
        Event event = new Event(EventTypeEnum.USER_BIRTHDATE_UPDATED, user);
        try {
            database.updateUser(event);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("Attribute value to update is null.", exception);
    }

    public void updateUser_userCityNull() {
        String exception = null;
        User user = userBuilder();
        database.addUser(user);
        user.setCity(null);
        Event event = new Event(EventTypeEnum.USER_CITY_UPDATED, user);
        try {
            database.updateUser(event);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("Attribute value to update is null.", exception);
    }

    public void updateUser_userNotFoundInDB() {
        String exception = null;
        User user = userBuilder();
        Event event = new Event(EventTypeEnum.USER_CITY_UPDATED, user);
        try {
            database.updateUser(event);
        } catch (IllegalStateException e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("User to update with id '" + user.getId() + "' not found in database.", exception);
    }

    public void updateUser_OK() {
        User user = userBuilder();
        database.addUser(user);
        user.setCity("newCity");
        Event event = new Event(EventTypeEnum.USER_CITY_UPDATED, user);
        database.updateUser(event);
        User result = database.getUserById(user.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(user.getCity(), result.getCity());
    }

    public void getNumberOfUsersByCity_EmptyListOk() {
        Database database = new Database();
        long result = database.getNumberOfUsersByCity("Namur");
        Assert.assertEquals(0L, result);
    }

    public void getNumberOfUsersByCity_Ok() {
        Database database = new Database();
        User user1 = userBuilder();
        database.addUser(user1);
        User user2 = userBuilder();
        user2.setCity("Londres");
        database.addUser(user2);
        User user3 = userBuilder();
        database.addUser(user3);

        long result = database.getNumberOfUsersByCity(user1.getCity());
        Assert.assertEquals(2L, result);
    }

}
