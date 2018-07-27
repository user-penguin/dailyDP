package administration.dbTools;


import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class DBRequests {
    private static Connection connection = null;
    private static Statement statement;
    private static ResultSet resultset;
    private static final String ipHost = "192.168.1.38";
    private static final String url = "jdbc:mysql://localhost:8082/administration?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public boolean createDBConnect(String user, String password) {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        if(connection != null)
            return true;
        else
            return false;
    }


    //getArrOfEmployeeFromDB() возвращает shit типа:
    //[{"lastName":"кобзев","firstName":"дмитрий","id":1,"idTypeAccount":"1","secondName":"игоревич"},
    // {"lastName":"демченко","firstName":"алексей","id":2,"idTypeAccount":"2","secondName":"григорьевич"},
    // {"lastName":"алексеев","firstName":"панч","id":3,"idTypeAccount":"5","secondName":"лайнович"}]
    public JSONArray getArrOfEmployeeFromDB() {
        JSONArray JSONArrayOfEmployee = new JSONArray();
        try {
            statement = connection.createStatement();
            String query = "SELECT employee.id_employee, employee.last_name, employee.first_name, " +
                    "employee.second_name, account_data.id_type_account\n" +
                    "FROM employee, account_data\n" +
                    "WHERE employee.id_employee = account_data.id_account_data;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                JSONObject data = new JSONObject();
                data.put("empId", Integer.parseInt(rs.getString("id_employee")));
                data.put("lastName", rs.getString("last_name"));
                data.put("firstName", rs.getString("first_name"));
                data.put("secondName", rs.getString("second_name"));
                data.put("idTypeAccount", rs.getString("id_type_account"));
                JSONArrayOfEmployee.put(data);
            }
        }
        catch (SQLException ex) {
        // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return JSONArrayOfEmployee;
    }

    public JSONArray getArrOfManagersFromDB() {
        JSONArray JSONArrayOfManagers = new JSONArray();
        try {
            statement = connection.createStatement();
            String query = "SELECT employee.id_employee, employee.last_name, employee.first_name, " +
                    "employee.second_name, account_data.id_type_account" +
                    "manager.id_manager, manager.id_department\n"+
                    "FROM employee, account_data, manager\n" +
                    "WHERE employee.id_employee = account_data.id_account_data AND manager.id_employee = employee.id_employee;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                JSONObject data = new JSONObject();
                data.put("empId", Integer.parseInt(rs.getString("id_employee")));
                data.put("lastName", rs.getString("last_name"));
                data.put("firstName", rs.getString("first_name"));
                data.put("secondName", rs.getString("second_name"));
                data.put("idTypeAccount", rs.getString("id_type_account"));
                data.put("manId", rs.getInt("id_manager"));
                data.put("departId", rs.getInt("id_department"));

                JSONArrayOfManagers.put(data);
            }
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return JSONArrayOfManagers;
    }

    public JSONArray getArrOfExpertsFromDB() {
        JSONArray JSONArrayOfExperts = new JSONArray();
        try {
            statement = connection.createStatement();
            String query = "SELECT employee.id_employee, employee.last_name, employee.first_name, " +
                    "employee.second_name, account_data.id_type_account" +
                    "expert.id_expert, expert.id_department\n"+
                    "FROM employee, account_data, expert\n" +
                    "WHERE employee.id_employee = account_data.id_account_data AND expert.id_employee = employee.id_employee;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                JSONObject data = new JSONObject();
                data.put("empId", Integer.parseInt(rs.getString("id_employee")));
                data.put("lastName", rs.getString("last_name"));
                data.put("firstName", rs.getString("first_name"));
                data.put("secondName", rs.getString("second_name"));
                data.put("idTypeAccount", rs.getString("id_type_account"));
                data.put("expId", rs.getInt("id_expert"));
                data.put("departId", rs.getInt("id_department"));

                JSONArrayOfExperts.put(data);
            }
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return JSONArrayOfExperts;
    }


//    todo вставка строки (или сделать ещё отдельный метод по вставке массива строк, типа пакетной для экономии тайма?)
//    public void  putArrOfEmployeeToDB(JSONObject employee) {
//        try {
//            statement = connection.createStatement();
//            String query = "INSERT INTO employee (jdata) VALUES ("
//                    + employee.toString()
//                    + ")";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
////            preparedStatement.execute();
//
//        }
//        catch (SQLException ex) {
//            // handle any errors
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());
//        }
//    }
}
