const wd_password = document.getElementById("wd_pw");
const wd_btn = document.getElementById("wd_btn");
var wd_select = document.getElementById("withdrawal_select");
var select_reason =
    document.getElementById("withdrawal_select").options.selectedIndex;

wd_btn.addEventListener("click", (e) => {
    e.preventDefault();
const token = sessionStorage.getItem("jwt");
const tokenHead = "Bearer " + token;

    //회원탈퇴 선택창 팝업
    var result = confirm("탈퇴하시겠습니까? ");
    if (result) {
        const req = {
            u_pw: wd_password.value,
            reason: wd_select.value,
        };
        const arrReq = [];
        arrReq.push(req);
        console.log(wd_select.value);

        fetch("/myPage/myInfo/withdrawal", {
            method: "POST",
            headers: {
                "Content-type": "application/json",
                "Authorization": tokenHead,},
            body: JSON.stringify(arrReq),
        })
            .then((response) => response.json())
            .then((data) => {
                console.log("response : " + data );
                if (Number(data) == 1) {
                    alert("탈퇴 처리되었습니다.");
                    location.href = "/mainPage";
                    console.log("탈퇴사유:" + wd_select.value);
                    sessionStorage.clear();
                } else {
                    alert("다시 확인하세요.");
                }
            })
    } else {
        alert("이전페이지로 돌아갑니다.")
        location.href = "/myPage/myInfo/webData";
    }
});
