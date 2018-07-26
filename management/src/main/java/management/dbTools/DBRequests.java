package management.dbTools;


import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class DBRequests {
    private static Connection connection = null;
    private static Statement statement;
    private static ResultSet resultset;
    private static final String ipHost = "192.168.1.38";
    private static final String url = "jdbc:mysql://192.168.1.38:3306/administration?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

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
    public JSONArray getArrOfCandidatesFromDB(int manId) {
        JSONArray JSONArrayOfCandidates = new JSONArray();
        try {
            statement = connection.createStatement();
            String query = "SELECT candidate.id, candidate.last_name, candidate.first_name, " +
                    "candidate.second_name, candidate.phone" +
                    "candidate.email, candidate.skills"+
                    "candidate.id_candidate_status, candidate.resume_id"+
                    "candidate.id_vacancy"+
                    "FROM candidate, vacancy\n" +
                    "WHERE candidate.id_vacancy = vacancy.id_vacancy " +
                    "AND vacancy.id_manager="+manId+";";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                JSONObject data = new JSONObject();
                data.put("id", Integer.parseInt(rs.getString("id")));
                data.put("lastName", rs.getString("last_name"));
                data.put("firstName", rs.getString("first_name"));
                data.put("secondName", rs.getString("second_name"));
                data.put("phone", rs.getString("phone"));
                data.put("email", rs.getString("email"));
                data.put("skills", rs.getString("skills"));
                data.put("idStatus", rs.getInt("id_candidate_status"));
                data.put("idResume", rs.getInt("resume_id"));
                data.put("idVacancy", rs.getInt("id_vacancy"));
                JSONArrayOfCandidates.put(data);
            }
        }
        catch (SQLException ex) {
        // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return JSONArrayOfCandidates;
    }

    public JSONArray getArrOfVacanciesFromDB(int manId) {
        JSONArray JSONArrayOfVacancies = new JSONArray();
        try {
            statement = connection.createStatement();
            String query = "SELECT vacancy.id, vacancy.id_manager"+
                    "vacancy.name, vacancy.description,"+
                    "vacancy.skills, vacancy.id_vacancy_status"+
                    "FROM vacancy\n" +
                    "WHERE vacancy.id_manager = " + manId + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                JSONObject data = new JSONObject();
                data.put("id", Integer.parseInt(rs.getString("id")));
                data.put("idManager", rs.getString("id_manager"));
                data.put("name", rs.getString("name"));
                data.put("description", rs.getString("desription"));
                data.put("skills", rs.getString("skills"));
                data.put("idStatus", rs.getInt("id_vacancy_status"));
                JSONArrayOfVacancies.put(data);
            }
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return JSONArrayOfVacancies;
    }

    public JSONArray getArrOfCandidatesOnVacancyFromDB(int vacancyId) {
        JSONArray JSONArrayOfCandidatesOnVacancy = new JSONArray();
        try {
            statement = connection.createStatement();
            String query = "SELECT candidate.id, candidate.last_name, candidate.first_name, " +
                    "candidate.second_name, candidate.phone" +
                    "candidate.email, candidate.skills"+
                    "candidate.id_candidate_status, candidate.resume_id"+
                    "candidate.id_vacancy"+
                    "FROM candidate, vacancy\n" +
                    "WHERE candidate.id_vacancy = vacancy.id_vacancy " +
                    "AND vacancy.id="+vacancyId+";";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                JSONObject data = new JSONObject();
                data.put("id", Integer.parseInt(rs.getString("id")));
                data.put("lastName", rs.getString("last_name"));
                data.put("firstName", rs.getString("first_name"));
                data.put("secondName", rs.getString("second_name"));
                data.put("phone", rs.getString("phone"));
                data.put("email", rs.getString("email"));
                data.put("skills", rs.getString("skills"));
                data.put("idStatus", rs.getInt("id_candidate_status"));
                data.put("idResume", rs.getInt("resume_id"));
                data.put("idVacancy", rs.getInt("id_vacancy"));
                JSONArrayOfCandidatesOnVacancy.put(data);
            }
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return JSONArrayOfCandidatesOnVacancy;
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