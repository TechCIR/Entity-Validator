package techcr.utility.entityvalidator.type;

import org.junit.Assert;
import org.junit.Test;

public class ValidatorUtilTest {

    @Test
    public void testWithoutMessageResolver() {
        ValidatorUtil.configureResolver(key -> key);
        testSimpleResolveMessage();
        testResolveMessageWithOutKey();
        testResolveMessageWith();
        testResolveMessageWithAlreadyConfiguredMessageResolver();
    }

    void testSimpleResolveMessage() {
        String key = ValidatorUtil.MESSAGE_KEY_PREFIX + "name";
        String resolvedMessage = ValidatorUtil.resolveMessage(key);
        Assert.assertEquals("name", resolvedMessage);

    }

    void testResolveMessageWithOutKey() {
        String key = "name-1";
        String resolvedMessage = ValidatorUtil.resolveMessage(key);
        Assert.assertEquals(key, resolvedMessage);
    }

    void testResolveMessageWith() {
        ValidatorUtil.configureResolver(String::toUpperCase);
        String key = ValidatorUtil.MESSAGE_KEY_PREFIX + "name-2";
        String resolvedMessage = ValidatorUtil.resolveMessage(key);
        Assert.assertEquals("NAME-2", resolvedMessage);
    }

    void testResolveMessageWithAlreadyConfiguredMessageResolver() {
        String key = ValidatorUtil.MESSAGE_KEY_PREFIX + "name-2";
        String resolvedMessage = ValidatorUtil.resolveMessage(key);
        Assert.assertEquals("NAME-2", resolvedMessage);
    }
}
