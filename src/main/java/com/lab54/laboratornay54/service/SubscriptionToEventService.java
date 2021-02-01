package com.lab54.laboratornay54.service;

import com.lab54.laboratornay54.model.SubscriptionToEvent;
import com.lab54.laboratornay54.repository.SubscriptionToEventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionToEventService {
    private final SubscriptionToEventRepository subscriptionToEventRepository;


    public SubscriptionToEventService(SubscriptionToEventRepository subscriptionToEventRepository) {
        this.subscriptionToEventRepository = subscriptionToEventRepository;
    }

    public Slice<SubscriptionToEvent> getSubscriptionToEvent(Pageable pageable) {
        return subscriptionToEventRepository.findAll(pageable);
    }

    public Page<SubscriptionToEvent> getSubscription(Pageable pageable) {
        return subscriptionToEventRepository.findAll(pageable);
    }

    public SubscriptionToEvent findById(String id) {
        return subscriptionToEventRepository.findById(Long.valueOf(id)).get();
    }

    public boolean deleteSubscriptionToEvent(String subscriptionId) {
        subscriptionToEventRepository.deleteById(Long.valueOf(subscriptionId));
        return true;

    }
}
