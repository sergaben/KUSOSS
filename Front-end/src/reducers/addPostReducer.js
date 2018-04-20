import actionTypes from '../constants/actionTypes';

const initialState = {};



export default function(state = initialState, action = {}){
    switch(action.type){
        case actionTypes.USER.ADD_POST:
            return action.postArray;
        default:
            return state;
    }
}