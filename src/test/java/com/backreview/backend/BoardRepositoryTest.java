package com.backreview.backend;

import com.backreview.backend.domain.board.Board;
import com.backreview.backend.domain.board.BoardRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {

  @Autowired
  BoardRepository boardRepository;

  @After
  public void cleanup(){
    //boardRepository.deleteAll();
  }

  @Test
  public void writeBoard(){
    //given
    boardRepository.save(Board.builder()
            .title("테스트")
            .content("내용입력")
            .nickName("noname")
            .writer("test123")
            .password("test123!@")
      .build());

    //when
    List<Board> boardList = boardRepository.findAll();

    Board board = boardList.get(0);
    assertThat(board.getWriter(), is("test123"));
    assertThat(board.getPassword(), is("test123!@"));
    assertThat(board.getNickName(), is("noname"));

  }

}
