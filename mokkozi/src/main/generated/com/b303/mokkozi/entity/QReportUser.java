package com.b303.mokkozi.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReportUser is a Querydsl query type for ReportUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReportUser extends EntityPathBase<ReportUser> {

    private static final long serialVersionUID = 595157323L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReportUser reportUser = new QReportUser("reportUser");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final DateTimePath<java.util.Date> regDate = createDateTime("regDate", java.util.Date.class);

    public final NumberPath<Long> targetId = createNumber("targetId", Long.class);

    public final QUser user;

    public QReportUser(String variable) {
        this(ReportUser.class, forVariable(variable), INITS);
    }

    public QReportUser(Path<? extends ReportUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReportUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReportUser(PathMetadata metadata, PathInits inits) {
        this(ReportUser.class, metadata, inits);
    }

    public QReportUser(Class<? extends ReportUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

