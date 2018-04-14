import Axios from '../util/axiosFunction';
import actionTypes from '../constants/actionTypes';

export function logIn(username,password){
    return(dispatch)=>{
        const headers = {
            'Content-Type':'application/json'
        }
        let request = Axios('post',true,'login',{username,password},headers);

        request.then((response)=>{
            dispatch({
                type:actionTypes.USER.LOGIN,
                payload:response
            })
        });

        return request;
    }
}