package management.dbTools;


import management.Model.Candidate;
import management.Model.Vacancy;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class DBRequests {
    private static Connection connection = null;
    private static Statement statement;
    private static ResultSet resultset;
    private static final String ipHost = "192.168.1.38";
    private static final String url = "jdbc:mysql://localhost:8082/managment?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";

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
                    "candidate.second_name, candidate.phone, " +
                    "candidate.email, candidate.skills, "+
                    "candidate.id_candidate_status, candidate.resume_id, "+
                    "candidate.id_manager "+
                    "FROM candidate \n" +
                    "WHERE candidate.id_manager = "+ manId +";";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                JSONObject data = new JSONObject();
                data.put("id", rs.getInt("id"));
                data.put("lastName", rs.getString("last_name"));
                data.put("firstName", rs.getString("first_name"));
                data.put("secondName", rs.getString("second_name"));
                data.put("phone", rs.getString("phone"));
                data.put("email", rs.getString("email"));
                data.put("skills", rs.getString("skills"));
                data.put("statusId", rs.getInt("id_candidate_status"));
                data.put("resumeId", rs.getInt("resume_id"));
                data.put("managerId", rs.getInt("id_manager"));
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
            String query = "SELECT vacancy.id, vacancy.id_manager, "+
                    "vacancy.name, vacancy.description,"+
                    "vacancy.skills, vacancy.id_vacancy_status "+
                    "FROM vacancy \n" +
                    "WHERE vacancy.id_manager = " + manId + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                JSONObject data = new JSONObject();
                data.put("id", Integer.parseInt(rs.getString("id")));
                data.put("manId", rs.getString("id_manager"));
                data.put("title", rs.getString("name"));
                data.put("description", rs.getString("description"));
                data.put("requirements", rs.getString("skills"));
                data.put("statusId", rs.getInt("id_vacancy_status"));
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

//    public JSONArray getArrOfCandidatesOnVacancyFromDB(int vacancyId) {
//        JSONArray JSONArrayOfCandidatesOnVacancy = new JSONArray();
//        try {
//            statement = connection.createStatement();
//            String query = "SELECT candidate.id, candidate.last_name, candidate.first_name, " +
//                    "candidate.second_name, candidate.phone" +
//                    "candidate.email, candidate.skills"+
//                    "candidate.id_candidate_status, candidate.resume_id"+
//                    "candidate.id_manager"+
//                    "FROM candidate, vacancy\n" +
//                    "WHERE candidate.id_vacancy = vacancy.id_vacancy " +
//                    "AND vacancy.id="+vacancyId+";";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.execute();
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                JSONObject data = new JSONObject();
//                data.put("id", Integer.parseInt(rs.getString("id")));
//                data.put("lastName", rs.getString("last_name"));
//                data.put("firstName", rs.getString("first_name"));
//                data.put("secondName", rs.getString("second_name"));
//                data.put("phone", rs.getString("phone"));
//                data.put("email", rs.getString("email"));
//                data.put("skills", rs.getString("skills"));
//                data.put("statusId", rs.getInt("id_candidate_status"));
//                data.put("resumeId", rs.getInt("resume_id"));
//                data.put("managerId", rs.getInt("id_manager"));
//                JSONArrayOfCandidatesOnVacancy.put(data);
//            }
//        }
//        catch (SQLException ex) {
//            // handle any errors
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());
//        }
//
//        return JSONArrayOfCandidatesOnVacancy;
//    }

    public boolean putCandidate(Candidate person) {
        boolean result = true;
        try {
            statement = connection.createStatement();
            String query = "INSERT INTO \n" +
                    "candidate (last_name, first_name, second_name, phone, email, skills," +
                    "id_manager, id_candidate_status, resume_id) \n" +
                    "VALUES\n" +
                    "(" + "'" + person.getLastName() + "','" + person.getFirstName() + "','" + person.getSecondName()
                    + "','" + person.getPhone() + "','"+ person.getEmail() +"','"+ person.getSkills()
                    + "'," + person.getManagerId() + "," + person.getStatusId() + "," + person.getResumeId() + ");";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
        return result;
    }

    public int getCandidateId(Candidate candidate) {
        int id = 0;
        try {
            statement = connection.createStatement();
            String query = "SELECT candidate.id \n" +
                    "FROM candidate\n" +
                    "WHERE candidate.last_name = '" + candidate.getLastName() +
                    "' AND candidate.first_name = '" + candidate.getFirstName() +
                    "' AND candidate.second_name = '" + candidate.getSecondName() + "';";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return id;
    }

    public boolean putVacancy(Vacancy vacancy) {
        boolean result = true;
        try {
            statement = connection.createStatement();
            String query = "INSERT INTO \n" +
                    "vacancy (id_manager, name, description, skills, id_vacancy_status) \n" +
                    "VALUES\n" +
                    "(" + vacancy.getManager() + ",'" + vacancy.getTitle() + "','" +
                    vacancy.getDescription() + "','" + vacancy.getRequirements() + "'," +
                    vacancy.getStatusId() + ");";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return false;
        }
        return result;
    }

    public int getVacancyId(Vacancy vacancy) {
        int id = 0;
        try {
            statement = connection.createStatement();
            String query = "SELECT vacancy.id \n" +
                    "FROM candidate \n" +
                    "WHERE vacancy.id_manager = " + vacancy.getManager() +
                    " AND vacancy.name = '" + vacancy.getTitle() +
                    "' AND vacancy.description = '" + vacancy.getDescription() +
                    "' AND vacancy.skills = '" + vacancy.getRequirements() + "';";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return id;
    }

    public JSONArray getCOV(int vacancyId) {
        JSONArray cov = new JSONArray();
        try {
            statement = connection.createStatement();
            String query = "SELECT id_record, id_vacancy, id_candidate \n" +
                    "FROM candidate_to_vacancy \n" +
                    "WHERE id_vacancy = " + vacancyId + ";";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                JSONObject data = new JSONObject();
                data.put("recId", rs.getInt("id_record"));
                data.put("vacId", rs.getString("id_vacancy"));
                data.put("canId", rs.getString("id_candidate"));

                cov.put(data);
            }
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return cov;
    }

    public void putCandidateToVacancy(int vacancyId, int candidateId) {
        try {
            statement = connection.createStatement();
            String query = "INSERT INTO \n" +
                    "candidate_to_vacancy (id_vacancy, id_candidate) \n" +
                    "VALUES \n" +
                    "(" + vacancyId + "," + candidateId + ");";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        }
        catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

}
