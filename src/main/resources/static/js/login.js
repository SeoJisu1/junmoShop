function btn_login_click() {

    if(!validationCheck()) {
        return false;
    }

}

function capsLockChk(event) {
    if(event.getModifierState("CapsLock")) {
        $("#p_message").css("display", "");
    } else {
        $("#p_message").css("display", "none");
    }
}

function validationCheck() {
    if(lg_id.value == "") {
        alert("아이디를 입력해주세요.");
        return false;
    }
    if(lg_password.value == "") {
        alert("비밀번호를 입력해주세요.");
        return false;
    }

    return true;
}