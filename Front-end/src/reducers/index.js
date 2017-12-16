import { routerReducer as routing } from 'react-router-redux'
import { combineReducers } from 'redux'
import { reducer as reduxFormReducer } from 'redux-form'
import { RESET_VALUES } from '../actions/types';


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
