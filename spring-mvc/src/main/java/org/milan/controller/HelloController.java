package org.milan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 * Hello Controller
 *
 * @author Milan Rathod
 */
@Controller
public class HelloController {

    private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);

    /**
     * Hello API to Display Hello Page
     */
    @GetMapping("/hello/{userName}")
    public ModelAndView hello(@PathVariable String userName) {

        LOG.debug("Received Request for the user: {}", userName);

        ModelAndView modelAndView = new ModelAndView("HelloPage");

        modelAndView.addObject("welcomeMessage", "Hi " + userName + ", welcome to the hello page");

        return modelAndView;
    }

}
