package com.rest.api.utils.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest.api.entity.Post;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentResponseDTO {

    Long id;

    String name;

    String email;

    String body;

    @JsonIgnore
    Post post;

}
