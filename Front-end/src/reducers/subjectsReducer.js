import actionTypes from '../constants/actionTypes';

const initialState = [];

export default function(state = initialState, action = {}){
    switch(action.type){
        case actionTypes.SUBJECTS.GET_SUBJECTS:
            return { ...state, ...action.payload};
        default:
            return state;
    }
}