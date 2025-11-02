package service;

import model.Event;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class Database {

    private List<User> userList;

    public Database() {
        this.userList = new ArrayList<>();
    };


    public void addUser(User userFromCSV) {
        for (User user : this.userList) {
            if (user.getId() == userFromCSV.getId()) {
                throw new IllegalStateException("User with id '" + user.getId() + "' already exists.");
            }
        }
        this.userList.add(userFromCSV);
    }

    public void updateUser(Event event) {
        User userFromCSV = event.getUser();
        if (userFromCSV == null || userFromCSV.getId() == null) {  // ADD TEST
            throw new IllegalArgumentException("Invalid line.");
        }
        boolean isFound = false;
        for (User user : this.userList) {
            if (user.getId() == userFromCSV.getId()) {
                isFound = true;
                switch (event.getEventType()) {
                    case USER_FIRSTNAME_UPDATED -> {
                        DatabaseValidator.validateAttributeToUpdate(userFromCSV.getFirstname());
                        user.setFirstname(userFromCSV.getFirstname());
                    }
                    case USER_SURNAME_UPDATED -> {
                        DatabaseValidator.validateAttributeToUpdate(userFromCSV.getSurname());
                        user.setSurname(userFromCSV.getSurname());
                    }
                    case USER_EMAIL_UPDATED -> {
                        DatabaseValidator.validateAttributeToUpdate(userFromCSV.getEmail());
                        user.setEmail(userFromCSV.getEmail());
                    }
                    case USER_BIRTHDATE_UPDATED -> {
                        DatabaseValidator.validateAttributeToUpdate(userFromCSV.getBirthdate());
                        user.setBirthdate(userFromCSV.getBirthdate());
                    }
                    case USER_CITY_UPDATED -> {
                        DatabaseValidator.validateAttributeToUpdate(userFromCSV.getCity());
                        user.setCity(userFromCSV.getCity());
                    }
                    default ->
                            throw new IllegalArgumentException("Unknown event type: '" + event.getEventType() + "'.");
                }
            }
        }
        if (!isFound)
            throw new IllegalStateException("User to update with id '" + userFromCSV.getId() + "' not found in database.");
    }

    public User getUserById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null.");
        }
        for (User user : this.userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        throw new NoSuchElementException("User not found for id : '" + id +"'.");
    }

    public List<User> getAllUsers() {
        return this.userList;
    }
}
