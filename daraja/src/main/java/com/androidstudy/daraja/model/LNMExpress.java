package com.androidstudy.daraja.model;

public class LNMExpress {
    private String BusinessShortCode;
    private String PassKey;
    private String Password;
    private String Timestamp;
    private String TransactionType;
    private String Amount;
    private String PartyA;
    private String PartyB;
    private String PhoneNumber;
    private String CallBackURL;
    private String AccountReference;
    private String TransactionDesc;

    public LNMExpress(String businessShortCode, String password, String timestamp, String transactionType, String amount, String partyA, String partyB, String phoneNumber, String callBackURL, String accountReference, String transactionDesc) {
        BusinessShortCode = businessShortCode;
        Password = password;
        Timestamp = timestamp;
        TransactionType = transactionType;
        Amount = amount;
        PartyA = partyA;
        PartyB = partyB;
        PhoneNumber = phoneNumber;
        CallBackURL = callBackURL;
        AccountReference = accountReference;
        TransactionDesc = transactionDesc;
    }

    public LNMExpress(String businessShortCode, String passKey, String amount, String partyA, String partyB, String phoneNumber, String callBackURL, String accountReference, String transactionDesc) {
        BusinessShortCode = businessShortCode;
        PassKey = passKey;
        Amount = amount;
        PartyA = partyA;
        PartyB = partyB;
        PhoneNumber = phoneNumber;
        CallBackURL = callBackURL;
        AccountReference = accountReference;
        TransactionDesc = transactionDesc;
    }

    public String getBusinessShortCode() {
        return BusinessShortCode;
    }

    public void setBusinessShortCode(String businessShortCode) {
        BusinessShortCode = businessShortCode;
    }

    public String getPassKey() {
        return PassKey;
    }

    public void setPassKey(String passKey) {
        PassKey = passKey;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        Timestamp = timestamp;
    }

    public String getTransactionType() {
        return TransactionType;
    }

    public void setTransactionType(String transactionType) {
        TransactionType = transactionType;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getPartyA() {
        return PartyA;
    }

    public void setPartyA(String partyA) {
        PartyA = partyA;
    }

    public String getPartyB() {
        return PartyB;
    }

    public void setPartyB(String partyB) {
        PartyB = partyB;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getCallBackURL() {
        return CallBackURL;
    }

    public void setCallBackURL(String callBackURL) {
        CallBackURL = callBackURL;
    }

    public String getAccountReference() {
        return AccountReference;
    }

    public void setAccountReference(String accountReference) {
        AccountReference = accountReference;
    }

    public String getTransactionDesc() {
        return TransactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        TransactionDesc = transactionDesc;
    }
}
