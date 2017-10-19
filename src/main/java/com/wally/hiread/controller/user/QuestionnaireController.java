package com.wally.hiread.controller.user;

import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.Questionnaire;
import com.wally.hiread.model.user.User;
import com.wally.hiread.service.sys.SecurityUserManager;
import com.wally.hiread.service.user.QuestionnaireService;
import com.wally.hiread.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by eric on 30/08/2017.
 */
@Controller
@RequestMapping(value = "/user/questionnaire")
public class QuestionnaireController {
    @Autowired
    QuestionnaireService questionnaireService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "")
    public String index() {
        return "user/questionnaire";
    }


    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public @ResponseBody SR submit(Questionnaire questionnaire) {
        SR sr = new SR();

        int score = questionnaireService.calculateTotalScore(questionnaire);

        User user = SecurityUserManager.getUser();
        questionnaire.setScore(score);
        userService.saveQuestionnaire(questionnaire, user);

        sr.setStatus(SR.SUCCESS);
        sr.setEntity(score);

        return sr;
    }
}
