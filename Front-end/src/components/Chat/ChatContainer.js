import React, { Component } from 'react';
import {Editor, EditorState} from 'draft-js';
import MessageContainer from './MessageContainer';
import Styles from './chat.css';

class Chat extends Component {
    constructor(props){
        super(props);
        this.state = {editorState: EditorState.createEmpty()};
        this.onChange = (editorState) =>this.setState({editorState});
    }
    render(){
        return(
            <div id="chat-container" className={Styles.chatContainer}>
                <div id="chat-body" className={Styles.chatBody}>
                    <MessageContainer/>
                </div>
                <div id="chat-input" className={Styles.chatInput}>
                    <Editor editorState={this.state.editorState} onChange={this.onChange} className={Styles.chatContainer}/>
                </div>
            </div>
        )
    }
}

export default Chat;