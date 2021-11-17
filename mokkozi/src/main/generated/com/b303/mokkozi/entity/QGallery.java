package com.b303.mokkozi.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGallery is a Querydsl query type for Gallery
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGallery extends EntityPathBase<Gallery> {

    private static final long serialVersionUID = -1470390106L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGallery gallery = new QGallery("gallery");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QBoard board;

    public final StringPath filePath = createString("filePath");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath sort = createString("sort");

    public final StringPath title = createString("title");

    public final QUser user;

    public QGallery(String variable) {
        this(Gallery.class, forVariable(variable), INITS);
    }

    public QGallery(Path<? extends Gallery> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGallery(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGallery(PathMetadata metadata, PathInits inits) {
        this(Gallery.class, metadata, inits);
    }

    public QGallery(Class<? extends Gallery> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

