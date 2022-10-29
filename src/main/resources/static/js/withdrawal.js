const wd_password = document.getElementById("wd_pw");
const wd_btn = document.getElementById("wd_btn");
var wd_select = document.getElementById("withdrawal_select");
var select_reason =
  document.getElementById("withdrawal_select").options.selectedIndex;

wd_btn.addEventListener("click", (e) => {
  e.preventDefault();


  //회원탈퇴 선택창 팝업  
  var result = confirm("탈퇴하시겠습니까? ");
  if (result) {
    const req = {
      u_id: "로그인할 때 세션에 저장되어지는 u_id값",
      u_pw: wd_password.value,
      reason: wd_select.value,
    };
    console.log(wd_select.value);

    fetch("/myPage/myInfowithdrawal", {
      method: "POST",
      headers: { "Content-type": "application.json",
      "Authorization": "Bearer eyJqd3QiOiJIUzI1NiIsImFsZyI6IkhTMjU2In0.eyJzdWIiOiJ0ZXN0MSIsInVfaWQiOiJ0ZXN0MSIsImdlbmRlciI6MSwidV9uaWNrIjoiVHV0aWMiLCJiaXJ0aCI6IjE5OTgtMDItMjAiLCJleHAiOjE2NjY5NjEzOTcsImlhdCI6MTY2NjkyNTM5N30.uTd8v-k9WeSmj3MnT3MlVGLcpOfuqoJYRr8aal3h8qI"
		  },
      body: JSON.stringify(req),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log("response : " + data + "TYPE : " + typeof data);
        if (Number(data) == 1) {
          alert("탈퇴 처리되었습니다.");
          location.href = "/mainPage";
          console.log("탈퇴사유:" + wd_select.value);
        } else {
          alert("다시 확인하세요.");
        }
      })
  } else {
    alert("이전페이지로 돌아갑니다.")
    location.href = "/myPage/myInfo/webData";
  }
});
