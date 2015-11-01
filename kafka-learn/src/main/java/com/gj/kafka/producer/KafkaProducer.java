package com.gj.kafka.producer;


import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * Created by guojing on 2015/10/25.
 */
public class KafkaProducer extends Thread {

    private final Producer<Integer,String> producer;
    private final String topic;
    private final Properties props =new Properties();

    public KafkaProducer(String topic){
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list", "159.203.248.132:9092");
        props.put("zookeeper.connect", "159.203.248.132:2181");
        producer = new Producer<Integer, String>(new ProducerConfig(props));
        this.topic= topic;
    }

    @Override
    public void run() {
        int messageNo = 1;
        while (true){
            String msgStr = new String("Message_"+ messageNo);
            System.out.println("Send:" + msgStr);
            producer.send(new KeyedMessage<Integer, String>(topic, msgStr));
            messageNo++;
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
