package com.example.vibeapp.post;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post get(Long no) {
        Post post = postRepository.findByNo(no);
        if (post != null) {
            post.setViews(post.getViews() + 1);
        }
        return post;
    }

    public void create(String title, String content) {
        Post post = new Post(null, title, content, java.time.LocalDateTime.now(), 0);
        post.setUpdatedAt(null); // Explicitly set as per requirement
        postRepository.save(post);
    }

    public void update(Long no, String title, String content) {
        Post post = postRepository.findByNo(no);
        if (post != null) {
            post.setTitle(title);
            post.setContent(content);
            post.setUpdatedAt(java.time.LocalDateTime.now());
        }
    }

    public void delete(Long no) {
        postRepository.deleteByNo(no);
    }

    public List<Post> getPaginated(int page, int size) {
        return postRepository.findAllPaginated(page, size);
    }

    public int getTotalPages(int size) {
        return (int) Math.ceil((double) postRepository.count() / size);
    }
}
