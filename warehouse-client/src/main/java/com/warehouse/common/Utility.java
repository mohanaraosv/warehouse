package com.warehouse.common;

import static java.lang.Character.getNumericValue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Utility {

	private static final Logger LOGGER = Logger.getLogger(Utility.class);

	private static final long MAX = 999999999;

	private static final long MODULUS = 97;

	private static String URL_PATH;
	
	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	public static final String LINE_CONTAIN_DIGIT_OR_NOT = "(.)*(\\d)(.)*";
	// alphanumeric word having space for bank account
	public static final String ACCOUNT_PATTERN_STR = "^[0-9a-zA-Z\\s]+$";
	// format dd-mm-yyyy
	public static final String DATE_PATTERN_DD_MM_YYYY = "(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((19|20|21|22)\\d\\d)";
	// date format yyyymmdd
	public static final String DATE_PATTERN_YYYYMMDD = "[0-9]{4}[0-1][0-9][0-3][0-9]";
	// date format DDMMYYYY
	public static final String DATE_PATTERN_DDMMYYYY = "^(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])(19|20)\\d\\d$";
	// date format yyyy-mm-dd
	public static final String DATE_PATTERN_YYYY_MM_DD = "((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
	// IBAN Validation
	public static final String IBAN_PATTERN = "^[A-Z]{2,2}[0-9]{2,2}[A-Z0-9]{1,30}$";
	// Debit Credit Pattern
	public static final String DEBITCREDIT_PATTERN = "^(?:C|c|D|d|AF|af|Af|aF|BIJ|bij|Bij)$";
	// Amount validation eg: 123444 12344.000 23444,898.000 -23434.00067
	public static final String AMOUNT_PATTERN = "[0-9]+(\\.[0-9][0-9]?)?";
	// Fund Name pattern
	public static final String FUND_NAME_PATTERN_STR = "^[a-zA-Z\\s-_]+$";
	// Fund Numbers
	public static final String FUND_NUMBER_PATTERN_STR = "^[0-9\\s]+$";
	// Fund rate
	public static final String FUND_RATE_VALUE_PATTERN_STR = "^[0-9]+(\\.[0-9]+)?$";

	private Utility() {
		/* private constructor */
	}

	/**
	 * generet randomAlphaNumeric
	 * @param count
	 * @return
	 */
	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
		int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
		builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
		}
	
	/**
	 * <b>IBAN</b> (International Bank Account Number) Check Digit
	 * calculation/validation.
	 * <p>
	 * This rountine is based on the ISO 7064 Mod 97,10 check digit caluclation
	 * routine.
	 * <p>
	 * The two check digit characters in a IBAN number are the third and fourth
	 * characters in the code. For <i>check digit</i> calculation/validation the
	 * first four characters are moved to the end of the code. So
	 * <code>CCDDnnnnnnn</code> becomes <code>nnnnnnnCCDD</code> (where
	 * <code>CC</code> is the country code and <code>DD</code> is the check
	 * digit). For check digit calcualtion the check digit value should be set
	 * to zero (i.e. <code>CC00nnnnnnn</code> in this example.
	 * <p>
	 * For further information see <a
	 * href="http://en.wikipedia.org/wiki/International_Bank_Account_Number"
	 * >Wikipedia - IBAN number</a>.
	 */
	public static boolean isValidIBAN(String code) {
		if (code.length() < 5) {
			return false;
		}

		String reformattedCode = code.substring(4) + code.substring(0, 4);
		long total = 0;
		for (int i = 0; i < reformattedCode.length(); i++) {
			int charValue = getNumericValue(reformattedCode.charAt(i));
			if (charValue < 0 || charValue > 35) {
				return false;
			}
			total = (charValue > 9 ? total * 100 : total * 10) + charValue;
			if (total > MAX) {
				total = (total % MODULUS);
			}
		}

		return (total % MODULUS) == 1;
	}

	/**
	 * Privileges are added to arrayList from comma separated string
	 * 
	 * @param assigned
	 *            String
	 * @return arList Returns the List
	 */
	public static List<String> addToArrayList(String assigned) {
		List<String> arList = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(assigned, ",");
		while (st.hasMoreTokens()) {
			arList.add(st.nextToken());
		}
		return arList;
	}

	/**
	 * Check if the passed in string is null, if true return an empty string.
	 * 
	 * @param originalString
	 * @return String
	 */
	public static String checkNull(final Object originalString) {
		String retStr = null;
		if (originalString == null || "null".equals(originalString)) {
			retStr = "";
		} else if ("undefined".equals(originalString)) {
			retStr = "";
		} else {
			retStr = originalString.toString().trim();
		}
		return retStr;
	}

	/**
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		if (checkNull(date).isEmpty()) {
			return null;
		} else {
			SimpleDateFormat dateformatyyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
			String date_to_string = dateformatyyyyMMdd.format(date);
			return date_to_string;
		}
	}

	/**
	 * Replace a Given String
	 * 
	 * @param target
	 * @param from
	 * @param to
	 * @return
	 */
	public static String replaceString(String target, String from, String to) {
		int start = target.indexOf(from);
		if (start == -1) {
			return target;
		}
		int lf = from.length();
		char[] targetChars = target.toCharArray();
		StringBuilder buffer = new StringBuilder();
		int copyFrom = 0;
		while (start != -1) {
			buffer.append(targetChars, copyFrom, start - copyFrom);
			buffer.append(to);
			copyFrom = start + lf;
			start = target.indexOf(from, copyFrom);
		}
		buffer.append(targetChars, copyFrom, targetChars.length - copyFrom);
		return buffer.toString();
	}

	/**
	 * Method to append sequence number for each '?'. this is required for HQL
	 * 
	 * @param strQuery
	 * @return
	 */
	public static String hqlToken(String strQuery) {
		String strReturn = "";
		int iVal = 1;
		if (strQuery.indexOf('?') > 0) {
			while (true) {
				if (strQuery.indexOf('?') > 0) {
					strQuery = strQuery.replaceFirst("\\?", "{" + (iVal++) + "}");
				} else {
					break;
				}
			}
			for (int i = 1; i < iVal; i++) {
				strQuery = strQuery.replace("{" + i + "}", "?" + i + "");
			}
			strReturn = strQuery;
		} else {
			strReturn = strQuery;
		}
		return strReturn;
	}

	/**
	 * Method to replace empty String with % values
	 * 
	 * @param originalString
	 * @return
	 */
	public static Object checkNullWithPercentage(Object originalString) {
		String retStr = null;
		if (originalString == null || "null".equals(originalString) || "".equals(replaceString(replaceString((originalString.toString()).trim(), "%", ""), "||", ""))) {
			retStr = "";
		} else if ("undefined".equals(originalString)) {
			retStr = "";
		} else {
			retStr = originalString.toString().trim();
		}
		return retStr;
	}

	/**
	 * Gets the Value from the object for the method name @param obj Object @param
	 * methodName String @return Object
	 * 
	 * @param obj
	 * @param methodName
	 * @return
	 */
	public static Object getMethodValue(Object obj, String methodName) {
		Class c = obj.getClass();
		Object ret = new Object();
		try {
			Method method = c.getMethod(methodName, new Class[0]);
			ret = method.invoke(obj, new Object[0]);
		} catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
			LOGGER.error("Error in getMethodValue call getMethod", e);
		}
		c = null;
		return ret;
	}

	/**
	 * Method to get the URL
	 * 
	 * @param strURL
	 * @return
	 */
	public static void prepareURLPATH(String strURL) {
		strURL = strURL.substring(0, strURL.lastIndexOf('/'));
		URL_PATH = strURL.substring(0, strURL.lastIndexOf('/'));
	}

	/**
	 * @param csvFileName
	 * @return
	 */

	/**
	 * @param image
	 * @param path
	 * @param u_id
	 * @param name
	 * @param companyId
	 * @param fileExt
	 * @return
	 */
	public static String getImagePath(byte[] image, String path, String filename) {
		StringBuilder getPath = new StringBuilder();
		FileOutputStream fos = null;

		try {
			getPath.append(path).append(File.separator).append("img").append(File.separator).append(filename).append(".png");
			fos = new FileOutputStream(getPath.toString(), false);
			fos.write(image);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			LOGGER.error("Error in getImagePath method :", e);
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}

			} catch (IOException e) {
				LOGGER.error("Error in getImagePath method :", e);
			}
		}
		return getPath.toString();
	}
	
	/**
	 * @param image
	 * @param path
	 * @param u_id
	 * @param name
	 * @param companyId
	 * @param fileExt
	 * @return
	 */
	public static String getImagePath(byte[] image, String path, Long u_id, String name, Long companyId, String fileExt) {
		StringBuilder getPath = new StringBuilder();
		FileOutputStream fos = null;

		try {
			if ((companyId == 0L) && (companyId != null)) {
				getPath.append(path).append(File.separator).append("img").append(File.separator).append(u_id).append("_").append(name).append(".png");
				fos = new FileOutputStream(getPath.toString(), false);
			} else {
				getPath.append(path).append(File.separator).append("img").append(File.separator).append(u_id).append("_").append(name).append("_").append(companyId).append(".png");
				fos = new FileOutputStream(getPath.toString(), false);
			}
			fos.write(image);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			LOGGER.error("Error in getImagePath method :", e);
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}

			} catch (IOException e) {
				LOGGER.error("Error in getImagePath method :", e);
			}
		}
		return getPath.toString();
	}

	/**
	 * Gets the Value from the object for the method name
	 * 
	 * @param obj
	 * @param methodName
	 * @return Object
	 */
	public static Object getMethodValueNoParams(Object obj, String methodName) {
		Class c = obj.getClass();
		Object ret = new Object();
		try {
			Method method = c.getMethod(methodName, new Class[0]);
			ret = method.invoke(obj, new Object[0]);

		} catch (InvocationTargetException ex) {
			LOGGER.error("Error in getMethodValueNoParams :", ex);
			ret = null;
		} catch (NoSuchMethodException ne) {
			LOGGER.error("Error in getMethodValueNoParams :", ne);
			ret = null;
		} catch (IllegalAccessException ia) {
			LOGGER.error("Error in getMethodValueNoParams :", ia);
			ret = null;
		}

		return ret;
	}// getMethodValueNoParams (Object obj, String methodName)

	/**
	 * Gets the Method object for the method name
	 * 
	 * @param obj
	 * @param lineFeed
	 *            *
	 * @return Method
	 */
	public static Method getMethodNames(Object obj, String lineFeed) {
		Class c = obj.getClass();
		Method[] method = c.getMethods();
		Method m = null;
		int size = method.length;
		for (int i = 0; i < size; i++) {
			String name = method[i].getName();
			if (name.equals(lineFeed)) {
				m = method[i];
				break;
			}
		}
		return m;
	}// getMethodNames (Object obj, String lineFeed)

	/**
	 * This method sorts a ArrayList containing Value objects(VO or DTO) based
	 * on the Method names and order by Example str[] =
	 * {"getLast#ASC",getFirstName#DESC......} where getLast , getFirstName are
	 * method names in the VO or DTO (VO or DTO are Classes with getter ang
	 * setter method)
	 * 
	 * @param arr
	 *            List
	 * @param str
	 *            [] String
	 */
	public static List getSortedArrayList(List arr, String str[]) {
		List artemp = (List) arr;
		QuickSort sort = new QuickSort();
		CustomObjectComparator sc = null;
		sc = new CustomObjectComparator(str);
		sort.sort(artemp, sc);
		return artemp;
	}

	/**
	 * This method sorts a ArrayList containing Value objects(VO or DTO) based
	 * on the Method names and order by Example str[] =
	 * {"getLast#ASC",getFirstName#DESC......} where getLast , getFirstName are
	 * method names in the VO or DTO (VO or DTO are Classes with getter ang
	 * setter method)
	 * 
	 * @param arr
	 *            List
	 * @param str
	 *            [] String
	 */
	public static List getSortedArrayList(List arr, String str) {
		List artemp = (List) arr;
		QuickSort sort = new QuickSort();
		CustomObjectComparator sc = null;
		sc = new CustomObjectComparator(str);
		sort.sort(artemp, sc);
		return artemp;
	}

	/**
	 * This method sorts a ArrayList containing Value objects(VO or DTO) based
	 * on the Method names and order by Example str[] =
	 * {"getLast#ASC",getFirstName#DESC......} where getLast , getFirstName are
	 * method names in the VO or DTO (VO or DTO are Classes with getter ang
	 * setter method)
	 * 
	 * @param arr
	 *            List
	 * @param arr2
	 *            List
	 */
	public static List getSortedArrayList(List arr, List arr2) {
		List artemp = arr;
		QuickSort sort = new QuickSort();
		CustomObjectComparator sc = null;
		sc = new CustomObjectComparator(arr2);
		sort.sort(artemp, sc);
		return artemp;
	}


	/**
	 * @param account_no
	 * @return
	 */
	public static String unformatAccount(String account_no) {
		if (account_no != null) {
			if (account_no.contains(" ")) {
				account_no = account_no.replace(" ", "");
			}
			if (account_no.contains(".")) {
				account_no = account_no.replace(".", "");
			}
		}
		return account_no;
	}

	/**
	 * @param account_no
	 * @return
	 */
	private static String unformatAmount(String amount) {
		if (amount != null && amount.contains(",")) {
			String aa = amount.substring(amount.lastIndexOf(",") + 1, amount.length());
			if (aa.contains(".")) {
				if (amount.contains(",")) {
					amount = amount.replace(",", "");
				}
			} else {
				if (amount.contains(".")) {
					amount = amount.replace(".", "");
				}
				amount = amount.replace(",", ".");
			}
		} else if (amount != null && (amount.contains(".") && !amount.contains(","))) {
			String aa = amount.substring(amount.lastIndexOf(".") + 1, amount.length());
			if (aa.length() > 2) {
				amount = amount.replace(".", "");
			}
		} else {
			amount = amount;
		}

		return amount;
	}

	/**
	 * @param dateValue
	 * @return
	 */
	private static String unformatDate(String dateValue) {
		StringBuilder strBul = new StringBuilder();
		if (dateValue != null && patternMatch(DATE_PATTERN_YYYYMMDD, dateValue)) { // 20140430
			strBul.append(dateValue.substring(0, 4)).append("-").append(dateValue.substring(4, 6)).append("-").append(dateValue.substring(6, 8));
		}
		if (dateValue != null && patternMatch(DATE_PATTERN_DD_MM_YYYY, dateValue)) {// 30-04-2014
			String[] valueSplit = dateValue.split("-");
			strBul.append(valueSplit[2]).append("-").append(valueSplit[1]).append("-").append(valueSplit[0]);
		}

		if (dateValue != null && patternMatch(DATE_PATTERN_DDMMYYYY, dateValue)) {// 30042014
			strBul.append(dateValue.substring(4)).append("-").append(dateValue.substring(2, 4)).append("-").append(dateValue.substring(0, 2));
		}

		if (dateValue != null && patternMatch(DATE_PATTERN_YYYY_MM_DD, dateValue)) {// 2014-04-30
			String[] valueSplit = dateValue.split("-");
			strBul.append(valueSplit[0]).append("-").append(valueSplit[1]).append("-").append(valueSplit[2]);
		}

		return strBul.toString();
	}

	/**
	 * @param patternStr
	 * @param value
	 * @return
	 */
	public static boolean patternMatch(String patternStr, String value) {
		boolean found;
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(value);
		found = matcher.find();
		return found;
	}

	/**
	 * @param patternStr
	 * @param value
	 * @return
	 */
	public static boolean anotherDatePatternMatch(String patternStr, String value) {
		boolean found;
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(value);
		found = matcher.find();
		return found;
	}


	/**
	 * @param nodeName
	 * @param listOfSelectedNode
	 * @return
	 */
	private static String getSlecetedNodeValue(String nodeName, NodeList listOfSelectedNode) {
		String returnValue = "";
		for (int bDt = 0; bDt < listOfSelectedNode.getLength(); bDt++) {
			Node bDtnode = listOfSelectedNode.item(bDt);
			if (bDtnode.getNodeType() == Node.ELEMENT_NODE) {
				Element dt = (Element) bDtnode;
				NodeList dtList = dt.getElementsByTagName(nodeName);
				if (dtList.getLength() > 0) {
					Element dtElement = (Element) dtList.item(0);
					NodeList textDtList = dtElement.getChildNodes();
					returnValue = ((Node) textDtList.item(0)).getNodeValue().trim();
				} else {
					returnValue = "";
				}
			}
		}
		return returnValue;
	}

	/**
	 * @param amtElement
	 * @return
	 */
	private static String getAttributeNameAndValue(Element amtElement) {
		NamedNodeMap attributes1 = amtElement.getAttributes();
		String currency = null;
		int numAttrs1 = attributes1.getLength();
		for (int i = 0; i < numAttrs1; i++) {
			Attr attr = (Attr) attributes1.item(i);
			String attrValue = attr.getNodeValue();
			currency = attrValue;
		}
		return currency;
	}

	/**
	 * @param date
	 * @return
	 * @throws WarehouseClientException
	 */
	public static Date checkDateForTransaction(String date) throws WarehouseClientException {
		Date convertedDate = null;
		if (checkNull(date).isEmpty()) {
			return null;
		} else {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				convertedDate = format.parse(date);
			} catch (ParseException e) {
				throw new WarehouseClientException(e);
			}
			return convertedDate;
		}

	}



	/**
	 * Method to create a date from Calendar
	 * 
	 * @param Year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date getUtilDate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
		return cal.getTime();
	}

	/**
	 * @param forMonth
	 * @return
	 */
	public static int returnMonthAsNumber(String forMonth) {
		String[] splitforMonth = forMonth.split("-");
		int month = 0;
		switch (splitforMonth[0]) {
		case "January":
			month = 1;
			break;
		case "February":
			month = 2;
			break;
		case "March":
			month = 3;
			break;
		case "April":
			month = 4;
			break;
		case "May":
			month = 5;
			break;
		case "June":
			month = 6;
			break;
		case "July":
			month = 7;
			break;
		case "August":
			month = 8;
			break;
		case "September":
			month = 9;
			break;
		case "October":
			month = 10;
			break;
		case "November":
			month = 11;
			break;
		case "December":
			month = 12;
			break;
		default:
			break;
		}
		return month;

	}

	/**
	 * @param splittoMonth
	 * @return
	 */
	public static int getLastDateOfMonth(String[] splittoMonth) {
		int monthNo = Utility.returnMonthAsNumber(splittoMonth[0]);
		int monthEndDate = 0;
		if (monthNo == 1 || monthNo == 3 || monthNo == 5 || monthNo == 7 || monthNo == 8 || monthNo == 10 || monthNo == 12) {
			monthEndDate = 31;
		}
		if (monthNo == 4 || monthNo == 6 || monthNo == 9 || monthNo == 11) {
			monthEndDate = 30;
		}
		if (monthNo == 2) {
			monthEndDate = 28;
		}
		return monthEndDate;
	}


	/**
	 * @param input
	 * @return
	 */
	private static String getDigitValueFromString(String input) {

		Pattern regex = Pattern.compile("(.)*(\\d)(.)*");
		String noOfYrs = "";
		if (input != null) {
			Matcher matcher = regex.matcher(input);
			if (matcher.matches()) {
				int len = input.length();
				if (len == 3) {
					noOfYrs = input.substring(1, 2);
				}
				if (len == 4) {
					noOfYrs = input.substring(1, 3);
				}
			} else {
				noOfYrs = "0";
			}
		} else {
			noOfYrs = "0";
		}

		return noOfYrs;
	}
	
}

