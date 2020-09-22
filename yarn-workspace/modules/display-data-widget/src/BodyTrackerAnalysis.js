import Highcharts from 'highcharts';
import PropTypes from 'prop-types';
import React from 'react';
import {
	Chart,
	HighchartsChart,
	Legend,
	LineSeries,
	Tooltip,
	withHighcharts,
	XAxis,
	YAxis,
} from 'react-jsx-highcharts';
import {connect} from 'react-redux';
import actions from 'redux-store/actions';
import {linearRegression, linearRegressionLine} from 'simple-statistics';

const measureTypeLabels = {
	fat: 'Body Fat',
	muscle: 'Body Muscle',
	weight: 'Body Weight',
	visceralFat: 'Visceral Fat',
};

const measureTypeUnits = {
	fat: '%',
	muscle: '%',
	weight: 'Kg',
	visceralFat: '',
};

const BodyTrackerAnalysis = ({
	measureType,
	period,
	measures,
	onMeasureTypeChange,
	onPeriodChange,
}) => {
	const data = processMeasures(measureType, period, measures);
	const regression = calculateRegression(data);

	return (
		<div className="BodyTrackerAnalysis">
			<div className="measureTypeBlock">
				<label>
					<input
						type="radio"
						name="measureType"
						value="fat"
						checked={measureType === 'fat'}
						onChange={onMeasureTypeChange}
					/>
					Body Fat
				</label>
				<label>
					<input
						type="radio"
						name="measureType"
						value="muscle"
						checked={measureType === 'muscle'}
						onChange={onMeasureTypeChange}
					/>
					Body Muscle
				</label>
				<label>
					<input
						type="radio"
						name="measureType"
						value="weight"
						checked={measureType === 'weight'}
						onChange={onMeasureTypeChange}
					/>
					Body Weight
				</label>
				<label>
					<input
						type="radio"
						name="measureType"
						value="visceralFat"
						checked={measureType === 'visceralFat'}
						onChange={onMeasureTypeChange}
					/>
					Visceral Fat
				</label>
			</div>
			<div className="periodBlock">
				<select
					name="period"
					defaultValue={period}
					onChange={onPeriodChange}
				>
					<option value="1m">1 Month</option>
					<option value="2m">2 Months</option>
					<option value="3m">3 Months</option>
					<option value="6m">6 Months</option>
					<option value="1y">1 Year</option>
					<option value="all">All</option>
				</select>
			</div>
			<HighchartsChart plotOptions={{}}>
				<Chart />

				<Legend />

				<Tooltip valueSuffix={` ${measureTypeUnits[measureType]}`} />

				<XAxis type="datetime" />

				<YAxis>
					<LineSeries
						name={measureTypeLabels[measureType]}
						color="#6060FF"
						data={data}
					/>
					<LineSeries
						name="Trend Line"
						type="line"
						color="#FF6060"
						data={regression}
					/>
				</YAxis>
			</HighchartsChart>
		</div>
	);
};

BodyTrackerAnalysis.propTypes = {
	measureType: PropTypes.string.isRequired,
	period: PropTypes.string.isRequired,
	measures: PropTypes.object.isRequired,
	onMeasureTypeChange: PropTypes.func.isRequired,
	onPeriodChange: PropTypes.func.isRequired,
};

BodyTrackerAnalysis.mapStateToProps = ({display, data}) => ({
	measureType: display.measureType || 'fat',
	period: display.period || '1m',
	measures: data,
});

BodyTrackerAnalysis.mapDispatchToProps = dispatch => ({
	onMeasureTypeChange: event =>
		dispatch(
			actions.mergeDisplay({
				measureType: event.target.value,
			})
		),
	onPeriodChange: event =>
		dispatch(
			actions.mergeDisplay({
				period: event.target.value,
			})
		),
});

/**
 * Filter measure records according to measure type and period and then map them
 * to Highcharts format.
 * @param {string} measureType
 * @param {string} period
 * @param {Array<object>} measures
 */
function processMeasures(measureType, period, measures) {
	const now = new Date(2019, 4, 4);

	let year = now.getFullYear();
	let month = now.getMonth();

	switch (period) {
		case '1m':
			month -= 1;
			break;

		case '2m':
			month -= 2;
			break;

		case '3m':
			month -= 3;
			break;

		case '6m':
			month -= 6;
			break;

		case '1y':
			month -= 12;
			break;

		case 'all':
			month -= 12 * 200;
			break;

		default:
			throw new Error(`Invalid period: ${period}`);
	}

	while (month < 0) {
		year -= 1;
		month += 12;
	}

	const start = new Date(year, month, now.getDate());

	measures = measures[measureType];
	measures = measures.filter(
		measure => measure.date.getTime() >= start.getTime()
	);

	return measures.map(measure => [measure.date.getTime(), measure.value]);
}

/**
 * Calculate a linear regression with given Highcharts data and return another
 * Array of Highcharts data representing the origin and end points of the
 * regression line.
 * @param {Array<object>} data
 */
function calculateRegression(data) {
	if (data.length < 2) {
		return [];
	}

	const lr = linearRegressionLine(linearRegression(data));

	const start = data[0][0];
	const end = data[data.length - 1][0];

	return [[start, lr(start)], [end, lr(end)]];
}

export default withHighcharts(
	connect(
		BodyTrackerAnalysis.mapStateToProps,
		BodyTrackerAnalysis.mapDispatchToProps
	)(BodyTrackerAnalysis),
	Highcharts
);
