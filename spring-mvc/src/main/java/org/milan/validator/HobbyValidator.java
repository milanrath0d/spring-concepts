package org.milan.validator;

import org.milan.annotation.IsValidHobby;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Milan Rathod
 */
public class HobbyValidator implements ConstraintValidator<IsValidHobby, String> {

    private String listOfHobbies;

    @Override
    public void initialize(IsValidHobby isValidHobby) {
        this.listOfHobbies = isValidHobby.listOfHobbies();
    }

    @Override
    public boolean isValid(String studentHobby, ConstraintValidatorContext paramConstraintValidatorContext) {
        return studentHobby.matches(listOfHobbies);
    }

}
