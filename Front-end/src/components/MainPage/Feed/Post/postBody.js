import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { EditorState,convertToRaw,ContentState,Editor,RichUtils,convertFromHTML} from 'draft-js';
import htmlToDraft from 'html-to-draftjs';
import '../../../../../node_modules/draft-js/dist/Draft.css';
import styles from '../../mainPage.css';
import _ from 'lodash';
import Parser from 'html-react-parser';
import DeletePostBody from './deletePostButton';

let idOfPosts = [];


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
    handleOnClick = () =>{
        console.log("clicking working");
    }
    render(){
        
        const { post } = this.props;
        if (_.isEmpty(post)){ 
            return <div/>
        }else{
            // console.log(localStorage.getItem("id"));
            // console.log(post.id);
            return(
                <div className={styles.posts}>
                    <h3 style={{'marginBottom':'20px'}}>Nickname: {post.creator_nickname}</h3>
                    <div style={{"padding": "20px","backgroundColor": "white","textAlign":"justify"}}>{Parser(post.content)}</div>
                    <div style={{"display":"flex"}}>
                        <div style={{"width": "auto","marginRight": "60%"}}>
                            <h4 style={{"marginTop":"20px"}}>Subject: {_.capitalize(post.related_subject)}</h4>    
                        </div>
                        <div style={{"width":"auto","marginTop":"2%"}}>
                            {localStorage.getItem("nickname") === post.creator_nickname ? <DeletePostBody onClick={this.handleOnClick()}/> : <span/>}
                        </div>
                    </div>
                </div>
                
            )
        }
        
    }
}

export default PostBody;