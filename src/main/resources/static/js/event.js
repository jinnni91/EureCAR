window.onload = function() {
   
    /* 화면에 랜더링 된 태그들이 존재하지 않는 경우 에러 발생 가능성이 있어서 if문으로 태그가 존재하는지 부터 확인하고 이벤트를 연결한다. */
    if(document.getElementById("regist")) {
        const $regist = document.getElementById("regist");
        $regist.onclick = function() {
            location.href = "/member/regist";
        }
    }
    if(document.getElementById("login")) {
        const $login = document.getElementById("login");
        $login.onclick = function() {
            location.href = "/member/login";
        }
    }
 	
    if(document.getElementById("logout")) {
        const $logout = document.getElementById("logout");
        $logout.onclick = function() {
            location.href = "/member/logout";
        }
    }
    
      if(document.getElementById("updateMember")) {
        const $update = document.getElementById("updateMember");
        $update.onclick = function() {
            location.href = "/mypage/update";
        }
    }
    
    if(document.getElementById("deleteMember")) {
        const $update = document.getElementById("deleteMember");
        $update.onclick = function() {
            location.href = "/mypage/delete";
        }
    }
    
/* 공지사항 게시판 */


	if(document.getElementById("writeNotice")) {
	        const $writeNotice = document.getElementById("writeNotice");
	        $writeNotice.onclick = function() {
	            location.href = "/notice/regist";
	   }
	}
	    
	if(document.getElementById("modifyNotice")) {
	        const $modifyNotice = document.getElementById("modifyNotice");
	        $modifyNotice.onclick = function() {
	            location.href = "/notice/modify";
	   }
	}    
	    
	    
	    
};

