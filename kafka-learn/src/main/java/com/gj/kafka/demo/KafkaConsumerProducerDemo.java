package com.gj.kafka.demo;

import com.gj.kafka.consumer.KafkaConsumer;
import com.gj.kafka.producer.KafkaProducer;

/**
 * Created by guojing on 2015/10/25.
 */
public class KafkaConsumerProducerDemo {

    public static void main(String[] args) {
        KafkaProducer producerThread = new KafkaProducer("test");
        producerThread.start();

        KafkaConsumer consumerThread = new KafkaConsumer("test");
        consumerThread.start();
    }
}
