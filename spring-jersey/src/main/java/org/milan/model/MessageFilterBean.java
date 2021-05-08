package org.milan.model;

import lombok.Data;

import javax.ws.rs.QueryParam;

/**
 * Message filter bean
 *
 * @author Milan Rathod
 */
@Data
public class MessageFilterBean {

    private @QueryParam("year")
    int year;

    private @QueryParam("start")
    int start;

    private @QueryParam("size")
    int size;

}
