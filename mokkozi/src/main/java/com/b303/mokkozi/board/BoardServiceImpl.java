package com.b303.mokkozi.board;

import com.b303.mokkozi.board.dto.BoardDto;
import com.b303.mokkozi.board.request.BoardListGetReq;
import com.b303.mokkozi.board.request.BoardWritePostReq;
import com.b303.mokkozi.entity.Board;
import com.b303.mokkozi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository boardRepository;

        @Override
        public Page<BoardDto> getBoardList(BoardListGetReq blgr) {

            int size = blgr.getSize();
            int page = blgr.getPage() <= 0 ? 0 : blgr.getPage() - 1;

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));

            Page<Board> pageTuts = boardRepository.findAll(pageable);
            Page<BoardDto> boardList = pageTuts.map(m -> new BoardDto(m.getId(),m.getTitle(),m.getContent(),m.getRegDate(),m.getActive(),m.getUser().getEmail()));

            return boardList;
    }

    @Override
    public Board createBoard(User user, BoardWritePostReq bwpr) {

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
//        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException("not found"));
//        BoardLike bl = new BoardLike();
//        bl.setUser(user);
//        bl.setBoard(board);
//        boardLikeRepository.save(bl);
    }

}
