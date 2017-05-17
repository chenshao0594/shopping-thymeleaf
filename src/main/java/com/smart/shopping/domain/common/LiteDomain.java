package com.smart.shopping.domain.common;

import java.io.Serializable;
import java.text.Collator;
import java.util.Locale;

import org.hibernate.Hibernate;

public abstract class LiteDomain<K extends Serializable & Comparable<K>, E extends LiteDomain<K, ?>>
		implements BusinessDomainInterface, Comparable<E> {

	public static final Collator DEFAULT_STRING_COLLATOR = Collator.getInstance(Locale.FRENCH);

	static {
		DEFAULT_STRING_COLLATOR.setStrength(Collator.PRIMARY);
	}

	public abstract K getId();

	public abstract void setId(K id);

	public boolean isNew() {
		return getId() == null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (object == this) {
			return true;
		}
		if (Hibernate.getClass(object) != Hibernate.getClass(this)) {
			return false;
		}
		LiteDomain<K, E> entity = (LiteDomain<K, E>) object;
		K id = getId();

		if (id == null) {
			return false;
		}

		return id.equals(entity.getId());
	}

	@Override
	public int hashCode() {
		int hash = 7;
		K id = getId();
		hash = 31 * hash + ((id == null) ? 0 : id.hashCode());
		return hash;
	}

	@Override
	public int compareTo(E o) {
		if (this == o) {
			return 0;
		}
		return this.getId().compareTo(o.getId());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("entity.");
		builder.append(Hibernate.getClass(this).getSimpleName());
		builder.append("<");
		builder.append(getId());
		builder.append("-");
		builder.append(super.toString());
		builder.append(">");

		return builder.toString();
	}

}
