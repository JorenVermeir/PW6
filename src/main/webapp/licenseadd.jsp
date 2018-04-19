<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="controller?action=addlicense&rnumber=${rnumber}" method="POST">
    <label>nummerplaat</label><input type="text" name="license">
    <input type="submit">
</form>
</body>
</html>
