package org.milan.controller;

import org.milan.dto.PostDto;
import org.milan.model.Post;
import org.milan.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for {@link org.milan.model.Post}
 *
 * @author Milan Rathod
 */
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    private final ModelMapper modelMapper;

    public PostController(PostService postService, ModelMapper modelMapper) {
        this.postService = postService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public PostDto get(@PathVariable Long id) {
        return convertToDto(postService.getById(id));
    }

    @GetMapping
    public List<PostDto> getAll() {
        return postService.getAll().
            stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody PostDto postDto) throws ParseException {
        Post post = convertToEntity(postDto);
        postService.create(post);
    }

    private PostDto convertToDto(Post post) {
        PostDto postDto = modelMapper.map(post, PostDto.class);
        postDto.setSubmissionDate(post.getSubmissionDate(), "IST");
        return postDto;
    }

    private Post convertToEntity(PostDto postDto) throws ParseException {
        Post post = modelMapper.map(postDto, Post.class);
        post.setSubmissionDate(postDto.getSubmissionDateConverted("IST"));
        return post;
    }
}