package com.peoplehealth.server.endpoint;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint("/flutter-app")
public final class FlutterApplicationWebSocket {

    private static final Logger LOGGER = Logger.getLogger(FlutterApplicationWebSocket.class.getName());

    private final BackendClientWebSocket backendClientWebSocket;

    public FlutterApplicationWebSocket() {
        this.backendClientWebSocket = new BackendClientWebSocket();
    }

    @OnOpen
    public void onOpen(Session session) {
        LOGGER.info("Flutter WebSocket connection opened");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        LOGGER.log(Level.INFO, "Received message from Flutter application: {0}", message);
        backendClientWebSocket.sendMessage(message);
    }

    @OnClose
    public void onClose(Session session) {
        LOGGER.info("Flutter WebSocket connection closed");
    }

    @OnError
    public void onError(Throwable error) {
        LOGGER.log(Level.SEVERE, "Error in Flutter WebSocket receiver", error);
    }
}