package com.backreview.backend.controller;

import com.backreview.backend.domain.JSONEntity;
import com.backreview.backend.domain.board.Board;
import com.backreview.backend.domain.board.BoardRepository;
import com.backreview.backend.utils.DeveloperHandleException;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@Api(value = "board")
@AllArgsConstructor
@RequestMapping(value = "/api/board")
public class BoardController {

  private BoardRepository boardRepository;

  @GetMapping("/")
  @ApiOperation(value = "전체 게시글 조회")
  public JSONEntity<List<Board>> findAll(){
    JSONEntity<List<Board>> result = new JSONEntity<>();
    result.setData(boardRepository.findAll());

    return result;
  }

  @GetMapping("/{searchValue}")
  @ApiOperation(value = "게시글 조회")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "searchValue", value = "제목 or 내용 or 닉네임", required = true, dataType = "string", paramType = "path")
  })
  public JSONEntity<List<Board>> findBySearchValue(@PathVariable String searchValue){
    JSONEntity<List<Board>> result = new JSONEntity<>();
    result.setData(boardRepository.findBySearchValue(searchValue));

    return result;
  }

  @PostMapping("/")
  @ApiOperation(value = "게시글 등록")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "title", value = "제목", required = true, dataType = "string", paramType = "query"),
          @ApiImplicitParam(name = "content", value = "내용", required = true, dataType = "string", paramType = "query"),
          @ApiImplicitParam(name = "nickName", value = "닉네임", required = true, dataType = "string", paramType = "query"),
          @ApiImplicitParam(name = "writer", value = "등록자", required = true, dataType = "string", paramType = "query"),
          @ApiImplicitParam(name = "password", value = "패스워드", required = true, dataType = "string", paramType = "query")
  })
  @ResponseStatus(code = HttpStatus.CREATED)
  public JSONEntity<Board> saveBoard(Board board) {
    JSONEntity<Board> result = new JSONEntity<>();
    boardRepository.save(board);
    result.setMessage("등록되었습니다.");
    return result;
  }

  @PutMapping("/")
  @ApiOperation(value = "게시글 수정")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "id", value = "게시글키", required = true, dataType = "long", paramType = "query"),
          @ApiImplicitParam(name = "title", value = "제목", dataType = "string", paramType = "query"),
          @ApiImplicitParam(name = "content", value = "내용", dataType = "string", paramType = "query"),
          @ApiImplicitParam(name = "nickName", value = "닉네임", dataType = "string", paramType = "query"),
          @ApiImplicitParam(name = "writer", value = "등록자", required = true, dataType = "string", paramType = "query"),
          @ApiImplicitParam(name = "password", value = "패스워드", required = true, dataType = "string", paramType = "query"),
  })
  public JSONEntity<Board> updateBoard(Board board) throws DeveloperHandleException {
    JSONEntity<Board> result = new JSONEntity<>();

    if(boardRepository.countByIdAndWriterAndPassword(board.getId(), board.getWriter(), board.getPassword()) > 0){
      boardRepository.save(board);
      result.setMessage("수정되었습니다.");
    } else {
      throw new DeveloperHandleException("UPDATE_ERROR", "아이디 OR 패스워드를 확인해주세요.");
    }

    return result;
  }

  @DeleteMapping("/")
  @ApiOperation(value = "게시글 삭제")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "id", value = "게시글키", required = true, dataType = "long", paramType = "query"),
          @ApiImplicitParam(name = "writer", value = "등록자", required = true, dataType = "string", paramType = "query"),
          @ApiImplicitParam(name = "password", value = "패스워드", required = true, dataType = "string", paramType = "query"),
  })
  public JSONEntity<Board> deleteBoard(Board board) throws DeveloperHandleException {
    JSONEntity<Board> result = new JSONEntity<>();

    if(boardRepository.countByIdAndWriterAndPassword(board.getId(), board.getWriter(), board.getPassword()) > 0){
      boardRepository.delete(board);
      result.setMessage("삭제되었습니다.");
    } else {
      throw new DeveloperHandleException("DELETE_ERROR", "아이디 OR 패스워드를 확인해주세요.");
    }

    return result;
  }
}
