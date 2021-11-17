package com.b303.mokkozi.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReportBoard is a Querydsl query type for ReportBoard
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QReportBoard extends EntityPathBase<ReportBoard> {

    private static final long serialVersionUID = 1252338022L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReportBoard reportBoard = new QReportBoard("reportBoard");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QBoard board;

    public final StringPath content = createString("content");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final DateTimePath<java.util.Date> regDate = createDateTime("regDate", java.util.Date.class);

    public final StringPath result = createString("result");

    public QReportBoard(String variable) {
        this(ReportBoard.class, forVariable(variable), INITS);
    }

    public QReportBoard(Path<? extends ReportBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReportBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReportBoard(PathMetadata metadata, PathInits inits) {
        this(ReportBoard.class, metadata, inits);
    }

    public QReportBoard(Class<? extends ReportBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoard(forProperty("board"), inits.get("board")) : null;
    }

}

