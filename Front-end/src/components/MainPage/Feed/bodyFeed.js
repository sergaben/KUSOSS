import React, { Component } from 'react';
import { EditorState,convertToRaw,ContentState,Editor,RichUtils,convertFromHTML} from 'draft-js';
import draftToHtml from 'draftjs-to-html';
import '../../../../node_modules/draft-js/dist/Draft.css';
import styles from '../mainPage.css';
import { RaisedButton, MuiThemeProvider } from 'material-ui';
import { getPosts } from '../../../actions/getPostsActions';

class BodyFeed extends Component{
    constructor(props){
        super(props);
        this.state = {
            editorState: EditorState.createEmpty()
        };
    }
    componentDidMount(){
        const subject = localStorage.getItem("subject");
        this.props.getPosts(subject).then(()=>{
            console.log(this.props.bodyFeedData);
        });
    }

    render(){
        const { editorState } = this.state;
        return(
            <div className={styles.posts}>
                <Editor
                    readOnly={true}
                    editorState={editorState}
                />
            </div>
        )
    }
}
const mapStateToProps=({getPostsReducer}) =>({
    bodyFeedData:getPostsReducer
});

const mapDispatchToProps=(dispatch) =>bindActionCreators({getPosts},dispatch);

export default connect(mapStateToProps,mapDispatchToProps)(BodyFeed);