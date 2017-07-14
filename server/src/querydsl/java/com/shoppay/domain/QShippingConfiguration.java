package com.shoppay.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QShippingConfiguration is a Querydsl query type for ShippingConfiguration
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QShippingConfiguration extends EntityPathBase<ShippingConfiguration> {

    private static final long serialVersionUID = 1297973991L;

    public static final QShippingConfiguration shippingConfiguration = new QShippingConfiguration("shippingConfiguration");

    public final StringPath env = createString("env");

    public final StringPath host = createString("host");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    public final StringPath name = createString("name");

    public final StringPath port = createString("port");

    public final StringPath scheme = createString("scheme");

    public final StringPath uri = createString("uri");

    public final StringPath version = createString("version");

    public QShippingConfiguration(String variable) {
        super(ShippingConfiguration.class, forVariable(variable));
    }

    public QShippingConfiguration(Path<? extends ShippingConfiguration> path) {
        super(path.getType(), path.getMetadata());
    }

    public QShippingConfiguration(PathMetadata metadata) {
        super(ShippingConfiguration.class, metadata);
    }

}

