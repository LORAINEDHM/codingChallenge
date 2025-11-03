package service;

import model.User;

public class DatabaseValidator {

    public static void validateUserData(User user) {
        if (user == null
                || user.getId() == null
                || user.getFirstname() == null
                || user.getSurname() == null
                || user.getBirthdate() == null
                || user.getEmail() == null
                || user.getCity() == null)
            throw new IllegalArgumentException("User data incomplete.");
    }

    public static void validateAttributeToUpdate(Object value) {
        if (value == null) {
            throw new IllegalArgumentException("Attribute value to update is null.");
        }
    }
}
