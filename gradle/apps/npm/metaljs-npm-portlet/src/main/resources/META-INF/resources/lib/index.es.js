'use strict';

import Modal from './Modal.es';

export default function(elementId) {
	const btn = document.getElementById(elementId);
	let modal;

	btn.addEventListener('click', () => {
		if (!modal) {
			modal = new Modal({
				header: 'A Metal.js Modal Dialog',
				body: `
					This is a Metal.js based dialog that has been rendered using SOY
					templates.
					Please, click the OK button to dismiss it.
				`,
			});
		} else {
			modal.shown = true;
		}
	});
}