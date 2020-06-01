package com.dtt.titiappv1.Utilities;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SupportClassLogic {
    public static String convertDateToString(Calendar calendar){
        return new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());
    }

    public static String revertDateString(String dateStr, String character){
        String[] arrNumber = dateStr.split(character);
        return arrNumber[2] + character + arrNumber[1] + character + arrNumber[0];
    }

    public static String formatMoney(int money){
        DecimalFormat f = new DecimalFormat("###,###,###");
        return f.format(money) + " đ";
    }

    public static String removeAccentsInString(String string){
        String chuoiMoi = "";
        boolean flag = false;
        int j;
        for(int i =0; i < string.length(); i++) {

            for(j=0; j < Constant.UNICODE.length(); j++) {
                if(string.charAt(i) == Constant.UNICODE.charAt(j)) {
                    chuoiMoi += Constant.NONUNICODE.charAt(j);
                    flag = true;
                    break;
                }
            }

            if(!flag) {
                chuoiMoi += string.charAt(i);
            } else {
                flag = false;
                continue;
            }

        }
        return chuoiMoi;
    }
}
