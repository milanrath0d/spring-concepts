<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Admission Success</title>
</head>
<h2>${headerMessage}</h2>
<body>
<h2>${student.name}</h2>
<h2>${student.hobby}</h2>
<h2>${student.mobile}</h2>
<h2>${student.dob}</h2>
<h2>${student.skills}</h2>
<h2>${student.address.city}</h2> : <h2>${student.address.state}</h2> :
<h2>${student.address.pincode}</h2>
</body>
</html>