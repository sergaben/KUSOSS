import actionTypes from '../constants/actionTypes';
import closeConnection from '../actions/closeSSEConnectionAction';
let sse;
let ids=[];
let reconnection;
export function establishConnection(url){
    return(dispatch)=>{
            sse = new EventSource(url);
            console.log(sse);
            return {
                type:actionTypes.SSE.CONNECT,
                sse:sse
            }
           
    }
}
