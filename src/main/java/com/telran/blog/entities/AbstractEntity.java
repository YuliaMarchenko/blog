package com.telran.blog.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity<U> {

    @Column(name = "updated_on")
    @CreatedDate
    private Instant createdOn;

    @Column(name = "created_on")
    @LastModifiedDate
    private Instant updatedOn;
}
