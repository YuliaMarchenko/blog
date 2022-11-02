package com.telran.blog.entities.converter;

import com.telran.blog.entities.type.BlogStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)

public class BlogStatusConverter implements AttributeConverter<BlogStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(BlogStatus status) {
        return status == null ? null : status.getBlogStatusId();
    }

    @Override
    public BlogStatus convertToEntityAttribute(Integer integer) {
        return integer == null ? null : BlogStatus.findByStatusById(integer);
    }
}
