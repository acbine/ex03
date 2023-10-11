<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

오늘의 리플개수: <span id="span1">???</span><br>
<button id ="btn1">확인</button><br>
리플내용
<div id ="div1">

</div>

<span>
</span>

<!-- jQuery -->
<script src="/resources/vendor/jquery/jquery.min.js"></script>
<script>
	$(function(){
		$("#btn1").on("click",function(){
			console.log("확인 버튼이 클릭됨");
			var todaycount=""
			$.ajax({
				url:"/myapi/board/newReply2",
				type:"get",
				contentType:"application/json;charset=UTF-8",
				success:function(a){//data 는 우리가 임의 변수로 적은 값 result가능
					console.log("오늘 리플갯수  요청성공",a)
					var htmlStr=a; //문자를 저장 하기위해 공백값을줌
					$("#span1").html(htmlStr);
				} 				
			});//ajax1
			$.ajax({
				url:"/myapi/board/newReply1",
				type:"get",
				contentType:"application/json;charset=UTF-8",
				success:function(b){//data 는 우리가 임의 변수로 적은 값 result가능
					console.log("오늘 리플내용  요청성공",b)
					var htmlStr=""; //문자를 저장 하기위해 공백값을줌
					for(var i=0; i<b.length; i++){
						//찍을 html 만들기
						htmlStr +=(i+1)+"."+b[i].reply+"("+b[i].replyer+")"+"<br>"
					}
					$("#div1").html(htmlStr);
				} 								
			});//ajax2
			
		}); //click
	}); //document
</script>
</body>
</html>