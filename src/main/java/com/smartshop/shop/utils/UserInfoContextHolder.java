package com.smartshop.shop.utils;

import com.smartshop.core.customer.Customer;
import com.smartshop.domain.MerchantStore;

public class UserInfoContextHolder {
	private static final ThreadLocal<UserInfo> userInfoLocal = new InheritableThreadLocal<UserInfo>() {
		@Override
		protected UserInfo initialValue() {
			return new UserInfo();
		}
	};

	public static void setUserInfo(UserInfo userInfo) {
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

	public static Long getUserId() {
		return userInfoLocal.get().userId;
	}

	public static String getSessionId() {
		return userInfoLocal.get().sessionId;
	}

	public static void setUserInfo(Long tenantId, Long userId, Long employeeId, String sessionId,
			MerchantStore merchantStore) {
		UserInfo info = userInfoLocal.get();
		info.merchantStore = merchantStore;
		info.userId = userId;
		info.tenantId = tenantId;
		info.sessionId = sessionId;
	}

	public static class UserInfo {
		private MerchantStore merchantStore;
		private Customer customer;
		private Long tenantId = null;
		private Long userId = null;
		private String sessionId = null;

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

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
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

	}
}
