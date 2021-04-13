<%@page import="com.rj.bd.user.entity.User"   isELIgnored="false"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User模块的查询</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/statc/jquery-1.4.4.js"></script>
<script type="text/javascript">

function doClear() {
	 //alert("清空session");
	 //alert($("#pname").val(""));
	 $("#pname").val("")
    $.ajax({
        type:"post",
        url:"${pageContext.request.contextPath }/user/cleasSession",
        data:'{"name":"null"}',
        contentType:"application/json;charset=utf-8",
        success:function(data) {
           
        }
    });
}

</script>
</head>
<body>

      <form action="<%=request.getContextPath()%>/user/query" >
        <table  border="2px"  bordercolor="green"  align="center">
        <tr>
         <td>姓名：</td>
         <td><input type="text" id="pname" onfocus="doClear()"  name="name"  value="${sessionScope.queryByNameStr}"/></td>
        </tr>
         <tr>
         <td colspan="2">
              <input  style="float: right" type="submit" value="查询"/>         
         </td>
         
         </tr>
        
        </table>
      
      
      
      </form>

     <br/><br/>


      <form action=""  method="post">
       <table  border="2px"  bordercolor="green" align="center">
       <tr>
       <td>序号</td>
       <td>姓名</td>
        <td>操作</td>
       </tr>
       
       
       
       <c:forEach  items="${list}"  var="user"   varStatus="i">
     
       <tr>
       <td>${i.count}</td>
       <td>${user.name}</td>
         <td>
             <a href="<%=request.getContextPath()%>/user/addPage.action">添加</a>
              <a href="<%=request.getContextPath()%>/user/editPage.action?id=${user.id}">修改</a>
              <a href="<%=request.getContextPath()%>/user/delete.action?id=${user.id}">删除</a>
           </td>
       </tr>
       </c:forEach>
     
       
       
       </table>
      </form>
   
    <table align="center">
  <tr>
    <td align="right">
         <a href="<%=request.getContextPath()%>/user/query?current=1">首页</a> 
         <c:if test="${page.hasPrevious()}">
         <a href="<%=request.getContextPath()%>/user/query?current=${page.getCurrent()-1}">  上一页</a>
         </c:if>          
                  
         <c:if test="${page.hasNext()}">
         <a href="<%=request.getContextPath()%>/user/query?current=${page.getCurrent()+1}">下一页</a>
         </c:if>
         
    	  <a href="<%=request.getContextPath()%>/user/query?current=${page.getPages()}">尾页</a> 
    	   pages:${page.current}/${page.getPages()} 共: ${page.getTotal()}条  
    
    </td>
  </tr>
</table>
        


</body>
</html>