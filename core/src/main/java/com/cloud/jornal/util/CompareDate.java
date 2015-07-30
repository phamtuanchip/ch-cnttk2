package com.cloud.jornal.util;

import java.util.Calendar;
import java.util.Date;

public class CompareDate {

    public static boolean isGreaterThan(Date inDate, Date dateToCompare){

       
        if(inDate.getYear() > dateToCompare.getYear()){
            return true;
        }else if(inDate.getYear() < dateToCompare.getYear()){
            return false;
        }else{
            if(inDate.getMonth() > dateToCompare.getMonth()){
                return true;
            }else if(inDate.getMonth() < dateToCompare.getMonth()) {
                return false;
            }else{

                if(inDate.getDate() > dateToCompare.getDate()){
                    return true;
                }else if (inDate.getDate() < dateToCompare.getDate()){
                    return false;
                }else{
                    return false;
                }
            }
        }


    }

    public static boolean isLowerThan(Date inDate, Date dateToCompare){
        if(inDate.getYear() < dateToCompare.getYear()){
            return true;
        }else if(inDate.getYear() > dateToCompare.getYear()){
            return false;
        }else{
            if(inDate.getMonth() < dateToCompare.getMonth()){
                return true;
            }else if(inDate.getMonth() > dateToCompare.getMonth()) {
                return false;
            }else{

                if(inDate.getDate() < dateToCompare.getDate()){
                    return true;
                }else if (inDate.getDate() > dateToCompare.getDate()){
                    return false;
                }else{
                    return false;
                }
            }
        }
    }

    public static boolean isBetweenThan(Date inDate, Date initDate, Date finalDate){
        /*
        System.out.println("in date year: "+inDate.getYear());
        System.out.println("in date month: "+inDate.getMonth());
        System.out.println("in date day: "+inDate.getDate());
        
        System.out.println("init date year: "+initDate.getYear());
        System.out.println("init date month: "+initDate.getMonth());
        System.out.println("init date day: "+initDate.getDate());
        
        System.out.println("final date year: "+finalDate.getYear());
        System.out.println("final date month: "+finalDate.getMonth());
        System.out.println("final date day: "+finalDate.getDate());
        */
        if(CompareDate.isGreaterThan(inDate, initDate) && 
           CompareDate.isLowerThan(inDate, finalDate)){
            return true;
        }

        return false;
    }

   
}
