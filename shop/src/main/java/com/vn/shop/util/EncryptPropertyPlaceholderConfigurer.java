package com.vn.shop.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 实现jdbc相关配置加载时解密操作
 */
public class EncryptPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {
	private String[] encryptPropNames = { "jdbc.username", "jdbc.password" };

	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if (isEncryptProp(propertyName)) {
			String decryptValue = DESUtils.getDecryptString(propertyValue);
			return decryptValue;
		} else {
			return propertyValue;
		}
	}

	private boolean isEncryptProp(String propertyName) {
		for (String encryptpropertyName : encryptPropNames) {
			if (encryptpropertyName.equals(propertyName)) {
				return true;
			}
		}
		return false;
	}
}
