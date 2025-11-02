package test.utils;

import model.Event;

public class Assert {

    // ANSI escape codes for colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void assertTrue(boolean bool) {
        if (!bool)
            System.out.println(printFail());
        else
            System.out.println(printSuccess());
    }

    public static void assertFalse(boolean bool) {
        if (bool)
            System.out.println(printFail());
        else
            System.out.println(printSuccess());
    }

    public static void assertNull(Object obj) {
        if (obj != null)
            System.out.println(printFail());
        else
            System.out.println(printSuccess());
    }

    public static void assertNotNull(Object obj) {
        if (obj == null)
            System.out.println(printFail());
        else
            System.out.println(printSuccess());
    }

    public static void assertEquals(String expected, String actual) {
        if (expected == null || !expected.equals(actual))
            System.out.println(printFail() + " Expected: '" + expected + "'  /  Actual: '" + actual + "'");
        else
            System.out.println(printSuccess());
    }

    public static void assertEquals(int expected, int actual) {
        if (!(expected == actual))
            System.out.println(printFail() + " Expected: '" + expected + "'  /  Actual: '" + actual + "'");
        else
            System.out.println(printSuccess());
    }

    public static void assertEquals(Event expected, Event actual) {
        if (actual == null) {
            System.out.println(printFail());
        }
        else if (!expected.getEventType().equals(actual.getEventType())) {
            System.out.println(printFail() + " Expected: '" + expected.getEventType() + "'  /  Actual: '" + actual.getEventType() + "'");
        }
        if (expected == null && actual == null) {
            System.out.println(printSuccess());
        }
        else if (expected != null && actual == null) {
            System.out.println(printFail() + " Expected: '" + expected.getUser() + "'  /  Actual: '" + actual.getUser() + "'");
        }
        else if (expected == null && actual != null) {
            System.out.println(printFail() + " user ->     Expected: '" + expected.getUser() + "'  /  Actual: '" + actual.getUser() + "'");
        }
        else if (!expected.getUser().getId().equals(actual.getUser().getId())) {
            System.out.println(printFail()+ " id ->     Expected: '" + expected.getUser().getId() + "'  /  Actual: '" + actual.getUser().getId() + "'");
        }
        else if (!expected.getUser().getFirstname().equals(actual.getUser().getFirstname())) {
            System.out.println(printFail()+ " firstname ->     Expected: '" + expected.getUser().getFirstname() + "'  /  Actual: '" + actual.getUser().getFirstname() + "'");
        }
        else if (!expected.getUser().getSurname().equals(actual.getUser().getSurname())) {
            System.out.println(printFail()+ " surname ->     Expected: '" + expected.getUser().getSurname() + "'  /  Actual: '" + actual.getUser().getSurname() + "'");
        }
        else if (!expected.getUser().getEmail().equals(actual.getUser().getEmail())) {
            System.out.println(printFail()+ " email ->     Expected: '" + expected.getUser().getEmail() + "'  /  Actual: '" + actual.getUser().getEmail() + "'");
        }
        else if (!expected.getUser().getBirthdate().equals(actual.getUser().getBirthdate())) {
            System.out.println(printFail()+ " birthdate ->     Expected: '" + expected.getUser().getBirthdate() + "'  /  Actual: '" + actual.getUser().getBirthdate() + "'");
        }
        else if (!expected.getUser().getCity().equals(actual.getUser().getCity())) {
            System.out.println(printFail()+ " city ->     Expected: '" + expected.getUser().getCity() + "'  /  Actual: '" + actual.getUser().getCity() + "'");
        }
        else
            System.out.println(printSuccess());
    }

    private static String printSuccess() {
        return ANSI_GREEN + "[SUCCESS]" + ANSI_RESET;
    }

    private static String printFail() {
        return ANSI_RED + "[FAIL]" + ANSI_RESET;
    }

}
