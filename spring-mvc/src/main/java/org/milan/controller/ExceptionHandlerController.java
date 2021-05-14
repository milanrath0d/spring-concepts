package org.milan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Milan Rathod
 */
@RestController
@RequestMapping("/exception")
public class ExceptionHandlerController {

    /**
     * To test exception handlers
     */
    @GetMapping("/test")
    public ModelAndView test() {
        throwIllegalArgumentException();

        return new ModelAndView();
    }

    private void throwIllegalArgumentException() {
        throw new IllegalArgumentException();
    }

    private void throwNullPointerException() {
        throw new NullPointerException();
    }
}
