package com.peoplehealth.server.message;

import lombok.val;

import java.util.ArrayList;
import java.util.List;

public final class MessageController {

    private final List<String> messages = new ArrayList<>();

    public MessageController() {
        INSTANCE = this;
    }

    public void registerMessage(String message) {
        this.messages.add(message);
    }

    public String[] collectAllClearing() {
        val result = this.messages.stream().toArray(String[]::new);
        this.messages.clear();

        return result;
    }

    private static MessageController INSTANCE;

    public static MessageController getInstance() {
        if (INSTANCE == null) return new MessageController();
        return INSTANCE;
    }
}
