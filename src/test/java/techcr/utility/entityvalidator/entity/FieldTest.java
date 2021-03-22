package techcr.utility.entityvalidator.entity;

import java.util.List;
import java.util.Set;

import techcr.utility.entityvalidator.type.NumberCriteriaType;
import techcr.utility.entityvalidator.type.NumberEqualValidator;
import techcr.utility.entityvalidator.type.notation.ArrayType;
import techcr.utility.entityvalidator.type.notation.CollectionType;
import techcr.utility.entityvalidator.type.notation.ConditionRule;
import techcr.utility.entityvalidator.type.notation.ConditionValidation;
import techcr.utility.entityvalidator.type.notation.Constant;
import techcr.utility.entityvalidator.type.notation.CustomCollectionType;
import techcr.utility.entityvalidator.type.notation.EntityField;
import techcr.utility.entityvalidator.type.notation.EnumType;
import techcr.utility.entityvalidator.type.notation.Length;
import techcr.utility.entityvalidator.type.notation.LengthBetween;
import techcr.utility.entityvalidator.type.notation.Mandatory;
import techcr.utility.entityvalidator.type.notation.NotEmpty;
import techcr.utility.entityvalidator.type.notation.NumberField;
import techcr.utility.entityvalidator.type.notation.NumberFormat;
import techcr.utility.entityvalidator.type.notation.Range;
import techcr.utility.entityvalidator.type.notation.Regex;
import techcr.utility.entityvalidator.type.notation.ValidatorFieldDescription;

@ConditionValidation(
        rules = {@ConditionRule(rule = "conditionValues != null", errorDesc = "Condition value Should not be empty")},
        errorDesc = "Condition Value failed"
)
public class FieldTest {

    @ValidatorFieldDescription(fieldDesc = "Name")
    @Mandatory()
    private String name;
    @Mandatory(errorDesc = "Address Mandatory")
    private String address;

    @NotEmpty
    private String notEmptyString;

    @NumberField(number = "10", criteriaType = NumberCriteriaType.GREATER_THAN_OR_EQUAL)
    private Long greaterThanOrEqualNumberLong;
    @NumberField(number = "20.5", criteriaType = NumberCriteriaType.LESS_THAN)
    private Double lessThanNumberDouble;

    @ValidatorFieldDescription(fieldDesc = "Equal Prop")
    @Length(length = 2)
    private String equalProperty;
    @Length(length = 2)
    private long longProperty;
    @Length(length = 4, lengthCriteriaType = NumberCriteriaType.GREATER_THAN_OR_EQUAL)
    private String gtoqProperty;
    @Length(length = 4, lengthCriteriaType = NumberCriteriaType.GREATER_THAN)
    private String gtProperty;
    @Length(length = 4, lengthCriteriaType = NumberCriteriaType.LESS_THAN_OR_EQUAL)
    private String ltoqProperty;
    @Length(length = 4, lengthCriteriaType = NumberCriteriaType.LESS_THAN)
    private String ltProperty;
    @Length(length = 6)
    private Double doubleValiateProperty;

    @ValidatorFieldDescription(fieldDesc = "Average")
    @NumberFormat(numberFormat = "##.###", lengthCriteria = NumberCriteriaType.EQAUL)
    private Number averageScore;

    @ValidatorFieldDescription(fieldDesc = "Between length")
    @LengthBetween(minLength = 5, maxLength = 10, minLengthCriteria = NumberCriteriaType.GREATER_THAN_OR_EQUAL,
            maxLengthCriteria = NumberCriteriaType.LESS_THAN_OR_EQUAL)
    private String lenthPropery;
    @LengthBetween(minLength = 5, maxLength = 10, minLengthCriteria = NumberCriteriaType.GREATER_THAN_OR_EQUAL,
            maxLengthCriteria = NumberCriteriaType.LESS_THAN_OR_EQUAL)
    private Long numberLengthProperty;

    @ValidatorFieldDescription(fieldDesc = "Number Constant")
    @Constant(value = "3.14", errorDesc = "")
    private String doubleNumber;
    @Constant(value = "123", equalValidator = NumberEqualValidator.class)
    private Long longNumber;

    @ValidatorFieldDescription(fieldDesc = "Range")
    @Range(minValue = "10", maxValue = "100")
    private Long longRangeValue;
    @Range(minValue = "3.25", maxValue = "10.50")
    private Double doubleRangeValue;

