const myInfoSettingButton = document.getElementById("myInfoSettingButton");
const passContainer = document.getElementById("pass_container");
const quit = document.getElementById("quit");
const identification = document.getElementById("userImportant");
const userPw = document.getElementById("userPw");

window.addEventListener("DOMContentLoaded", () => {

	fetch("/myPage/myInfo/getMyData", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
			"Authorization": "Bearer eyJqd3QiOiJIUzI1NiIsImFsZyI6IkhTMjU2In0.eyJzdWIiOiJ0ZXN0MSIsInVfaWQiOiJ0ZXN0MSIsImdlbmRlciI6MSwidV9uaWNrIjoiVHV0aWMiLCJiaXJ0aCI6IjE5OTgtMDItMjAiLCJleHAiOjE2NjY5NjEzOTcsImlhdCI6MTY2NjkyNTM5N30.uTd8v-k9WeSmj3MnT3MlVGLcpOfuqoJYRr8aal3h8qI"
		},
	})
		.then((response) => response.json())
		.then((myPageData) => {
			console.log(myPageData);
		})
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
			if (data == 0) {
				alert("다시 확인하세요.");
			} else {
				location.href = "myInfoSetting";
			}
		})
})