package com.shoppay.common.utils;

import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.shoppay.common.reference.Address;

public class GeoLocationUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(GeoLocationUtils.class);
	private static final String[] HEADERS_TO_TRY = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
			"HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
			"HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR" };

	private static DatabaseReader reader = null;

	public static String getClientIpAddress(HttpServletRequest request) {
		for (String header : HEADERS_TO_TRY) {
			String ip = request.getHeader(header);
			if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
				return ip;
			}
		}
		return request.getRemoteAddr();
	}

	public static Address getAddress(String ipAddress) throws Exception {

		if (reader == null) {
			try {
				java.io.InputStream inputFile = GeoLocationUtils.class.getClassLoader()
						.getResourceAsStream("reference/GeoLite2-City.mmdb");
				reader = new DatabaseReader.Builder(inputFile).build();
			} catch (Exception e) {
				LOGGER.error("Cannot instantiate IP database", e);
			}
		}

		Address address = new Address();

		CityResponse response = reader.city(InetAddress.getByName(ipAddress));
		address.setCountry(response.getCountry().getIsoCode());
		address.setPostalCode(response.getPostal().getCode());
		address.setZone(response.getMostSpecificSubdivision().getIsoCode());
		address.setCity(response.getCity().getName());
		return address;

	}
}
