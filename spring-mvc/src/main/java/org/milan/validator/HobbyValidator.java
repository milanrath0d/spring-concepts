package org.milan.validator;

import org.milan.annotation.IsValidHobby;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HobbyValidator implements ConstraintValidator<IsValidHobby, String> {

    private String listOfHobbies;

    @Override
    public void initialize(IsValidHobby isValidHobby) {
        this.listOfHobbies = isValidHobby.listOfHobbies();
    }

    @Override
    public boolean isValid(String studentHobby,
                           ConstraintValidatorContext paramConstraintValidatorContext) {
        if (studentHobby.matches(listOfHobbies)) {
            return true;
        }
        return false;
    }

}
