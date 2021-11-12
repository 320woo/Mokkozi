package com.b303.mokkozi.gallery;

import com.b303.mokkozi.entity.Board;
import com.b303.mokkozi.entity.Gallery;
import com.b303.mokkozi.entity.User;
import com.b303.mokkozi.gallery.request.GalleryPostReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GalleryServiceImpl implements GalleryService{
    @Autowired
    GalleryRepository galleryRepository;

    /**
     * 사용자 프로필 이미지인 경우에 DB에 프로필 이미지 저장하기
     * @param fileInfo
     * @return 저장한 DB객체를 반환한다.
     */
    @Override
    public Gallery userGalleryCreate(GalleryPostReq fileInfo, User user) {
        Gallery gallery = new Gallery();
        gallery.setFilePath(fileInfo.getFilePath());
        gallery.setSort(fileInfo.getSort());
        gallery.setTitle(fileInfo.getTitle());
        gallery.setUser(user);

        return galleryRepository.save(gallery);
    }

    @Override
    public Gallery boardGalleryCreate(GalleryPostReq fileInfo, Board board) {
        return null;
    }
}
