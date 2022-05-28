package git.dimitrikvirik.springjs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/auth")
    public String auth(){ return "auth";}

    @GetMapping("/user/**")
    public String user(){
        return "user";
    }




}
