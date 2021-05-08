package org.milan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

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
     *
     * @param pathVars
     * @return
     */
    @RequestMapping("/hello/{userName}")
    protected ModelAndView hello(@PathVariable Map<String, String> pathVars) {

        LOG.debug("Received Request for the user: {}", pathVars.get("userName"));

        ModelAndView modelAndView = new ModelAndView("HelloPage");

        modelAndView.addObject("welcomeMessage", "Hi " + pathVars.get("userName") + ", welcome to the hello page");

        return modelAndView;
    }

}
