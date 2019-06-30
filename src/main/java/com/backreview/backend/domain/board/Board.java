package com.backreview.backend.domain.board;

import com.backreview.backend.domain.CommonDate;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.*;
import java.io.Serializable;

@ToString(exclude = "password")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class Board extends CommonDate<String> implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="TITLE", length = 500, nullable = false)
  private String title;

  @Column(name="CONTENT", columnDefinition = "TEXT", nullable = false)
  private String content;

  @Column(name="NICKNAME", length = 100, nullable = false)
  private String nickName;

  @Column(name="WRITER", length = 100, nullable = false)
  private String writer;

  @Column(name="PASSWORD", length = 30, nullable = false)
  private String password;

  @Builder
  public Board(String title, String content, String nickName, String writer, String password) {
    this.title = title;
    this.content = content;
    this.nickName = nickName;
    this.writer = writer;
    this.password = password;
  }
}
