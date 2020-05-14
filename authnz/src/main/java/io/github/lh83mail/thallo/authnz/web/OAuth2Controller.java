package io.github.lh83mail.thallo.authnz.web;

import io.github.lh83mail.thallo.authnz.config.OAuth2ServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OAuth2Controller {
    private OAuth2ServerProperties oAuth2ServerProperties;

    @Autowired
    public OAuth2Controller(OAuth2ServerProperties oAuth2ServerProperties) {
        this.oAuth2ServerProperties = oAuth2ServerProperties;
    }

//    @GetMapping("/oauth/confirm_access")
//    public String confirmAccess(ModelMap modelMap, @AuthenticationPrincipal Principal principal, HttpServletRequest request) {
//        return "/authz/default/confirm_access";
//    }
}
