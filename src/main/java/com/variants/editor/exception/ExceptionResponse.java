package com.variants.editor.exception;

import java.util.Date;
import java.util.List;

public class ExceptionResponse {
    private Date timestamp;
    private String uri;
    private List<String> messages;

    public ExceptionResponse(Date timestamp, List<String> messages, String uri) {
        this.timestamp = timestamp;
        this.messages = messages;
        this.uri = uri;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getUri() {
        return uri;
    }

    public List<String> getMessages() {
        return messages;
    }
}
