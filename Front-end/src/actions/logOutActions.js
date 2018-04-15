import Axios from '../util/axiosFunction';
import actionTypes from '../constants/actionTypes';

export function logOut(username){
    return(dispatch)=>{
        const headers = {
            'Content-Type':'application/json'
        }
        let request = Axios('post',true,'logout',{username},headers);

        request.then((response)=>{
            // console.log(response);
            dispatch({
                type:actionTypes.USER.LOGOUT,
                payload:response
            })
        });

        return request;
    }
}