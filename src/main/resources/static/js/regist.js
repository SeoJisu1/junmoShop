var idChk = false; // 아이디 중복확인용 변수

// 가입하기 버튼 클릭
function btn_save_onclick() {
    if(!validationCheck()) {
        return false;
    }

    if(!idChk) {
        alert("id 중복확인을 해 주세요");
        return false;
    }

    alert("가입이 완료되었습니다.");
}

// 유효성 검사
function validationCheck() {
    console.log(rst_phone.value);
    console.log(rst_phone.value.indexOf("-"));
    if(rst_id.value == "") {
        alert("아이디를 입력하세요");
        return false;
    } else {
        const pattern = new RegExp("^[a-zA-Z][0-9a-zA-Z]{4,7}$");
        if(!pattern.test(rst_id.value)) {
            alert("id는 영문+숫자 조합으로 5~8 글자만 입력해야합니다.");
            return false;
        }
    }

    if(rst_pass.value == "") {
        alert("비밀번호를 입력하세요");
        return false;
    } else {
        const pattern = new RegExp("(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9])(?=.{8,})");
        if(!pattern.test(rst_pass.value)) {
            alert("비밀번호는 최소 하나 이상의 소문자, 숫자, 특수문자를 포함하여 8자리 이상 입력해야합니다.");
            return false;
        }
    }

    if(rst_passChk.value == "") {
        alert("비밀번호 확인을 입력하세요");
        return false;
    }
    if(rst_name.value == "") {
        alert("이름을 입력하세요");
        return false;
    }

    if(rst_phone.value == "") {
        alert("전화번호를 입력하세요('-' 제외)");
        return false;
    }

    if(rst_addrs.value == "") {
        alert("주소를 입력하세요");
        return false;
    }
    
    if(rst_email.value != "") {
        const pattern = new RegExp("^([\\w\\.\\_\\-])*[a-zA-Z0-9]+([\\w\\.\\_\\-])*([a-zA-Z0-9])+([\\w\\.\\_\\-])+@([a-zA-Z0-9]+\\.)+[a-zA-Z0-9]{2,3}$");
        if(!pattern.test(rst_email.value)) {
            alert("이메일 형식을 확인해주세요.");
            return false;
        }    
    }

    if(rst_pass.value != rst_passChk.value) {
        alert("비밀번호를 다시 확인해주세요.");
        return false;
    }
    
    return true;
}

// 아이디 중복확인
function btn_duplication() {
    var id = rst_id.value;

    if(id == '') {
        alert("id를 입력해주세요");
        return false;
    } else if(id.length < 5) {
        alert("5~8자로 입력해주세요");
        return false;
    }

    var memberDto = {
        member_id : id
    };

    $.ajax({
        url : "/member/duplication"
       ,data : memberDto
       ,type : "POST"
    }).done(function(result) {
        if(result == 1) {
            alert("이미 존재하는 id 입니다.");
            idChk = false;
        } else if(result == 0) {
            alert("사용 가능한 id 입니다.");
            idChk = true;
        }
    });

}

// kakao api 주소검색
function btn_addrsSearch() {
    new daum.Postcode({
        oncomplete: function(data) {
            document.getElementById("rst_addrs").value = data.address;
            $("#rst_addrsDetail").css("display", "");
        }
    }).open();
}

// 비밀번호 확인 문구 컨트롤
function warningTxtControll(e) {
   var txt = document.getElementsByClassName("sp_warningTxt")[0];

   if(rst_pass.value != rst_passChk.value) {
       txt.style.display="block";
       txt.innerHTML = "*비밀번호가 일치하지 않습니다.";
   } else {
       txt.style.display="none";
       txt.innerHTML = "";
   }

}
