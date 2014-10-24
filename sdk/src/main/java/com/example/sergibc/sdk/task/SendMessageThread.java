package com.example.sergibc.sdk.task;

import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

/**
 * Created by sergibc on 25/10/14.
 */
public class SendMessageThread extends Thread {

    private static final String TAG = SendMessageThread.class.getSimpleName();

    GoogleApiClient googleApiClient;
    String path;
    String message;

    // Constructor to send a message to the data layer
    public SendMessageThread(GoogleApiClient googleApiClient, String path, String message) {
        this.googleApiClient = googleApiClient;
        this.path = path;
        this.message = message;
    }

    public void run() {
        NodeApi.GetConnectedNodesResult nodes = Wearable.NodeApi.getConnectedNodes(googleApiClient).await();
        for (Node node : nodes.getNodes()) {
            MessageApi.SendMessageResult result = Wearable.MessageApi.sendMessage(googleApiClient, node.getId(), path, message.getBytes()).await();
            if (result.getStatus().isSuccess()) {
                Log.v(TAG, "Message: {" + message + "} sent to: " + node.getDisplayName());
            } else {
                Log.v(TAG, "ERROR: failed to send Message");
            }
        }
    }
}
