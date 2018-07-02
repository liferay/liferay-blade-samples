import { Component } from '@angular/core';

@Component({
	template: `
		<div>{{caption}}</div>
	`,
})
export class AppComponent {
	caption = 'Hello world from Angular 6!';
}
