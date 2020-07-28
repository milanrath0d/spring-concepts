package org.milan.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import org.milan.annotation.IsValidHobby;

import javax.validation.constraints.Max;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;

/**
 * Student Class
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"mobile"})
@JsonPropertyOrder({"student_name", "hobby", "mobile", "dob", "address", "skills"})
public class Student {

    /**
     * Name of the student
     */
    @Pattern(regexp = "[^0-9]*")
    @JsonProperty("student_name")
    private String name;

    /**
     * Hobby
     */
    @Size(min = 2, max = 30, message = "Please Entry Size between {min} and {max} characters.")
    @IsValidHobby
    private String hobby;

    /**
     * Mobile Number
     */
    @Max(222)
    private Long mobile;

    /**
     * Date Of Birth
     */
    @Past
    private Date dob;

    /**
     * Address
     */
    private Address address;

    /**
     * Skills
     */
    private ArrayList<String> skills;

}
