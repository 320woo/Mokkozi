package com.b303.mokkozi.gallery;

import com.b303.mokkozi.entity.Gallery;
import com.b303.mokkozi.gallery.request.GalleryVO;

public interface GalleryService {
    Gallery galleryCreate(GalleryVO galleryVo, String id);
}
