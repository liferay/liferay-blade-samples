import {createStore} from 'redux';

import reduce from './reducers';

export default createStore(reduce);
