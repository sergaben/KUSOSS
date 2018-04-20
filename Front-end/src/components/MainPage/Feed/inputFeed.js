import React, { Component } from 'react';
import { EditorState,convertToRaw,ContentState,Editor,RichUtils,convertFromHTML} from 'draft-js';
import draftToHtml from 'draftjs-to-html';
import '../../../../node_modules/draft-js/dist/Draft.css';
import styles from '../mainPage.css';
import { RaisedButton, MuiThemeProvider } from 'material-ui';
import { savePost } from '../../../actions/savePostActions';
import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';
import moment from 'moment';
import Alert from 'react-s-alert';

class InputFeed extends Component {
    constructor(props){
        super(props);
        this.state = {
            editorState: EditorState.createEmpty()
        };
        
    }
    onChange = (editorState) => {
        this.setState({editorState});
    }
    handleOnClick = (e) => {
        e.preventDefault();
        const rawContentState = convertToRaw(this.state.editorState.getCurrentContent());
        const dateCreatedNoUtc = moment(new Date());
        const content = draftToHtml(rawContentState);
        const created_at = dateCreatedNoUtc.utc().format("DD-MM-YYYY HH:mm:ss");
        const created_by = parseInt(localStorage.getItem("id"));
        const creator_nickname = localStorage.getItem("nickname");
        const related_subject = localStorage.getItem("subject");
        // console.log(content, createdAt, createdBy, creatorNickname, relatedSubject);
        this.props.savePost(content,created_at,created_by,creator_nickname,related_subject).then(()=>{
            // console.log(this.props.inputFeedData);
            if(this.props.inputFeedData.data.status === "OK" && this.props.inputFeedData.data.saved === true){
                Alert.success('Your post has been published',{
                    position:'bottom',
                    effect:'genie',
                    beep:false,
                    timeout:2000,
                    offset:0
                })
                this.setState({editorState:EditorState.createEmpty()});
            }
            
        });

    }
    handleKeyCommand= (command,editorState) =>{
        const newState = RichUtils.handleKeyCommand(editorState,command);
        if(newState){
            this.onChange(newState);
            return 'handled';
        }
        return 'not-handled';
    }
    render(){
        const { editorState } = this.state;
        const style = {
            margin: 12,
          };
        return(
            <MuiThemeProvider>
                <div id="content">
                    
                    <div className={styles.editor}>
                        <Editor
                           
                            editorState={editorState}
                            onChange={(editorState)=>{this.onChange(editorState)}}
                            handleKeyCommand={this.handleKeyCommand}
                        />
                    </div> 
                    <RaisedButton 
                        label="Publish" 
                        secondary={true} 
                        style={style} 
                        onClick={this.handleOnClick} 
                        disabled={this.state.editorState.getCurrentContent().hasText() ? false : true}/>
                </div>
                   
            </MuiThemeProvider>
        )
    }
}
const mapStateToProps=({savePostReducer}) =>({
    inputFeedData:savePostReducer
});

const mapDispatchToProps=(dispatch) =>bindActionCreators({savePost},dispatch);

export default connect(mapStateToProps,mapDispatchToProps)(InputFeed);