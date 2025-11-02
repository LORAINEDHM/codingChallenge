package test;

import model.EventTypeEnum;
import test.utils.Assert;

public class EventTypeEnumTest {

    public void isUpdateType_shouldReturnFalse() {
        Assert.assertFalse(EventTypeEnum.isUpdateType(EventTypeEnum.USER_CREATED));
    }

    public void isUpdateType_shouldReturnTrue() {
        Assert.assertTrue(EventTypeEnum.isUpdateType(EventTypeEnum.USER_BIRTHDATE_UPDATED));
        Assert.assertTrue(EventTypeEnum.isUpdateType(EventTypeEnum.USER_CITY_UPDATED));
        Assert.assertTrue(EventTypeEnum.isUpdateType(EventTypeEnum.USER_EMAIL_UPDATED));
        Assert.assertTrue(EventTypeEnum.isUpdateType(EventTypeEnum.USER_SURNAME_UPDATED));
        Assert.assertTrue(EventTypeEnum.isUpdateType(EventTypeEnum.USER_FIRSTNAME_UPDATED));
    }

    public void isCreateType_shouldReturnTrue() {
        Assert.assertFalse(EventTypeEnum.isUpdateType(EventTypeEnum.USER_CREATED));
    }

    public void isCreateType_shouldReturnFalse() {
        Assert.assertFalse(EventTypeEnum.isCreateType(EventTypeEnum.USER_BIRTHDATE_UPDATED));
        Assert.assertFalse(EventTypeEnum.isCreateType(EventTypeEnum.USER_CITY_UPDATED));
        Assert.assertFalse(EventTypeEnum.isCreateType(EventTypeEnum.USER_EMAIL_UPDATED));
        Assert.assertFalse(EventTypeEnum.isCreateType(EventTypeEnum.USER_SURNAME_UPDATED));
        Assert.assertFalse(EventTypeEnum.isCreateType(EventTypeEnum.USER_FIRSTNAME_UPDATED));
    }
}
