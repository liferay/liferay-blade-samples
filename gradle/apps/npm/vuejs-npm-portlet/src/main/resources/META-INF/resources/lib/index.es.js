//
// We are using the runtime + compiler module in this case so that we don't need
// to process templates during build time.
//
// See https://vuejs.org/v2/guide/installation.html#Runtime-Compiler-vs-Runtime-only
// for more information.
//
import Vue from 'vue/dist/vue.common';

export default function(portletNamespace) {
	// Application 1
	new Vue({
		el: `#${portletNamespace}-1`,
		data: {
			message: 'Hello from Vue.js!',
		},
		methods: {
			reverseMessage: function() {
				this.message = this.message
					.split('')
					.reverse()
					.join('');
			},
		},
	});

	// Application 2
	Vue.component('todo-item', {
		props: ['todo'],
		template: '<li>{{ todo.text }}</li>',
	});
	new Vue({
		el: `#${portletNamespace}-2`,
		data: {
			groceryList: [
				{ id: 0, text: 'Vegetables' },
				{ id: 1, text: 'Cheese' },
				{ id: 2, text: 'Whatever else humans are supposed to eat' },
			],
		},
	});
}