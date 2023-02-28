package io.github.ztnozdormu.common.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class WebSocketConnection extends WebSocketListener {

    private static final AtomicInteger connectionCounter = new AtomicInteger(0);
    private static final int NORMAL_CLOSURE_STATUS = 1000;
    private static final OkHttpClient client = OkHttpUtils.builder().okHttpClient;

    private final WebSocketCallback onOpenCallback;
    private final WebSocketCallback onMessageCallback;
    private final WebSocketCallback onClosingCallback;
    private final WebSocketCallback onFailureCallback;

    private final int connectionId;
    private final Request request;
    private final String streamName;

    private WebSocket webSocket;

    private final Object mutex;

    private String message;

    public WebSocketConnection(
            WebSocketCallback onOpenCallback,
            WebSocketCallback onMessageCallback,
            WebSocketCallback onClosingCallback,
            WebSocketCallback onFailureCallback,
            Request request
    ) {
        this.onOpenCallback = onOpenCallback;
        this.onMessageCallback = onMessageCallback;
        this.onClosingCallback = onClosingCallback;
        this.onFailureCallback = onFailureCallback;
        this.connectionId = WebSocketConnection.connectionCounter.incrementAndGet();
        this.request = request;
        this.streamName = request.url().host() + request.url().encodedPath();
        this.webSocket = null;
        this.mutex = new Object();
    }

    public WebSocketConnection(
            WebSocketCallback onOpenCallback,
            WebSocketCallback onMessageCallback,
            WebSocketCallback onClosingCallback,
            WebSocketCallback onFailureCallback,
            Request request,
            String message
    ) {
        this.onOpenCallback = onOpenCallback;
        this.onMessageCallback = onMessageCallback;
        this.onClosingCallback = onClosingCallback;
        this.onFailureCallback = onFailureCallback;
        this.connectionId = WebSocketConnection.connectionCounter.incrementAndGet();
        this.request = request;
        this.streamName = request.url().host() + request.url().encodedPath();
        this.webSocket = null;
        this.mutex = new Object();
        this.message = message;
    }

    public void connect() {
        synchronized (mutex) {
            if (null == webSocket) {
                log.info("[Connection {}] Connecting to {}", connectionId, streamName);
                webSocket = client.newWebSocket(request, this);
            } else {
                log.info("[Connection {}] is already connected to {}", connectionId, streamName);
            }
        }
    }

    public int getConnectionId() {
        return connectionId;
    }


    public void close() {
        if (null != webSocket) {
            log.info("[Connection {}] Closing connection to {}", connectionId, streamName);
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
        }
    }

    @Override
    public void onOpen(WebSocket ws, Response response) {
        log.info("[Connection {}] Connected to Server", connectionId);
        if(this.message!=null){
            ws.send(message);
        }
        onOpenCallback.onReceive(null);
    }

    @Override
    public void onClosing(WebSocket ws, int code, String reason) {
        super.onClosing(ws, code, reason);
        onClosingCallback.onReceive(reason);
    }

    @Override
    public void onMessage(WebSocket ws, String text) {
        onMessageCallback.onReceive(text);
    }

    @Override
    public void onFailure(WebSocket ws, Throwable t, Response response) {
        log.error("[Connection {}] Failure", connectionId, t);
        onFailureCallback.onReceive(null);
    }
}
