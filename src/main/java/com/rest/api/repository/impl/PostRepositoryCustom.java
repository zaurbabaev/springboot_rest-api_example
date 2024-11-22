package com.rest.api.repository.impl;

import com.rest.api.utils.PageDTO;
import com.rest.api.utils.request.PostDTO;
import org.springframework.data.domain.Page;

public interface PostRepositoryCustom {

    PageDTO findAllWithCustomPage(int size, int page, String direction, String properties, String content, String title);
}
