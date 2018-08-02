package connect;

import org.json.JSONArray;
import org.json.JSONObject;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String... args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 8080);
        RemoteConnection service = (RemoteConnection) registry.lookup("connect/ConnectService");

//        Scanner in = new Scanner(System.in);
//
//        String command = "";
//        String result = "";
//        do {
//            System.out.println("Дорова, бичара, слушай сюда, есть две команды:\n" +
//                    "1) DOYOULOVEME\n" +
//                    "2) exit\n" +
//                    "Вводи любую, не ошибёшься!");
//            command = in.next();
//            if (command.compareTo("DOYOULOVEME") == 0) {
//                result = service.sayHello("Егор").toString();
//                System.out.println(result);
//            }
//
//        } while (command.compareTo("exit") != 0);

        JSONArray jsonArray = service.getEmployeeList();
        System.out.println(jsonArray.get(0).toString());
    }
}
