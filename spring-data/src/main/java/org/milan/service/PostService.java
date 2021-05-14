package org.milan.service;

import org.milan.model.Post;
import org.milan.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Milan Rathod
 */
@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void create(Post post) {
        postRepository.save(post);
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Post getById(Long id) {
        return postRepository.findById(id).orElse(null);
    }
}