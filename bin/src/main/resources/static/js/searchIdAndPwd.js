/* alert창 디자인 */
function toast(icon, title) {
	const Toast = Swal.mixin({
		toast: true,
    position: 'center-center',
    showConfirmButton: false,
    timer: 3000,
    timerProgressBar: true,
	})

	Toast.fire({
		icon: icon,
		title: title
	})
}
// 아이디 찾기 js
function findId() {

	let name = document.getElementById('name').value;
	let email = document.getElementById('email').value;

	if (name == '' || email == '') {
		toast('warning', '가입된 정보를 입력하세요!');
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
				Swal.fire({
					title: "아이디 찾기 완료",
					text: "가입된 아이디는 " + data + " 입니다.",
					/*icon: 'success',*/
					imageUrl: '../images/로고(글자포함)ver1.png',
 				 	imageWidth: 400,
  					imageHeight: 200,
  					imageAlt: 'Custom image',
				}).then(() => {
					location.href = '/member/login';
				})

			} else {
				toast('warning', '가입하신 이름과 이메일을 다시 입력해주세요');
			}

		}, error: function() {
			toast('error', 'error! 다시 시도해주세요!');
		}

	}); 
}

