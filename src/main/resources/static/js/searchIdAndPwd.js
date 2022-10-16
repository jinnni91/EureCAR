// 아이디 찾기 js
function findId() {

	let name = document.getElementById('name').value;
	let email = document.getElementById('email').value;

	if (name == '' || email == '') {
		alert('이름과 이메일을 입력해주세요.');
		return false;
	}


	$.ajax({
		type: "POST",
		url: "/member/searchId",
		data: {
			"name": name,
			"email": email
		},
		success: function(data) {
			if (data != "") {
				alert("가입된 아이디는 " + data + " 입니다.")
				location.href = '/member/login';
				
			} else {
				alert('일치하는 정보가 없습니다.');
			}
		}, error: function() {
			alert('다시 시도해주세요.');
		}

	}); 
}

// 비밀번호 찾기 js
function findPwd() {
	
		let memberId = document.getElementById('memberId').value;
		let name = document.getElementById('name').value;
		let phone = document.getElementById('phone').value;

			
		if (name == '' || name == '' || phone == '') {
			alert('빈칸을 빠짐없이 입력해주세요.');
			return false;
		}

			$.ajax({
				type : "POST",
				url : "/member/findMemberPwd",
				data : {
					"memberId" :memberId,
					"name" : name,
					"phone" : phone
				},
				success : function(data){
					if(data == '변경완료'){
						console.log("data :"+data)
						alert("회원님의 임시 비밀번호를 가입된 이메일로 발송하였습니다. ")
						location.href = "/member/login";
					}
					else if(data == '변경실패') {
						alert("일치하는 정보가 없습니다. ")
					}
				}, error: function() {
			alert("일치하는 정보가 없습니다.");
		}
		});
		}

