import React,{ Component } from 'react';
import messageContainer from './chat.css';
import Message from './Message';

// const messageContainer = {
//     paddingLeft:'3%',
//     paddingRight:'3%',
// }


class MessageContainer extends Component{
    render(){
        return(
            <div id="chat-message-container" style={messageContainer} className="row">
                    <Message/>
            </div>
        )
    }
}

export default MessageContainer;