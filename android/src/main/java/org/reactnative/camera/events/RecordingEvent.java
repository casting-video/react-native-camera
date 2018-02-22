package org.reactnative.camera.events;

import android.support.v4.util.Pools;

import org.reactnative.camera.CameraViewManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import java.util.Date;

public class RecordingEvent extends Event<RecordingEvent> {
    private static final Pools.SynchronizedPool<RecordingEvent> EVENTS_POOL = new Pools.SynchronizedPool<>(3);
    private RecordingEvent() {}

    public static RecordingEvent obtain(int viewTag) {
        RecordingEvent event = EVENTS_POOL.acquire();
        if (event == null) {
            event = new RecordingEvent();
        }
        event.init(viewTag);
        return event;
    }

    @Override
    public short getCoalescingKey() {
        return 0;
    }

    @Override
    public String getEventName() {
        return CameraViewManager.Events.EVENT_RECORDING.toString();
    }

    @Override
    public void dispatch(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
    }

    private WritableMap serializeEventData() {
        return Arguments.createMap();
    }
}
