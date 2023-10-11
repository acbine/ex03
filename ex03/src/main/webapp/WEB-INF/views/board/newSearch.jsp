<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

페이지번호:<input  type="number" id="page" /> <br>
보여줄갯수:<input  type="number" id="amount" /> <br>
검색할 종류<select id="type">
            <option value="T" >제목</option>
            <option value="C">내용</option>
            <option value="W" >작성자</option>
            <option value="TC">제목 or 내용</option>
            <option value="TW">제목 or 작성자</option>
            <option value="CW">내용 or 작성자</option>
            <option value="TCW">제목 or 내용 or 작성자</option>
         </select> <br>
검색할 내용<input	type="text"   id="keyword"><br>
<button id="serchBtn">확인</button>
<div>

</div>

<!-- jQuery -->
<script src="/resources/vendor/jquery/jquery.min.js"></script>
<script>
//1.버튼이 클릭되었을떄 필요한 내용실행
$(function(){	//문서가 로딩되면 이함수를 수행하시오 $(document).ready(); 와같음
	$("#serchBtn").on("click",function(e){	//버튼이클릭되면  이벤트를 ㅣㄹ행하시오 
		e.preventDefault(); //기본이벤트무시
		var page=$("#page").val(); //값을 가져오고 변수에 넣어줌
		var amount=$("#amount").val();
		var type=$("#type").val();
		var keyword=$("#keyword").val();
		console.log("읽은값확인:",page,amount,type,keyword);
		$.ajax({
			url:"http://localhost:8080/myapi/board/"+page+"/"+amount,
			type:"post",
			data: '{"keyword": "'+keyword+'" , "type":"'+type+'"}',
					//JSON.stringify({keyword:keyword,type:type}),
			contentType:"application/json;charset=UTF-8",
			success:function(result){ //data 는 우리가 임의 변수로 적은 값 result가능
				console.log("요청성공",result);
				var htmlStr=""; //문자를 저장 하기위해 공백값을줌
				for(var i=0; i<result.length; i++){
					//찍을 html 만들기
					htmlStr += (i+1)+". "+result[i].title+"<br>"
				}
				$("div").html(htmlStr);
			}
		});
	
		
	}); 
});
//2.사용자가 입력한 값들을 일겅오기
//3.그값들은 이용해서 필요한 api 요청
//4. 응답받은 데이터를 이용해서 필요한 화면부분을 갱신

/*
$(document).ready(function(){
	$('#search').click(function(){
		console.log("클릭됨");
		var pageNum = $("#pageNum").val;
		
	});
});
	*/
	


</script>

</body>
</html>