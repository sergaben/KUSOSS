import React, {Component} from 'react';
import BodyFeed from './bodyFeed';
import  InputFeed  from './inputFeed';

class Feed extends Component{
    
    render(){
        return(
            <div>
                <InputFeed/>
                <BodyFeed/>
            </div>
        )
    }
}

export default Feed;