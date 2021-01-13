package org.aknife.base.controller;

import org.aknife.message.model.Message;

/**
 * @ClassName BaseController
 * @Author HeZiLong
 * @Data 2021/1/13 11:14
 */
public class BaseController {

    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
