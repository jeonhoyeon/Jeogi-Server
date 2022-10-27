const findPwId = document.getElementById("findPw-id");
const findPwEmail = document.getElementById("findPw-email");
const findPwBtn = document.getElementById("findPw__button");


findPwBtn.addEventListener("click", () => {

  const req = {
    u_id: findPwId.value,
    u_email: findPwEmail.value,
  };
  const arrReq = [];
  arrReq.push(req);

  console.log(arrReq);
  console.log(JSON.stringify(arrReq));
  fetch("/login/findMyPw", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(arrReq),
  })
    .then((response) => response.json())
    .then((findPwdata) => {
      alert("비밀번호: " + findPwdata)
    });

})
