package administration;

public class EntryPoint {

    public static void main(String[] args) {
//        boolean isAsync = false;
//        AdmProducer producerThread = new AdmProducer(TOPIC, isAsync);
//
//        producerThread.sendMessage("___");
        AdmConsumer admConsumer = new AdmConsumer("con2adm");
        admConsumer.start();



    }
    //todo при инициализации заполняются все нужные листы
}
