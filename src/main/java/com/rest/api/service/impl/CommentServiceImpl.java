package com.rest.api.service.impl;

import com.rest.api.entity.Comment;
import com.rest.api.entity.Post;
import com.rest.api.exceptions.ResourceNotFoundException;
import com.rest.api.repository.CommentRepository;
import com.rest.api.repository.PostRepository;
import com.rest.api.service.CommentService;
import com.rest.api.utils.request.CommentDTO;
import com.rest.api.utils.response.CommentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public List<CommentResponseDTO> getAll() {
        return commentRepository.findAll()
                .stream()
                .map(CommentServiceImpl::mapperToCommentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CommentResponseDTO> findById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        return Optional.of(mapperToCommentDTO(comment));
    }


    @Override
    public CommentResponseDTO save(CommentDTO dto) {
        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + dto.getPostId()));
        Comment comment = new Comment();
        comment.setName(dto.getName());
        comment.setEmail(dto.getEmail());
        comment.setBody(dto.getBody());
        comment.setPost(post);
        return mapperToCommentDTO(commentRepository.save(comment));
    }

    @Override
    public CommentResponseDTO update(CommentDTO dto, Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found to update"));

        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("Post not found"));

        comment.setEmail(dto.getEmail());
        comment.setBody(dto.getBody());
        comment.setName(dto.getName());
        comment.setPost(post);
        return mapperToCommentDTO(commentRepository.save(comment));
    }

    @Override
    public String delete(Long id) {
        commentRepository.deleteById(id);
        return "Success";
    }

    public static CommentResponseDTO mapperToCommentDTO(Comment entity) {
        CommentResponseDTO dto = new CommentResponseDTO();
        dto.setId(entity.getId());
        dto.setPost(entity.getPost());
        dto.setName(entity.getName());
        dto.setBody(entity.getBody());
        dto.setEmail(entity.getEmail());
        return dto;

    }
}
