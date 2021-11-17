package com.b303.mokkozi.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserFollow is a Querydsl query type for UserFollow
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserFollow extends EntityPathBase<UserFollow> {

    private static final long serialVersionUID = -121096824L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserFollow userFollow = new QUserFollow("userFollow");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QUser fromUser;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QUser toUser;

    public QUserFollow(String variable) {
        this(UserFollow.class, forVariable(variable), INITS);
    }

    public QUserFollow(Path<? extends UserFollow> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserFollow(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserFollow(PathMetadata metadata, PathInits inits) {
        this(UserFollow.class, metadata, inits);
    }

    public QUserFollow(Class<? extends UserFollow> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fromUser = inits.isInitialized("fromUser") ? new QUser(forProperty("fromUser")) : null;
        this.toUser = inits.isInitialized("toUser") ? new QUser(forProperty("toUser")) : null;
    }

}

