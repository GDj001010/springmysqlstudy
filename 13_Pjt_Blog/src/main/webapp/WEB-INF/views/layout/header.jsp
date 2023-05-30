<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!-- 타임스탬프 값 --> 
<c:set var="dt" value="<%=System.currentTimeMillis()%>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${param.title eq null ? 'Welcome' : param.title}</title>
<script src="${contextPath }/resources/js/lib/jquery-3.6.4.min.js"></script>
<script src="${contextPath }/resources/summernote-0.8.18-dist/summernote-lite.min.js"></script>
<script src="${contextPath }/resources/summernote-0.8.18-dist/lang/summernote-ko-KR.min.js"></script>
<link rel="stylesheet" href="${contextPath }/resources/summernote-0.8.18-dist/summernote-lite.min.css"/>
<link rel="stylesheet" href="${contextPath}/resources/css/header.css?dt=${dt}"/>
<link rel="stylesheet" href="${contextPath}/resources/css/init.css?dt=${dt}""/>
<script>
  function fnLogout(){
	  location.href = '${contextPath}/user/logout.do';
  }
</script>
</head>
<body>

	<div>
    <h1>My Blog</h1>
    <ul>
      <li><a href="${contextPath}/blog/list.do">블로그</a></li>
      <li><a href="${contextPath}/store/store.do">스토어</a></li>
      <li><a href="${contextPath}/movie/movie.do">영화</a></li>
    </ul>
  </div>
  
  <hr>
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
</body>
</html>