package com.drozd.person;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
class PersonController {

    private static final String PERSONAL_DATA_VIEW = "personalData/personalData";

    @RequestMapping(value = "personalData", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public String accounts() {
        return PERSONAL_DATA_VIEW;
    }
}
