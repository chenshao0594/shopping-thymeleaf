package com.shoppay.core.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSalesOrder is a Querydsl query type for SalesOrder
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSalesOrder extends EntityPathBase<SalesOrder> {

    private static final long serialVersionUID = 2061083688L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSalesOrder salesOrder = new QSalesOrder("salesOrder");

    public final com.shoppay.common.reference.QBilling billing;

    public final EnumPath<com.shoppay.core.order.enumeration.SalesOrderChannel> channel = createEnum("channel", com.shoppay.core.order.enumeration.SalesOrderChannel.class);

    public final BooleanPath confirmedAddress = createBoolean("confirmedAddress");

    public final SimplePath<java.util.Currency> currency = createSimple("currency", java.util.Currency.class);

    public final NumberPath<java.math.BigDecimal> currencyValue = createNumber("currencyValue", java.math.BigDecimal.class);

    public final BooleanPath customerAgreement = createBoolean("customerAgreement");

    public final NumberPath<Long> customerId = createNumber("customerId", Long.class);

    public final DatePath<java.util.Date> datePurchased = createDate("datePurchased", java.util.Date.class);

    public final com.shoppay.common.reference.QDelivery delivery;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ipAddress = createString("ipAddress");

    public final SimplePath<java.util.Locale> locale = createSimple("locale", java.util.Locale.class);

    public final com.shoppay.common.domain.QMerchantStore merchant;

    public final DateTimePath<java.util.Date> orderDateFinished = createDateTime("orderDateFinished", java.util.Date.class);

    public final SetPath<SalesOrderStatusHistory, QSalesOrderStatusHistory> orderHistory = this.<SalesOrderStatusHistory, QSalesOrderStatusHistory>createSet("orderHistory", SalesOrderStatusHistory.class, QSalesOrderStatusHistory.class, PathInits.DIRECT2);

    public final EnumPath<com.shoppay.core.order.enumeration.SalesOrderType> orderType = createEnum("orderType", com.shoppay.core.order.enumeration.SalesOrderType.class);

    public final StringPath paymentCode = createString("paymentCode");

    public final EnumPath<com.shoppay.core.payment.enumeration.PaymentType> paymentType = createEnum("paymentType", com.shoppay.core.payment.enumeration.PaymentType.class);

    public final SetPath<OrderProductLine, QOrderProductLine> productLines = this.<OrderProductLine, QOrderProductLine>createSet("productLines", OrderProductLine.class, QOrderProductLine.class, PathInits.DIRECT2);

    public final StringPath shippingCode = createString("shippingCode");

    public final EnumPath<com.shoppay.core.order.enumeration.SalesOrderStatus> status = createEnum("status", com.shoppay.core.order.enumeration.SalesOrderStatus.class);

    public final NumberPath<java.math.BigDecimal> total = createNumber("total", java.math.BigDecimal.class);

    public QSalesOrder(String variable) {
        this(SalesOrder.class, forVariable(variable), INITS);
    }

    public QSalesOrder(Path<? extends SalesOrder> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSalesOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSalesOrder(PathMetadata metadata, PathInits inits) {
        this(SalesOrder.class, metadata, inits);
    }

    public QSalesOrder(Class<? extends SalesOrder> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.billing = inits.isInitialized("billing") ? new com.shoppay.common.reference.QBilling(forProperty("billing")) : null;
        this.delivery = inits.isInitialized("delivery") ? new com.shoppay.common.reference.QDelivery(forProperty("delivery")) : null;
        this.merchant = inits.isInitialized("merchant") ? new com.shoppay.common.domain.QMerchantStore(forProperty("merchant")) : null;
    }

}

