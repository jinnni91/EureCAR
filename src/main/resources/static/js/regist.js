window.onload = function() {
	/* 비밀번호 재확인 기능 및 유효성 검사 및 false 일시 가입 금지*/
	$('.pwcheck').keyup(function(){
    	let pass1 = $("#memberPwd").val();
        let pass2 = $("#memberPwd2").val();
        
        if (pass1 != "" || pass2 != ""){
        	if (pass1 == pass2){
            	$("#checkPw").html('비밀번호가 일치합니다.');
            	$("#checkPw").attr('color','green');
            } else {
            	$("#checkPw").html('비밀번호가 불일치합니다.');
                $("#checkPw").attr('color','red');
            }
        }
        
      	 var num = pass1.search(/[0-9]/g);
		 var eng = pass1.search(/[a-z]/ig);
		 var spe = pass1.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
		
		 if(pass1.length < 8 || pass1.length > 20){
		  $("#passwordCheck").html("8자리 ~ 20자리 이내로 입력해주세요.");
		  return false;
		 }else if(pass1.search(/\s/) != -1){
		  $("#passwordCheck").html("비밀번호는 공백 없이 입력해주세요.");
		  return false;
		 }else if(num < 0 || eng < 0 || spe < 0 ){
			$("#passwordCheck").html("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
		  return false;
		 }else {
			$("#passwordCheck").html("적정한 비밀번호");
			$("#passwordCheck").attr('color','green');
			 if(pass1 == pass2)
			 {
				 $("#send").attr("disabled", false); //설정
			 }
			 else{
				  $("#send").attr("disabled", true); //설정
			 }
		    return true;
		    
		 }
		 
		
		 
		}) 
	
	
	
	
	
	
    }