package com.lybl.subscription.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateSubscriptionRequest {
    private String plan_id = null;
    private String total_count = null;
    private String quantity = null;
    private String start_at = null;
    private Boolean customer_notify = null;
}
