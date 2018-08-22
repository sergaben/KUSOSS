import actionTypes from '../constants/actionTypes';

const initialState = {};



export default function(state = initialState, action = {}){
    switch(action.type){
        case actionTypes.SSE.MESSAGE:
            return action.post;
        default:
            return state;
    }
}