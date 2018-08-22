import React, { Component } from 'react';
import Styles from './chat.css';
class Message extends Component {
    render(){
        return(
            <div id="msg" className={Styles.messageStyle}>
                <div>
                    <span id="author"> Sergio </span><br/>
                </div>
                <div>
                    <span id="text"> This is the message </span><br/>
                </div>
                <div>
                    <span id="meta-data"> 21:41 PM </span>           
                </div>
            </div>
        )
    }
}

export default Message;