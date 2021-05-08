package org.milan.controller;

import org.milan.custom.CustomNameEditor;
import org.milan.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Controller for Student Admission
 *
 * @author Milan Rathod
 */
@Controller
public class StudentAdmissionController {

    private static final Logger LOG = LoggerFactory.getLogger(StudentAdmissionController.class);

    @InitBinder
    public void intiBinder(WebDataBinder binder) {

        /**
         * Remove any property from data binding
         */
        binder.setDisallowedFields("mobile");

        /**
         * Built in Date Property Editor
         */
        SimpleDateFormat format = new SimpleDateFormat("dd/mm*yyyy");

        binder.registerCustomEditor(Date.class, "dob", new CustomDateEditor(format, false));

        /**
         * Custom Property Editor
         */
        binder.registerCustomEditor(String.class, "name", new CustomNameEditor());
    }

    /**
     * Adding Common Method to display Header Message in Every Page
     */
    @ModelAttribute
    public void commonHeader(Model model) {
        model.addAttribute("headerMessage", "Admission Form");
    }

    @GetMapping(value = "/admissionForm")
    public ModelAndView getAdmissionForm() {

        LOG.debug("Received Request for Admission Form");

        String npe = "Test";
        if (npe.equals("Test")) {
            throw new NullPointerException();
        }

        return new ModelAndView("AdmissionForm");

    }

    @PostMapping(value = "/submitAdmissionForm")
    public ModelAndView submitAdmissionForm(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {

        LOG.debug("User Submitted Admission Form");

        if (bindingResult.hasErrors()) {
            return new ModelAndView("AdmissionForm");
        }

        return new ModelAndView("AdmissionSuccess");

    }
}
