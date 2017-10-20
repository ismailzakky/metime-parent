package com.cus.metime.promosi.dto2;

import java.io.Serializable;

/**
 * Created by C-US on 9/28/2017.
 */
public class EventWrapperDTO<T> implements Serializable {

    private MessageEvent event;
    private T message;

    public EventWrapperDTO() {
    }

    public EventWrapperDTO(MessageEvent event, T message) {
        this.event = event;
        this.message = message;
    }

    public MessageEvent getEvent() {
        return event;
    }

    public void setEvent(MessageEvent event) {
        this.event = event;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventWrapperDTO)) return false;

        EventWrapperDTO<?> that = (EventWrapperDTO<?>) o;

        if (getEvent() != that.getEvent()) return false;
        return getMessage() != null ? getMessage().equals(that.getMessage()) : that.getMessage() == null;
    }

    @Override
    public int hashCode() {
        int result = getEvent() != null ? getEvent().hashCode() : 0;
        result = 31 * result + (getMessage() != null ? getMessage().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EventWrapperDTO{" +
            "event=" + event +
            ", message=" + message +
            '}';
    }
}
