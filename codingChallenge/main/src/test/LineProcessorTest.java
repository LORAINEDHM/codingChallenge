package test;

import model.Event;
import model.EventTypeEnum;
import model.User;
import service.LineProcessor;
import test.utils.Assert;
import utils.DateUtils;

import java.util.UUID;

public class LineProcessorTest {

    LineProcessor lineProcessor = new LineProcessor();

    public void parseLine_TooManyColumns() {
        String line = "Test;UserCreated;{\"id\": \"bdc9eaa6-bdfd-44d8-914d-7aac460e6e92\",\"firstname\": \"Scott\",\"surname\": \"Morales\",\"email\": \"scott.morales@google.com\",\"birthdate\": \"1963-01-23\",\"city\": \"Gembloux\"}";
        String exception = null;
        try {
            lineProcessor.parseLine(line);
        }
        catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("Invalid line.", exception);
    }

    public void parseLine_OneColumnMissing() {
        String line = "{\"id\": \"bdc9eaa6-bdfd-44d8-914d-7aac460e6e92\",\"firstname\": \"Scott\",\"surname\": \"Morales\",\"email\": \"scott.morales@google.com\",\"birthdate\": \"1963-01-23\",\"city\": \"Gembloux\"}";
        String exception = null;
        try {
            lineProcessor.parseLine(line);
        }
        catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("Invalid line.", exception);
    }

    public void parseLine_ok() {

        Event expected = new Event();
        expected.setEventType(EventTypeEnum.USER_CREATED);
        expected.setUser(new User(UUID.fromString("bdc9eaa6-bdfd-44d8-914d-7aac460e6e92"), "Scott", "Morales", "scott.morales@google.com", DateUtils.stringToDate("1963-01-23"), "Gembloux"));
        String line = "UserCreated;{\"id\": \"bdc9eaa6-bdfd-44d8-914d-7aac460e6e92\",\"firstname\": \"Scott\",\"surname\": \"Morales\",\"email\": \"scott.morales@google.com\",\"birthdate\": \"1963-01-23\",\"city\": \"Gembloux\"}";
        Event result = lineProcessor.parseLine(line);
        Assert.assertEquals(expected, result);
    }

