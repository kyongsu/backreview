package com.backreview.backend.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

  @Query("SELECT b FROM Board b WHERE b.title LIKE %?1% OR b.nickName LIKE %?1% OR b.content LIKE %?1%")
  List<Board> findBySearchValue(@Param("searchValue") String searchValue);

  int countByIdAndWriterAndPassword(@Param("id") Long id, @Param("writer") String writer, @Param("password") String password);

}
