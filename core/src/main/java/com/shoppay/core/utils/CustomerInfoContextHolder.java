package com.shoppay.core.utils;

import com.shoppay.common.domain.MerchantStore;
import com.shoppay.core.customer.Customer;

public class CustomerInfoContextHolder {
	private static final ThreadLocal<CustomerInfo> userInfoLocal = new InheritableThreadLocal<CustomerInfo>() {
		@Override
		protected CustomerInfo initialValue() {
			return new CustomerInfo();
		}
	};

	public static void setCustomerInfo(CustomerInfo userInfo) {
		if (userInfo != null) {
			userInfoLocal.set(userInfo);
		}
	}

	public static void clear() {
		userInfoLocal.remove();
	}

	public static Customer getCustomer() {
		return userInfoLocal.get().customer;
	}

	public static MerchantStore getMerchantStore() {
		return userInfoLocal.get().merchantStore;
	}


	public static String getSessionId() {
		return userInfoLocal.get().sessionId;
	}

	public static String getCartCode() {
		return userInfoLocal.get().cartCode;
	}

	public static boolean isAnony() {
		return userInfoLocal.get().isAnony;
	}
	public static String getCartTotal() {
		return userInfoLocal.get().cartTotal;
	}
	
	public static Integer getCartQuantity() {
		return userInfoLocal.get().cartQuantity;
	}

	
	public static CustomerInfo getCustomerInfo() {
		return userInfoLocal.get();
	}
	
	

	public static void setCustomerInfo(Long tenantId, Long employeeId, String sessionId,
			MerchantStore merchantStore, String cartCode) {
		CustomerInfo info = userInfoLocal.get();
		info.merchantStore = merchantStore;
		info.tenantId = tenantId;
		info.sessionId = sessionId;
		info.cartCode = cartCode;
	}

	public static class CustomerInfo {
		private boolean isAnony=false;
		private MerchantStore merchantStore;
		private Customer customer;
		private Long tenantId = null;
		private String sessionId = null;

		private String cartCode;
		private Integer cartQuantity;
		private String cartTotal;
		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		public Long getTenantId() {
			return tenantId;
		}

		public void setTenantId(Long tenantId) {
			this.tenantId = tenantId;
		}

		public String getSessionId() {
			return sessionId;
		}

		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}

		public MerchantStore getMerchantStore() {
			return merchantStore;
		}

		public void setMerchantStore(MerchantStore merchantStore) {
			this.merchantStore = merchantStore;
		}

		public String getCartCode() {
			return cartCode;
		}

		public void setCartCode(String cartCode) {
			this.cartCode = cartCode;
		}

		public boolean isAnony() {
			return isAnony;
		}

		public void setAnony(boolean isAnony) {
			this.isAnony = isAnony;
		}
		

		public Integer getCartQuantity() {
			return cartQuantity;
		}

		public void setCartQuantity(Integer cartQuantity) {
			this.cartQuantity = cartQuantity;
		}

		public String getCartTotal() {
			return cartTotal;
		}

		public void setCartTotal(String cartTotal) {
			this.cartTotal = cartTotal;
		}

		@Override
		public String toString() {
			return "CustomerInfo [isAnony=" + isAnony + ", merchantStore=" + merchantStore + ", customer=" + customer
					+ ", tenantId=" + tenantId + ", sessionId=" + sessionId + ", cartCode=" + cartCode
					+ ", cartQuantity=" + cartQuantity + ", cartTotal=" + cartTotal + "]";
		}


	}
}
