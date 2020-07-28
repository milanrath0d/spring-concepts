<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Student Admission Form</title>
</head>
<h2>${headerMessage}</h2>
<body>
<form:errors path="student.*"/>

<form action="submitAdmissionForm" method="post">
    <table>
        <tr>
            <td>Name <input type="text" name="name"></td>
        </tr>
        <tr>
            <td>Hobby<input type="text" name="hobby"></td>
        </tr>
        <tr>
            <td>Mobile<input type="text" name="mobile"></td>
        </tr>
        <tr>
            <td>DOB<input type="text" name="dob"></td>
        </tr>
        <tr>
            <td>Skils<select name="skills" multiple="multiple">
                <option value="Core Java">Core Java</option>
                <option value="Spring">Spring</option>
                <option value="Hibernate">Hibernate</option>
            </select></td>
        </tr>
        <tr>
            <td>Address <input type="text" name="address.city">
                <input type="text" name="address.state">
                <input type="text" name="address.pincode">
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"></td>
        </tr>
    </table>
</form>
</body>
</html>