package com.example.vibeapp.post;

import org.springframework.stereotype.Service;
import com.example.vibeapp.post.dto.PostCreateDto;
import com.example.vibeapp.post.dto.PostListDto;
import com.example.vibeapp.post.dto.PostResponseDto;
import com.example.vibeapp.post.dto.PostUpdateDto;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponseDto get(Long no) {
        Post post = postRepository.findByNo(no);
        if (post != null) {
            post.setViews(post.getViews() + 1);
        }
        return post != null ? PostResponseDto.from(post) : null;
    }

    public void create(PostCreateDto dto) {
        Post post = dto.toEntity();
        postRepository.save(post);
    }

    public void update(Long no, PostUpdateDto dto) {
        Post post = postRepository.findByNo(no);
        if (post != null) {
            post.setTitle(dto.getTitle());
            post.setContent(dto.getContent());
            post.setUpdatedAt(LocalDateTime.now());
        }
    }

    public void delete(Long no) {
        postRepository.deleteByNo(no);
    }

    public List<PostListDto> getPaginated(int page, int size) {
        return postRepository.findAllPaginated(page, size)
                .stream()
                .map(PostListDto::from)
                .toList();
    }

    public int getTotalPages(int size) {
        return (int) Math.ceil((double) postRepository.count() / size);
    }
}
