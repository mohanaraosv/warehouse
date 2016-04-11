
package com.warehouse.common;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HQLQueryManager {

    /**
     * Method that returns the Query object based on the parameter values
     * 
     * @return query The Query object, whose variables are instantiated.
     * @param String Sqlid The Query ID
     * @param String[] params Parameters to the corresponding query
     */
    public QueryHQL getQueryObject(String[] strQuery, List<Object> Params) throws WarehouseServerException {
        QueryHQL qobj = new QueryHQL();
        String static_Query = "";
        int iQueryArraySize = strQuery == null ? 0 : strQuery.length;
        String[] dynamic_query = new String[iQueryArraySize - 1];
        if (strQuery != null && strQuery.length > 0) {
            static_Query = strQuery[0];
            for (int i = 1; i < (strQuery.length); i++) {
                dynamic_query[i - 1] = strQuery[i];
            }
        }
        String query = static_Query;
        boolean previous = false;
        int iVaildParamLen = 0;
        int iWhereIndex = static_Query.toUpperCase().indexOf("WHERE");
        if (iWhereIndex > 0) {
            previous = true;
        }
        StringTokenizer strz1 = new StringTokenizer(query, "?");
        int iNom = strz1.countTokens();
        if (query.lastIndexOf("?") != query.length() - 1) {
            iNom = iNom - 1;
        }
        iVaildParamLen = iNom;
        if ((!Params.isEmpty()) && (dynamic_query.length > 0)) {
            for (int i = 0; i < dynamic_query.length || i < (Params.size()) - iNom; i++) {
                if (i == Params.size()) {
                    break;
                }
                if (!"".equals(getObjectVal(Utility.checkNullWithPercentage(Params.get(i + iNom)))) && previous) {
                    query += dynamic_query[i];
                    StringTokenizer strz = new StringTokenizer(Params.get(i + iNom).toString(), "||");
                    iVaildParamLen = iVaildParamLen + strz.countTokens();
                } else if (!"".equals(getObjectVal(Utility.checkNullWithPercentage(Params.get(i)))) && iWhereIndex <= 0) {
                    if (dynamic_query[i].toUpperCase().indexOf("WHERE") > 0) {
                        query += dynamic_query[i];
                    } else {
                        query += " WHERE " + dynamic_query[i];
                        StringTokenizer strz = new StringTokenizer(Params.get(i + iNom).toString(), "||");
                        iVaildParamLen = iVaildParamLen + strz.countTokens();
                        previous = true;
                    }
                }
            }
        }
        List<Object> arValidParams = new ArrayList<Object>();
        int j = 0;
        if (dynamic_query.length > 0) {
            for (int i = 0; i < Params.size(); i++) {
                if (!"".equals(this.getObjectVal(Utility.checkNullWithPercentage(Params.get(i))))) {
                    if (Params.get(i).toString().indexOf("||") > 0) {
                        StringTokenizer strzy = new StringTokenizer(Params.get(i).toString(), "||");
                        while (strzy.hasMoreTokens()) {
                            arValidParams.add(j, strzy.nextToken());
                            j++;
                        }
                    } else {
                        arValidParams.add(j, Params.get(i));
                        j++;
                    }
                }
            }
        }
        if (dynamic_query.length <= 0) {
            qobj.setValidParams(Params);
        } else {
            qobj.setValidParams(arValidParams);
            qobj.setDynamicQuery(query);
        }
        return qobj;
    }

    private String getObjectVal(Object dataVal) {
        return dataVal.toString();
    }
}
