import { createStore as _createStore, applyMiddleware } from 'redux'
import { logger } from '../middleware/index';
import thunk from 'redux-thunk';
import rootReducer from '../reducers/index';

export const createStore = () => {
  let middleware = [ thunk ];
  let isProd = process.env.NODE_ENV === "production";

  if (!isProd) {
    middleware.push(logger);
  }
// ,window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
  return _createStore(rootReducer, applyMiddleware.apply(null, middleware));
};

export default createStore;