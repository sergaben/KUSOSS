import actionTypes from '../constants/actionTypes';


export function closeConnection(sse){
    return(dispatch)=>{
        sse.close();
        return{
            type:actionTypes.SSE.CLOSE,
            close:true
        }
    }
}