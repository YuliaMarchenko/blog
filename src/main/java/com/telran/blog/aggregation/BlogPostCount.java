package com.telran.blog.aggregation;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class BlogPostCount {

    private Long blogUserId;
    private Long blogsCount;
}
