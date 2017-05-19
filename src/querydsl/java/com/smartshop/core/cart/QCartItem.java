package com.smartshop.core.cart;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCartItem is a Querydsl query type for CartItem
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCartItem extends EntityPathBase<CartItem> {

    private static final long serialVersionUID = -841221938L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCartItem cartItem = new QCartItem("cartItem");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final QCart shoppingCart;

    public final NumberPath<Long> skuId = createNumber("skuId", Long.class);

    public QCartItem(String variable) {
        this(CartItem.class, forVariable(variable), INITS);
    }

    public QCartItem(Path<? extends CartItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCartItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCartItem(PathMetadata metadata, PathInits inits) {
        this(CartItem.class, metadata, inits);
    }

    public QCartItem(Class<? extends CartItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.shoppingCart = inits.isInitialized("shoppingCart") ? new QCart(forProperty("shoppingCart"), inits.get("shoppingCart")) : null;
    }

}

