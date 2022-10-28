const myPageLocation = document.getElementById("myPageLocation");
const myCourseContainerLocation = document.getElementById("myPageContainerLocation");

sessionStorage.getItem('jwt');

myPageLocation.addEventListener("click", () => {

	const req = "leehj";

	fetch("/myPage/myInfo/webData", {
		method: "POST",
		headers: { "Content-Type": "application/json" },
		body: req,
	})
		.then((response) => response.text())
		.then((html) => {
			let parser = new DOMParser();
			let doc = parser.parseFromString(html, 'text/html');

			console.log(doc);
			window.document.innterHTML = doc;

		})
		.catch((err) => {
			console.log("Failed to fetch page: ", err)
		})

})