import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app/app.module';

export default function() {
	platformBrowserDynamic().bootstrapModule(AppModule);
}
