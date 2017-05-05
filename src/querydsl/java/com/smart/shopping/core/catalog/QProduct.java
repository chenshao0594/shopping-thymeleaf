package com.smart.shopping.core.catalog;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 811488543L;

    public static final QProduct product = new QProduct("product");

    public final BooleanPath available = createBoolean("available");

    public final DatePath<java.time.LocalDate> dateAvailable = createDate("dateAvailable", java.time.LocalDate.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<java.math.BigDecimal> length = createNumber("length", java.math.BigDecimal.class);

    public final StringPath metatagDescription = createString("metatagDescription");

    public final StringPath metatagKeywords = createString("metatagKeywords");

    public final StringPath metatagTitle = createString("metatagTitle");

    public final StringPath name = createString("name");

    public final StringPath productExternalDl = createString("productExternalDl");

    public final NumberPath<java.math.BigDecimal> productHeight = createNumber("productHeight", java.math.BigDecimal.class);

    public final StringPath productHighlight = createString("productHighlight");

    public final SetPath<ProductOption, QProductOption> productOptions = this.<ProductOption, QProductOption>createSet("productOptions", ProductOption.class, QProductOption.class, PathInits.DIRECT2);

    public final NumberPath<Integer> productOrdered = createNumber("productOrdered", Integer.class);

    public final BooleanPath productShipeable = createBoolean("productShipeable");

    public final NumberPath<java.math.BigDecimal> productWeight = createNumber("productWeight", java.math.BigDecimal.class);

    public final StringPath refSku = createString("refSku");

    public final NumberPath<java.math.BigDecimal> retailPrice = createNumber("retailPrice", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> salePrice = createNumber("salePrice", java.math.BigDecimal.class);

    public final StringPath searchUrl = createString("searchUrl");

    public final StringPath sku = createString("sku");

    public final NumberPath<Integer> sortOrder = createNumber("sortOrder", Integer.class);

    public final NumberPath<java.math.BigDecimal> standardPrice = createNumber("standardPrice", java.math.BigDecimal.class);

    public final StringPath title = createString("title");

    public final NumberPath<java.math.BigDecimal> width = createNumber("width", java.math.BigDecimal.class);

    public QProduct(String variable) {
        super(Product.class, forVariable(variable));
    }

    public QProduct(Path<? extends Product> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct(PathMetadata metadata) {
        super(Product.class, metadata);
    }

}

