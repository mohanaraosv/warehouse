
package com.warehouse.common;

import java.util.Comparator;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

/**
 * This Class contains custom comparator logic for all types of datatypes and
 * object
 */
@SuppressWarnings("rawtypes")
public class CustomObjectComparator implements Comparator {
    String strColNames[] = null;

    List arThis;

    String strMethodName = "";

    String strType = "";

    public CustomObjectComparator(String str[]) {
        this.strColNames = str;
    }// AlphanumComparator(String str[])

    public CustomObjectComparator(String str) {
        this.strColNames = new String[] { str };
    }// AlphanumComparator(String str[])

    public CustomObjectComparator(List ar) {
        this.arThis = (ArrayList) ar;
    }// AlphanumComparator(String str[])

    private final boolean isDigit(char ch) {
        return ch >= 48 && ch <= 57;
    }

    /**
     * Length of string is passed in for improved efficiency (only need to
     * calculate it once)
     * 
     * @param s
     * @param slength
     * @param marker
     * @return
     */
    private final String getChunk(String s, int slength, int marker) {
        StringBuilder chunk = new StringBuilder();
        char c = s.charAt(marker);
        chunk.append(c);
        marker++;
        if (isDigit(c)) {
            while (marker < slength) {
                c = s.charAt(marker);
                if (!isDigit(c)) {
                    break;
                }
                chunk.append(c);
                marker++;
            }
        } else {
            while (marker < slength) {
                c = s.charAt(marker);
                if (isDigit(c)) {
                    break;
                }
                chunk.append(c);
                marker++;
            }
        }
        return chunk.toString();
    }

    public int compareAlphaNumericString(String sVOne, String sVTwo, String SortType) {
        String s1 = sVOne;
        String s2 = sVTwo;

        int thisMarker = 0;
        int thatMarker = 0;
        int s1Length = s1.length();
        int s2Length = s2.length();

        while (thisMarker < s1Length && thatMarker < s2Length) {
            String thisChunk = getChunk(s1, s1Length, thisMarker);
            thisMarker += thisChunk.length();

            String thatChunk = getChunk(s2, s2Length, thatMarker);
            thatMarker += thatChunk.length();

            // If both chunks contain numeric characters, sort them numerically
            int result = 0;
            if (isDigit(thisChunk.charAt(0)) && isDigit(thatChunk.charAt(0))) {
                // Simple chunk comparison by length.
                int thisChunkLength = thisChunk.length();
                result = thisChunkLength - thatChunk.length();
                // If equal, the first different number counts
                if (result == 0) {
                    for (int i = 0; i < thisChunkLength; i++) {
                        if ("ASC".equals(SortType)) {
                            result = thisChunk.charAt(i) - thatChunk.charAt(i);
                        } else {
                            result = thatChunk.charAt(i) - thisChunk.charAt(i);
                        }
                        if (result != 0) {
                            return result;
                        }
                    }
                }
            } else {
                if ("ASC".equals(SortType)) {
                    result = thisChunk.compareTo(thatChunk);
                } else {
                    result = thatChunk.compareTo(thisChunk);
                }
            }
            if (result != 0) {
                return result;
            }
        }

        return s1Length - s2Length;
    }

    /**
     * compares Date values and rerurns 0,1 or -1
     * 
     * @param dateOne
     * @param dateTwo
     * @param SortType
     * @return int
     */
    private int compareDate(Date dateOne, Date dateTwo, String SortType) {
        if (dateOne == null && dateTwo == null) {
            return 0;
        }
        if ("ASC".equals(SortType)) {
            if (dateOne == null && dateTwo != null) {
                return -1;
            }
            if (dateOne != null && dateTwo == null) {
                return 1;
            }
        } else {
            if (dateOne == null && dateTwo != null) {
                return 1;
            }
            if (dateOne != null && dateTwo == null) {
                return -1;
            }
        }
        if ("ASC".equals(SortType)) {
            if (dateOne.compareTo(dateTwo) > 0) {
                return 1;
            } else if (dateOne.compareTo(dateTwo) < 0) {
                return -1;
            } else {
                return 0;
            }
        } else {
            if (dateOne.compareTo(dateTwo) < 0) {
                return 1;
            } else if (dateOne.compareTo(dateTwo) > 0) {
                return -1;
            } else {
                return 0;
            }
        } // else of(SortType.equals("ASC"))
    }// compareDate(Date dateOne,Date dateTwo, String SortType)

