import Axios from '../util/axiosFunction';
import actionTypes from '../constants/actionTypes';

export function getSubjects(){
    return (dispatch) =>{
        let request = Axios('get',true,'getSubjectsNamesAsJson');

        request.then((response) => {
            dispatch({
                type:actionTypes.SUBJECTS.GET_SUBJECTS,
                payload:response
            })
        });

        return request;

    }
}