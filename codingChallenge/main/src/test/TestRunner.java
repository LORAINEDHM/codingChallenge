package test;

public class TestRunner {

    public static void main(String[] args) {

        System.out.println("\n");
        System.out.println("=======================================");
        System.out.println("Starting Dedicated JDK Unit Test Runner");
        System.out.println("=======================================");

        EventTypeEnumTest eventTypeEnumTest = new EventTypeEnumTest();
        System.out.println("\nTesting : eventTypeEnumTest.isUpdateType");
        eventTypeEnumTest.isUpdateType_shouldReturnTrue();
        eventTypeEnumTest.isUpdateType_shouldReturnFalse();

        System.out.println("\nTesting : eventTypeEnumTest.isCreateType");
        eventTypeEnumTest.isCreateType_shouldReturnTrue();
        eventTypeEnumTest.isCreateType_shouldReturnFalse();

        LineProcessorTest lineProcessorTest = new LineProcessorTest();
        System.out.println("\nTesting : lineProcessorTest.parseLine");
        lineProcessorTest.parseLine_TooManyColumns();
        lineProcessorTest.parseLine_OneColumnMissing();
        lineProcessorTest.parseLine_ok();
        lineProcessorTest.parseLine_UnknownEventType();
        lineProcessorTest.parseLine_idWrongFormat();
        lineProcessorTest.parseLine_dateWrongFormat();
        lineProcessorTest.parseLine_UnknownAttribute();
        lineProcessorTest.parseLine_AttributeKeyMissing();
        lineProcessorTest.parseLine_AttributeKeyEmpty();
        lineProcessorTest.parseLine_AttributeValueMissing();
        lineProcessorTest.parseLine_AttributeValueEmpty();

        DatabaseTest databaseTest = new DatabaseTest();
        System.out.println("\nTesting : databaseTest.addUser");
        databaseTest.addUser_ok();
        databaseTest.addUser_userAlreadyExists();


    }
}
