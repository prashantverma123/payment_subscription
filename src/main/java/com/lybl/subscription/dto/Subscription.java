package com.lybl.subscription.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Subscription {
    private String id;
    private String entity;
    private String plan_id;
    private String customer_id;
    private Integer total_count;
    private Boolean customer_notify;
    private Integer start_at;
    private Integer quantity;
//    private Map<String,String> notes;
//    private List<Map<String,String>> addons;
    private String status;
    private Integer paid_count;
    private Integer current_start;
    private Integer current_end;
    private Integer ended_at;
    private Integer charge_at;
    private Integer auth_attempts;
    private Integer expire_by;
    private Integer offer_id;
    private String short_url;
    private Boolean has_scheduled_changes;
    private String schedule_change_at;
    private Integer remaining_count;
}
