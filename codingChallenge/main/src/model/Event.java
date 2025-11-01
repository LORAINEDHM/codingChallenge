package model;

public class Event {
    private EventTypeEnum eventType;
    private User user;

    public Event() {
        this.eventType = null;
        this.user = null;
    }

    public Event(EventTypeEnum eventType, User user) {
        this.eventType = eventType;
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public EventTypeEnum getEventType() {
        return this.eventType;
    }

    public void setEventType(EventTypeEnum eventType) {
        this.eventType = eventType;
    }

    public void setUser(User user) {
        this.user = user;
    }

}


