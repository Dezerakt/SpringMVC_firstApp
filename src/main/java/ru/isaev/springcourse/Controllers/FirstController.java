package ru.isaev.springcourse.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class FirstController {
    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model){
        model.addAttribute("message", name + " " + surname);

        return "Pages/hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage(@RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "surname", required = false) String surname){
        System.out.println(name + " " + surname);
        return "Pages/goodbye";
    }

    @GetMapping("/calc")
    public String calcPage(@RequestParam("a") int a,
                           @RequestParam("b") int b,
                           @RequestParam("action") String action,
                           Model model){
        double res = 0;
        switch (action){
            case "mult":
                res = a * b;
                break;
            case "add":
                res = a + b;
                break;
            case "sub":
                res = a - b;
                break;
            case "div":
                res = a / b;
                break;
        }
        model.addAttribute("val", res);

        return "Pages/calc";
    }
}