    @ValidatorFieldDescription(fieldDesc = "Numeric")
    private String numericValue;

    @EntityField
    private User user;

    @ValidatorFieldDescription(fieldDesc = "Regex")
    @Regex(regex = "^[0-9]*$")
    private Long posNumberRegex;
    @Regex(regex = "^-[0-9]*$")
    private Long negNumberRegex;
    @Regex(regex = "^[1-9][0-9]*$")
    private Long nonZeroPosNumberRegex;
    @Regex(regex = "[a-zA-Z]+")
    private String stringRegex;
    @Regex(regex = ".+")
    private String anyStringRegex;

    @ValidatorFieldDescription(fieldDesc = "Condition")
    private String conditionValues;

    private techcr.utility.entityvalidator.entity.TestEnum testEnum;
    @ConditionValidation(
        rules = {@ConditionRule(rule = "testEnum == TestEnum.SUCCESS", errorDesc = "Enum Condition")},
        errorDesc = "Enum Condition"
    )
    private String conditionEnumValue;

    @ConditionValidation(
            rules = {@ConditionRule(rule = "conditionDepenField != null && conditionValues == null", errorDesc = "Condition 1 ")},
            errorDesc = "Condition 1 "
    )
    private String conditionValues2;
    private String conditionDepenField;
    @ConditionValidation(
            rules = {@ConditionRule(rule = "if (conditionValueCheck == 1 ) {conditionValues == null;} else { true; }", errorDesc = "Condition Equal Check 1 ")
                    ,@ConditionRule(rule = "if (conditionValueCheck == 2 ) {conditionDepenField != null && conditionValues == null;} else {true;}",
                    errorDesc = "Condition Equal Check 2 ")
            },
            objectAlias = "t",
            errorDesc = "Condition 1 "
    )
    private int conditionValueCheck;

    @ArrayType(minLength = 0, maxLength = 1)
    private User[] usersArrayWithoutInternalValidation;
    @ArrayType(minLength = 0, maxLength = 1, validateInnerEntity = true)
    private User[] usersArrayWithInternalValidation;

    @CollectionType(minLength = 0, maxLength = 1)
    private List<User> usersListWithoutInternalValidation;
    @CollectionType(minLength = 0, maxLength = 1, validateInnerEntity = true)
    private List<User> usersListWithInternalValidation;

    @CollectionType(minLength = 0, maxLength = 1)
    private Set<User> usersSetWithoutInternalValidation;
    @CollectionType(minLength = 0, maxLength = 1, validateInnerEntity = true)
    private Set<User> usersSetWithInternalValidation;

    @CustomCollectionType(minLength = 0, maxLength = 1, customCollectionClass = techcr.utility.entityvalidator.entity.UserList.class)
    private techcr.utility.entityvalidator.entity.UserList customListWithoutInternalValidation;
    @CustomCollectionType(minLength = 0, maxLength = 1, customCollectionClass = techcr.utility.entityvalidator.entity.UserList.class, validateInnerEntity = true)
    private techcr.utility.entityvalidator.entity.UserList customListWithInternalValidation;

    @EnumType(clazz = techcr.utility.entityvalidator.entity.TestEnum.class, validAny = true)
    private techcr.utility.entityvalidator.entity.TestEnum testEnumAny;
    @EnumType(clazz = techcr.utility.entityvalidator.entity.TestEnum.class, equalTo = 1)
    private techcr.utility.entityvalidator.entity.TestEnum testEnumEqualTo;
    @EnumType(clazz = techcr.utility.entityvalidator.entity.TestEnum.class, notEqualTo = 2)
    private techcr.utility.entityvalidator.entity.TestEnum testEnumAnyNotEqualTo;
    @EnumType(clazz = techcr.utility.entityvalidator.entity.TestEnum.class, notEqualTo = 3)
    private techcr.utility.entityvalidator.entity.TestEnum invalidIndex;


    //Getters Setters

    public techcr.utility.entityvalidator.entity.UserList getCustomListWithoutInternalValidation() {
        return customListWithoutInternalValidation;
    }

    public void setCustomListWithoutInternalValidation(techcr.utility.entityvalidator.entity.UserList customListWithoutInternalValidation) {
        this.customListWithoutInternalValidation = customListWithoutInternalValidation;
    }

    public techcr.utility.entityvalidator.entity.UserList getCustomListWithInternalValidation() {
        return customListWithInternalValidation;
    }

