package com.example.vibeapp.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.example.vibeapp.post.dto.PostCreateDto;
import com.example.vibeapp.post.dto.PostListDto;
import com.example.vibeapp.post.dto.PostResponseDto;
import com.example.vibeapp.post.dto.PostUpdateDto;
import java.util.List;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String list(
            @RequestParam(value = "page", defaultValue = "0") int page,
            Model model) {
        int size = 5;
        List<PostListDto> posts = postService.getPaginated(page, size);
        int totalPages = postService.getTotalPages(size);

        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "post/posts";
    }

    @GetMapping("/posts/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        PostResponseDto post = postService.get(no);
        model.addAttribute("post", post);
        return "post/post_detail";
    }

    @GetMapping("/posts/new")
    public String createForm(Model model) {
        model.addAttribute("postCreateDto", new PostCreateDto());
        return "post/post_new_form";
    }

    @GetMapping("/posts/{no}/edit")
    public String updateForm(@PathVariable("no") Long no, Model model) {
        PostResponseDto post = postService.get(no);
        model.addAttribute("post", post);
        PostUpdateDto dto = new PostUpdateDto(post.getTitle(), post.getContent());
        model.addAttribute("postUpdateDto", dto);
        return "post/post_edit_form";
    }

    @PostMapping("/posts/add")
    public String create(@Valid @ModelAttribute PostCreateDto postCreateDto,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "post/post_new_form";
        }
        postService.create(postCreateDto);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{no}/save")
    public String update(@PathVariable("no") Long no,
            @Valid @ModelAttribute PostUpdateDto postUpdateDto,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            PostResponseDto post = postService.get(no);
            model.addAttribute("post", post);
            return "post/post_edit_form";
        }
        postService.update(no, postUpdateDto);
        return "redirect:/posts/" + no;
    }

    @PostMapping("/posts/{no}/delete")
    public String delete(@PathVariable("no") Long no) {
        postService.delete(no);
        return "redirect:/posts";
    }
}
