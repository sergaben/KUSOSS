import { reducer as reduxFormReducer } from 'redux-form'
import { RESET_VALUES } from '../actions/types';
export default function(){
    return reduxFormReducer.plugin({
    logInForm:(state,action)=>{
      switch(action.type){
        // case RESET_VALUES:
        //   state.nickname = action
        //   return undefined;
        default:
          return state;
      }
    },
    signUpForm:(state,action)=>{
      switch(action.type){
        // case RESET_VALUES:
        //   state.nickname = action
        //   return undefined;
        default:
          return state;
      }
    }
  })
}