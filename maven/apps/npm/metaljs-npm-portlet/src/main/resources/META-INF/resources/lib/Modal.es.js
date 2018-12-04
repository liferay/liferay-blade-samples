'use strict';

import Component from 'metal-component';
import Soy from 'metal-soy';
import CloseHeader from './CloseHeader.es';

import templates from './Modal.soy';

class Modal extends Component {
	close() {
		this.shown = false;
	}
}
Soy.register(Modal, templates);

Modal.STATE = {
	body: {
		value: 'Default body',
	},
	header: {
		value: 'Default header',
	},
	shown: {
		// The default value will be: `true`.
		value: true,
	},
};

export default Modal;