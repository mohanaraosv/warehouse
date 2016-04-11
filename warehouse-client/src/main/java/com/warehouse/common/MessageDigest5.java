/**
 * @ (#) MessageDigest5.java
 * Project     : SPAN Self Lending Service
 * File        : MessageDigest5.java
 * Author      : Namdev Shenoy K
 * Company     : Span Systems
 * Date Created: 07/July/2014
 *
 * ========================================================================================================================
 *  No | Modified date |      Modfied by     |  Reason
 * ========================================================================================================================
 *        ---
 * ========================================================================================================================
 */

package com.warehouse.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import com.warehouse.common.WarehouseClientException;

public class MessageDigest5 {
	// ############################################## Don't Modify this
	// ArrayList or rearrange it.##############################################
	static char[] hexDigits= {'1', '2', 'a', 'b', 'c', '#', '$', '&', 'd', '3', '4', '5', 'e', '6', '7', '8', '9', '0', 'f', 'g', 'h', 'i', 'j', '@', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	// ##########################################################################################################################################
	private static final Logger LOGGER = Logger.getLogger(MessageDigest5.class);
	private MessageDigest5() {
		/*private constructor*/
	}
	/**
	 * @param input
	 * @return
	 */
	public static String generatePassword(String input) throws WarehouseClientException{
		byte[] source;

		try {
			// Get byte according by specified coding.
			source = input.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			source = input.getBytes();
			LOGGER.info("generatePassword",e);
		}
		String result = null;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source);
			// The result should be one 128 integer
			byte[] temp = md.digest();
			char[] str = new char[16 * 2];
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = temp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			result = new String(str);
		} catch (NoSuchAlgorithmException e) {
			throw new WarehouseClientException("Error in generatePassword",100,e);
		}
		return result.substring(0, 10);
	}
}