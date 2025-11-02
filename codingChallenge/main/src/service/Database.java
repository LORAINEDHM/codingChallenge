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
                throw new IllegalStateException("User with id " + user.getId() + " already exists");
            }
        }
        this.userList.add(userFromCSV);
    }

    public void updateUser(Event event) {
        User userFromCSV = event.getUser();
        if (userFromCSV == null) {
            throw new IllegalArgumentException();
        }
        for (User user : this.userList) {
            if (user.getId() == userFromCSV.getId()) {

                switch(event.getEventType()) {
                    case USER_FIRSTNAME_UPDATED:
                        user.setFirstname(userFromCSV.getFirstname());
                        break;
                    case USER_SURNAME_UPDATED:
                        user.setSurname(userFromCSV.getSurname());
                        break;
                    case USER_EMAIL_UPDATED:
                        user.setEmail(userFromCSV.getEmail());
                        break;
                    case USER_BIRTHDATE_UPDATED:
                        user.setBirthdate(userFromCSV.getBirthdate());
                        break;
                    case USER_CITY_UPDATED:
                        user.setCity(userFromCSV.getCity());
                        break;
                    default: throw new IllegalArgumentException("Unknown event type: " + event.getEventType());
                }
            }
        }
    }

    public User getUserById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id is null");
        }
        for (User user : this.userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        throw new NoSuchElementException("User not found for id : " + id);
    }

    public List<User> getAllUsers() {
        return this.userList;
    }
}
