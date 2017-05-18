package com.smartshop.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPaymentConfiguration is a Querydsl query type for PaymentConfiguration
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPaymentConfiguration extends EntityPathBase<PaymentConfiguration> {

    private static final long serialVersionUID = -1341473858L;

    public static final QPaymentConfiguration paymentConfiguration = new QPaymentConfiguration("paymentConfiguration");

    public final StringPath config1 = createString("config1");

    public final StringPath configDetails = createString("configDetails");

    public final StringPath configuration = createString("configuration");

    public final StringPath env = createString("env");

    public final StringPath host = createString("host");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    public final StringPath name = createString("name");

    public final StringPath port = createString("port");

    public final StringPath regions = createString("regions");

    public final StringPath scheme = createString("scheme");

    public final StringPath type = createString("type");

    public final StringPath uri = createString("uri");

    public final StringPath version = createString("version");

    public QPaymentConfiguration(String variable) {
        super(PaymentConfiguration.class, forVariable(variable));
    }

    public QPaymentConfiguration(Path<? extends PaymentConfiguration> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPaymentConfiguration(PathMetadata metadata) {
        super(PaymentConfiguration.class, metadata);
    }

}

