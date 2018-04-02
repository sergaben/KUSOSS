import { Router, Route, browserHistory } from 'react-router'
import { syncHistoryWithStore } from 'react-router-redux'
import { Provider } from 'react-redux'
import ReactDOM from 'react-dom'
import React from 'react'

import App from './containers/App'
import configure from './store'
import Chat from './components/Chat';
import MainPage from './components/MainPage/mainPage';
import '../node_modules/draft-js/dist/Draft.css';

const store = configure();
const history = syncHistoryWithStore(browserHistory, store);

ReactDOM.render(
  <Provider store={store}>
    <Router history={history}>
      <Route path="/" component={App}/>
      <Route path="/main" component={MainPage}/>
    </Router>
  </Provider>,
  document.getElementById('root')
)
