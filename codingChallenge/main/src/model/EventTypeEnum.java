package model;

public enum EventTypeEnum {

    USER_CREATED("UserCreated"),
    USER_FIRSTNAME_UPDATED("UserFirstnameUpdated"),
    USER_SURNAME_UPDATED("UserSurnameUpdated"),
    USER_EMAIL_UPDATED("UserEmailUpdated"),
    USER_BIRTHDATE_UPDATED("UserBirthdateUpdated"),
    USER_CITY_UPDATED("UserCityUpdated");


    private final String value;

    // Constructor
    EventTypeEnum(String value) {
        this.value = value;
    }

    // Getter
    public String getValue() {
        return value;
    }

    public static EventTypeEnum fromValue(String value) {
        switch (value) {
            case "UserCreated": return USER_CREATED;
            case "UserFirstnameUpdated": return USER_FIRSTNAME_UPDATED;
            case "UserSurnameUpdated": return USER_SURNAME_UPDATED;
            case "UserEmailUpdated": return USER_EMAIL_UPDATED;
            case "UserBirthdateUpdated": return USER_BIRTHDATE_UPDATED;
            case "UserCityUpdated": return  USER_CITY_UPDATED;
            default: throw new IllegalArgumentException("Unknown event type: " + value);
        }
    }

}
