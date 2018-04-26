package com.twigafoods.daraja.model;

public class C2BRegister {
    private String ShortCode;
    private String ResponseType;
    private String ConfirmationURL;
    private String ValidationURL;

    public C2BRegister(String shortCode, String responseType, String confirmationURL, String validationURL) {
        ShortCode = shortCode;
        ResponseType = responseType;
        ConfirmationURL = confirmationURL;
        ValidationURL = validationURL;
    }

    public String getShortCode() {
        return ShortCode;
    }

    public void setShortCode(String shortCode) {
        ShortCode = shortCode;
    }

    public String getResponseType() {
        return ResponseType;
    }

    public void setResponseType(String responseType) {
        ResponseType = responseType;
    }

    public String getConfirmationURL() {
        return ConfirmationURL;
    }

    public void setConfirmationURL(String confirmationURL) {
        ConfirmationURL = confirmationURL;
    }

    public String getValidationURL() {
        return ValidationURL;
    }

    public void setValidationURL(String validationURL) {
        ValidationURL = validationURL;
    }
}
