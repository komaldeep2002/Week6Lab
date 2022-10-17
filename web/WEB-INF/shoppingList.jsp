<%-- 
    Document   : shoppingList
    Created on : Oct 16, 2022, 5:49:55 PM
    Author     : Komaldeep Kaur Virk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello, ${username}  </p> 
        <a href="<c:url value='/ShoppingList?action=logout'/>">Logout</a>
        <br>
        <h1>List</h1>
        <form method="post" action="">
            Add item: <input type="text" name="item" required> 
            <input type="submit" value="Add">
            <input type="hidden" name="action" value="add">
        </form>

        <form method="post" action="">
            <br>
                <!--loop for collection of items-->
                <c:forEach items="${list}" var="item">
                    <input type="radio" name="item" value="${item}"> <c:out value="${item}"/><br>
                </c:forEach> 
                    <br>
            <input type="submit" value="Delete">
            <input type="hidden" name="action" value="delete">
        </form>
    </body>
</html>
