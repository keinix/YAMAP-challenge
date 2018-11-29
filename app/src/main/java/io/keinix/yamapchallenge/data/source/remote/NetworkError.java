package io.keinix.yamapchallenge.data.source.remote;


/**
 * Types of Network Errors to be post to a {@link NetworkErrorListener}
 * so that the user can be notified.
 */
public enum NetworkError {
    SERVER_ERROR,
    GENERAL_NETWORK_ERROR
}
