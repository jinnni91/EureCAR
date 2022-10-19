window.onload = function() {
	checkEvent();
	selectBirth();
	checkZipCode();
	/* 아이디 유효성 검사 */
	$('.memberId').keyup(function() {
		let memberId = $("#memberId").val();

		var numId = memberId.search(/[0-9]/g);
		var engId = memberId.search(/[a-z]/ig);
		var speId = memberId.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
		$("#duplicationCheck").hide();
		if (memberId.length < 1 || memberId.length > 13) {
			$("#CheckId").html("13자리 이내로 입력해주세요.");
			$("#CheckId").attr('color', 'red');
			$("#send").attr("disabled", true); //설정
			return false;
		} else if (memberId.search(/\s/) != -1) {
			$("#CheckId").html("아이디는 공백 없이 입력해주세요.");
			$("#CheckId").attr('color', 'red');
			$("#send").attr("disabled", true); //설정
			return false;
		} else if (numId < 0 || engId < 0 || speId > 0) {
			$("#CheckId").html("영문,숫자를 혼합하여 입력해주세요.");
			$("#CheckId").attr('color', 'red');
			$("#send").attr("disabled", true); //설정
			return false;
		} else {
			$("#CheckId").html("");
			$("#CheckId").attr('color', 'black');
			$("#duplicationCheck").show();
			return true;
		}
	})

	/* 비밀번호 재확인 기능 및 유효성 검사 및 false 일시 가입 금지*/
	$('.pwcheck').keyup(function() {
		let pass1 = $("#memberPwd").val();
		let pass2 = $("#memberPwd2").val();

		if (pass1 != "" || pass2 != "") {
			if (pass1 == pass2) {
				$("#checkPw").html('비밀번호가 일치합니다.');
				$("#checkPw").attr('color', 'green');
			} else {
				$("#checkPw").html('비밀번호가 불일치합니다.');
				$("#checkPw").attr('color', 'red');
			}
		}

		var num = pass1.search(/[0-9]/g);
		var eng = pass1.search(/[a-z]/ig);
		var spe = pass1.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

		if (pass1.length < 8 || pass1.length > 20) {
			$("#passwordCheck").html("8자리 ~ 20자리 이내로 입력해주세요.");
			$("#passwordCheck").attr('color', 'red');
			return false;
		} else if (pass1.search(/\s/) != -1) {
			$("#passwordCheck").html("비밀번호는 공백 없이 입력해주세요.");
			$("#passwordCheck").attr('color', 'red');
			return false;
		} else if (num < 0 || eng < 0 || spe < 0) {
			$("#passwordCheck").html("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
			$("#passwordCheck").attr('color', 'red');
			return false;
		} else {
			$("#passwordCheck").html("적정한 비밀번호");
			$("#passwordCheck").attr('color', 'green');
			if (pass1 == pass2) {
				$("#send").attr("disabled", true); //설정
			}
			else {
				$("#send").attr("disabled", true); //설정
			}
			return true;

		}


	}
	)


	/* 아이디 중복 체크 */
	if (document.getElementById("duplicationCheck")) {

		const $duplication = document.getElementById("duplicationCheck");

		$duplication.onclick = function() {
			let memberId = document.getElementById("memberId").value.trim();

			fetch("/member/idDupCheck", {
				method: "POST",
				headers: {
					'Content-Type': 'application/json;charset=UTF-8'
				},
				body: JSON.stringify({ memberId: memberId })
			})
				.then(result => result.text())
				.then(result => alert(result))
				.catch((error) => error.text().then((res) => alert(res)));
		}
	}


	/* 휴대전화 융효성 검사 */ 
	$('.phone').keyup(function() {
		var phoneNum = $("#phone").val();
		var patternPhone = /01[016789]-[^0][0-9]{2,3}-[0-9]{3,4}/;

	
		if (!patternPhone.test(phoneNum)) {
			$("#CheckPhone").html("다시 입력해주세요.");
			$("#CheckPhone").attr('color', 'red');
			return false;
		}

		else {
			$("#CheckPhone").hide();
			return true;
		}
	})
}
function selectBirth() {
							var now = new Date();
							var year = now.getFullYear();
							var mon = (now.getMonth() + 1) > 9 ? ''
									+ (now.getMonth() + 1) : '0'
									+ (now.getMonth() + 1);
							var day = (now.getDate()) > 9 ? ''
									+ (now.getDate()) : '0' + (now.getDate());

							//년도 selectbox만들기               
							for (var i = 1900; i <= year; i++) {
								$('#year').append(
										'<option value="' + i + '">' + i
												+ '</option>');
							}

							// 월별 selectbox 만들기            
							for (var i = 1; i <= 12; i++) {
								var mm = i > 9 ? i : "0" + i;
								$('#month').append(
										'<option value="' + mm + '">' + mm
												+ '</option>');
							}

							// 일별 selectbox 만들기
							for (var i = 1; i <= 31; i++) {
								var dd = i > 9 ? i : "0" + i;
								$('#day').append(
										'<option value="' + dd + '">' + dd
												+ '</option>');
							}

							const $year = document.getElementById("year").value
							const $month = document.getElementById("month").value
							const $day = document.getElementById("day").value

	}

function checkZipCode() {
	
	
	const $searchZipCode = document.getElementById("searchZipCode");

		$searchZipCode.onclick = function() {

			/* 다음 우편번호 검색 창을 오픈하면서 동작할 콜백 메소드를 포함한 객체를 매개변수로 전달한다. */
			new daum.Postcode({
				oncomplete : function(data) {
					/* 팝업에서 검색결과 항목을 클릭했을 시 실행할 코드를 작성하는 부분 */
					document.getElementById("zipCode").value = data.zonecode;
					document.getElementById("address1").value = data.address;
					document.getElementById("address2").focus();
				}
			}).open();
		}

	}
/* 약관 동의 시 회원가입 가능 */
function checkEvent() {


	$("#agree").change(function() {
		if ($("#agree").is(":checked") && $("#checkPw").attr('color') == 'green' && $("#CheckId").attr('color') == 'black'
			&& emconfirmchk == true) {
			$("#send").attr("disabled", false);
		}
		else {
			$("#send").attr("disabled", true);
		}
	});
}



