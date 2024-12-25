package com.example.simpleBlog.controller;

import com.example.simpleBlog.model.Post;
import com.example.simpleBlog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String home(Model model) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm, dd MMMM yyyy");
        String date = formatter.format(new Date());

        model.addAttribute("date", date);
        return "home";
    }

    @GetMapping("/posts")
    public String showAllPosts(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping("/add")
    public String showAddPostForm() {
        return "addPost";
    }

    @PostMapping("/add")
    public String addPost(@RequestParam String title, @RequestParam String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCreatedAt(new Date());
        postRepository.save(post);
        return "redirect:/posts";
    }
}
