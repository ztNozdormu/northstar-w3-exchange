package io.github.ztnozdormu.common.utils;

@FunctionalInterface
public interface WebSocketCallback {
    /**
     * onReceive will be called when data is received from server.
     *
     * @param data The data send by server.
     */
    void onReceive(String data);
}
