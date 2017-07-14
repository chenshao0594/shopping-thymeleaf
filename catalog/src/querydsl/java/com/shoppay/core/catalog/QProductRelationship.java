package com.shoppay.core.catalog;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductRelationship is a Querydsl query type for ProductRelationship
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductRelationship extends EntityPathBase<ProductRelationship> {

    private static final long serialVersionUID = -1475821620L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductRelationship productRelationship = new QProductRelationship("productRelationship");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProduct product;

    public final QProduct relatedProduct;

    public final EnumPath<ProductRelationshipEnum> type = createEnum("type", ProductRelationshipEnum.class);

    public QProductRelationship(String variable) {
        this(ProductRelationship.class, forVariable(variable), INITS);
    }

    public QProductRelationship(Path<? extends ProductRelationship> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductRelationship(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductRelationship(PathMetadata metadata, PathInits inits) {
        this(ProductRelationship.class, metadata, inits);
    }

    public QProductRelationship(Class<? extends ProductRelationship> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new QProduct(forProperty("product"), inits.get("product")) : null;
        this.relatedProduct = inits.isInitialized("relatedProduct") ? new QProduct(forProperty("relatedProduct"), inits.get("relatedProduct")) : null;
    }

}

