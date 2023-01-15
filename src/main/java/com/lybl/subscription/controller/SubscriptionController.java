package com.lybl.subscription.controller;

import com.lybl.subscription.dto.RazorpayPlan;
import com.lybl.subscription.dto.Subscription;
import com.lybl.subscription.dto.request.CreateSubscription;
import com.lybl.subscription.dto.request.SubscriptionEventPayload;
import com.lybl.subscription.dto.request.UpdateSubscriptionRequest;
import com.lybl.subscription.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/{subscriptionId}")
    public ResponseEntity<Subscription> fetchById(@PathVariable(value = "subscriptionId") String subscriptionId) {
        return ResponseEntity.ok(subscriptionService.fetchById(subscriptionId));
    }

    @GetMapping("/user")
    public ResponseEntity<List<Subscription>> fetchByUserId() {
        return ResponseEntity.ok(subscriptionService.fetchByUserId());
    }

    @GetMapping("/{subscriptionId}/cancel")
    public ResponseEntity<Subscription> cancel(@PathVariable(value = "subscriptionId") String subscriptionId) {
        return ResponseEntity.ok(subscriptionService.cancel(subscriptionId));
    }

    @PatchMapping("/{subscriptionId}")
    public ResponseEntity<Subscription> updateSubscription(@PathVariable(value = "subscriptionId") String subscriptionId,@Validated @RequestBody UpdateSubscriptionRequest updateSubscriptionRequest) {
        return ResponseEntity.ok(subscriptionService.update(subscriptionId, updateSubscriptionRequest));
    }

    @PostMapping
    public ResponseEntity<Subscription> saveSubscription(@Validated @RequestBody CreateSubscription createSubscription) {
        Subscription subscription;
        subscription = subscriptionService.save(createSubscription);
        return ResponseEntity.ok(subscription);
    }

    // PLANS API

    @GetMapping("/plans")
    public List<RazorpayPlan> findAllUsers() {
        return subscriptionService.fetchAllPlans();
    }

    @GetMapping("/plans/{id}")
    public ResponseEntity<RazorpayPlan> findPlanById(@PathVariable(value = "id") String id) {
        RazorpayPlan plan;
        plan = subscriptionService.fetchPlanById(id);
        return ResponseEntity.ok(plan);

    }

    @PostMapping("/events")
    public ResponseEntity<String> eventWebhook(@RequestBody SubscriptionEventPayload payload) {
        subscriptionService.eventHandler(payload);
        return ResponseEntity.ok("Event Captured");

    }
}
