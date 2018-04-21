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
import { uniqBy} from 'lodash';
import _ from 'lodash';

let valuesAlreadyPresent = false;


class BodyFeed extends Component{
    constructor(props){
        super(props);
        this.state = {
            posts:null,
            key:new Date()
        };
    }  
    
    render(){
        let postsArr = [];
        const subjectForSSE = localStorage.getItem("subject");
        const url =`https://kussos-backend.herokuapp.com/getPosts?subject=${subjectForSSE}`;
        const renderPostBody = event => <PostBody post={event} key={event.id}/>

            return(
                <div>
                  <ReactEventSource url={url}>
                    { events => { 
                        postsArr = [...postsArr, ...events];
                        const test = postsArr.map((e) => JSON.parse(e));
                        const posts = uniqBy(test, post => post.id);
                        const reversePosts = _.reverse(posts);
                        return reversePosts.map(renderPostBody)}
                    }
                  </ReactEventSource>
                </div>
    
            )
        
        
        
    }
}
export default connect()(BodyFeed);