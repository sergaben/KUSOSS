import actionTypes from '../constants/actionTypes';

const initialState = {};

export default function(state = initialState, action = {}){
    switch(action.type){
        case actionTypes.SSE.CONNECT:
            console.log(action.sse);
            return action.sse;
        default:
            return state;
    }
}