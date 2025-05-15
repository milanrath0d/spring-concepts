package org.milan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author Milan Rathod
 */
@XmlRootElement
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private int code;

    private String message;

    private String documentation;

}
