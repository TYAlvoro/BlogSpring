package yahyaev.petsn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        if (!preview.endsWith("...")) {
            preview += "...";
            post.setPreview(preview);
        }
        postRepository.save(post);
        return "redirect:/main";
    }

    @GetMapping("/view-post/{id}")
    public String viewPost(@PathVariable long id, Model model) {
        var post = postRepository.findById(id);
        model.addAttribute("post", post.get());
        return "view-post";
    }

    @GetMapping("/edit-post/{id}")
    public String editPost(@PathVariable long id, Model model) {
        var post = postRepository.findById(id);
        model.addAttribute("post", post.get());
        return "edit-post";
    }

    @PostMapping("/edit-post/{id}")
    public String editOldPost(@ModelAttribute("post") Post post) {
        String preview = post.getPreview();
        if (!preview.endsWith("...")) {
            preview += "...";
            post.setPreview(preview);
        }
        postRepository.save(post);
        return "redirect:/view-post/" + post.getId();
    }

}
