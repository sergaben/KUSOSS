import { routerReducer as routing } from 'react-router-redux'
import { combineReducers } from 'redux'
import signUpReducer from '../reducers/signUpDialogReducer';
import subjectReducer from './subjectsReducer';
import logInReducer from './logInDialogReducer';
import reduxForm from './reduxForm';
import {reducer as formReducer} from 'redux-form';
import actionTypes from '../constants/actionTypes';

//Reducers
//1. Reducers are pure functions
//2. Never change state or action
//3. 
export default combineReducers({
  routing,
  form:formReducer,
  signUpReducer,
  subjectReducer,
  logInReducer
})
