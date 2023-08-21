package com.example.java_sales;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "task4Servlet", value = "/task4")
public class Task4Servlet extends HttpServlet {

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

//    Показати самого успішного продавця (максимальна сума продажів)
//// Показати самого успішного покупця (максимальна сума купівлі)
//// Показати суму середньої покупки
//// Показати товар, що найбільше користується попитом


            out.println("<p>Показати самого успішного продавця (максимальна сума продажів)</p>");

            String command = "SELECT man.id AS salesman_id, man.name AS salesman_name, man.phone AS phone, " +
                    "MAX(sale.price) AS max_sale_amount " +
                    "FROM Salesman man " +
                    "JOIN Sale sale ON man.id = sale.salesmanid " +
                    "GROUP BY man.id, man.name, man.phone " +
                    "ORDER BY max_sale_amount DESC " +
                    "LIMIT 1";

            try (Statement stat3 = conn.createStatement();
                 ResultSet set3 = stat3.executeQuery(command)) {
                while (set3.next()) {
                    int id = set3.getInt("salesman_id");
                    String name = set3.getString("salesman_name");
                    String phone = set3.getString("phone");
                    int maxSaleAmount = set3.getInt("max_sale_amount");

                    out.println("<p>ID: " + id + ", Name: " + name + ", Phone: " + phone + ", Max Sale Amount: " + maxSaleAmount + "</p>");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            out.println("<p> Показати самого успішного покупця (максимальна сума купівлі)</p>");

            String command2 = "SELECT man.id AS salesman_id, man.name AS salesman_name, man.phone AS phone, " +
                    "MAX(sale.price) AS max_sale_amount " +
                    "FROM Buyer man " +
                    "JOIN Sale sale ON man.id = sale.salesmanid " +
                    "GROUP BY man.id, man.name, man.phone " +
                    "ORDER BY max_sale_amount DESC " +
                    "LIMIT 1";

            try (Statement stat3 = conn.createStatement();
                 ResultSet set3 = stat3.executeQuery(command2)) {
                while (set3.next()) {
                    int id = set3.getInt("salesman_id");
                    String name = set3.getString("salesman_name");
                    String phone = set3.getString("phone");
                    int maxSaleAmount = set3.getInt("max_sale_amount");

                    out.println("<p>ID: " + id + ", Name: " + name + ", Phone: " + phone + ", Max Sale Amount: " + maxSaleAmount + "</p>");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            out.println("<p>Показати суму середньої покупки</p>");

            String command3 = "SELECT AVG(price) AS avgPrice FROM Sale";
            Statement statement = conn.createStatement();
            ResultSet set = statement.executeQuery(command3);

            if(set.next()){

                double avgprice = set.getDouble("avgPrice");
                out.println("<p>Average Price = "+ avgprice +"</p>");
            }

            out.println("<p>Показати товар, що найбільше користується попитом</p>");

            String command9 = "SELECT s.name AS product_name, s.id AS product_id, " +
                    "SUM(s.quantity) AS total_quantity " +
                    "FROM Sale s " +
                    "GROUP BY s.product_id, p.name " +
                    "ORDER BY total_quantity DESC " +
                    "LIMIT 1";

            try (Statement stat4 = conn.createStatement();
                 ResultSet set4 = stat4.executeQuery(command9)) {
                if (set4.next()) {
                    int productId = set4.getInt("product_id");
                    String productName = set4.getString("product_name");
                    int totalQuantity = set4.getInt("total_quantity");

                    out.println("<p>Product ID: " + productId + ", Product Name: " + productName + ", Total Quantity: "
                            + totalQuantity + "</p>");
                } else {
                    out.println("<p>Немає даних про товари</p>");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
                throw new RuntimeException(e);
        }


    }
}