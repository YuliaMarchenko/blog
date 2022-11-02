package com.telran.blog.entities.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter

public enum BlogStatus {
    PUBLISHED(0, "Published"),
    UNPUBLISHED(1, "Unpublished"),
    BLOCKED(2, "Blocked");

    private Integer blogStatusId;
    private String blogStatusName;

    @JsonValue
    public String getBlogStatusName() {
        return blogStatusName;
    }

    @JsonCreator
    public static BlogStatus findByStatusName(String blogStatusName){
        if (blogStatusName == null){
            return UNPUBLISHED;
        }
        return Arrays.stream(BlogStatus.values())
                .filter(x -> x.getBlogStatusName().equals(blogStatusName))
                .findFirst()
                .orElse(UNPUBLISHED);
    }

    public static BlogStatus findByStatusById(Integer blogStatusId) {

        if (blogStatusId == null) {
            return null;
        }

        return Arrays.stream(BlogStatus.values())
                .filter(x -> x.getBlogStatusId().equals(blogStatusId))
                .findFirst()
                .orElse(null);
    }
}
