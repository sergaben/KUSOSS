import actionTypes from '../constants/actionTypes';

export function callingOnMessageSSE(sse){
    return(dispatch)=>{
        sse.onmessage = (event) =>dispatch(gettingPosts(event.data));
    }
}
export function gettingPosts(post){
    let postAsJson = convertToJson(post);
    return{
        type:actionTypes.SSE.MESSAGE,
        post:postAsJson
    }
}
function convertToJson(post){
    return JSON.parse(post);
}