    public void parseLine_UnknownEventType() {

        Event expected = new Event();
        expected.setEventType(EventTypeEnum.USER_CREATED);
        expected.setUser(new User(UUID.fromString("bdc9eaa6-bdfd-44d8-914d-7aac460e6e92"), "Scott", "Morales", "scott.morales@google.com", DateUtils.stringToDate("1963-01-23"), "Gembloux"));
        String line = "EventType;{\"id\": \"bdc9eaa6-bdfd-44d8-914d-7aac460e6e92\",\"firstname\": \"Scott\",\"surname\": \"Morales\",\"email\": \"scott.morales@google.com\",\"birthdate\": \"1963-01-23\",\"city\": \"Gembloux\"}";
        String exception = null;
        try {
            lineProcessor.parseLine(line);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("Unknown event type: EventType", exception);
    }

    public void parseLine_idWrongFormat() {
        String line = "UserCreated;{\"id\": \"bdc9eaa6-bdfd-44d8-914d-7aac460e6e92sd\",\"firstname\": \"Scott\",\"surname\": \"Morales\",\"email\": \"scott.morales@google.com\",\"birthdate\": \"1963-01-23\",\"city\": \"Gembloux\"}";
        String exception = null;
        try {
            lineProcessor.parseLine(line);
        }
        catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("UUID string too large", exception);
    }

    public void parseLine_dateWrongFormat() {
        String line = "UserCreated;{\"id\": \"bdc9eaa6-bdfd-44d8-914d-7aac460e6e92\",\"firstname\": \"Scott\",\"surname\": \"Morales\",\"email\": \"scott.morales@google.com\",\"birthdate\": \"1963-janv-23\",\"city\": \"Gembloux\"}";
        String exception = null;
        try {
            lineProcessor.parseLine(line);
        }
        catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("The string '"+ "1963-janv-23' could not be converted into a date.", exception);
    }

    // public void addUser_emailWrongFormat()
    // public void addUser_firstnameWrongFormat()
    // public void addUser_surnameWrongFormat()
    // public void addUser_cityWrongFormat()

    public void parseLine_UnknownAttribute() {

        Event expected = new Event();
        expected.setEventType(EventTypeEnum.USER_CREATED);
        expected.setUser(new User(UUID.fromString("bdc9eaa6-bdfd-44d8-914d-7aac460e6e92"), "Scott", "Morales", "scott.morales@google.com", DateUtils.stringToDate("1963-01-23"), "Gembloux"));
        String line = "UserCreated;{\"id\": \"bdc9eaa6-bdfd-44d8-914d-7aac460e6e92\",\"firstnamewrong\": \"Scott\",\"surname\": \"Morales\",\"email\": \"scott.morales@google.com\",\"birthdate\": \"1963-01-23\",\"city\": \"Gembloux\"}";
        String exception = null;
        try {
            lineProcessor.parseLine(line);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("Unknown attribute: 'firstnamewrong'.", exception);
    }

    public void parseLine_AttributeKeyMissing() {

        Event expected = new Event();
        expected.setEventType(EventTypeEnum.USER_CREATED);
        expected.setUser(new User(UUID.fromString("bdc9eaa6-bdfd-44d8-914d-7aac460e6e92"), "Scott", "Morales", "scott.morales@google.com", DateUtils.stringToDate("1963-01-23"), "Gembloux"));
        String line = "UserCreated;{\"id\": \"bdc9eaa6-bdfd-44d8-914d-7aac460e6e92\",\"firstname\":,\"surname\": \"Morales\",\"email\": \"scott.morales@google.com\",\"birthdate\": \"1963-01-23\",\"city\": \"Gembloux\"}";
        String exception = null;
        try {
            lineProcessor.parseLine(line);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("Invalid line.", exception);
    }

    public void parseLine_AttributeKeyEmpty() {

        Event expected = new Event();
        expected.setEventType(EventTypeEnum.USER_CREATED);
        expected.setUser(new User(UUID.fromString("bdc9eaa6-bdfd-44d8-914d-7aac460e6e92"), "Scott", "Morales", "scott.morales@google.com", DateUtils.stringToDate("1963-01-23"), "Gembloux"));
        String line = "UserCreated;{\"id\": \"bdc9eaa6-bdfd-44d8-914d-7aac460e6e92\",\"firstname\":\"\",\"surname\": \"Morales\",\"email\": \"scott.morales@google.com\",\"birthdate\": \"1963-01-23\",\"city\": \"Gembloux\"}";
        String exception = null;
        try {
            lineProcessor.parseLine(line);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("Invalid line.", exception);
    }

    public void parseLine_AttributeValueMissing() {

        Event expected = new Event();
        expected.setEventType(EventTypeEnum.USER_CREATED);
        expected.setUser(new User(UUID.fromString("bdc9eaa6-bdfd-44d8-914d-7aac460e6e92"), "Scott", "Morales", "scott.morales@google.com", DateUtils.stringToDate("1963-01-23"), "Gembloux"));
        String line = "UserCreated;{\"id\": \"bdc9eaa6-bdfd-44d8-914d-7aac460e6e92\",: \"Scott\",\"surname\": \"Morales\",\"email\": \"scott.morales@google.com\",\"birthdate\": \"1963-01-23\",\"city\": \"Gembloux\"}";
        String exception = null;
        try {
            lineProcessor.parseLine(line);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("Invalid line.", exception);
    }

    public void parseLine_AttributeValueEmpty() {

        Event expected = new Event();
        expected.setEventType(EventTypeEnum.USER_CREATED);
        expected.setUser(new User(UUID.fromString("bdc9eaa6-bdfd-44d8-914d-7aac460e6e92"), "Scott", "Morales", "scott.morales@google.com", DateUtils.stringToDate("1963-01-23"), "Gembloux"));
        String line = "UserCreated;{\"id\": \"bdc9eaa6-bdfd-44d8-914d-7aac460e6e92\",\"\": \"Scott\",\"surname\": \"Morales\",\"email\": \"scott.morales@google.com\",\"birthdate\": \"1963-01-23\",\"city\": \"Gembloux\"}";
        String exception = null;
        try {
            lineProcessor.parseLine(line);
        } catch (IllegalArgumentException e) {
            exception = e.getMessage();
        }
        Assert.assertEquals("Invalid line.", exception);
    }

}
