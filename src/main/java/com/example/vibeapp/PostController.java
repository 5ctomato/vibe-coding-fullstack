package com.example.vibeapp;

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
    public String listPosts(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping("/posts/{no}")
    public String detailPost(@org.springframework.web.bind.annotation.PathVariable Long no, Model model) {
        Post post = postService.getPost(no);
        model.addAttribute("post", post);
        return "post_detail";
    }

    @GetMapping("/posts/new")
    public String newPostForm() {
        return "post_new_form";
    }

    @GetMapping("/posts/{no}/edit")
    public String editPostForm(@org.springframework.web.bind.annotation.PathVariable Long no, Model model) {
        Post post = postService.getPost(no);
        model.addAttribute("post", post);
        return "post_edit_form";
    }

    @org.springframework.web.bind.annotation.PostMapping("/posts/add")
    public String addPost(@org.springframework.web.bind.annotation.RequestParam("title") String title,
            @org.springframework.web.bind.annotation.RequestParam("content") String content) {
        postService.addPost(title, content);
        return "redirect:/posts";
    }

    @org.springframework.web.bind.annotation.PostMapping("/posts/{no}/save")
    public String savePost(@org.springframework.web.bind.annotation.PathVariable Long no,
            @org.springframework.web.bind.annotation.RequestParam("title") String title,
            @org.springframework.web.bind.annotation.RequestParam("content") String content) {
        postService.updatePost(no, title, content);
        return "redirect:/posts/" + no;
    }
}
