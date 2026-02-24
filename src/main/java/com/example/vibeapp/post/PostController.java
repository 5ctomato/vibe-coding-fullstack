package com.example.vibeapp.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String list(
            @org.springframework.web.bind.annotation.RequestParam(value = "page", defaultValue = "0") int page,
            Model model) {
        int size = 5;
        List<Post> posts = postService.getPaginated(page, size);
        int totalPages = postService.getTotalPages(size);

        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "post/posts";
    }

    @GetMapping("/posts/{no}")
    public String detail(@org.springframework.web.bind.annotation.PathVariable Long no, Model model) {
        Post post = postService.get(no);
        model.addAttribute("post", post);
        return "post/post_detail";
    }

    @GetMapping("/posts/new")
    public String createForm() {
        return "post/post_new_form";
    }

    @GetMapping("/posts/{no}/edit")
    public String updateForm(@org.springframework.web.bind.annotation.PathVariable Long no, Model model) {
        Post post = postService.get(no);
        model.addAttribute("post", post);
        return "post/post_edit_form";
    }

    @org.springframework.web.bind.annotation.PostMapping("/posts/add")
    public String create(@org.springframework.web.bind.annotation.RequestParam("title") String title,
            @org.springframework.web.bind.annotation.RequestParam("content") String content) {
        postService.create(title, content);
        return "redirect:/posts";
    }

    @org.springframework.web.bind.annotation.PostMapping("/posts/{no}/save")
    public String update(@org.springframework.web.bind.annotation.PathVariable Long no,
            @org.springframework.web.bind.annotation.RequestParam("title") String title,
            @org.springframework.web.bind.annotation.RequestParam("content") String content) {
        postService.update(no, title, content);
        return "redirect:/posts/" + no;
    }

    @org.springframework.web.bind.annotation.PostMapping("/posts/{no}/delete")
    public String delete(@org.springframework.web.bind.annotation.PathVariable Long no) {
        postService.delete(no);
        return "redirect:/posts";
    }
}
