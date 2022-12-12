package com.example.demo;

import java.sql.*;
import java.util.ArrayList;

public class Sql {
    private static final String DB_USERNAME = "admin";
    private static final String DB_PASSWORD = "admin";
    private static final String DB_URL = "jdbc:postgresql://192.168.56.102:5432/users";

    public static ArrayList<String> select(String login) throws SQLException {

            ArrayList <String> arr = new ArrayList<>();
            Connection connection = null;
            Statement statement = null;
            ResultSet result = null;
            try{connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                statement = connection.createStatement();
                String SELECT_LOGIN = "select * from users.login_time where login ='" + login +"'";
                result = statement.executeQuery(SELECT_LOGIN);
                ResultSetMetaData resultMeta = result.getMetaData();
                while(result.next()){
                    for(int i = 1;i<= resultMeta.getColumnCount();i++){
                        arr.add(result.getString(i));
                    }
                    }
                } catch (SQLException e) {
                throw e;
            }
            finally {
                assert connection != null;
                connection.close();
                assert statement != null;
                statement.close();
                assert result != null;
                result.close();
            }
        return arr;
        }

    public static int insert(String insert_login, String insert_password, String insert_name) {

        int exception = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)){;
            String insert_1 = "insert into users.logins (login, password, name) values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insert_1);
            preparedStatement.setString(1, insert_login);
            preparedStatement.setString(2, insert_password);
            preparedStatement.setString(3, insert_name);
            preparedStatement.executeQuery();
            preparedStatement.close();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            if(ex.getMessage().equals("Запрос не вернул результатов.")||ex.getMessage().equals("No results were returned by the query.")){
                System.out.println("true");
                exception = 1;
            }
        }
        return exception;
    }
    public static String insert_time(String insert_login, String time)  {

        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {;
            String insert = "insert into users.login_time (login, login_time) values (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, insert_login);
            preparedStatement.setString(2, time);
            preparedStatement.executeQuery();
            preparedStatement.close();
        }
        catch (SQLException e) {
            return e.toString();
        }
        return null;
    }

    }




