package co.edu.udea.unittestcase.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/")
public class BaseController {

    @GetMapping
    public void redirect(HttpServletResponse httpServletResponse){
        httpServletResponse.setHeader("Location", "http://localhost:8080/swagger-ui.html");
        httpServletResponse.setStatus(302);
    }

}
