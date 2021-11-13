package com.b303.mokkozi.gallery;

import com.b303.mokkozi.board.BoardService;
import com.b303.mokkozi.entity.Board;
import com.b303.mokkozi.entity.Gallery;
import com.b303.mokkozi.entity.User;
import com.b303.mokkozi.gallery.request.GalleryVO;
import com.b303.mokkozi.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GalleryServiceImpl implements GalleryService{
    @Autowired
    BoardService boardService;

    @Autowired
    UserService userService;

    @Autowired
    GalleryRepository galleryRepository;

    @Override
    public Gallery galleryCreate(GalleryVO galleryVo, String id) {
        // sort 통해 확인해보자.
        Gallery gallery = new Gallery();
        gallery.setFilePath(galleryVo.getFilePath());
        gallery.setTitle(galleryVo.getTitle());
        gallery.setSort(galleryVo.getSort());

        if (galleryVo.getSort().equals("board")) {
            Board board = boardService.getBoardDetail(Long.getLong(id));
            gallery.setBoard(board);
        } else {
            User user = userService.findByEmail(id).get();
            gallery.setUser(user);
        }

        return galleryRepository.save(gallery);
    }
}
