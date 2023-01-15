package com.lybl.subscription.events;

import com.lybl.subscription.events.handlers.ActivatedEventHandler;
import com.lybl.subscription.events.handlers.AuthenticatedEventHandler;
import com.lybl.subscription.events.handlers.BaseEventHandler;
import com.lybl.subscription.events.handlers.ChargedEventHandler;

public class EventHandler {

    public BaseEventHandlerInterface getHandler(String event){
        var handler = new BaseEventHandler();
        switch (event) {
            case "subscription.authenticated" -> {
                handler = new AuthenticatedEventHandler();
            }
            case "subscription.activated" -> {
                handler =  new ActivatedEventHandler();
                ;
            }
            case "subscription.charged" -> {
                handler =  new ChargedEventHandler();
                ;
            }
        }

        return handler;
    }

}
