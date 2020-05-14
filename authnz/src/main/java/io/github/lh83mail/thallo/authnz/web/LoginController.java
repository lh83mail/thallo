package io.github.lh83mail.thallo.authnz.web;

import io.github.lh83mail.thallo.authnz.config.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class LoginController {

    private SecurityProperties securityProperties;

    @Autowired
    public LoginController(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @GetMapping("/login")
    public String login(Model modelMap, @AuthenticationPrincipal Principal principal) {
        if (principal != null) {
            return "redirect:" + securityProperties.getLoginSuccessUrl();
        }
        return securityProperties.getLoginPage();
    }

    @GetMapping("/")
    public String loginSuccess(ModelMap modelMap, @AuthenticationPrincipal Principal principal) {
        return securityProperties.getLoginSuccessPage();
    }
}
