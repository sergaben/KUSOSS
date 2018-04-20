import React, { Component } from 'react';
import { EditorState,convertToRaw,ContentState,Editor,RichUtils,convertFromHTML} from 'draft-js';
import draftToHtml from 'draftjs-to-html';
import '../../../../node_modules/draft-js/dist/Draft.css';
import styles from '../mainPage.css';
import { RaisedButton, MuiThemeProvider } from 'material-ui';
// import { getPosts } from '../../../actions/getPostsActions';
import { establishConnection } from '../../../actions/establishSSEConnectionAction';
import { addPostToGlobalArray  } from '../../../actions/addPostAction';
import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';
import PostBody from './postBody';

let valuesAlreadyPresent = false;
let controlArray = [];

class BodyFeed extends Component{
    constructor(props){
        super(props);
        this.state = {
            posts:null,
            key:new Date()
        };
        this.componentWillReceiveProps = null;
    }
    componentDidMount(){
        const subject = localStorage.getItem("subject");
        const url =`https://kussos-backend.herokuapp.com/getPosts?subject=${subject}`;

        this.props.connectToSSE(url);
    }
    static getDerivedStateFromProps(nextProps,prevState){
        let self = this;
       
        if(nextProps.post !== undefined){
            
                controlArray.push(nextProps.post);
               
                return{
                    posts:controlArray,
                }

            return null;
        }
        return null;
    }
    
    addPost= (post) =>{
        let updated = this.state.posts;

            updated.push(post);
            this.setState({
                posts:updated,
                counter:this.state.counter + 1
            });
        
        
    }
    
    render(){
        let newArrayOfPostObjects = [];
        let counter=0;
        const { posts } = this.state;
        let date;
        let dateMilliseconds;
        date = new Date();
        dateMilliseconds = date.getMilliseconds();

        const arrayOfObjects = Object.keys(posts).map((key) => posts[key]);
            return(
                <div>
                 { arrayOfObjects.map((post) =>{
                    // if(post == null) return <div/>

                    return <PostBody
                        post={post}
                        key={new Date()}
                        />  
                 })

                 }
                </div>
    
            )
        
        
        
    }
}
const mapStateToProps=({getPostsReducer,addPostReducer}) =>{
    return{
        post:getPostsReducer,
        postArray:addPostReducer
    }
};

const mapDispatchToProps=(dispatch) =>{
    return{
        connectToSSE: (url) => dispatch(establishConnection(url)),
        addPostToArray: (post) => dispatch(addPostToGlobalArray(post))
    };
};

export default connect(mapStateToProps,mapDispatchToProps)(BodyFeed);