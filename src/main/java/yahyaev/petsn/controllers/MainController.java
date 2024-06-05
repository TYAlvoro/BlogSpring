package yahyaev.petsn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {
    @GetMapping("/main")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/new-post")
    public String newPost(Model model) {
        return "new-post";
    }
}
