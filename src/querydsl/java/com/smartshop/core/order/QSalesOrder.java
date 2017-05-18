package com.smartshop.core.order;

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

    private static final long serialVersionUID = 1863609845L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSalesOrder salesOrder = new QSalesOrder("salesOrder");

    public final com.smartshop.core.common.QBilling billing;

    public final EnumPath<com.smartshop.core.order.enumeration.OrderChannel> channel = createEnum("channel", com.smartshop.core.order.enumeration.OrderChannel.class);

    public final BooleanPath confirmedAddress = createBoolean("confirmedAddress");

    public final NumberPath<java.math.BigDecimal> currencyValue = createNumber("currencyValue", java.math.BigDecimal.class);

    public final BooleanPath customerAgreement = createBoolean("customerAgreement");

    public final StringPath customerEmailAddress = createString("customerEmailAddress");

    public final NumberPath<Long> customerId = createNumber("customerId", Long.class);

    public final DatePath<java.util.Date> datePurchased = createDate("datePurchased", java.util.Date.class);

    public final com.smartshop.core.common.QDelivery delivery;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ipAddress = createString("ipAddress");

    public final SimplePath<java.util.Locale> locale = createSimple("locale", java.util.Locale.class);

    public final com.smartshop.domain.QMerchantStore merchant;

    public final DateTimePath<java.util.Date> orderDateFinished = createDateTime("orderDateFinished", java.util.Date.class);

    public final SetPath<OrderStatusHistory, QOrderStatusHistory> orderHistory = this.<OrderStatusHistory, QOrderStatusHistory>createSet("orderHistory", OrderStatusHistory.class, QOrderStatusHistory.class, PathInits.DIRECT2);

    public final EnumPath<com.smartshop.core.order.enumeration.OrderType> orderType = createEnum("orderType", com.smartshop.core.order.enumeration.OrderType.class);

    public final StringPath paymentCode = createString("paymentCode");

    public final EnumPath<com.smartshop.core.payment.enumeration.PaymentType> paymentType = createEnum("paymentType", com.smartshop.core.payment.enumeration.PaymentType.class);

    public final StringPath shippingCode = createString("shippingCode");

    public final EnumPath<com.smartshop.core.order.enumeration.OrderStatus> status = createEnum("status", com.smartshop.core.order.enumeration.OrderStatus.class);

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
        this.billing = inits.isInitialized("billing") ? new com.smartshop.core.common.QBilling(forProperty("billing"), inits.get("billing")) : null;
        this.delivery = inits.isInitialized("delivery") ? new com.smartshop.core.common.QDelivery(forProperty("delivery"), inits.get("delivery")) : null;
        this.merchant = inits.isInitialized("merchant") ? new com.smartshop.domain.QMerchantStore(forProperty("merchant"), inits.get("merchant")) : null;
    }

}

