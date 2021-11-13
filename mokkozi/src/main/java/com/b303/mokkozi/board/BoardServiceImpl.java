package com.b303.mokkozi.board;

import com.b303.mokkozi.board.dto.BoardDto;
import com.b303.mokkozi.board.request.BoardModifyPatchReq;
import com.b303.mokkozi.board.request.BoardWritePostReq;
import com.b303.mokkozi.entity.Board;
import com.b303.mokkozi.entity.User;
import com.b303.mokkozi.entity.UserBoardLike;
import com.b303.mokkozi.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        logger.info("BoardServiceImpl.getBoardList 40 : pageable 객체 생성. ", pageable);
        Page<Board> pageTuts = boardRepository.findAll(pageable);
        logger.info("BoardServiceImpl.getBoardList 42 : 게시글 목록 불러오기 완료! : {} ", pageTuts.getContent());
        Page<BoardDto> result = pageTuts.map(m -> new BoardDto(m.getId(),m.getTitle(), m.getContent(), m.getRegDate(),m.getActive(), m.getUser().getEmail()));

        return result;
    }

    @Autowired
    UserRepository userRepository;

    @Override
    public BoardDto createBoard(User user, BoardWritePostReq bwpr) {
//            User user1 = userRepository.getById((long)1);
        Board board = new Board();
        board.setTitle(bwpr.getTitle());
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
        } else if (type.equals("title")) {
            Page<Board> pageTuts = boardRepository.findByTitleContaining(pageable, keyword);
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
        ublRepository.save(bl);
    }

    @Override
    public BoardDto modifyBoard(User user, BoardModifyPatchReq bmpr) {
        Board board = boardRepository.findById(bmpr.getId()).orElseThrow(() -> new NoSuchElementException("not found"));
        if (board.getUser().getId() == user.getId()) {

            board.setTitle(bmpr.getTitle());
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
