package com.example.java_sales;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "task1Servlet", value = "/task1")
public class Task1Servlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        String url = "jdbc:postgresql://localhost:5432/Sales";
        String username = "postgres";
        String password = "postgres";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Приєднання до бази даних
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PrintWriter out = response.getWriter();
            //CREATE TABLE Salesman
//            String postgreCommand = "CREATE TABLE Salesman (" +
//                    "id SERIAL PRIMARY KEY," +
//                    "name VARCHAR(255)," +
//                    "phone VARCHAR(255),"+
//                    "email VARCHAR(255))";
//
//            Statement statement = conn.createStatement();
//            statement.executeUpdate(postgreCommand);

            //CREATE TABLE Buyer
//            String postgreCommand2 = "CREATE TABLE Buyer (" +
//                    "id SERIAL PRIMARY KEY," +
//                    "name VARCHAR(255)," +
//                    "phone VARCHAR(255)," +
//                    "email VARCHAR(255))";
//
//            Statement statement2 = conn.createStatement();
//            statement2.executeUpdate(postgreCommand2);

            //CREATE TABLE Sale
//            String command = "CREATE TABLE Sale(" +
//                    "id SERIAL PRIMARY KEY," +
//                    "price INT," +
//                    "sale_date DATE)";
//            Statement statement2 = conn.createStatement();
//            statement2.executeUpdate(command);
//------------------------------------------------------------------------
//            String insertDataCommand = "INSERT INTO Salesman (name, phone, email) " +
//                    "VALUES\n" +
//                    "('Svetlana', '0987532562', 'sveta@gmail.com')," +
//                    "('Boris', '0677532588', 'boris@gmail.com')," +
//                    "('Ivan', '0687532599', 'ivan@gmail.com')," +
//                    "('Anna', '0977532522', 'anna@gmail.com')";
//
//
//            Statement insertDataStatement = conn.createStatement();
//            insertDataStatement.executeUpdate(insertDataCommand);
//
//            String insertDataCommandBuyer = "INSERT INTO Buyer (name, phone, email) " +
//                    "VALUES\n" +
//                    "('Lubov', '0987532562', 'lubov@gmail.com')," +
//                    "('Iryna', '0677532588', 'iryna@gmail.com')," +
//                    "('Sergey', '0687532599', 'sergey@gmail.com')," +
//                    "('Ilya', '0977532522', 'ilya@gmail.com')";
//
//
//            Statement insertDataStatementBuyer = conn.createStatement();
//            insertDataStatementBuyer.executeUpdate(insertDataCommandBuyer);

            //INSERT INTO Sale
//            String insertDataCommand = "INSERT INTO Sale (price, sale_date) " +
//                    "VALUES\n" +
//                    "(200, '2023-08-20'), " +
//                    "(500, '2023-08-21'), " +
//                    "(700, '2023-08-19')," +
//                    "(1000, '2023-08-15')," +
//                    "(800, '2023-08-21')," +
//                    "(900, '2023-08-16')" ;
//
//
//            Statement insertDataStatement = conn.createStatement();
//            insertDataStatement.executeUpdate(insertDataCommand);

              // Відображення всіх продавців
           out.println("<h3>All salesman </h3>");
            String commandShowAllSalesman = "SELECT * FROM Salesman";
            Statement stat = conn.createStatement();
            ResultSet set = stat.executeQuery(commandShowAllSalesman);

            while(set.next())
            {
                String name = set.getString("name");
                String phone = set.getString("phone");
                String email = set.getString("email");

                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<th>Name</th>");
                out.println("<th>Phone</th>");
                out.println("<th>Email</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + phone + "</td>");
                out.println("<td>" + email + "</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<br>");
            }
            out.println("<hr>");
            out.println("<h3>All buyers </h3>");
            String commandShowAllBuyers = "SELECT * FROM Buyer";
            Statement stat2 = conn.createStatement();
            ResultSet set2 = stat2.executeQuery(commandShowAllBuyers);

            while(set2.next())
            {
                String name = set2.getString("name");
                String phone = set2.getString("phone");
                String email = set2.getString("email");

                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<th>Name</th>");
                out.println("<th>Phone</th>");
                out.println("<th>Email</th>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + phone + "</td>");
                out.println("<td>" + email + "</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<br>");
            }


            out.println("<hr>");
            out.println("<h3>All goods: </h3>");
            String commandShowAllSale = "SELECT * FROM Sale";
            Statement stat3 = conn.createStatement();
            ResultSet set3 = stat3.executeQuery(commandShowAllSale);

            while(set3.next())
            {
                int price = set3.getInt("price");
               Date date = set3.getDate("sale_date");


                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<th>Price</th>");
                out.println("<th>Date</th>");

                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>" + price + "</td>");
                out.println("<td>" + date + "</td>");

                out.println("</tr>");
                out.println("</table>");
                out.println("<br>");
            }

// Відображення всіх покупців
// Відображення всіх товарів


            out.println("<html><body>");
            out.println("<h1>" + "Data!" + "</h1>");
            out.println("</body></html>");
            // Hello

            out.println("<html><body>");
            out.println("<h1>" + message + "</h1>");
            out.println("</body></html>");


            try {
                // Закриваємо з'єднання
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {

                e.printStackTrace();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void destroy() {
    }

}
