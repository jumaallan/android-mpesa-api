package com.twigafoods.daraja.model;

public class C2BSimulate {
    private String ShortCode;
    private String CommandID;
    private String Amount;
    private String Msisdn;
    private String BillRefNumber;

    public C2BSimulate(String shortCode, String commandID, String amount, String msisdn, String billRefNumber) {
        ShortCode = shortCode;
        CommandID = commandID;
        Amount = amount;
        Msisdn = msisdn;
        BillRefNumber = billRefNumber;
    }

    public String getShortCode() {
        return ShortCode;
    }

    public void setShortCode(String shortCode) {
        ShortCode = shortCode;
    }

    public String getCommandID() {
        return CommandID;
    }

    public void setCommandID(String commandID) {
        CommandID = commandID;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getMsisdn() {
        return Msisdn;
    }

    public void setMsisdn(String msisdn) {
        Msisdn = msisdn;
    }

    public String getBillRefNumber() {
        return BillRefNumber;
    }

    public void setBillRefNumber(String billRefNumber) {
        BillRefNumber = billRefNumber;
    }
}
