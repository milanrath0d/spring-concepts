package org.milan.dto;

import org.junit.Test;
import org.milan.model.Post;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertEquals;

/**
 * Test class for {@link PostDto}
 *
 * @author Milan Rathod
 */
public class PostDtoTest {

    private static final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertPostEntityToPostDto_thenCorrect() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("test");

        PostDto postDto = modelMapper.map(post, PostDto.class);
        assertEquals(post.getId(), postDto.getId());
        assertEquals(post.getTitle(), postDto.getTitle());
    }

    @Test
    public void whenConvertPostDtoToPostEntity_thenCorrect() {
        PostDto postDto = new PostDto();
        postDto.setId(1L);
        postDto.setTitle("test");

        Post post = modelMapper.map(postDto, Post.class);
        assertEquals(postDto.getId(), post.getId());
        assertEquals(postDto.getTitle(), post.getTitle());
    }

}