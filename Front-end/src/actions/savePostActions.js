import Axios from '../util/axiosFunction';
import actionTypes from '../constants/actionTypes';

export function savePost(content,created_at,created_by,creator_nickname,related_subject){
    return(dispatch)=>{
        const headers = {
            'Content-Type':'application/json'
        }
        let request = Axios('post',true,'savePost',{content,created_at,created_by,creator_nickname,related_subject},headers);

        request.then((response)=>{
            dispatch({
                type:actionTypes.USER.SAVE_POST,
                payload:response
            })
        });

        return request;
    }
}