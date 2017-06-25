package com.smartshop.core.catalog;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSKU is a Querydsl query type for SKU
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSKU extends EntityPathBase<SKU> {

    private static final long serialVersionUID = 234095669L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSKU sKU = new QSKU("sKU");

    public final StringPath attributes = createString("attributes");

    public final StringPath code = createString("code");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDefault = createBoolean("isDefault");

    public final StringPath name = createString("name");

    public final QProduct product;

    public final SetPath<ProductOptionValue, QProductOptionValue> productOptionValues = this.<ProductOptionValue, QProductOptionValue>createSet("productOptionValues", ProductOptionValue.class, QProductOptionValue.class, PathInits.DIRECT2);

    public final NumberPath<Long> quantity = createNumber("quantity", Long.class);

    public final NumberPath<java.math.BigDecimal> retailPrice = createNumber("retailPrice", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> standardPrice = createNumber("standardPrice", java.math.BigDecimal.class);

    public final EnumPath<com.smartshop.core.enumeration.StatusEnum> status = createEnum("status", com.smartshop.core.enumeration.StatusEnum.class);

    public QSKU(String variable) {
        this(SKU.class, forVariable(variable), INITS);
    }

    public QSKU(Path<? extends SKU> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSKU(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSKU(PathMetadata metadata, PathInits inits) {
        this(SKU.class, metadata, inits);
    }

    public QSKU(Class<? extends SKU> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
    }

}

