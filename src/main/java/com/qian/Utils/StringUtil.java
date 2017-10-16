package com.qian.Utils;

/**
 * Created by wuhuaiqian on 17-6-28.
 */
public class StringUtil {
    /**
     * trim such as String s="110000    ";
     * @param s
     * @return
     */
    public static String trim(String s){
        byte[] bytes = s.getBytes();
        StringBuffer stringBuffer =new StringBuffer();
        int cnt =0;
        for (byte b:bytes){
            if(b>=0){
                cnt++;
            }
        }
        byte [] newb = new byte[cnt];
        cnt=0;
        for (int i = 0; i <bytes.length ; i++) {
            if(bytes[i]>=0){
                newb[cnt++]=bytes[i];
            }
        }
        return new String(newb);
    }
}
