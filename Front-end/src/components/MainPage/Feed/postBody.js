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
        console.log();
        if (_.isEmpty(post)){ 
            return <div/>
        }else{
            return(
                <div className={styles.posts}>
                    <h3>{post.creator_nickname}</h3>
                    {Parser(post.content)}
                    <p>{_.capitalize(post.related_subject)}</p>
                </div>
                
            )
        }
        
    }
}

export default PostBody;