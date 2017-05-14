package com.smart.shopping.core.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderStatusHistory is a Querydsl query type for OrderStatusHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrderStatusHistory extends EntityPathBase<OrderStatusHistory> {

    private static final long serialVersionUID = -1043843377L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderStatusHistory orderStatusHistory = new QOrderStatusHistory("orderStatusHistory");

    public final StringPath comments = createString("comments");

    public final NumberPath<Integer> customerNotified = createNumber("customerNotified", Integer.class);

    public final DateTimePath<java.util.Date> dateAdded = createDateTime("dateAdded", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QSalesOrder order;

    public final EnumPath<com.smart.shopping.core.order.enumeration.OrderStatus> status = createEnum("status", com.smart.shopping.core.order.enumeration.OrderStatus.class);

    public QOrderStatusHistory(String variable) {
        this(OrderStatusHistory.class, forVariable(variable), INITS);
    }

    public QOrderStatusHistory(Path<? extends OrderStatusHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderStatusHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderStatusHistory(PathMetadata metadata, PathInits inits) {
        this(OrderStatusHistory.class, metadata, inits);
    }

    public QOrderStatusHistory(Class<? extends OrderStatusHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new QSalesOrder(forProperty("order"), inits.get("order")) : null;
    }

}

