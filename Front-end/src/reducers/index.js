import { routerReducer as routing } from 'react-router-redux'
import { combineReducers } from 'redux'
import signUpReducer from '../reducers/signUpDialogReducer';
import subjectReducer from './subjectsReducer';
import logInReducer from './logInDialogReducer';
import logOutReducer from './logOutReducer';
import savePostReducer from './savePostReducer';
import getPostsReducer from './getPostsReducer';
import {reducer as formReducer} from 'redux-form';

//Reducers
//1. Reducers are pure functions
//2. Never change state or action
//3. 
export default combineReducers({
  routing,
  form:formReducer,
  signUpReducer,
  subjectReducer,
  logInReducer,
  logOutReducer,
  savePostReducer,
  getPostsReducer
})
