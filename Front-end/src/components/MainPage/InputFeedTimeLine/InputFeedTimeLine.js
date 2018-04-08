import React, { Component } from 'react';
import { EditorState } from 'draft-js';
import styles from '../mainPage.css';
import Editor from 'draft-js-editor';
import { RaisedButton, MuiThemeProvider } from 'material-ui';
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
        const style = {
            margin: 12,
          };
        return(
            <MuiThemeProvider>
            <div id="content">
                <h1>Write a post</h1>
                <div className={styles.editor}>
                    <Editor
                        placeholder="Share your thoughts :D"
                        editorState={this.state.editorState}
                        onChange={this._onChange}
                    />
                    
                </div> 
            </div>
            <RaisedButton label="Publish" secondary={true} style={style}/>
            </MuiThemeProvider>
        )
    }
}


export default FeedTimeLine;