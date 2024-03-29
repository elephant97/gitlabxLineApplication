package com.githooknotifyline.gitlabxlineapplication.handler.exception;

public class InvalidHeaderException extends Exception {

    private String inputHeaderEvent;

    public InvalidHeaderException(String inputHeaderEvent) {
        this.inputHeaderEvent = inputHeaderEvent;
    }

    @Override
    public String getMessage() {
        return String.format("'%s' is invalid GitLab Header", inputHeaderEvent);
    }

}