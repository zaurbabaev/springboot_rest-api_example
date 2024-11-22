package com.rest.api.utils.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostResponseDTO {

    Long id;

    String title;

    String description;

    String content;

    Integer maximumOfComments;

    Set<CommentResponseDTO> comments;
}