    public void setCustomListWithInternalValidation(techcr.utility.entityvalidator.entity.UserList customListWithInternalValidation) {
        this.customListWithInternalValidation = customListWithInternalValidation;
    }

    public Set<User> getUsersSetWithoutInternalValidation() {
        return usersSetWithoutInternalValidation;
    }

    public void setUsersSetWithoutInternalValidation(Set<User> usersSetWithoutInternalValidation) {
        this.usersSetWithoutInternalValidation = usersSetWithoutInternalValidation;
    }

    public Set<User> getUsersSetWithInternalValidation() {
        return usersSetWithInternalValidation;
    }

    public void setUsersSetWithInternalValidation(Set<User> usersSetWithInternalValidation) {
        this.usersSetWithInternalValidation = usersSetWithInternalValidation;
    }

    public List<User> getUsersListWithoutInternalValidation() {
        return usersListWithoutInternalValidation;
    }

    public void setUsersListWithoutInternalValidation(List<User> usersListWithoutInternalValidation) {
        this.usersListWithoutInternalValidation = usersListWithoutInternalValidation;
    }

    public List<User> getUsersListWithInternalValidation() {
        return usersListWithInternalValidation;
    }

    public void setUsersListWithInternalValidation(List<User> usersListWithInternalValidation) {
        this.usersListWithInternalValidation = usersListWithInternalValidation;
    }

    public User[] getUsersArrayWithoutInternalValidation() {
        return usersArrayWithoutInternalValidation;
    }

    public void setUsersArrayWithoutInternalValidation(User[] usersArrayWithoutInternalValidation) {
        this.usersArrayWithoutInternalValidation = usersArrayWithoutInternalValidation;
    }

    public User[] getUsersArrayWithInternalValidation() {
        return usersArrayWithInternalValidation;
    }

    public void setUsersArrayWithInternalValidation(User[] usersArrayWithInternalValidation) {
        this.usersArrayWithInternalValidation = usersArrayWithInternalValidation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNotEmptyString() {
        return notEmptyString;
    }

    public void setNotEmptyString(String notEmptyString) {
        this.notEmptyString = notEmptyString;
    }

    public Long getGreaterThanOrEqualNumberLong() {
        return greaterThanOrEqualNumberLong;
    }

    public void setGreaterThanOrEqualNumberLong(Long greaterThanOrEqualNumberLong) {
        this.greaterThanOrEqualNumberLong = greaterThanOrEqualNumberLong;
    }

    public Double getLessThanNumberDouble() {
        return lessThanNumberDouble;
    }

    public void setLessThanNumberDouble(Double lessThanNumberDouble) {
        this.lessThanNumberDouble = lessThanNumberDouble;
    }

    public String getEqualProperty() {
        return equalProperty;
    }

    public void setEqualProperty(String equalProperty) {
        this.equalProperty = equalProperty;
    }

    public long getLongProperty() {
        return longProperty;
    }

    public void setLongProperty(long longProperty) {
        this.longProperty = longProperty;
    }

    public String getGtoqProperty() {
        return gtoqProperty;
    }

    public void setGtoqProperty(String gtoqProperty) {
        this.gtoqProperty = gtoqProperty;
    }

    public String getGtProperty() {
        return gtProperty;
    }

    public void setGtProperty(String gtProperty) {
        this.gtProperty = gtProperty;
    }

    public String getLtoqProperty() {
        return ltoqProperty;
    }

    public void setLtoqProperty(String ltoqProperty) {
        this.ltoqProperty = ltoqProperty;
    }

    public String getLtProperty() {
        return ltProperty;
    }

    public void setLtProperty(String ltProperty) {
        this.ltProperty = ltProperty;
    }

    public Double getDoubleValiateProperty() {
        return doubleValiateProperty;
    }

    public void setDoubleValiateProperty(Double doubleValiateProperty) {
        this.doubleValiateProperty = doubleValiateProperty;
    }

    public Number getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Number averageScore) {
        this.averageScore = averageScore;
    }

    public String getLenthPropery() {
        return lenthPropery;
    }

    public void setLenthPropery(String lenthPropery) {
        this.lenthPropery = lenthPropery;
    }

    public Long getNumberLengthProperty() {
        return numberLengthProperty;
    }

    public void setNumberLengthProperty(Long numberLengthProperty) {
        this.numberLengthProperty = numberLengthProperty;
    }

