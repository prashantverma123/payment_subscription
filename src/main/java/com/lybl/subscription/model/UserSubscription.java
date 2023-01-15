package com.lybl.subscription.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_subscriptions")
@Getter
@Setter
@ToString()
@NoArgsConstructor
@AllArgsConstructor
public class UserSubscription {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String userId;

    private String subscriptionId;

    private String planId;

    private String status;

    public UserSubscription(String userId, String subscriptionId, String planId, String status) {
        this.userId = userId;
        this.subscriptionId = subscriptionId;
        this.planId = planId;
        this.status = status;
    }
}