package yahyaev.petsn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import yahyaev.petsn.model.Post;
import yahyaev.petsn.repositories.PostRepository;

@Controller
public class MainController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/main")
    public String index(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping("/new-post")
    public String newPost(Model model) {
        model.addAttribute("post", new Post());
        return "new-post";
    }

    @PostMapping("/new-post")
    public String addNewPost(@ModelAttribute("post") Post post) {
        String preview = post.getPreview();
        preview += "...";
        post.setPreview(preview);
        postRepository.save(post);
        return "redirect:/main";
    }

}
