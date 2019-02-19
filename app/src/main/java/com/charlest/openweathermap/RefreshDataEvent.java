package com.charlest.openweathermap;

public class RefreshDataEvent {
    private String message;

    public RefreshDataEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
