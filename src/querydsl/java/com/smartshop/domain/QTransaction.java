package com.smartshop.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTransaction is a Querydsl query type for Transaction
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTransaction extends EntityPathBase<Transaction> {

    private static final long serialVersionUID = -1008199696L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTransaction transaction = new QTransaction("transaction");

    public final NumberPath<java.math.BigDecimal> amount = createNumber("amount", java.math.BigDecimal.class);

    public final StringPath details = createString("details");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.smartshop.core.order.QSalesOrder order;

    public final EnumPath<com.smartshop.core.payment.enumeration.PaymentType> paymentType = createEnum("paymentType", com.smartshop.core.payment.enumeration.PaymentType.class);

    public final DateTimePath<java.util.Date> transactionDate = createDateTime("transactionDate", java.util.Date.class);

    public final EnumPath<com.smartshop.core.enumeration.TransactionType> transactionType = createEnum("transactionType", com.smartshop.core.enumeration.TransactionType.class);

    public QTransaction(String variable) {
        this(Transaction.class, forVariable(variable), INITS);
    }

    public QTransaction(Path<? extends Transaction> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTransaction(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTransaction(PathMetadata metadata, PathInits inits) {
        this(Transaction.class, metadata, inits);
    }

    public QTransaction(Class<? extends Transaction> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.order = inits.isInitialized("order") ? new com.smartshop.core.order.QSalesOrder(forProperty("order"), inits.get("order")) : null;
    }

}

