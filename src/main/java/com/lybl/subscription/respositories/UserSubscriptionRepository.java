package com.lybl.subscription.respositories;

import com.lybl.subscription.model.UserSubscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserSubscriptionRepository extends CrudRepository<UserSubscription, Long> {

    Optional<UserSubscription> findBySubscriptionId(String subscriptionId);

    Optional<List<UserSubscription>> findAllByUserId(String userId);

    Optional<List<UserSubscription>> findAllByUserIdAndStatus(String userId, String status);
}
