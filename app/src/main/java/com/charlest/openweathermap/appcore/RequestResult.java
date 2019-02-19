package com.charlest.openweathermap.appcore;

public class RequestResult {

    public static final int RESULT_OK = 1;

    public static final int RESULT_FAILED = 0;

    public static final int RESULT_ERROR = -1;

    private int resultCode = RESULT_OK;

    public RequestResult(int resultCode) {
        this.resultCode = resultCode;
    }


    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public void setResultOk() {
        resultCode = RESULT_OK;
    }

    public void setResultFailed() {
        resultCode = RESULT_FAILED;
    }

    public void setResultError() {
        resultCode = RESULT_ERROR;
    }

    public boolean isResultOk() {
        return resultCode == RESULT_OK;
    }


    public boolean isResultFailed() {
        return resultCode == RESULT_FAILED;
    }


    public boolean isResultError() {
        return resultCode == RESULT_ERROR;
    }
}
