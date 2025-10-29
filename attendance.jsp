<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Student Attendance</title>
</head>
<body>
  <h2>Student Attendance Form</h2>
  <form action="WebAppServlet" method="post">
    <input type="hidden" name="action" value="attendance">
    Student ID: <input type="text" name="studentID" required><br><br>
    Date: <input type="date" name="date" required><br><br>
    Status:
    <select name="status" required>
      <option value="Present">Present</option>
      <option value="Absent">Absent</option>
    </select><br><br>
    <input type="submit" value="Submit Attendance">
  </form>
</body>
</html>
