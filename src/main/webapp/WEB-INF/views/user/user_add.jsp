<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User模块的添加</title>
</head>
<body>

     <form action="<%=request.getContextPath()%>/user/add.action" method="post">
        <table>
         <tr>
         <td>用户名：</td>
         <td><input  type="text"  name="name"  value=""/></td>
         </tr>
         
          <tr>
           <td colspan="2">
             <input  type="submit"  value="保存"  />
           </td>
          </tr>
        
        </table>
     
     
     </form>



</body>
</html>