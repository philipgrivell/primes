package uk.grivell.primes;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PrimesErrorController implements ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public String handleError() {
        return "{\"error\" : \"Something went wrong\"}";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}