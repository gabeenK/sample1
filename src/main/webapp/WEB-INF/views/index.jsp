<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
		multipart/from-data
		- 모든 문자를 인코딩하지 않고 보내는 것을 명시할 때 쓰는 명령어
		- 기존 application/x-www-from-urlencoded는 기존 문자들을 서버로 보내기전에 인코딩을 한다.
		- 이 과정에서 바이너리로 보내게 될 데이터를 보낼 수가 없게 되고 type="file"로 처리되는 데이터는 서버로
		  전송이 불가능하다.
		- enctype을 multipart/from-data로 변경하게 되면 기존의 데이터 뿐만 아니라 바이너리 데이터까지 전송이 가능해진다.
		
		파일의 바이너리 데이터까지 보낸다
	 -->
	
	<h2>단일 파일 업로드1</h2>
	<form action="${pageContext.request.contextPath}/test1" method="post" enctype="multipart/form-data">
		<ul>
			<li><h4>select File</h4></li>
			<li><input type="file" name="mediaFile"/></li>
			<li><button>Upload</button></li>
		</ul>
	</form>
	<h2>단일 파일 업로드2</h2>
	<form action="${pageContext.request.contextPath}/test2" method="post" enctype="multipart/form-data">
		<ul>
			<li><h4>select File</h4></li>
			<li><input type="file" name="mediaFile" multiple/></li>
			<li><button>Upload</button></li>
		</ul>
	</form>
	<h2>단일 파일 업로드 + 추가 정보</h2>
	<form action="${pageContext.request.contextPath}/test3" method="post" enctype="multipart/form-data">
		<ul>
			<li><h4>select File</h4></li>
			<li> <label for="user">user : </label><input type="text" name="user" id="user" /></li>
			<li> <label for="url">url : </label><input type="text" name="url" id="url" /></li>
			<li><input type="file" name="mediaFile" multiple/></li>
			<li><button>Upload</button></li>
		</ul>
	</form>
	
	<h2>단일 파일 업로드 + 추가 정보2</h2>
	<form action="${pageContext.request.contextPath}/test4" method="post" enctype="multipart/form-data">
		<ul>
			<li><h4>select File</h4></li>
			<li> <label for="user">user : </label><input type="text" name="user" id="user" /></li>
			<li> <label for="url">url : </label><input type="text" name="url" id="url" /></li>
			<li><input type="file" name="mediaFile" multiple/></li>
			<li><button>Upload</button></li>
		</ul>
	</form>
	
	<h2>비동기 통신 업로드</h2>
	<div class="uploadDiv">
		<input type="file" name="mediaFile" id="mediaFile" multiple />
		<button id="btn">Upload</button>
	</div>
	
	<script>
	// 이런식으로 미리 비동기로 서버에 넣어놓으면 보안검사를 먼저 진행 한 후에 db로 file들을 넘길 수 있다
	// 비동기 파일 처리 방법
	
		document.getElementById("btn").addEventListener("click",function(){
			// formData를 통해 데이터를 보낼 양식 설정
			const formData = new FormData;
			// mediaFile input=file 태그 엘리먼트를 선언
			const inputFiles = document.getElementById("mediaFile");
			// inputFiles에서 파일에 대한 정보들을 전부 가져와 변수에 저장
			let files = inputFiles.files;
			// 어떤 값이 오는지 임시 출력
			console.log(files);
			
			// files의 정보를 formData에 담기
			for (const file of files) {
				formData.append("uploadFile", file);
			}
			// fetch를 통해 formData 전송
			fetch("${pageContext.request.contextPath}/test5",{
				method : "post",		//Post만 사용 가능
				body : formData})
			.then(response => console.log(response))
			.catch(error => console.log(error));
		});
	
		
	</script>
	
	<hr />
	<h1>파일 다운로드</h1>
	<a href="${pageContext.request.contextPath}/download1">다운로드</a>
	
	<img src="upload/muffin.png" />
	
	
	<h1>깃허브에 반영할 업데이트 소스11111</h1>
	
</body>
</html>
