package com.b303.mokkozi.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatRoomJoin is a Querydsl query type for ChatRoomJoin
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QChatRoomJoin extends EntityPathBase<ChatRoomJoin> {

    private static final long serialVersionUID = 843880265L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatRoomJoin chatRoomJoin = new QChatRoomJoin("chatRoomJoin");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QChatRoom chatRoom;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QUser user;

    public QChatRoomJoin(String variable) {
        this(ChatRoomJoin.class, forVariable(variable), INITS);
    }

    public QChatRoomJoin(Path<? extends ChatRoomJoin> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatRoomJoin(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatRoomJoin(PathMetadata metadata, PathInits inits) {
        this(ChatRoomJoin.class, metadata, inits);
    }

    public QChatRoomJoin(Class<? extends ChatRoomJoin> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chatRoom = inits.isInitialized("chatRoom") ? new QChatRoom(forProperty("chatRoom")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

