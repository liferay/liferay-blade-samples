import $ from 'jquery';

export default function(rootElementId) {
	var el = $(`#${rootElementId}`);

	el.html('Hello from jQuery! 👋');
	el.click(() => {
		alert('Cool! 🙌');
	});
}