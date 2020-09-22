import PropTypes from 'prop-types';
import React from 'react';
import {Provider} from 'react-redux';
import store from 'redux-store';
import actions from 'redux-store/actions';

import BodyTrackerAnalysis from './BodyTrackerAnalysis';

const AppComponent = ({configuration}) => {
	store.dispatch(
		actions.mergeDisplay({
			measureType: configuration.system.measureType || 'fat',
			period: configuration.system.period || '1m',
		})
	);

	return (
		<div>
			<Provider store={store}>
				<BodyTrackerAnalysis />
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
