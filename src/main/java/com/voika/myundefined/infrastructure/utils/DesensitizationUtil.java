package com.voika.myundefined.infrastructure.utils;

public class DesensitizationUtil {

    /**
     * 手机号码脱敏
     *
     * @param mobilePhone
     * @return
     */
    public static String mobilePhoneEncryption(String mobilePhone) {
        // 手机号码保留前三后四
        if (StringUtil.isNotEmpty(mobilePhone)) {
            if (mobilePhone.length() == 11) {
                mobilePhone = mobilePhone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
            }
        }
        return mobilePhone;
    }

}
