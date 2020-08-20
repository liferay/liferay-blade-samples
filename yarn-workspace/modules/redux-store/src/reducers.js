import Papa from 'papaparse';
import {handleActions} from 'redux-actions';

import initialState from './initialState';

export default handleActions(
	{
		LOAD_FORM_DATA: state =>
			merge(state, {
				data: parseFormData(state.formData),
			}),
		MERGE_DISPLAY: (state, {payload: {display}}) =>
			merge(state, {
				display: merge(state.display, display),
			}),
		RESET_DATA: state =>
			merge(state, {
				data: {
					fat: [],
					muscle: [],
					visceralFat: [],
					weight: [],
				},
			}),
		SET_FORM_DATA: (state, {payload: {csv}}) =>
			merge(state, {
				formData: csv,
			}),
	},
	initialState
);

/**
 * Merges some objects into another object or array. If the target is an object,
 * the rest of objects are Object.assigned to it. If the target is an array, the
 * rest of objects are Array.concatenated to it.
 *
 * This method always returns a new copy of the target object or array.
 *
 * @param {object|array} target
 * @param  {...any} objects
 */
function merge(target, ...objects) {
	if (Array.isArray(target)) {
		return target.concat(objects);
	} else {
		return Object.assign({}, target, ...objects);
	}
}

/**
 * Parse a CSV string to convert it into the data field format of the Redux
 * store.
 * @param {string} csvData
 */
function parseFormData(csvData) {
	const measureTypeMap = {
		'Body Fat': 'fat',
		'Body Muscle': 'muscle',
		Bodyweight: 'weight',
		'Visceral Fat': 'visceralFat',
	};

	const bodyMeasures = {
		fat: [],
		muscle: [],
		visceralFat: [],
		weight: [],
	};

	const csv = Papa.parse(csvData, {
		header: true,
	});

	csv.data.forEach(record => {
		try {
			const type = record['Measurement'];

			if (type === undefined || type === '') {
				return;
			}

			const date = record['Date'].split('-');
			const time = record['Time'].split(':');

			const measureType = measureTypeMap[type];

			if (measureType === undefined) {
				return;
			}

			bodyMeasures[measureType].push({
				date: new Date(
					Date.UTC(
						date[0],
						date[1] - 1,
						date[2],
						time[0],
						time[1],
						time[2]
					)
				),
				value: parseFloat(record['Value']),
			});
		} catch (err) {
			console.error('Invalid record found:', record);
		}
	});

	Object.keys(bodyMeasures).forEach(measureType =>
		bodyMeasures[measureType].sort((a, b) => a.date.time - b.date.time)
	);

	return bodyMeasures;
}
