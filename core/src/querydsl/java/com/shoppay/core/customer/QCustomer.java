package com.shoppay.core.customer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomer is a Querydsl query type for Customer
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCustomer extends EntityPathBase<Customer> {

    private static final long serialVersionUID = -101109496L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomer customer = new QCustomer("customer");

    public final BooleanPath anonymous = createBoolean("anonymous");

    public final com.shoppay.common.user.QAuthority authority;

    public final com.shoppay.common.reference.QBilling billing;

    public final StringPath company = createString("company");

    public final DatePath<java.time.LocalDate> dateOfBirth = createDate("dateOfBirth", java.time.LocalDate.class);

    public final com.shoppay.common.reference.QDelivery delivery;

    public final StringPath emailAddress = createString("emailAddress");

    public final StringPath firstName = createString("firstName");

    public final EnumPath<com.shoppay.common.enumeration.Gender> gender = createEnum("gender", com.shoppay.common.enumeration.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isActivity = createBoolean("isActivity");

    public final StringPath lastName = createString("lastName");

    public final com.shoppay.common.domain.QMerchantStore merchantStore;

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath remark = createString("remark");

    public final DateTimePath<java.time.ZonedDateTime> resetDate = createDateTime("resetDate", java.time.ZonedDateTime.class);

    public final StringPath resetKey = createString("resetKey");

    public QCustomer(String variable) {
        this(Customer.class, forVariable(variable), INITS);
    }

    public QCustomer(Path<? extends Customer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCustomer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCustomer(PathMetadata metadata, PathInits inits) {
        this(Customer.class, metadata, inits);
    }

    public QCustomer(Class<? extends Customer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.authority = inits.isInitialized("authority") ? new com.shoppay.common.user.QAuthority(forProperty("authority")) : null;
        this.billing = inits.isInitialized("billing") ? new com.shoppay.common.reference.QBilling(forProperty("billing")) : null;
        this.delivery = inits.isInitialized("delivery") ? new com.shoppay.common.reference.QDelivery(forProperty("delivery")) : null;
        this.merchantStore = inits.isInitialized("merchantStore") ? new com.shoppay.common.domain.QMerchantStore(forProperty("merchantStore")) : null;
    }

}

