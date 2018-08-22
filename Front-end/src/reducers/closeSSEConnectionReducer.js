import actionTypes from '../constants/actionTypes';

const initialState = {};

export default function(state = initialState, action = {}){
    switch(action.type){
        case actionTypes.SSE.CLOSE:
            return action.close;
        default:
            return state;
    }
}