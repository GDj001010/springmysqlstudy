<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<jsp:include page="../layout/header.jsp">
  <jsp:param value="블로그" name="title" />
</jsp:include>
<script>
  
   // 자바스크립트 파일(*.js)에서는 ${contextPath}가 인식되지 않기 때문에
   // ContextPath를 반환하는 별도의 자바스크립트 코드가 필요하다.
  
   function getContextPath(){
	   let begin = location.href.indexOf(location.origin) + location.origin.length;
	   let end = location.href.indexOf("/", begin + 1);
	   return location.href.substring(begin, end);
   }
   
   function fnList(){
      location.href = getContextPath() + '/blog/list.do';
   }
   
   $(function(){
      $('#content').summernote({
         width: 1280,
         height: 1024,
         lang: 'ko-KR',
         toolbar: [
            ['style', ['style']],
            ['font', ['bold', 'underline', 'clear']],
            ['fontname', ['fontname']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['table', ['table']],
           ['insert', ['link', 'picture', 'video']],
            ['view', ['fullscreen', 'codeview', 'help']],
         ],
         callbacks: {
        	  onImageUpload: function(files){            // summernote 편집기에 이미지를 올리면 해당 이미지의 정보는 함수(function)의 매개변수 files(배열)로 전달된다.
        		  for(let i = 0; i < files.length; i++){   // files 배열을 순회하며서 이미지를 하나씩 처리한다.
        			  var formData = new FormData();         // formData 객체 가상 form (주로 ajax 처리에서 사용)
        			  formData.append('file', files[i]);     // 가상 form에 추가한 요소
        			  $.ajax({                               // 가상 form에 저장된 이미지를 HDD에 저장하고 저장된 경로(매핑)와 이름을 받아오는 ajax
        				  contentType: false,                  // ajax을 이용한 파일 첨부에서 사용해야 되는 두 가지 속성
        				  processData: false,                  // ajax을 이용한 파일 첨부에서 사용해야 되는 두 가지 속성     
        				  type: 'post',
        				  url: getContextPath() + '/blog/imageUpload.do',
        				  data: formData,
        				  dataType: 'json',
        				  success: function(resData){           // resData = {"src": "/app13/imageLoad/abcdefg.jag"}
        					  $('#content').summernote('insertImage', resData.src);  // content에 <img src="/app13/imageLoad/abcdefg.jpg"> 태그가 추가된다.
        				  }                                                        // content에 추가된 img 태그의 src 속성 값은 servlet-context.xml의 resources 태그에 의해서 /storage/summernote/abcdefg.jpg로 처리된다.
        			  }) // ajax
        		  } // for
        	  } // onImageUpload
         } // callbacks
      }) 
   })
</script>
</head>
<body>
   <div>
      <h1>작성화면</h1>
      <form method="post" action="${contextPath}/blog/add.do">
      <div>
        작성자 : ${sessionScope.loginId}
      </div>
         <div>
            <label for="title">제목</label>
            <input type="text" id="title" name="title">
         </div>
         <div>
            <div><label for="content">내용</label></div>
            <textarea id="content" name="content"></textarea> <!-- summernote 편집기로 바뀌는 textarea -->
         </div>
         <div>
            <input type="hidden" name="memberNo" value="${sessionScope.loginNo }">
            <button>작성완료</button>
            <input type="button" value="목록" onclick="fnList()">
         </div>
      </form>
   </div>
</body>
</html>