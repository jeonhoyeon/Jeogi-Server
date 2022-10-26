const myPageLocation = document.getElementById("myPageLocation");
const myCourseContainerLocation = document.getElementById("myPageContainerLocation");

myPageLocation.addEventListener("click", () => {

	const req = "leehj";

	fetch("/myPage/myInfo/webData", {
		method: "POST",
		headers: { "Content-Type": "application/json" },
		body: req,
	})
		.then((response) => response.json())
		.then((myPage) => {
			console.log("data: " + myPage + ", 타입: " + typeof (myPage))
		})

})