package com.jpmc.midascore;

import org.springframework.kafka.annotation.KafkaListener;
import com.jpmc.midascore.foundation.Transaction;
import org.springframework.stereotype.Component;

@Component
public class Listener
{
    @KafkaListener(topics = "${general.kafka-topic}")
    public void process(Transaction transaction) {
        System.out.println(transaction);
    }
}
