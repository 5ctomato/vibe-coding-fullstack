package com.example.vibeapp;

import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepository {
    private final List<Post> posts = new ArrayList<>();

    public PostRepository() {
        // 샘플 데이터 10개 생성
        for (long i = 1; i <= 10; i++) {
            posts.add(new Post(
                    i,
                    "Vibe Coding의 정수 " + i,
                    "이것은 세련된 코드의 시대를 여는 고에너지 게시글 " + i + "입니다.",
                    LocalDateTime.now().minusDays(10 - i),
                    (int) (Math.random() * 100)));
        }
    }

    public List<Post> findAll() {
        List<Post> sortedList = new ArrayList<>(posts);
        sortedList.sort((p1, p2) -> p2.getNo().compareTo(p1.getNo()));
        return sortedList;
    }

    public Post findByNo(Long no) {
        return posts.stream()
                .filter(post -> post.getNo().equals(no))
                .findFirst()
                .orElse(null);
    }

    public void save(Post post) {
        long nextNo = posts.stream()
                .mapToLong(Post::getNo)
                .max()
                .orElse(0L) + 1;
        post.setNo(nextNo);
        posts.add(post);
    }

    public void deleteByNo(Long no) {
        posts.removeIf(post -> post.getNo().equals(no));
    }

    public List<Post> findAllPaginated(int page, int size) {
        List<Post> sortedList = findAll(); // This already returns a sorted copy
        int fromIndex = page * size;
        if (fromIndex >= sortedList.size()) {
            return new ArrayList<>();
        }
        int toIndex = Math.min(fromIndex + size, sortedList.size());
        return sortedList.subList(fromIndex, toIndex);
    }

    public long count() {
        return posts.size();
    }
}
