package com.shoppay.core.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderProductLine is a Querydsl query type for OrderProductLine
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrderProductLine extends EntityPathBase<OrderProductLine> {

    private static final long serialVersionUID = 718280539L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderProductLine orderProductLine = new QOrderProductLine("orderProductLine");

    public final SimplePath<java.util.Currency> currency = createSimple("currency", java.util.Currency.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> itemQuantity = createNumber("itemQuantity", Integer.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final QSalesOrder salesOrder;

    public final NumberPath<Long> skuId = createNumber("skuId", Long.class);

    public final StringPath skuName = createString("skuName");

    public final NumberPath<java.math.BigDecimal> total = createNumber("total", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> unitPrice = createNumber("unitPrice", java.math.BigDecimal.class);

    public QOrderProductLine(String variable) {
        this(OrderProductLine.class, forVariable(variable), INITS);
    }

    public QOrderProductLine(Path<? extends OrderProductLine> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderProductLine(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderProductLine(PathMetadata metadata, PathInits inits) {
        this(OrderProductLine.class, metadata, inits);
    }

    public QOrderProductLine(Class<? extends OrderProductLine> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.salesOrder = inits.isInitialized("salesOrder") ? new QSalesOrder(forProperty("salesOrder"), inits.get("salesOrder")) : null;
    }

}

