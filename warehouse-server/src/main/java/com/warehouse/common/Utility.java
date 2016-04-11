
package com.warehouse.common;

import static java.lang.Character.getNumericValue;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class Utility {

    // ***************************************
    public static final String FULL_APPLICATION_ACCESS = "FULL_APPLICATION_ACCESS";

    public static final String ADMINISTRATION = "ADMINISTRATION";

    public static final String COMPANY_MANAGEMENT = "COMPANY_MANAGEMENT";

    public static final String USER_MANAGEMENT = "USER_MANAGEMENT";

    public static final String ROLE_MANAGEMENT = "ROLE_MANAGEMENT";

    public static final String CONFIGURATION_MANAGEMENT = "CONFIGURATION_MANAGEMENT";

    private static final long MAX = 999999999;

    private static final long MODULUS = 97;

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    // *****************************************

    private Utility() {
        /* private constructor */
    }

    /**
     * <b>IBAN</b> (International Bank Account Number) Check Digit calculation/validation.
     * <p>
     * This rountine is based on the ISO 7064 Mod 97,10 check digit caluclation routine.
     * <p>
     * The two check digit characters in a IBAN number are the third and fourth characters
     * in the code. For <i>check digit</i> calculation/validation the first four characters are moved
     * to the end of the code.
     *  So <code>CCDDnnnnnnn</code> becomes <code>nnnnnnnCCDD</code> (where
     *  <code>CC</code> is the country code and <code>DD</code> is the check digit). For
     *  check digit calcualtion the check digit value should be set to zero (i.e.
     *  <code>CC00nnnnnnn</code> in this example.
     * <p>
     * For further information see
     *  <a href="http://en.wikipedia.org/wiki/International_Bank_Account_Number">Wikipedia -
     *  IBAN number</a>.
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
     * generet randomAlphaNumeric
     * @param count
     * @return
     */
    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    /**
     * Privileges are added to arrayList from comma separated string
     * 
     * @param assigned String
     * @return arList Returns the List
     */
    public static List<String> addToArrayList(String assigned) {
        List<String> arList = new ArrayList<String>();
        if (assigned != null) {
            StringTokenizer st = new StringTokenizer(assigned, ",");
            while (st.hasMoreTokens()) {
                arList.add(st.nextToken());
            }
        }
        return arList;
    }

    /**
     * Check if the passed in string is null, if true return an empty string.
     * 
     * @param originalString String
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
     * 
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
     * converts the given string into date object
     * 
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date checkDate(String date) throws WarehouseServerException {
        Date convertedDate;
        if (checkNull(date).isEmpty()) {
            return null;
        } else {
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            try {
                convertedDate = format.parse(date);
            } catch (ParseException e) {
                throw new WarehouseServerException(e);
            }

            return convertedDate;
        }
    }

    /**
     * @param date
     * @return
     * @throws WarehouseServerException
     */
    public static Date checkDateForTransaction(String date) throws WarehouseServerException {
        Date convertedDate = null;
        if (checkNull(date).isEmpty()) {
            return null;
        } else {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                convertedDate = format.parse(date);
            } catch (ParseException e) {
                throw new WarehouseServerException(e);
            }
            return convertedDate;
        }

    }

    /**
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        if (null != date) {
            SimpleDateFormat dateformatyyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
            String date_to_string = dateformatyyyyMMdd.format(date);
            return date_to_string;
        }
        return null;
    }

    /**
     * converts the lonf to String
     * 
     * @param strValue
     * @return
     */
    public static Long checkLong(Long lngValue) {
        return (lngValue == 0) ? null : lngValue;
    }

    /**
     * converts the string to Bigdecimal
     * 
     * @param strValue
     * @return
     */
    public static BigDecimal checkBigDecimal(String strValue) {
        if (checkNull(strValue).isEmpty()) {
            return null;
        } else {
            return new BigDecimal(strValue);
        }
    }

    /**
     * converts the string to Bigdecimal
     * 
     * @param strValue
     * @return
     */
    public static Integer checkInteger(String strValue) {
        if (checkNull(strValue).isEmpty()) {
            return null;
        } else {
            return Integer.parseInt(strValue);
        }
    }

    /**
     * converts the String to character
     * 
     * @param strValue
     * @return
     */
    public static Character checkCharacter(String strValue) {
        if (checkNull(strValue).isEmpty()) {
            return null;
        } else {
            return strValue.charAt(0);
        }
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
     * @param highest
     * @return
     */
    public static String getPriorDate(Date highest) {
        String fmtDt = new SimpleDateFormat("dd-MM-yyyy").format(highest);
        String date = null, month = null;
        String[] dtSlpit = fmtDt.split("-");
        if (dtSlpit[0].length() == 1) {
            date = "0" + dtSlpit[0];
        } else {
            date = dtSlpit[0];
        }
        if (dtSlpit[1].length() == 1) {
            int mon = Integer.parseInt(dtSlpit[1]);
            if (mon == 2) {
                month = "12";
            } else if (mon == 1) {
                month = "11";
            } else {
                month = "0" + (Integer.parseInt(dtSlpit[1]) - 2);
            }
        } else {
            String val = String.valueOf(Integer.parseInt(dtSlpit[1]) - 2);
            if (val.length() == 1) {
                month = "0" + val;
            } else {
                month = val;
            }
        }

        if (("04".equals(month) || "06".equals(month) || "09".equals(month) || "11".equals(month)) && ("31".equals(date))) {
            date = "30";
        }
        if ("02".equals(month) && ("31".equals(date) || "30".equals(date) || "29".equals(date))) {
            date = "28";
        }
        String newDt = date + "-" + month + "-" + dtSlpit[2];
        return newDt;
    }

    public static Integer getMonthNumber(String monthName) throws WarehouseServerException {
        Integer monthNumber = 0;
        try {
            Date date = new SimpleDateFormat("MMM").parse(monthName);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            monthNumber = cal.get(Calendar.MONTH);
        } catch (Exception e) {
            throw new WarehouseServerException(e);
        }
        return monthNumber + 1;
    }

    public static Date getOneMonthPriorDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date result = cal.getTime();
        return result;
    }

    public static Integer calculateDifference(Calendar currentDate, Calendar expiryDate) {
        Integer diffYear = expiryDate.get(Calendar.YEAR) - currentDate.get(Calendar.YEAR);
        Integer diffMonth = diffYear * 12 + expiryDate.get(Calendar.MONTH) - currentDate.get(Calendar.MONTH);
        return diffMonth;
    }
}
