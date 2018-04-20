import actionTypes from '../constants/actionTypes';
let sse;
let ids=[];
let reconnection;
export function establishConnection(url){
    return(dispatch)=>{
            sse = new EventSource(url);
            sse.onmessage = (event) =>dispatch(gettingPosts(event.data))
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