package com.lybl.subscription.events;

import com.lybl.subscription.dto.Subscription;

public interface BaseEventHandlerInterface {

    void process (Subscription payload);
}
