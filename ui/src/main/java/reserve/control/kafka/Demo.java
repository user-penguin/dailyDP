package reserve.control.kafka;

import java.util.concurrent.ExecutionException;

public class Demo {
    public static final String TOPIC = "topic";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        boolean isAsync = false;
        SiteProducer producerThread = new SiteProducer(TOPIC, isAsync);

        //producerThread.start();
        producerThread.sendMessage("san'huysosi lovi maslinu");
    }
}
