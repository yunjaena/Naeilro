package com.koreatech.naeilro.network.entity.restaurant;

import com.koreatech.naeilro.network.entity.Message;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "response", strict = false)
public class RestaurantList {

    @ElementList(inline = true, required = false)
    private List<Message> messageList;

    @ElementList(inline = true, required = false)
    private List<RestaurantInfoBody>  restaurantInfoBodyList;

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public List<RestaurantInfoBody> getRestaurantInfoBodyList() {
        return restaurantInfoBodyList;
    }

    public void setRestaurantInfoBodyList(List<RestaurantInfoBody> restaurantInfoBodyList) {
        this.restaurantInfoBodyList = restaurantInfoBodyList;
    }
}
