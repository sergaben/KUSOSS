import React, { Component } from 'react';
import { EditorState } from 'draft-js';
import styles from '../mainPage.css';
import Editor from 'draft-js-editor';
import 'draft-js/dist/Draft.css';

class FeedTimeLine extends Component {
    constructor(props){
        super(props);
        this.state = {
            editorState: EditorState.createEmpty()
        };
        
    }
    _onChange = (editorState) => {
        this.setState({editorState});
    }
    render(){
        return(
            <div id="content">
                <h1>Write a post</h1>
                <div className={styles.editor}>
                    <Editor
                        placeholder="Share your thoughts :D"
                        editorState={this.state.editorState}
                        onChange={this._onChange}
                    />
                </div>
                <div>
                    
                </div>
            </div>
        )
    }
}


export default FeedTimeLine;