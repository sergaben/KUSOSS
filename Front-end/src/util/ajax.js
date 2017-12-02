import { Promise } from 'es6-promise'
import $ from 'jquery'

function generateUrl(url) {
  //return `localhost:9000/${url}`;
  return `https://kussos-backend.herokuapp.com/${url}`;
}

export default function(url, type, options = {}) {
  return new Promise((resolved, rejected) => {
    $.ajax({
      type: type,
      url: generateUrl(url),
      data: options,
      success: (data) =>{ resolved(JSON.parse(data))},
      error: (data) => rejected(data)
    });
  });
}