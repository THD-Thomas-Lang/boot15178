package de.thd.okb.component;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

/**
 * Own validation constraint.
 * Checks if two (string) paramters are equal.
 *
 * @author tlang
 * @see <a href=https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#section-cross-parameter-constraints></a>
 */
@Slf4j
public class CrossPropertyStringMatcherValidator implements ConstraintValidator<CrossPropertyStringMatcher, Object> {

    private String targetField;
    private String sourceField;

    /**
     * Init method.
     *
     * @param constraint the given constraint
     */
    public void initialize(CrossPropertyStringMatcher constraint) {
        this.sourceField = constraint.sourceProperty();
        this.targetField = constraint.targetProperty();
    }

    /**
     * Validates the constraints.
     *
     * @param object                     a given object
     * @param constraintValidatorContext a given context
     * @return boolean
     */
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Object sourceField = getFieldValue(object, this.sourceField);
            Object targetField = getFieldValue(object, this.targetField);
            boolean result = (sourceField != null && targetField != null) && sourceField.equals(targetField);
            if (!result) {
                final String defaultConstraintMessageTemplate = constraintValidatorContext.getDefaultConstraintMessageTemplate();
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate(defaultConstraintMessageTemplate).addPropertyNode(this.targetField).addConstraintViolation();
            }
            return result;
        } catch (Exception exception) {
            log.error(exception.getMessage());
            // log error
            return false;
        }
    }

    private Object getFieldValue(Object object, String fieldName) throws Exception {
        Class<?> clazz = object.getClass();
        Field passwordField = clazz.getDeclaredField(fieldName);
        passwordField.setAccessible(true);
        return passwordField.get(object);
    }


}
