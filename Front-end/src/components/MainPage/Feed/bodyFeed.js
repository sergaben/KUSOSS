import React, { Component } from 'react';
import { EditorState,convertToRaw,ContentState,Editor,RichUtils,convertFromHTML} from 'draft-js';
import draftToHtml from 'draftjs-to-html';
import '../../../../node_modules/draft-js/dist/Draft.css';
import styles from '../mainPage.css';
import { RaisedButton, MuiThemeProvider } from 'material-ui';
import { callingOnMessageSSE } from '../../../actions/getPostsActions';
import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';
import PostBody from './postBody';
import ReactEventSource from 'react-eventsource';

let valuesAlreadyPresent = false;
let controlArray = [];

class BodyFeed extends Component{
    constructor(props){
        super(props);
        this.state = {
            posts:null,
            key:new Date()
        };
    }  
    
    render(){
        let date;
        let dateMilliseconds;
        date = new Date();
        dateMilliseconds = date.getMilliseconds();
        const subjectForSSE = localStorage.getItem("subject");
        const url =`https://kussos-backend.herokuapp.com/getPosts?subject=${subjectForSSE}`;
        const renderPostBody = event => <PostBody post={event} key={dateMilliseconds}/>

            return(
                <div>
                  <ReactEventSource url={url}>
                    { events => events.map(renderPostBody)}
                  </ReactEventSource>
                </div>
    
            )
        
        
        
    }
}
export default connect()(BodyFeed);