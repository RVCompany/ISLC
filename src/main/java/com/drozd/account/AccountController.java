package com.drozd.account;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Secured("ROLE_USER")
class AccountController {

    private AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @RequestMapping(value = "account/current", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Account accounts(Principal principal) {
        Assert.notNull(principal);
        return accountRepository.findByEmail(principal.getName());
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ModelAndView list(Principal principal) {
        Assert.notNull(principal);
        ModelAndView modelAndView = new ModelAndView("list/employeeList");
        modelAndView.addObject("accounts", accountRepository.getAllAccounts());
        return modelAndView;
    }
}
