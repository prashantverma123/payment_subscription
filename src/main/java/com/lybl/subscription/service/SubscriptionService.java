package com.lybl.subscription.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
import com.lybl.subscription.dto.RazorpayPlan;
import com.lybl.subscription.dto.Subscription;
import com.lybl.subscription.dto.request.CreateSubscription;
import com.lybl.subscription.dto.request.SubscriptionEventPayload;
import com.lybl.subscription.dto.request.UpdateSubscriptionRequest;
import com.lybl.subscription.events.EventHandler;
import com.lybl.subscription.model.UserSubscription;
import com.lybl.subscription.respositories.UserSubscriptionRepository;
import com.lybl.subscription.utils.GeneralUtils;
import com.razorpay.Entity;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {

    @Autowired
    private UserSubscriptionRepository userSubscriptionRepository;

    private final RazorpayClient instance;

    public SubscriptionService() throws RazorpayException {
        this.instance = new RazorpayClient("rzp_test_sKLslWNZY0s79Z", "Mjs5NyUDpD7Elmj4eDoFLaCM");
    }

    @SneakyThrows
    public List<RazorpayPlan> fetchAllPlans() {
        var mapper = new ObjectMapper();
        mapper.registerModule(new JsonOrgModule());
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        var listCollectionType = mapper.getTypeFactory()
                .constructCollectionType(List.class, RazorpayPlan.class);
        String planListJson = mapper.writeValueAsString(
                instance.plans.fetchAll().stream()
                        .map(Entity::toJson)
                        .collect(Collectors.toList()));
        return mapper.readValue(planListJson, listCollectionType);
    }

    @SneakyThrows
    public RazorpayPlan fetchPlanById(String planId) {
        var mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(instance.plans.fetch(planId).toString(), RazorpayPlan.class);
    }

    @SneakyThrows
    private Long parseDateStringToUnix(String dt) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format.parse(dt);
        return date.getTime() / 1000;
    }

    @SneakyThrows
    public Subscription save(CreateSubscription createSubscription) {
        var currentUser = GeneralUtils.getLoggedInUser();

        var mapper = new ObjectMapper();
        mapper.registerModule(new JsonOrgModule());

        JSONObject subscriptionRequest = new JSONObject((new ObjectMapper().writeValueAsString(createSubscription)));

        // Customer Notification Handle
        if (!currentUser.getEmail().isEmpty()) {
            JSONObject notifyInfo = new JSONObject();
            notifyInfo.put("notify_email", currentUser.getEmail());
            subscriptionRequest.put("notify_info", notifyInfo);
            subscriptionRequest.put("customer_notify", 1);
        }
        subscriptionRequest.put("start_at", this.parseDateStringToUnix(subscriptionRequest.getString("start_at")));

        var response = mapper.readValue(instance.subscriptions.create(subscriptionRequest).toString(), Subscription.class);

        this.createUserSubscription(currentUser.getId().toString(),response);

        return mapper.readValue(instance.subscriptions.create(subscriptionRequest).toString(), Subscription.class);

    }

    private UserSubscription createUserSubscription(String userId,Subscription response){
        var userSubscription = new UserSubscription(userId, response.getId(), response.getPlan_id(), response.getStatus());
        return userSubscriptionRepository.save(userSubscription);
    }

    @SneakyThrows
    public Subscription fetchById(String id) {
        var mapper = new ObjectMapper();
        mapper.registerModule(new JsonOrgModule());
        return mapper.readValue(instance.subscriptions.fetch(id).toString(), Subscription.class);
    }

    @SneakyThrows
    public List<Subscription> fetchByUserId() {
        var currentUser = GeneralUtils.getLoggedInUser();
        var userSubscriptionsResponse = userSubscriptionRepository.findAllByUserId(currentUser.getId().toString());
        List<Subscription> results = new ArrayList<>();
        userSubscriptionsResponse.ifPresent(userSubscriptions -> userSubscriptions.forEach(p -> {
            var subsDetails = fetchById(p.getSubscriptionId());
            results.add(subsDetails);
        }));
        return results;
    }

    @SneakyThrows
    public Subscription cancel(String id) {
        var mapper = new ObjectMapper();
        mapper.registerModule(new JsonOrgModule());
        return mapper.readValue(instance.subscriptions.cancel(id).toString(), Subscription.class);
    }

    @SneakyThrows
    public Subscription update(String id, UpdateSubscriptionRequest updateParams) {
        var mapper = new ObjectMapper();
        mapper.registerModule(new JsonOrgModule());

        JSONObject subscriptionRequest = new JSONObject((new ObjectMapper().writeValueAsString(updateParams)));

        return mapper.readValue(instance.subscriptions.update(id, subscriptionRequest).toString(), Subscription.class);
    }

    public void eventHandler(SubscriptionEventPayload payload){
        var handler = new EventHandler();
        var processor = handler.getHandler(payload.getEvent());
        processor.process(payload.getPayload().getSubscription().getEntity());
    }

}
