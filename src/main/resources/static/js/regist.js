function btn_save_onclick() {
    if(!validationCheck()) {
        return false;
    }

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
    
    return true;
}


function btn_duplication() {
    const pattern = new RegExp("^([\\w\\.\\_\\-])*[a-zA-Z0-9]+([\\w\\.\\_\\-])*([a-zA-Z0-9])+([\\w\\.\\_\\-])+@([a-zA-Z0-9]+\\.)+[a-zA-Z0-9]{2,3}$");
    var test = "hello@google.com";
    console.log(test);
    console.log(pattern.test(test));

}

