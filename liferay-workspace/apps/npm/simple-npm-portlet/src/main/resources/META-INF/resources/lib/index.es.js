import isArray from 'isarray';

export default function() {
	var t;

	out.innerHTML += 'Calling isArray([])';
	t = isArray([]);
	out.innerHTML += ' -> returns ' + t + '.\n';

	out.innerHTML += 'Calling isArray([])';
	t = isArray([]);
	out.innerHTML += ' -> returns ' + t + '.\n';
}