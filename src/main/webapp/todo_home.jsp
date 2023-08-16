<%@page import="java.util.List"%>
<%@page import="dto.Task"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<style>
    .a1{
        position: relative;
        top: 10px;
        height: 40px;
        width: 105px;
        background-color: rgb(107, 107, 243);
        color: white;
        font-size: 15px;
    }
    .a2{
        position: relative;
        top: 10px;
        left:510px;
        height: 40px;
        width: 105px;
        background-color: rgb(89, 89, 232);
        color: white;
        font-size: 15px;
    }
</style>
<body>
<%
List<Task> tasks = (List<Task>) request.getAttribute("list");
%>
    <h1>Todo Home</h1>
    <table style="border: 2px solid black;" border="2px solid black">
        <tr>
            <th>Id</th>
            <th>Task_Name</th>
            <th>Task_Description</th>
            <th>Task_Date</th>
            <th>Completion_Date</th>
            <th>Status</th>
            <th>Change</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
        <%--this is receiving from servlet --%>
        <%for(Task task:tasks){ %>
        <%--printing values if it exists --%>
        <tr>
        
            <td><%=task.getId() %></td>
            <td><%=task.getName() %></td>
            <td><%=task.getDescription() %></td>
            <td><%=task.getTaskdate() %></td>
            <td><%=task.getCompletionDate() %></td>
            <td><%if(task.isStatus()) %>Completed<%else %>Not completed</td>
            <td><a href="changestatus?id=<%=task.getId()%>"><button>change</button></a></td>
            <td><a href="updatestatus?id=<%=task.getId()%>"><button>update</button></a></td>
            <td><a href="deletestatus?id=<%=task.getId()%>"><button>delete</button></a></td>
        </tr>
        <%} %>
        
    </table>

<a href="tasksession"><button class="a1">Add Task</button></a>
<a href="logout"><button class="a2">Log out</button></a>
</body>
</html>