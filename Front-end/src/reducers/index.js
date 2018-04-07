import { routerReducer as routing } from 'react-router-redux'
import { combineReducers } from 'redux'
import { reducer as reduxFormReducer } from 'redux-form'
import { RESET_VALUES } from '../actions/types';

//Reducers
//1. Reducers are pure functions
//2. Never change state or action
//3. 
export default combineReducers({
  routing,
  form:reduxFormReducer.plugin({
    logInForm:(state,action)=>{
      switch(action.type){
        case RESET_VALUES:
          state.nickname = action
          return undefined;
        default:
          return state;
      }
    }
  })
})
