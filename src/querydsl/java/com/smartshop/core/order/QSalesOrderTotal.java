package com.smartshop.core.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSalesOrderTotal is a Querydsl query type for SalesOrderTotal
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSalesOrderTotal extends EntityPathBase<SalesOrderTotal> {

    private static final long serialVersionUID = -2071007665L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSalesOrderTotal salesOrderTotal = new QSalesOrderTotal("salesOrderTotal");

    public final StringPath code = createString("code");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath module = createString("module");

    public final QSalesOrder order;

    public final EnumPath<SalesOrderTotalType> orderTotalType = createEnum("orderTotalType", SalesOrderTotalType.class);

    public final NumberPath<Integer> sortOrder = createNumber("sortOrder", Integer.class);

    public final StringPath text = createString("text");

    public final StringPath title = createString("title");

    public final NumberPath<java.math.BigDecimal> value = createNumber("value", java.math.BigDecimal.class);

    public QSalesOrderTotal(String variable) {
        this(SalesOrderTotal.class, forVariable(variable), INITS);
    }

    public QSalesOrderTotal(Path<? extends SalesOrderTotal> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSalesOrderTotal(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSalesOrderTotal(PathMetadata metadata, PathInits inits) {
        this(SalesOrderTotal.class, metadata, inits);
    }

    public QSalesOrderTotal(Class<? extends SalesOrderTotal> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new QSalesOrder(forProperty("order"), inits.get("order")) : null;
    }

}

