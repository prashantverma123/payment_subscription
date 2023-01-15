package com.lybl.subscription.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RazorpayPlan {

    private String id;
    private String entity;
    private Integer interval;
    private String period;


    @JsonProperty("item")
    @JsonDeserialize(as = PlanItem.class)
    private PlanItem item;


    private Integer created_at;

}
