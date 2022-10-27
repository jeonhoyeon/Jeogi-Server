window.addEventListener("DOMContentLoaded", () => {
  const req = "leehj"

  fetch("/myPage/myInfo/webData", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: req,
  })
    .then((response)
    .then((myPageData) => {
      // 페이지를 보내주면 어떻게 띄울 수 있는지 확인
    });
});
  