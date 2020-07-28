package org.milan.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.milan.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility Class to get all dummy students details
 *
 * @author Milan Rathod
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DummyStudents {

    public static List<Student> allStudents() {
        List<Student> studentList = new ArrayList<>();

        Student s1 = new Student();
        s1.setName("Test_1");
        s1.setHobby("Cricket");

        Student s2 = new Student();
        s2.setName("Test_2");
        s2.setHobby("Badminton");

        Student s3 = new Student();
        s3.setName("Test_3");
        s3.setHobby("Tennis");

        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);

        return studentList;

    }
}
