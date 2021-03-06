package com.shoppay.core.catalog;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductOption is a Querydsl query type for ProductOption
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductOption extends EntityPathBase<ProductOption> {

    private static final long serialVersionUID = -126185207L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductOption productOption = new QProductOption("productOption");

    public final StringPath code = createString("code");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.shoppay.common.domain.QMerchantStore merchantStore;

    public final SetPath<ProductOptionValue, QProductOptionValue> productOptionValues = this.<ProductOptionValue, QProductOptionValue>createSet("productOptionValues", ProductOptionValue.class, QProductOptionValue.class, PathInits.DIRECT2);

    public final BooleanPath readOnly = createBoolean("readOnly");

    public final NumberPath<Integer> sortOrder = createNumber("sortOrder", Integer.class);

    public final EnumPath<ProductOptionEnum> type = createEnum("type", ProductOptionEnum.class);

    public QProductOption(String variable) {
        this(ProductOption.class, forVariable(variable), INITS);
    }

    public QProductOption(Path<? extends ProductOption> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductOption(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductOption(PathMetadata metadata, PathInits inits) {
        this(ProductOption.class, metadata, inits);
    }

    public QProductOption(Class<? extends ProductOption> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.merchantStore = inits.isInitialized("merchantStore") ? new com.shoppay.common.domain.QMerchantStore(forProperty("merchantStore")) : null;
    }

}

