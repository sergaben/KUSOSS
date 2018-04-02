import axios from 'axios';

function generateURL(prodMode, url){
    if(!prodMode){
        return `localhost:9000/${url}`;
    }
    return `https://kussos-backend.herokuapp.com/${url}`;
}

export default function(type,mode,url,data = {},headers = {}){
    axios({
        method:type,
        url:generateURL(mode,url),
        data,
        headers,
    }).then((response)=>{
        console.log(response);
    }).catch((error)=>{
        console.log(error);
    })
}