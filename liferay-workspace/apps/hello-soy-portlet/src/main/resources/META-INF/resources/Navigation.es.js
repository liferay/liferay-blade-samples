import Component from 'metal-component';
import Footer from './Footer.soy';
import Header from './Header.soy';
import Soy from 'metal-soy';
import templates from './Navigation.soy';

class Navigation extends Component {}

// Register component

Soy.register(Navigation, templates);

export default Navigation;
