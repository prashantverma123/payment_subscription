package com.lybl.subscription.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
import java.util.Map;


@Getter
@Setter
@ToString
public class CreateSubscription {
    private String plan_id;
    private Integer total_count;
    private Integer quantity;
    private String start_at;
    private Boolean customer_notify;
}
