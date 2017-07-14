package com.shoppay.common.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMerchantStore is a Querydsl query type for MerchantStore
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMerchantStore extends EntityPathBase<MerchantStore> {

    private static final long serialVersionUID = -1495954327L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMerchantStore merchantStore = new QMerchantStore("merchantStore");

    public final StringPath address = createString("address");

    public final StringPath city = createString("city");

    public final StringPath code = createString("code");

    public final StringPath continueShoppingURL = createString("continueShoppingURL");

    public final com.shoppay.core.common.QCountry country;

    public final StringPath domainName = createString("domainName");

    public final StringPath emailAddress = createString("emailAddress");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath invoiceTemplate = createString("invoiceTemplate");

    public final StringPath Logo = createString("Logo");

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public final StringPath postalcode = createString("postalcode");

    public final EnumPath<com.shoppay.common.enumeration.MeasureUnit> sizeUnit = createEnum("sizeUnit", com.shoppay.common.enumeration.MeasureUnit.class);

    public final StringPath stateProvince = createString("stateProvince");

    public final EnumPath<com.shoppay.common.enumeration.MeasureUnit> weightUnit = createEnum("weightUnit", com.shoppay.common.enumeration.MeasureUnit.class);

    public final com.shoppay.core.common.QZone zone;

    public QMerchantStore(String variable) {
        this(MerchantStore.class, forVariable(variable), INITS);
    }

    public QMerchantStore(Path<? extends MerchantStore> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMerchantStore(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMerchantStore(PathMetadata metadata, PathInits inits) {
        this(MerchantStore.class, metadata, inits);
    }

    public QMerchantStore(Class<? extends MerchantStore> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.country = inits.isInitialized("country") ? new com.shoppay.core.common.QCountry(forProperty("country")) : null;
        this.zone = inits.isInitialized("zone") ? new com.shoppay.core.common.QZone(forProperty("zone"), inits.get("zone")) : null;
    }

}

