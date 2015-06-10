package com.drozd.controller;

import javax.validation.Valid;

import com.drozd.persistence.models.Account;
import com.drozd.persistence.models.Person;
import com.drozd.persistence.repository.AccountRepository;
import com.drozd.persistence.repository.PersonRepository;
import com.drozd.service.UserService;
import com.drozd.forms.SignupForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.drozd.support.web.*;

@Controller
public class SignupController {

    private static final String SIGNUP_VIEW = "signup/signup";

    private static final String SIGNUP_RM = "signup";

	@Autowired
	private AccountRepository accountRepository;

    @Autowired
    private PersonRepository personRepository;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = SIGNUP_RM)
	public String signup(Model model) {
		model.addAttribute(new SignupForm());
        return SIGNUP_VIEW;
	}
	
	@RequestMapping(value = SIGNUP_RM, method = RequestMethod.POST)
	public String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors, RedirectAttributes ra, Model model) {
		if (errors.hasErrors()) {
			return SIGNUP_VIEW;
		}
		Account account = accountRepository.save(signupForm.createAccount());
        Person person = signupForm.createPerson(account);
        personRepository.save(person);

		userService.signin(account);
        // see /WEB-INF/i18n/messages.properties and /WEB-INF/views/homeSignedIn.html
        MessageHelper.addSuccessAttribute(ra, "signup.success");
        model.addAttribute("person", person);
		return "redirect:/";
	}
}
