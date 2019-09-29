package com.consumercredits.test_data;

public class testData {
    public static String testAccountNumbers(String account_group) {

        String acc_cspl = "104416106";
        String acc_crpl = "107362252";
        String acc_ccpl = "490969917";
        String acc_crcpl = "213319101";
        String acc_crcnpl = "213116782";
        String acc_ccnpl = "213101882";
        String acc_alfam = "12345888";

        if (account_group == "CSPL")
            return acc_cspl;
        else if (account_group == "CRPL")
            return acc_crpl;
        else if (account_group == "CCPL")
            return acc_ccpl;
        else if (account_group == "CRCPL")
            return acc_crcpl;
        else if (account_group == "CRCNPL")
            return acc_crcnpl;
        else if (account_group == "CCNPL")
            return acc_ccnpl;
        else if (account_group == "ALFAM")
            return acc_alfam;
        else
            return "Invalid Test group";
    }
}
