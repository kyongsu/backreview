package com.backreview.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class CommonDate<U> {

  @CreatedBy
  @Column(name = "create_id", nullable = false, updatable = false)
  @JsonIgnore
  private U createId;

  @CreatedDate
  @Column(name = "create_date")
  @JsonIgnore
  private Date createDate;

  @LastModifiedBy
  @Column(name = "modify_id", nullable = false)
  @JsonIgnore
  private U modifyId;

  @LastModifiedDate
  @Column(name = "modify_date")
  @JsonIgnore
  private Date modifyDate;
}
