package com.lybl.subscription.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lybl.subscription.dto.request.EventEntity;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventPayload {
    @JsonProperty("subscription")
    @JsonDeserialize(as = EventEntity.class)
    private EventEntity subscription;
}
