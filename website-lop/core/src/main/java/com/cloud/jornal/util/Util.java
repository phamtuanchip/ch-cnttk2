package com.cloud.jornal.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    private Util() {
        
    }
    
    /**
     * Método utilizado para transformar uma string no formato dd/MM/yyyy em um objeto Date
     * 
     * @param param
     * @return
     */
    public static Date treatToDate(String param) {
        if (param != null && param.trim().length() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date result = sdf.parse(param);
                return result;
            } catch (ParseException ex) {
                return null;
            }
        }
        return null;
    }   
    
    /**
     * Método utilizado para tratar o valor de uma string
     * 
     * @param param
     * @return
     */
    public static String treatToString(String param) {
        if (param != null && param.trim().length() > 0)
            return param.trim();
        return "";
    }
    
    /**
     * Método utilizado para transformar o valor de uma string em um objeto Long
     * 
     * @param param
     * @return
     */
    public static Long treatToLong(String param) {
        if (param != null && param.trim().length() > 0)
            return new Long(param.trim()).longValue();
        return null;
    }
    
    /**
     * Método utilizado para transformar um Long em uma string
     * 
     * @param param
     * @return
     */
    public static String treatInterface(Long param) {
        if (param == null || param == -1L)
            return "";
        else
            return String.valueOf(param);
    }
    
    /**
     * Método utilizado para transformar um Date em uma string
     *
     * @param param
     * @return
     */
    public static String treatInterface(Date param) {
        if (param != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String result = sdf.format(param);
            return result;
        }
        return "";
    }

        /**
     * Método utilizado para transformar um Date em uma string no formato dd/MM
     *
     * @param param
     * @return
     */
    public static String treatInterfaceDiaMes(Date param) {
        if (param != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
            String result = sdf.format(param);
            return result;
        }
        return "";
    }


    /**
     * Método utilizado para transformar um Date em uma string
     *
     * @param param
     * @return
     */
    public static String treatDateToSQLDate(Date param) {
        return "'"+treatInterface(param)+"'";
    }
    
    /**
     * Método utilizado para transformar uma String em uma string válida
     * 
     * @param param
     * @return
     */    
    public static String treatInterface(String param) {
        if (param == null)
            return "";
        else
            return String.valueOf(param);
    }
    
    /**
     * Método utilizado para transformar um parametro string (texto) no formato apropriado 
     * para a alteração em uma SQL, o que inclui as aspas simples.
     *
     * @param param
     * @return ' param '
     */
    public static String treatStringToSQLVarchar(String param) {
        if (param != null && param.trim().length() > 0){
            param = param.replaceAll("\'", "\\ '");
            param =  param.replaceAll("'", "''");
        }
        return "'"+param+"'";
    }

    /**
     * Método utilizado para transformar um parametro string (texto) no formato apropriado
     * para a alteração em uma SQL, o que inclui as aspas simples.
     *
     * @param param
     * @return ' param '
     */
    public static String treatStringToSQLVarchar(String param, int size) {
        if (param != null && param.trim().length() > 0){
            param = param.substring(0, size-1).replaceAll("\'", "\\ '");
            param =  param.substring(0, size-1).replaceAll("'", "''");
        }
        return "'"+param+"'";
    }

    /**
     * Método utilizado para transformar um parametro string (texto) no formato apropriado para a consulta
     * 
     * @param param
     * @return
     */
    public static String treatSQLToString(String param) {
        if (param != null && param.trim().length() > 0)
            return "%" + param.trim() + "%";
        return "%";        
    }

    /**
     * Método utilizado para transformar um parametro string em um long apropriado para a consulta
     * 
     * @param param
     * @return
     */
    public static Long treatSQLToLong(String param) {
        if (param != null && param.trim().length() > 0)
            return new Long(param).longValue();
        return -1L;        
    }    

    /**
     * Método utilizado para transformar um parametro string em um date apropriado para a consulta
     * 
     * @param param
     * @return
     */
    public static Date treatSQLToDate(String param) {
        if (param != null && param.trim().length() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date result = sdf.parse(param);
                return result;
            } catch (ParseException ex) {
                return null;
            }
        }
        return null;
    }    


    /**
     * Método utilizado para transformar um parametro float em uma string
     * 
     * @param param
     * @return
     */
    public static String treatFloatToString(double param) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(1);
        return df.format(param);
    }    
    
}
