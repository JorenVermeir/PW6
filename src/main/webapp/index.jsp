<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="nl">
<head>

<meta charset="UTF-8">
<title>Homepagina - mijn boekenkast</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<link rel="stylesheet" media="all" href="css/reset.css">
<link rel="stylesheet" media="all" href="css/project.css">
    <style>
        #plates {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #plates td, #plates th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #plates tr:nth-child(even){background-color: #f2f2f2;}

        #plates tr:hover {background-color: #ddd;}

        #plates th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>

<body>
<main>


</main>
<section>
    <c:if test="${error != null}">
        <div>
            <p style="color: red;">${error}</p>
        </div>
    </c:if>

    <table id="plates">
    <tr>
        <th>Naam</th>
        <th>R-nummer</th>
        <th>Toegelate plaat 1</th>
        <th>Toegelate plaat 2</th>
        <th>Toegelate plaat 3</th>
    </tr>
    <c:forEach var="person" items="${ db }">
        <tr>
            <td>${person.firstName} ${person.lastName}</td>
            <td>${person.rNumber}</td>
            <c:forEach var="plate" items="${person.plates}">
                <td>${plate} </td>
            </c:forEach>
            <c:if test="${fn:length(person.plates) < 3}">
            <td><a href="controller?action=licenseAdd&rnumber=${person.rNumber}">Voeg een plaat toe</a></td>
            </c:if>
        </tr>
    </c:forEach>
</table>
    <table id="plates">
        <tr>
            <th>Alle Toegelate Nummerplaten</th>
        </tr>
        <c:forEach var="plate" items="${ plates }">
            <tr>
                <td>${plate}</td>
            </tr>
        </c:forEach>
    </table>
</section>
<p><a href="controller?action=addpage">Add a new person</a></p>

<form action="controller?action=genplate" method="POST">
    <p>Generete your own plate: </p><input type="text" name="gen">
    <input type="submit" value="generate license plate">
</form>

</main>
</body>
</html>