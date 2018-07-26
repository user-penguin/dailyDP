package administration;

public class EntryPoint {
    public static final String TOPIC = "topic";

    public static void main(String[] args) {
        boolean isAsync = false;
        AdmProducer producerThread = new AdmProducer(TOPIC, isAsync);

        producerThread.sendMessage("___");
    }
    //todo при инициализации заполняются все нужные листы
    //todo сделать отдельные листы экспертов и менеджеров
}
