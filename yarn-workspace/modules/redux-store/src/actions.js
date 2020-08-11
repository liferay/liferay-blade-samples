import {createActions} from 'redux-actions';

export default createActions({
	LOAD_FORM_DATA: () => ({}),
	MERGE_DISPLAY: display => ({display}),
	RESET_DATA: () => ({}),
	SET_FORM_DATA: csv => ({csv}),
});
