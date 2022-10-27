const myInfoSettingButton = document.getElementById("myInfoSettingButton");
const passContainer = document.getElementById("pass_container");
const quit = document.getElementById("quit");
const identification = document.getElementById("userImportant");
const userPw = document.getElementById("userPw");

window.addEventListener("DOMContentLoaded", () => {
  const req = {
    u_id: "세션에 저장되어 있는 u_id",
  };
  const arrReq = [];
  arrReq.push(req);

  fetch("/myPage/myInfo/webData", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(arrReq),
  })
    .then((response) => response.json())
    .then((myPageData) => {
      // 페이지를 보내주면 어떻게 띄울 수 있는지 확인
    });
});

myInfoSettingButton.addEventListener("click", () => {
  passContainer.style.display = "flex";
});

quit.addEventListener("click", () => {
  passContainer.style.display = "none";
})

identification.addEventListener("click", () => {
  const req = {
    u_pw: userPw.value,
  }
  fetch("/myPage/myInfo/CheckPW", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(req),
  })
    .then((response) => response.json())
    .then((data) => {
      if(data == 0) {
        alert("다시 확인하세요.");
      } else {
        location.href="myInfoSetting";
      }
    })
})