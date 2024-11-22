package com.rest.api.utils.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostDTO {

    String title;

    String description;

    Integer maximumOfComments;

    String content;



}
