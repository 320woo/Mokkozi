package com.b303.mokkozi.gallery;

import com.b303.mokkozi.entity.Board;
import com.b303.mokkozi.entity.Gallery;
import com.b303.mokkozi.entity.User;
import com.b303.mokkozi.gallery.request.GalleryPostReq;

public interface GalleryService {
    public Gallery userGalleryCreate(GalleryPostReq fileInfo, User user);

    public Gallery boardGalleryCreate(GalleryPostReq fileInfo, Board board);
}
