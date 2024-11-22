package com.rest.api.utils;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageDTO<T> {

    int page;
    int size;
    long totalElement;
    boolean isLast;
    boolean isFirst;
    long totalPage;
    List<T> data;

}
