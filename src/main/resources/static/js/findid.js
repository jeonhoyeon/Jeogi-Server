const findIdEmail = document.getElementById("findId-email")
const findIdBtn = document.getElementById("findId__button")
const token = sessionStorage.getItem('jwt');
const tokenHead = "Bearer " + token;

findIdBtn.addEventListener("click", () => {
  
  const req = {
    u_email: findIdEmail.value,
  };
  const arrReq = [];
  arrReq.push(req);

  console.log(arrReq);
  console.log(JSON.stringify(arrReq));
  fetch("/login/findMyId", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(arrReq),
  })
    .then((response) => response.json())
    .then((findIdData) => {
      //서버에서 u_id 보내줌
    });

})
