package com.lybl.subscription.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlanItem {
    public String id;
    public String name;
    public Integer amount;
    public String currency;
    public String description;
}
