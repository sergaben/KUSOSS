import axios from 'axios';

function generateURL(prodMode, url){
    if(!prodMode){
        return `localhost:9000/${url}`;
    }
    return `https://kussos-backend.herokuapp.com/${url}`;
}

export default function(type,mode,url,data = {}){
    axios({
        method:type,
        url:generateURL(mode,url),
        data,
       })
    .then(function (response){
        console.log("This works pretty well");
    })
    .catch(function(error){
        console.log("This is throwing an error");
    })
}