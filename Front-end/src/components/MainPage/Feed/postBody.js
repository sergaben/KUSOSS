import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { EditorState,convertToRaw,ContentState,Editor,RichUtils,convertFromHTML} from 'draft-js';
import htmlToDraft from 'html-to-draftjs';
import '../../../../node_modules/draft-js/dist/Draft.css';
import styles from '../mainPage.css';
import _ from 'lodash';
import Parser from 'html-react-parser';
class PostBody extends Component{

    static propTypes = {
        post: PropTypes.any.isRequired
    }

    constructor(props){
        super(props);
        this.state = {
            editorState: EditorState.createEmpty()
        };
        
    }

    render(){
        
        const { post } = this.props;
        let postAsJson = JSON.parse(post);
        console.log(postAsJson);
        if (_.isEmpty(postAsJson)){ 
            return <div/>
        }else{
            return(
                <div className={styles.posts}>
                    <h3 style={{'marginBottom':'20px'}}>Nickname: {postAsJson.creator_nickname}</h3>
                    <div style={{"padding": "20px","backgroundColor": "white","textAlign":"justify"}}>{Parser(postAsJson.content)}</div>
                    <h4 style={{"marginTop":"20px"}}>Subject: {_.capitalize(postAsJson.related_subject)}</h4>
                </div>
                
            )
        }
        
    }
}

export default PostBody;