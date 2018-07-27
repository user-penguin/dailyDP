package administration;

import administration.Model.ListEmployee;
import administration.Model.ListExpert;
import administration.Model.ListManager;
import administration.dbTools.DBRequests;
import kafka.utils.ShutdownableThread;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.json.JSONObject;

import java.util.*;

public class AdmConsumer extends ShutdownableThread {
    private final KafkaConsumer<Integer, String> consumer;
    private final String topic;
    private Map<String, Object> container;

    public static final String KAFKA_SERVER_URL = "localhost";
    public static final int KAFKA_SERVER_PORT = 9092;
    public static final String CLIENT_ID = "AdmConsumer";

    public AdmConsumer(String topic, Map<String, Object> container) {
        super("AdmConsumer", false);
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER_URL + ":" + KAFKA_SERVER_PORT);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, CLIENT_ID);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer<>(props);
        this.topic = topic;
        this.container = container;
    }

    private void handle(JSONObject message)
    {
        DBRequests dbRequests = new DBRequests();
        dbRequests.createDBConnect("root", "");
        switch (message.getString("command")){
            case "get emp list":{
                ListEmployee listEmployee = (ListEmployee) container.get("listEmp");
                AdmProducer admProducer = new AdmProducer("con2adm", false);
                admProducer.sendMessage(listEmployee.toString());
            }
            case "change emp info":{

            }
            case "put emp":{
                ListEmployee listEmployee = (ListEmployee) container.get("listEmp");

                JSONObject employee = new JSONObject( message.getString("content"));

                listEmployee.addEmployee(employee);
            }
            case "get man list":{
                ListManager listManager = (ListManager) container.get("listMan");
                AdmProducer admProducer = new AdmProducer("con2adm", false);
                admProducer.sendMessage(listManager.toString());
            }
            case "get exp list":{
                ListExpert listExpert = (ListExpert) container.get("listExp");
                AdmProducer admProducer = new AdmProducer("con2adm", false);
                admProducer.sendMessage(listExpert.toString());
            }
        }
    }

    @Override
    public void doWork() {
        consumer.subscribe(Collections.singletonList(this.topic));
        ConsumerRecords<Integer, String> records = consumer.poll(1000);
        //почитать про асинхрон
        for (ConsumerRecord<Integer, String> record : records) {
            //System.out.println("Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset());
            //
            //
            //обработка сообщений к сервису администрирования
            //
            //

            JSONObject message = new JSONObject(record.value());
            handle(message);
        }
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public boolean isInterruptible() {
        return false;
    }
}
