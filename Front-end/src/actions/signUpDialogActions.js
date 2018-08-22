import Axios from '../util/axiosFunction';
import actionTypes from '../constants/actionTypes';

export function signUp(nickname,email,password,subject,typeOfStudy){
    return (dispatch) =>{
        const headers = {
            'Content-Type':'application/json'
        }
        let request = Axios('post',true,'signup',{nickname,email,password,subject,typeOfStudy},headers);

        request.then((response) => {
            console.log('actions ',response);
            dispatch({
                type:actionTypes.USER.SIGN_UP,
                payload:response
            })
        });

        return request;

    }
}