import actionTypes from '../constants/actionTypes';
// console.log("calling this");
let postArray=[];
console.log(postArray);
export function addPostToGlobalArray(post){
    if(!isPostAlreadyInArray(post)){
        postArray.push(post);
       
    }else{
        return{
            type:actionTypes.USER.ADD_POST,
            postArray:[]
        }
    }
    return{
        type:actionTypes.USER.ADD_POST,
        postArray:postArray
    }
    
    
}
export function isPostAlreadyInArray(post){
    if(postArray.includes(post)){
        // console.log(true);
        return true;
    }
    // console.log(false);
    return false;
}