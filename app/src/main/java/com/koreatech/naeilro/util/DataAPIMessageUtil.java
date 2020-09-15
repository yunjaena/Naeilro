package com.koreatech.naeilro.util;

import com.koreatech.naeilro.network.entity.Message;

public class DataAPIMessageUtil {
    public static boolean isSuccess(Message message){
        return message.getResultCode().equals("00") ||  message.getResultCode().equals("0000") ;
    }
}
