import actionTypes from '../constants/actionTypes';

const initialState = {};

export default function(state = initialState, action = {}){
    switch(action.type){
        case actionTypes.USER.GET_POSTS:
            return { ...state,...action.payload};
        default:
            return state;
    }
}