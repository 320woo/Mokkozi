package com.b303.mokkozi.board;

import com.b303.mokkozi.board.dto.BoardDto;
import com.b303.mokkozi.board.request.BoardModifyPatchReq;
import com.b303.mokkozi.board.request.BoardWritePostReq;
import com.b303.mokkozi.entity.Board;
import com.b303.mokkozi.entity.User;
import org.springframework.data.domain.Page;

import java.util.NoSuchElementException;

public interface BoardService {

    Page<BoardDto> getBoardList(int page);

    Board createBoard(User user, BoardWritePostReq bwpr);

    void deleteBoard(Long boardId) throws NoSuchElementException;

    Board getBoardDetail(Long boardId) throws NoSuchElementException;

    Page<BoardDto> searchBoardList(String type,String keyword, int pageIdx);

    void createBoardLike(User userEmail, Long boardId);

    Board modifyBoard(User user, BoardModifyPatchReq bmpr);

    void deleteBoardLike(User user, Long boardId);
}