    public String getDoubleNumber() {
        return doubleNumber;
    }

    public void setDoubleNumber(String doubleNumber) {
        this.doubleNumber = doubleNumber;
    }

    public Long getLongNumber() {
        return longNumber;
    }

    public void setLongNumber(Long longNumber) {
        this.longNumber = longNumber;
    }

    public Long getLongRangeValue() {
        return longRangeValue;
    }

    public void setLongRangeValue(Long longRangeValue) {
        this.longRangeValue = longRangeValue;
    }

    public Double getDoubleRangeValue() {
        return doubleRangeValue;
    }

    public void setDoubleRangeValue(Double doubleRangeValue) {
        this.doubleRangeValue = doubleRangeValue;
    }

    public String getNumericValue() {
        return numericValue;
    }

    public void setNumericValue(String numericValue) {
        this.numericValue = numericValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getPosNumberRegex() {
        return posNumberRegex;
    }

    public void setPosNumberRegex(Long posNumberRegex) {
        this.posNumberRegex = posNumberRegex;
    }

    public Long getNegNumberRegex() {
        return negNumberRegex;
    }

    public void setNegNumberRegex(Long negNumberRegex) {
        this.negNumberRegex = negNumberRegex;
    }

    public Long getNonZeroPosNumberRegex() {
        return nonZeroPosNumberRegex;
    }

    public void setNonZeroPosNumberRegex(Long nonZeroPosNumberRegex) {
        this.nonZeroPosNumberRegex = nonZeroPosNumberRegex;
    }

    public String getStringRegex() {
        return stringRegex;
    }

    public void setStringRegex(String stringRegex) {
        this.stringRegex = stringRegex;
    }

    public String getAnyStringRegex() {
        return anyStringRegex;
    }

    public void setAnyStringRegex(String anyStringRegex) {
        this.anyStringRegex = anyStringRegex;
    }

    public String getConditionValues() {
        return conditionValues;
    }

    public void setConditionValues(String conditionValues) {
        this.conditionValues = conditionValues;
    }

    public techcr.utility.entityvalidator.entity.TestEnum getTestEnum() {
        return testEnum;
    }

    public void setTestEnum(techcr.utility.entityvalidator.entity.TestEnum testEnum) {
        this.testEnum = testEnum;
    }

    public String getConditionEnumValue() {
        return conditionEnumValue;
    }

    public void setConditionEnumValue(String conditionEnumValue) {
        this.conditionEnumValue = conditionEnumValue;
    }

    public String getConditionValues2() {
        return conditionValues2;
    }

    public void setConditionValues2(String conditionValues2) {
        this.conditionValues2 = conditionValues2;
    }

    public String getConditionDepenField() {
        return conditionDepenField;
    }

    public void setConditionDepenField(String conditionDepenField) {
        this.conditionDepenField = conditionDepenField;
    }

    public int getConditionValueCheck() {
        return conditionValueCheck;
    }

    public void setConditionValueCheck(int conditionValueCheck) {
        this.conditionValueCheck = conditionValueCheck;
    }

    public techcr.utility.entityvalidator.entity.TestEnum getTestEnumAny() {
        return testEnumAny;
    }

    public void setTestEnumAny(techcr.utility.entityvalidator.entity.TestEnum testEnumAny) {
        this.testEnumAny = testEnumAny;
    }

    public techcr.utility.entityvalidator.entity.TestEnum getTestEnumEqualTo() {
        return testEnumEqualTo;
    }

    public void setTestEnumEqualTo(techcr.utility.entityvalidator.entity.TestEnum testEnumEqualTo) {
        this.testEnumEqualTo = testEnumEqualTo;
    }

    public techcr.utility.entityvalidator.entity.TestEnum getTestEnumAnyNotEqualTo() {
        return testEnumAnyNotEqualTo;
    }

    public void setTestEnumAnyNotEqualTo(techcr.utility.entityvalidator.entity.TestEnum testEnumAnyNotEqualTo) {
        this.testEnumAnyNotEqualTo = testEnumAnyNotEqualTo;
    }

    public techcr.utility.entityvalidator.entity.TestEnum getInvalidIndex() {
        return invalidIndex;
    }

    public void setInvalidIndex(techcr.utility.entityvalidator.entity.TestEnum invalidIndex) {
        this.invalidIndex = invalidIndex;
    }
}
