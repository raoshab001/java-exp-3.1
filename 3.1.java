package com.webapp;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class WebAppServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/webappdb", "root", "yourpassword"
            );

            if ("login".equalsIgnoreCase(action)) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM Users WHERE username=? AND password=?");
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    out.println("<h2>Welcome, " + username + "!</h2>");
                } else {
                    out.println("<h3>Invalid credentials!</h3>");
                }

                out.println("<br><a href='index.html'>Back to Home</a>");
            }

            else if ("employee".equalsIgnoreCase(action)) {
                String empid = request.getParameter("empid");
                Statement st = con.createStatement();
                ResultSet rs;

                if (empid != null && !empid.isEmpty()) {
                    rs = st.executeQuery("SELECT * FROM Employee WHERE EmpID=" + empid);
                } else {
                    rs = st.executeQuery("SELECT * FROM Employee");
                }

                out.println("<h2>Employee Records</h2>");
                out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Salary</th></tr>");
                while (rs.next()) {
                    out.println("<tr><td>" + rs.getInt(1) + "</td><td>" +
                            rs.getString(2) + "</td><td>" + rs.getDouble(3) + "</td></tr>");
                }
                out.println("</table>");
                out.println("<br><a href='index.html'>Back to Home</a>");
            }

            else if ("attendance".equalsIgnoreCase(action)) {
                String sid = request.getParameter("studentID");
                String date = request.getParameter("date");
                String status = request.getParameter("status");

                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Attendance VALUES (?, ?, ?)");
                ps.setInt(1, Integer.parseInt(sid));
                ps.setString(2, date);
                ps.setString(3, status);
                int result = ps.executeUpdate();

                if (result > 0) {
                    out.println("<h2>Attendance recorded successfully!</h2>");
                } else {
                    out.println("<h3>Failed to record attendance.</h3>");
                }
                out.println("<br><a href='attendance.jsp'>Back to Attendance Page</a>");
            }

            con.close();
        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
