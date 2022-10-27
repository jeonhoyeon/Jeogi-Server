window.addEventListener("DOMContentLoaded", () => {
  const req = {
    u_id: "leehj",
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
  