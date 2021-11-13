package com.b303.mokkozi.board;

import com.b303.mokkozi.board.dto.BoardDto;
import com.b303.mokkozi.board.request.BoardModifyPatchReq;
import com.b303.mokkozi.board.request.BoardWritePostReq;
import com.b303.mokkozi.entity.Board;
import com.b303.mokkozi.entity.User;
import com.b303.mokkozi.entity.UserBoardLike;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserBoardLikeRepository ublRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Page<BoardDto> getBoardList(User user, int pageIdx) {

        int size = 10;
        int page = pageIdx <= 0 ? 0 : pageIdx - 1;

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));

        Page<Board> pageTuts = boardRepository.findAll(pageable);
        Page<BoardDto> boardList = pageTuts.map(m -> new BoardDto(m, ublRepository.findByUserIdAndBoardId(user.getId(), m.getId()).isPresent()));

        return boardList;
    }

    @Override
    public BoardDto createBoard(User user, BoardWritePostReq bwpr) {
        Board board = new Board();
        board.setContent(bwpr.getContent());
        board.setUser(user);
        board.setActive("1");
        board = boardRepository.save(board);

        boolean boardLike = ublRepository.findByUserIdAndBoardId(user.getId(), board.getId()).isPresent();

        return new BoardDto(board, boardLike);

    }

    @Override
    public void deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException("not found"));
        boardRepository.deleteById(board.getId());
    }

    @Override
    public BoardDto getBoardDetail(User user, Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException("not found"));
        boolean boardLike = ublRepository.findByUserIdAndBoardId(user.getId(), board.getId()).isPresent();
        return new BoardDto(board, boardLike);
    }

    @Override
    public Page<BoardDto> searchBoardList(User user, String type, String keyword, int pageIdx) {

        int size = 10;
        Pageable pageable = PageRequest.of(pageIdx, size, Sort.by(Sort.Direction.DESC, "id"));
        if (type.equals("writer")) {
            Page<Board> pageTuts = boardRepository.findByUserIdContaining(pageable, keyword);
            Page<BoardDto> boardList = pageTuts.map(m -> new BoardDto(m, ublRepository.findByUserIdAndBoardId(user.getId(), m.getId()).isPresent()));
            return boardList;
        } else if (type.equals("tag")) {
            //태그..?
            //Page<Board> pageTuts = boardRepository.findByTitleContaining(pageable,keyword);
        }


        return null;
    }

    @Override
    public void createBoardLike(User user, Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException("not found"));
        UserBoardLike bl = new UserBoardLike();
        bl.setUser(user);
        bl.setBoard(board);
        if (!ublRepository.findByUserIdAndBoardId(user.getId(), boardId).isPresent()) {
            ublRepository.save(bl);
        }
    }

    @Override
    public BoardDto modifyBoard(User user, BoardModifyPatchReq bmpr) {
        Board board = boardRepository.findById(bmpr.getId()).orElseThrow(() -> new NoSuchElementException("not found"));
        if (board.getUser().getId() == user.getId()) {

            board.setContent(bmpr.getContent());
            board = boardRepository.save(board);

            boolean boardLike = ublRepository.findByUserIdAndBoardId(user.getId(), board.getId()).isPresent();

            return new BoardDto(board, boardLike);

        } else throw new AccessDeniedException("");
    }

    @Override
    public void deleteBoardLike(User user, Long boardId) {
        UserBoardLike ubl = ublRepository.findByUserIdAndBoardId(user.getId(), boardId).orElseThrow(() -> new NoSuchElementException("not found"));
        ublRepository.delete(ubl);
    }

}
