package com.shoppay.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEmailSetting is a Querydsl query type for EmailSetting
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEmailSetting extends EntityPathBase<EmailSetting> {

    private static final long serialVersionUID = -75049163L;

    public static final QEmailSetting emailSetting = new QEmailSetting("emailSetting");

    public final StringPath fromAddress = createString("fromAddress");

    public final StringPath host = createString("host");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath password = createString("password");

    public final NumberPath<Integer> port = createNumber("port", Integer.class);

    public final EnumPath<com.shoppay.core.enumeration.SMTPSecurityEnum> smtpSecurity = createEnum("smtpSecurity", com.shoppay.core.enumeration.SMTPSecurityEnum.class);

    public final StringPath userName = createString("userName");

    public QEmailSetting(String variable) {
        super(EmailSetting.class, forVariable(variable));
    }

    public QEmailSetting(Path<? extends EmailSetting> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmailSetting(PathMetadata metadata) {
        super(EmailSetting.class, metadata);
    }

}

