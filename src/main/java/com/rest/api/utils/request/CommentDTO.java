package com.rest.api.utils.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentDTO {

    String name;

    String email;

    String body;

    Long postId;
}
