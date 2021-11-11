package com.b303.mokkozi.board;

import com.b303.mokkozi.board.dto.BoardDto;
import com.b303.mokkozi.board.request.BoardModifyPatchReq;
import com.b303.mokkozi.board.request.BoardWritePostReq;
import com.b303.mokkozi.entity.Board;
import com.b303.mokkozi.entity.User;
import com.b303.mokkozi.entity.UserBoardLike;
import com.b303.mokkozi.user.UserRepository;
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

        @Override
        public Page<BoardDto> getBoardList(int pageIdx) {

            int size = 15;
            int page = pageIdx <= 0 ? 0 : pageIdx - 1;

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));

            Page<Board> pageTuts = boardRepository.findAll(pageable);
            Page<BoardDto> boardList = pageTuts.map(m -> new BoardDto(m.getId(),m.getTitle(),m.getContent(),m.getRegDate(),m.getActive(),m.getUser().getEmail()));

            return boardList;
    }

    @Autowired
    UserRepository userRepository;

    @Override
    public Board createBoard(User user, BoardWritePostReq bwpr) {
//            User user1 = userRepository.getById((long)1);
            Board board = new Board();
            board.setTitle(bwpr.getTitle());
            board.setContent(bwpr.getContent());
            board.setUser(user);
            board.setActive("1");
            return boardRepository.save(board);

    }

    @Override
    public void deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException("not found"));
        boardRepository.deleteById(board.getId());
    }

    @Override
    public Board getBoardDetail(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException("not found"));
        return board;
    }

    @Override
    public Page<BoardDto> searchBoardList(String type,String keyword, int pageIdx) {

            int size = 15;
            Pageable pageable = PageRequest.of(pageIdx,size,Sort.by(Sort.Direction.DESC,"id"));
            if(type.equals("writer")){
                Page<Board> pageTuts = boardRepository.findByUserIdContaining(pageable,keyword);
                Page<BoardDto> boardList = pageTuts.map(m -> new BoardDto(m.getId(),m.getTitle(),m.getContent(),m.getRegDate(),m.getActive(),m.getUser().getEmail()));
                return boardList;
            }else if(type.equals("title")){
                Page<Board> pageTuts = boardRepository.findByTitleContaining(pageable,keyword);
                Page<BoardDto> boardList = pageTuts.map(m -> new BoardDto(m.getId(),m.getTitle(),m.getContent(),m.getRegDate(),m.getActive(),m.getUser().getEmail()));
                return boardList;
            }else if(type.equals("tag")){
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
    public Board modifyBoard(User user, BoardModifyPatchReq bmpr) {
        Board board = boardRepository.findById(bmpr.getId()).orElseThrow(() -> new NoSuchElementException("not found"));
        if(board.getUser().getId()==user.getId()){
            board.setTitle(bmpr.getTitle());
            board.setContent(bmpr.getContent());
            boardRepository.save(board);
            return board;
        } else throw new AccessDeniedException("");
    }

    @Override
    public void deleteBoardLike(User user, Long boardId) {
        UserBoardLike ubl = ublRepository.findByUserIdAndBoardId(user.getId(),boardId).orElseThrow(() -> new NoSuchElementException("not found"));
        ublRepository.delete(ubl);
    }

}
