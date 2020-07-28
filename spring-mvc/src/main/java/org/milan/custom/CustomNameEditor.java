package org.milan.custom;

import java.beans.PropertyEditorSupport;

/**
 * Custom Name Editor
 */
public class CustomNameEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String studentName) {
        if (studentName.contains("Mr.") || studentName.contains("Ms.")) {
            setValue(studentName);
        } else {
            studentName = "Ms." + studentName;
            setValue(studentName);
        }
    }
}
