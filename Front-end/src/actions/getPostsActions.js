import Axios from '../util/axiosFunction';
import actionTypes from '../constants/actionTypes';

export function getPosts(subject){
    return(dispatch)=>{
        const headers = {
            'Content-Type':'application/json'
        }
        let request = Axios('get',true,'getPosts',{subject},headers);

        request.then((response)=>{
            dispatch({
                type:actionTypes.USER.GET_POSTS,
                payload:response
            })
        });

        return request;
    }
}