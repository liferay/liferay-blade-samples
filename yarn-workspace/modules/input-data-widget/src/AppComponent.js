import PropTypes from 'prop-types';
import React from 'react';
import {Provider} from 'react-redux';
import store from 'redux-store';
import actions from 'redux-store/actions';

import BodyDataLoader from './BodyDataLoader';

const AppComponent = ({configuration}) => {
	const initialCsv = configuration.portletInstance.initialCsv.replace(
		/\|/g,
		'\n'
	);

	store.dispatch(actions.setFormData(initialCsv));

	return (
		<div>
			<Provider store={store}>
				<BodyDataLoader />
			</Provider>
		</div>
	);
};

AppComponent.propTypes = {
	configuration: PropTypes.object.isRequired,
	contextPath: PropTypes.string.isRequired,
	portletElementId: PropTypes.string.isRequired,
	portletNamespace: PropTypes.string.isRequired,
};

export default AppComponent;
