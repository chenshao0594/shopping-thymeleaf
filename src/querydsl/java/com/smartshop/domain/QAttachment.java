package com.smartshop.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAttachment is a Querydsl query type for Attachment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAttachment extends EntityPathBase<Attachment> {

    private static final long serialVersionUID = 151661009L;

    public static final QAttachment attachment = new QAttachment("attachment");

    public final EnumPath<com.smartshop.attachment.common.AttachmentEnum> attachmentType = createEnum("attachmentType", com.smartshop.attachment.common.AttachmentEnum.class);

    public final NumberPath<Long> boId = createNumber("boId", Long.class);

    public final StringPath boName = createString("boName");

    public final ArrayPath<byte[], Byte> content = createArray("content", byte[].class);

    public final StringPath contentType = createString("contentType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> size = createNumber("size", Long.class);

    public QAttachment(String variable) {
        super(Attachment.class, forVariable(variable));
    }

    public QAttachment(Path<? extends Attachment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAttachment(PathMetadata metadata) {
        super(Attachment.class, metadata);
    }

}