    /**
     * compares BigDecimal values and rerurns 0,1 or -1
     * 
     * @param sVOne
     * @param sVTwo
     * @param SortType
     * @return int
     */
    private int compareBigDecimal(String sVOne, String sVTwo, String SortType) {
        if ("".equals(sVOne.trim())) {
            sVOne = "0.0";
        }
        if ("".equals(sVTwo.trim())) {
            sVTwo = "0.0";
        }
        BigDecimal bd1 = new BigDecimal(sVOne);
        BigDecimal bd2 = new BigDecimal(sVTwo);
        if ("ASC".equals(SortType)) {
            if (bd1.compareTo(bd2) > 0) {
                return 1;
            } else if (bd1.compareTo(bd2) < 0) {
                return -1;
            } else {
                return 0;
            }
        } else {
            if (bd1.compareTo(bd2) < 0) {
                return 1;
            } else if (bd1.compareTo(bd2) > 0) {
                return -1;
            } else {
                return 0;
            }
        } // else of(SortType.equals("ASC"))
    }// compareBigDecimal(String sVOne,String sVTwo, String SortType)

    /**
     * @param o1
     * @param o2
     * @return
     */
    public int compare(Object o1, Object o2) {
        int iVal = 0;
        int iSize = 0;
        boolean continueCondition = false;
        if (strColNames == null) {
            iSize = arThis.size();
        } else {
            iSize = strColNames.length;
        }
        for (int i = 0; i < iSize; i++) {
            if (strColNames == null) {
                strMethodName = (String) arThis.get(i);
            } else {
                strMethodName = strColNames[i];
            }
            strType = (Utility.getMethodNames(o1, strMethodName.substring(0, strMethodName.indexOf('#'))).getReturnType()).getName();
            if (strType.indexOf("String") > 0) {
                String nameOne = (String) Utility.getMethodValueNoParams(o1, strMethodName.substring(0, strMethodName.indexOf('#')));
                String nameTwo = (String) Utility.getMethodValueNoParams(o2, strMethodName.substring(0, strMethodName.indexOf('#')));
                try {
                    nameOne = nameOne.replaceAll(" ", "");
                    nameTwo = nameTwo.replaceAll(" ", "");
                    iVal = compareAlphaNumericString(nameOne, nameTwo, strMethodName.substring(strMethodName.indexOf('#') + 1, strMethodName.length()));
                    if (iVal == 0) {
                        continueCondition = true;
                    } else {
                        return iVal;
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            } else if (strType.indexOf("Date") > 0) {
                Date dateOne = (Date) Utility.getMethodValueNoParams(o1, strMethodName.substring(0, strMethodName.indexOf('#')));
                Date dateTwo = (Date) Utility.getMethodValueNoParams(o2, strMethodName.substring(0, strMethodName.indexOf('#')));
                iVal = compareDate(dateOne, dateTwo, strMethodName.substring(strMethodName.indexOf('#') + 1, strMethodName.length()));
                if (iVal == 0) {
                    continueCondition = true;
                } else {
                    return iVal;
                }
            } else if (strType.indexOf("Integer") > 0 || "int".equals(strType) || "float".equals(strType) || "double".equals(strType) || strType.indexOf("BigDecimal") > 0
                    || strType.indexOf("Float") > 0 || strType.indexOf("Double") > 0 || strType.indexOf("Long") > 0) {
                String sVal1 = "" + Utility.getMethodValueNoParams(o1, strMethodName.substring(0, strMethodName.indexOf('#')));
                String sVal2 = "" + Utility.getMethodValueNoParams(o2, strMethodName.substring(0, strMethodName.indexOf('#')));
                iVal = compareBigDecimal(sVal1, sVal2, strMethodName.substring(strMethodName.indexOf('#') + 1, strMethodName.length()));
                if (iVal == 0) {
                    continueCondition = true;
                } else {
                    return iVal;
                }
            } else {
                return 0;
            }
            if (continueCondition) {
                continue;
            }
        }
        return iVal;
    }
}
