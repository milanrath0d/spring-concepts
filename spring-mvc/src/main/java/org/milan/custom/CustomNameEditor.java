package org.milan.custom;

import java.beans.PropertyEditorSupport;

/**
 * Custom Name Editor
 *
 * @author Milan Rathod
 */
public class CustomNameEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String studentName) {
        if (!studentName.contains("Mr.") && !studentName.contains("Ms.")) {
            studentName = "Ms." + studentName;
        }
        setValue(studentName);
    }
}
