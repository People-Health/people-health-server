package com.peoplehealth.server.endpoint;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

@ServerEndpoint("/backend")
public final class BackendClientWebSocket {

    private static final Logger LOGGER = Logger.getLogger(BackendClientWebSocket.class.getName());

    private Session session;

    public BackendClientWebSocket() {
        try {
            final WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, URI.create("ws://localhost:8080/backend"));
        } catch (IOException | DeploymentException exception) {
            throw new RuntimeException(exception);
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        LOGGER.log(Level.INFO, "Connected to backend WebSocket server");
    }

    @OnClose
    public void onClose(Session session) {
        this.session = null;
        LOGGER.log(Level.INFO, "Disconnected from backend WebSocket server");
    }

    @OnError
    public void onError(Throwable throwable) {
        LOGGER.log(Level.SEVERE, "Error in backend WebSocket client", throwable);
    }

    public void sendMessage(String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
