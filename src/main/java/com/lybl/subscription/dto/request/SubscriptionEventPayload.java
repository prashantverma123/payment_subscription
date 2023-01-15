package com.lybl.subscription.dto.request;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lybl.subscription.dto.request.EventPayload;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubscriptionEventPayload {
    private String entity;
    private String account_id;
    private String event;
    private List<String> contains;

    @JsonProperty("payload")
    @JsonDeserialize(as = EventPayload.class)
    private EventPayload payload;
}

