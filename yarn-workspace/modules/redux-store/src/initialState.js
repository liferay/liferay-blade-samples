export default {
	data: {
		// [{date: <Date>, value: <number>}, ...]
		fat: [],
		muscle: [],
		visceralFat: [],
		weight: [],
	},
	display: {
		measureType: undefined, // {'fat'|'muscle'|'visceralFat'|'weight'}
		period: undefined, // {'1m'|'2m'|'3m'|'6m'|'1y'|'all'}
	},
	formData: '', // <CSV data>
};
