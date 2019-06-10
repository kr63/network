package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.UserSubscription;
import com.example.demo.repo.UserDetailsRepo;
import com.example.demo.repo.UserSubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    private final UserDetailsRepo userDetailsRepo;
    private final UserSubscriptionRepo subscriptionRepo;

    @Autowired
    public ProfileService(
            UserDetailsRepo userDetailsRepo,
            UserSubscriptionRepo subscriptionRepo) {

        this.userDetailsRepo = userDetailsRepo;
        this.subscriptionRepo = subscriptionRepo;
    }

    public User changeSubscription(User channel, User subscriber) {
        List<UserSubscription> subscriptions = channel.getSubscribers()
                .stream()
                .filter(subscription ->
                        subscription.getSubscriber().equals(subscriber)
                )
                .collect(Collectors.toList());

        if (subscriptions.isEmpty()) {
            UserSubscription subscription = new UserSubscription(channel, subscriber);
            channel.getSubscribers().add(subscription);
        } else {
            channel.getSubscribers().removeAll(subscriptions);
        }

        return userDetailsRepo.save(channel);
    }

    public List<UserSubscription> getSubscribers(User channel) {
        return subscriptionRepo.findByChannel(channel);
    }

    public UserSubscription changeSubscriptionStatus(User channel, User subscriber) {
        UserSubscription subscription = subscriptionRepo.findByChannelAndSubscriber(channel, subscriber);
        subscription.setActive(!subscription.isActive());
        return subscriptionRepo.save(subscription);
    }
}
