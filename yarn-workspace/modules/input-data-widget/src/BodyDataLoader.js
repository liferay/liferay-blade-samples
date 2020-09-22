import PropTypes from 'prop-types';
import React from 'react';
import {connect} from 'react-redux';
import actions from 'redux-store/actions';

const BodyDataLoader = ({csv, onDataChange, onLoadData, onResetData}) => {
	return (
		<div className="BodyDataLoader">
			<textarea
				placeholder="💡 Paste CSV data here"
				onChange={onDataChange}
				value={csv}
				wrap="off"
			/>
			<button onClick={onLoadData}>Load data</button>
			<button onClick={onResetData}>Reset data</button>
		</div>
	);
};

BodyDataLoader.propTypes = {
	csv: PropTypes.string.isRequired,
	onDataChange: PropTypes.func.isRequired,
	onLoadData: PropTypes.func.isRequired,
	onResetData: PropTypes.func.isRequired,
};

BodyDataLoader.mapStateToProps = ({formData}) => ({
	csv: formData,
});

BodyDataLoader.mapDispatchToProps = dispatch => ({
	onDataChange: event => dispatch(actions.setFormData(event.target.value)),
	onLoadData: () => dispatch(actions.loadFormData()),
	onResetData: () => dispatch(actions.resetData()),
});

export default connect(
	BodyDataLoader.mapStateToProps,
	BodyDataLoader.mapDispatchToProps
)(BodyDataLoader);
