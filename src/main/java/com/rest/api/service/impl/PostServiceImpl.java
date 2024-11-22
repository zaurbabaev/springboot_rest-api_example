package com.rest.api.service.impl;

import com.rest.api.entity.Post;
import com.rest.api.exceptions.ResourceNotFoundException;
import com.rest.api.repository.PostRepository;
import com.rest.api.service.PostService;
import com.rest.api.utils.request.PostDTO;
import com.rest.api.utils.response.PostResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;


    @Override
    public List<PostResponseDTO> getAll() {
        return postRepository.findAll()
                .stream()
                .map(this::mapperToPostDTO)
                .toList();
    }

    @Override
    public Optional<PostResponseDTO> findById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        return Optional.of(mapperToPostDTO(post));
    }

    @Override
    public PostResponseDTO save(PostDTO dto) {
        Post post = new Post();
        post.setDescription(dto.getDescription());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setMaximumOfComments(dto.getMaximumOfComments());
        Post savedPost = postRepository.save(post);
        return mapperToPostDTO(savedPost);
    }

    @Override
    public PostResponseDTO update(PostDTO dto, Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        post.setDescription(dto.getDescription());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        postRepository.save(post);
        return mapperToPostDTO(post);
    }

    @Override
    public String delete(Long id) {
        postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found to delete"));
        postRepository.deleteById(id);
        return "Delete successfully";
    }


    private PostResponseDTO mapperToPostDTO(Post entity) {
        PostResponseDTO dto = new PostResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setMaximumOfComments(entity.getMaximumOfComments());
        dto.setContent(entity.getContent());

        if (entity.getComments() != null && entity.getComments().size() > 0) {
            dto.setComments(entity.getComments()
                    .stream().map(CommentServiceImpl::mapperToCommentDTO)
                    .collect(Collectors.toSet()));
        }
        return dto;

    }
}
