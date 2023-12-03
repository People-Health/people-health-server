package com.peoplehealth.server;

import com.peoplehealth.server.endpoint.BackendClientWebSocket;
import com.peoplehealth.server.endpoint.FlutterApplicationWebSocket;
import lombok.val;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

public class MessagingServer {

    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) throws Exception {
        final Server server = new Server(SERVER_PORT);

        final ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        WebSocketServerContainerInitializer.configure(context, (servletContext, serverContainer) -> {
            serverContainer.addEndpoint(BackendClientWebSocket.class);
            serverContainer.addEndpoint(FlutterApplicationWebSocket.class);
        });

        server.start();
    }
}
