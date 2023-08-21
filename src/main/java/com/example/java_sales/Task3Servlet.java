package com.example.java_sales;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "task3Servlet", value = "/task3")
public class Task3Servlet extends HttpServlet {

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

          out.println("<p>Відображення інформації про всі угоди</p>");
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
// Відображення інформації про угоди в конкретну дату
            out.println("<p>Відображення інформації про угоди 21.08</p>");
//            String command2 = "SELECT * FROM Sale WHERE sale_date = '2023-08-21'" ;
//            Statement stat2 = conn.createStatement();
//            ResultSet set2 = stat2.executeQuery(command2);

            String command2 = "SELECT * FROM Sale WHERE sale_date = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(command2);
            preparedStatement.setDate(1, Date.valueOf("2023-08-21"));
            ResultSet set2 = preparedStatement.executeQuery();

            while(set2.next())
            {
                int price = set2.getInt("price");
                Date date = set2.getDate("sale_date");


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

//            // Додавання стовпця BuyerID до таблиці Sale
//            String addBuyerIDColumn = "ALTER TABLE Sale ADD BuyerID INT";
//            try (PreparedStatement preparedStatement3 = conn.prepareStatement(addBuyerIDColumn)) {
//                preparedStatement3.executeUpdate();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//// Додавання зовнішнього ключа до стовпця BuyerID
//            String addForeignKey = "ALTER TABLE Sale ADD CONSTRAINT fk_buyer FOREIGN KEY (BuyerID) REFERENCES Buyer(id)";
//            try (PreparedStatement preparedStatement2 = conn.prepareStatement(addForeignKey)) {
//                preparedStatement2.executeUpdate();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }


//            // Додавання стовпця BuyerID до таблиці Sale
//            String addSalesmanIDColumn = "ALTER TABLE Sale ADD salesmanID INT";
//            try (PreparedStatement preparedStatement4 = conn.prepareStatement(addSalesmanIDColumn)) {
//                preparedStatement4.executeUpdate();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//// Додавання зовнішнього ключа до стовпця BuyerID
//            String addForeignKey2 = "ALTER TABLE Sale ADD CONSTRAINT fk_salesman FOREIGN KEY (salesmanID) REFERENCES Salesman(id)";
//            try (PreparedStatement preparedStatement2 = conn.prepareStatement(addForeignKey2)) {
//                preparedStatement2.executeUpdate();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }



//            String addForeignKey2 = "ALTER TABLE Sale " +
//                    "ADD FOREIGN KEY (SalesmanID) REFERENCES Salesman(id)";
//            try (PreparedStatement preparedStatement2 = conn.prepareStatement(addForeignKey2)) {
//                preparedStatement2.executeUpdate();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }


             // Відображення інформації про угоди в вказаний діапазон дат
            out.println("<p>Відображення інформації про угоди в вказаний діапазон дат</p>");

            String command3 = "SELECT * FROM Sale WHERE sale_date BETWEEN  ? AND  ?";
            PreparedStatement preparedStatement3 = conn.prepareStatement(command3);
            preparedStatement3.setDate(1, Date.valueOf("2023-08-20"));
            preparedStatement3.setDate(2, Date.valueOf("2023-08-21"));
            ResultSet sett = preparedStatement3.executeQuery();

            while(sett.next())
            {
                int price = sett.getInt("price");
                Date date = sett.getDate("sale_date");


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

           // Відображення інформації про угоди конкретного продавця
            out.println("<p>Відображення інформації про угоди конкретного продавця</p>");

            String command ="SELECT * FROM Sale s "+
                    "JOIN Salesman sm ON s.salesmanid=sm.id " +
                    "WHERE name = 'Anna'";

           Statement statement = conn.createStatement();
           ResultSet set8= statement.executeQuery(command);

            while(set8.next())
            {
                int price = set8.getInt("price");
                Date date = set8.getDate("sale_date");


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

            // Відображення інформації про угоди конкретного покупця
            out.println("<p>Відображення інформації про угоди конкретного покупця</p>");

            String command9 = "SELECT * FROM Sale s "+
                    "JOIN Buyer b ON b.id=s.buyerid "+
                    "WHERE b.name = 'Iryna'";

            Statement statement9 = conn.createStatement();
            ResultSet set9= statement9.executeQuery(command9);

            while(set9.next())
            {
                int price = set9.getInt("price");
                Date date = set9.getDate("sale_date");


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




            try {
                // Закриваємо з'єднання
                if (conn != null) {
                    conn.close();

                }
                if(set3 !=null || set2!=null)
                {
                    set2.close();
                    set3.close();
                }
                if(stat3 !=null || set2!=null) {
                    stat3.close();


                }
            } catch (SQLException e) {

                e.printStackTrace();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }







}

