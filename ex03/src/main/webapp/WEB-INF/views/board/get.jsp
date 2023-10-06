<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%--헤더 파일 넣기 --%>    
<%@ include file="../includes/header.jsp" %>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">게시판</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            상세보기
                           
                            	
                            	<a href="good?bno=${board.bno}&pageNum=${cri.pageNum}&amount=${cri.amount}"><button type="button">좋아요+1</button></a>
                           
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                        <div class="form-group">
                                            <label>제목</label>
                                            <input class="form-control" placeholder="제목 입력" name="title" value="${board.title}" readonly >
                                        </div>
                                        <div class="form-group">
                                            <label>작성자</label>
                                            <input class="form-control" placeholder="작성자 입력" name="writer" value="${board.writer}" readonly>
                                        </div>                                        
                                        <div class="form-group">
                                            <label>Text area</label>
                                            <textarea class="form-control" rows="5" placeholder="내용 입력" name="content" readonly >${board.content}</textarea>
                                        </div>
                                        <a href="/board/modify?bno=${board.bno}&pageNum=${cri.pageNum}&amount=${cri.amount}"><button type="button" class="btn btn-outline btn-warning">수정으로</button></a>
                                        <a href="/board/list?pageNum=${cri.pageNum}&amount=${cri.amount}"> <button type="button" class="btn btn-outline btn-primary" >목록보기</button></a>
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>		<!-- 댓글시작 -->
                        <table id="class" border="1">
				           	<tr>
				         		<td>순번</td><td>내용</td><td>작성자</td><td>시간</td>
				           	</tr>    	        	      
				        </table>		        
                        <!-- 댓글끝 -->
                        <input id="replyData" type="text" placeholder="댓글내용" style="width:50%;">
                        <input id="replyWriter" type="text" placeholder="작성자" style="width:100px;">
                        <button id="btnReplyWrite">작성</button>
            
            
            
            
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
    
    
    <!-- jQuery -->
    <script src="/resources/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="/resources/vendor/metisMenu/metisMenu.min.js"></script>
 

    <!-- Custom Theme JavaScript -->
    <script src="/resources/dist/js/sb-admin-2.js?ver=1.0"></script>
    
    <script>
  
    	$(function(){
    		$("#good").click(function(){
    			console.log("굿버튼클릭")
    			alert("좋야요를 눌렀습니다");			
    			a+1;
    			console.log(a);
    		});
    	})
    </script>
	<%--댓글처리 자바스크립트 --%>
	<script src="/resources/js/reply.js?ver=2"></script>
	<script>
		console.log(replyService);
		//reply={reply:"자바스크립트로테스트2",replyer:"자바스크립유저2" , bno:2346469};
		
		//replyService.add(reply);
		//replyService.getList(2346469, function(a){console.log(a)});
		//replyService.del(29);
		//reply={reply:"자바스크립트로 수정 테스트1",bno:2346469};
		//replyService.modify(30,reply);
		//replyService.get(31,function(a){console.log(a)});
		//replyService.time
	</script> 
	
	<script>
	
	//console.log("직접생성 현재시간"+todayGT);
	
	//문서로딘이 완료되면    수행할함수
	function replyList(){
		//1.해당하는 게시글의 댓글 가져오고
		replyService.getList(${board.bno} , function(reply){
				//2.댓글 잘 가공해서 화면에 넣을 내용을 만들고 (html)	
				//console.log(reply)
				replyStr="";
				for(var i=0; i<reply.length; i++){
					var today = new Date();	
					var todayGT=today.getTime();//밀리세컨드 변환
					replyStr +=// "<li>"+reply[i].reply+"("+reply[i].replyer+") - " + reply[i].replyDate +"</li>";
								"<table>"+"<tr>"+
											"<td>"+(i+1)+"</td>"+"<td>"+"<span hidden>"+reply[i].rno+"</span>"+"<span class='replyModify'>"+reply[i].reply+"</span>"+"</td>"+"<td>"+reply[i].replyer+"</td>"+
											"<td>"+Math.floor((todayGT-reply[i].replyDate) / (60 * 60 * 1000))+"시간전"+'<span class="btnDel">'+'<a href=""#">[X]</a>'+'</span>'+     "</td>"
											+
										"</tr>"
								+"</table>";
				}
				//console.log("넣어야할 리플화면",replyStr);
				//3.해당하는 위치에 넣어준다.
				$("#class").html(replyStr);
				
			} );
		
		
	}
	
	$(document).ready(function(){
		replyList();		
	});
	
	
	$("#btnReplyWrite").on("click",function(e){ //버튼 클릭시 이벤트
		//1.작성한 리플내용과 작성자와 글번호르 읽어서
		var replyData=$("#replyData").val();
		var replyWriter=$("#replyWriter").val();
		var bno="${board.bno}";
		console.log(replyData,replyWriter,bno);
		//2.리플객체를만들어서
		var reply={bno:bno,reply:replyData,replyer:replyWriter};		
		//3.라풀등록처리 //4.리플목록갱신(안에넣어줘야 등록되고 갱신)
		replyService.add(reply,replyList);
		$("#replyData").val(""); // 도배방지용 작성내용 비우기
		$("#replyWriter").val("");// 도배방지용 작성내용 비우기
	});
/*	동적으로 만들어진 dom 임으로 이벤트 리스너가 등록이 불가함으로 위임을 통해서 인벤트를 등록시키자
 *
 *$(".btnDel").on("click",function(e){		
 *	console.log("삭제버튼 클릭");
 *  });
*/
//리플 삭제 처리
$("#class").on("click",".btnDel",function(e){
	e.preventDefault();//걸려있는 이벤트 무시
	console.log("삭제버튼 클릭 되는지 확인");
	var rno=$(this).parent().prev().prev().children().prev().text();
	console.log("댓글번호 확인-----------",rno);
	var pw=prompt("패스워드를 입력하세요");
	if(pw==1234){
		//1.해당댓글번호 읽기
		// 		  현재위치의 부모의 	 형제의 	형제의 	자식의 		문자  
		//2.삭제 js 호출 //3.댓글 목록갱신
		replyService.del(rno,replyList );
		
	}else{
		alert("다시입력하세요");
	}
	
	
});

$("#class").on("click",".replyModify",function(e){
	console.log("댓글내용클릭");
	$(this).removeClass();//다시 클기이 안되도록 class 속성 삭제
	var replyData=$(this).text();
	console.log("댓글내용2",replyData);
	//		 현재위치의 형제이전의 택스트
	var rno=$(this).prev().text();
	console.log("댓글번호",rno);
	var str='<input type="text" value="'+replyData+'">';
	$(this).html(str).children().focus(); 	//바로 수정이 가능하도록 포커스 이동
	
	//2.포커스가 사라지면서 수정이 되도록 js호출
	//$("#chat").on("blur","input",function(e){  //누적문제 발생함으로
	//(why? 엘리먼트 삭제시 포커스를 잃었다는 이벤트를 발생함으로)
	$(":focus").on("blur",function(e){ //현재포커스 기준으로 수정
		console.log("댓글수정작업");
		//변경한내용읽기
		var modifyData=$(this).val();		
		console.log("수정할내용",modifyData);		
		reply={reply:modifyData}; 
		replyService.modify(rno,reply,replyList); //3.댓글 목록재갱신
	});
	
	
	
	
});

//수정
//1.댓글매용이 클릭되면 수정이 가능하도록 입력창으로 변경
//2.포커스가 사라지면 수정이 되도록 js 호출
//3.목록재갱신
	
	</script>  
</body>

</html>
