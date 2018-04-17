import actionTypes from '../constants/actionTypes';

const initialState = {};

export default function(state = initialState, action = {}){
    switch(action.type){
        case actionTypes.USER.SAVE_POST:
            return { ...state,...action.payload};
        default:
            return state;
    }
}