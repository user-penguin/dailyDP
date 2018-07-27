package administration;

import administration.Model.ListEmployee;
import administration.Model.ListExpert;
import administration.Model.ListManager;
import administration.dbTools.DBRequests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EntryPoint {

    public static void main(String[] args) {
//        boolean isAsync = false;
//        AdmProducer producerThread = new AdmProducer(TOPIC, isAsync);
//
//        producerThread.sendMessage("___");
        DBRequests dbRequests = new DBRequests();
        dbRequests.createDBConnect("root", "");

        Map<String, Object> container = new HashMap<>();

        ListEmployee listEmployee = new ListEmployee();
        listEmployee.fillList(dbRequests.getArrOfEmployeeFromDB());

        container.put("listEmp", listEmployee);

        ListManager listManager = new ListManager();
        listManager.fillList(dbRequests.getArrOfManagersFromDB());

        container.put("listMan", listManager);

        ListExpert listExpert = new ListExpert();
        listExpert.fillList(dbRequests.getArrOfExpertsFromDB());

        container.put("listExp", listExpert);

        AdmConsumer admConsumer = new AdmConsumer("con2adm", container);
        admConsumer.start();
    }
    //todo при инициализации заполняются все нужные листы
}
