package com.smart.shopping.core.catalog;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductOptionValue is a Querydsl query type for ProductOptionValue
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductOptionValue extends EntityPathBase<ProductOptionValue> {

    private static final long serialVersionUID = -1059957923L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductOptionValue productOptionValue = new QProductOptionValue("productOptionValue");

    public final StringPath code = createString("code");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.smart.shopping.domain.QMerchantStore merchantStore;

    public final QProductOption productOption;

    public final BooleanPath productOptionDisplayOnly = createBoolean("productOptionDisplayOnly");

    public final StringPath productOptionValueImage = createString("productOptionValueImage");

    public final NumberPath<Integer> productOptionValueSortOrder = createNumber("productOptionValueSortOrder", Integer.class);

    public QProductOptionValue(String variable) {
        this(ProductOptionValue.class, forVariable(variable), INITS);
    }

    public QProductOptionValue(Path<? extends ProductOptionValue> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductOptionValue(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductOptionValue(PathMetadata metadata, PathInits inits) {
        this(ProductOptionValue.class, metadata, inits);
    }

    public QProductOptionValue(Class<? extends ProductOptionValue> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.merchantStore = inits.isInitialized("merchantStore") ? new com.smart.shopping.domain.QMerchantStore(forProperty("merchantStore"), inits.get("merchantStore")) : null;
        this.productOption = inits.isInitialized("productOption") ? new QProductOption(forProperty("productOption"), inits.get("productOption")) : null;
    }

}

