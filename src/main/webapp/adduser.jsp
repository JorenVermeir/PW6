<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Adduser</title>
</head>
<body>
<form action="controller?action=add" method="POST">
    <label>firstname</label><input type="text" name="firstname">
    <label>lastname</label><input type="text" name="lastname">
    <label>r-number</label><input type="text" name="rnumber">
    <label>plate 1</label><input type="text" name="plate">
    <input type="submit">
</form>
</body>
</html>
