import React, { Component } from 'react';
import { EditorState,convertToRaw,ContentState,Editor,RichUtils} from 'draft-js';
import '../../../../node_modules/draft-js/dist/Draft.css';
import styles from '../mainPage.css';
import { RaisedButton, MuiThemeProvider } from 'material-ui';
// import 'draft-js/dist/Draft.css';

class FeedTimeLine extends Component {
    constructor(props){
        super(props);
        this.state = {
            editorState: EditorState.createEmpty()
        };
        
    }
    onChange = (editorState) => {
        this.setState({editorState});
    }
    handleKeyCommand= (command,editorState) =>{
        const newState = RichUtils.handleKeyCommand(editorState,command);
        if(newState){
            this._onChange(newState);
            return 'handled';
        }
        return 'not-handled';
    }
    render(){
        const { editorState } = this.state;
        const style = {
            margin: 12,
          };
        const raw = convertToRaw(editorState.getCurrentContent());
        console.log(raw);
        return(
            <MuiThemeProvider>
                <div id="content">
                    
                    <div className={styles.editor}>
                        <Editor
                            placeholder="type something"
                            editorState={editorState}
                            onChange={(editorState)=>{this.onChange(editorState)}}
                            handleKeyCommand={this.handleKeyCommand}
                        />
                    </div> 
                    <RaisedButton label="Publish" secondary={true} style={style}/>
                </div>
                
            </MuiThemeProvider>
        )
    }
}


export default FeedTimeLine;