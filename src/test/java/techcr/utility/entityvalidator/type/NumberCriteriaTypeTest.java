package techcr.utility.entityvalidator.type;

import org.junit.Assert;
import org.junit.Test;

public class NumberCriteriaTypeTest {

    @Test
    public void isValid() throws Exception {

        int thresholdLength = 2;

        NumberCriteriaType criteriaType = NumberCriteriaType.EQAUL;
        boolean isValid = criteriaType.isValid(thresholdLength, 2);
        Assert.assertTrue(isValid);
        isValid = criteriaType.isValid(thresholdLength, 1);
        Assert.assertFalse(isValid);

        criteriaType = NumberCriteriaType.LESS_THAN_OR_EQUAL;
        isValid = criteriaType.isValid(thresholdLength, 2);
        Assert.assertTrue(isValid);
        isValid = criteriaType.isValid(thresholdLength, 1);
        Assert.assertTrue(isValid);
        isValid = criteriaType.isValid(thresholdLength, 3);
        Assert.assertFalse(isValid);

        criteriaType = NumberCriteriaType.LESS_THAN;
        isValid = criteriaType.isValid(thresholdLength, 2);
        Assert.assertFalse(isValid);
        isValid = criteriaType.isValid(thresholdLength, 1);
        Assert.assertTrue(isValid);

        criteriaType = NumberCriteriaType.GREATER_THAN_OR_EQUAL;
        isValid = criteriaType.isValid(thresholdLength, 2);
        Assert.assertTrue(isValid);
        isValid = criteriaType.isValid(thresholdLength, 3);
        Assert.assertTrue(isValid);
        isValid = criteriaType.isValid(thresholdLength, 1);
        Assert.assertFalse(isValid);


        criteriaType = NumberCriteriaType.GREATER_THAN;
        isValid = criteriaType.isValid(thresholdLength, 2);
        Assert.assertFalse(isValid);
        isValid = criteriaType.isValid(thresholdLength, 3);
        Assert.assertTrue(isValid);

    }
}
