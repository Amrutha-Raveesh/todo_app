<!DOCTYPE html>
<%@page import="dto.Task"%>
<%@page import="java.time.Period"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
 <style>
        fieldset{
            position: relative;
            top: 230px;
        }
        input{
            height: 40px;
            width: 220px;
        }
        #a{
            position: relative;
            top: 10px;
            left: 75px;
        }
        #b{
            position: relative;
            top: 10px;
            left: 80px;
        }
                #c{
            position: relative;
            top: 250px;
            right:130px;
            
        }
    </style>
<body>
<%
Task task = (Task)request.getAttribute("task");
%>
   <center><fieldset style="width: 300px;border-color: black;border-radius: 10px;" >
        <form action="updatetask" method="post">
         <th><input type="text" name="id" value="1" hidden required></th>
            <table>

                    <h1>Enter Task</h1>
                
                <tr>
                    <th><label for="name" >Name:</label></th>
                    <td><input type="text" name="name" value="<%=task.getName() %>" required></td>
                </tr>
                <tr>
                    <th><label for="description">Description:</label></th>
                    <td><input type="text" name="description" value="<%=task.getDescription() %>" required></td>
                </tr>
                <tr>
                    <th><label for="days">Number of Days:</label></th>
                    <td><input type="text" name="days" value="<%=Period.between(task.getTaskdate(),task.getCompletionDate()).getDays() %>" required></td>
                </tr>
                <tr>
                    <th><button id="a">UPDATE</button></th>
                    <th><button id="b" type="reset">BACK</button></th>
                </tr>
            </table>
        </form>
    </fieldset>
    <a href="backtohome"><button id="c">BACK</button></a>
        </center> 
</body>
</html>