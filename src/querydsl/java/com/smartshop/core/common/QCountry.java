package com.smartshop.core.common;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCountry is a Querydsl query type for Country
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCountry extends EntityPathBase<Country> {

    private static final long serialVersionUID = 1073297606L;

    public static final QCountry country = new QCountry("country");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath isoCode = createString("isoCode");

    public final StringPath name = createString("name");

    public final SetPath<Zone, QZone> zones = this.<Zone, QZone>createSet("zones", Zone.class, QZone.class, PathInits.DIRECT2);

    public QCountry(String variable) {
        super(Country.class, forVariable(variable));
    }

    public QCountry(Path<? extends Country> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCountry(PathMetadata metadata) {
        super(Country.class, metadata);
    }

}

