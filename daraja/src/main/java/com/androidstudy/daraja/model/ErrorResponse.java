package com.androidstudy.daraja.model;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {

    @SerializedName("requestId")
    private String requestId;

    @SerializedName("errorCode")
    private String code;

    @SerializedName("errorMessage")
    private String message;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
