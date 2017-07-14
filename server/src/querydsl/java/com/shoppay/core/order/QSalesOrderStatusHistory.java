package com.shoppay.core.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSalesOrderStatusHistory is a Querydsl query type for SalesOrderStatusHistory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSalesOrderStatusHistory extends EntityPathBase<SalesOrderStatusHistory> {

    private static final long serialVersionUID = 40355066L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSalesOrderStatusHistory salesOrderStatusHistory = new QSalesOrderStatusHistory("salesOrderStatusHistory");

    public final StringPath comments = createString("comments");

    public final NumberPath<Integer> customerNotified = createNumber("customerNotified", Integer.class);

    public final DateTimePath<java.util.Date> dateAdded = createDateTime("dateAdded", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QSalesOrder order;

    public final EnumPath<com.shoppay.core.order.enumeration.SalesOrderStatus> status = createEnum("status", com.shoppay.core.order.enumeration.SalesOrderStatus.class);

    public QSalesOrderStatusHistory(String variable) {
        this(SalesOrderStatusHistory.class, forVariable(variable), INITS);
    }

    public QSalesOrderStatusHistory(Path<? extends SalesOrderStatusHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSalesOrderStatusHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSalesOrderStatusHistory(PathMetadata metadata, PathInits inits) {
        this(SalesOrderStatusHistory.class, metadata, inits);
    }

    public QSalesOrderStatusHistory(Class<? extends SalesOrderStatusHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new QSalesOrder(forProperty("order"), inits.get("order")) : null;
    }

}

