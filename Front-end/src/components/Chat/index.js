import React, { Component } from 'react';
import { connect } from 'react-redux'
//import ChatContainer from './ChatContainer';
import Launcher from 'react-chat-window';

class Chat extends Component {
  
  // constructor() {
  //   super();
  //   this.state = {
  //     messageList: messageHistory
  //   };
  // }

  // _onMessageWasSent(message) {
  //   this.setState({
  //     messageList: [...this.state.messageList, message]
  //   })
  // }

  // _sendMessage(text) {
  //   if (text.length > 0) {
  //     this.setState({
  //       messageList: [...this.state.messageList, {
  //         author: 'them',
  //         type: 'text',
  //         data: { text }
  //       }]
  //     })
  //   }
  // }
  render() {
    return (
      <div>
        <Launcher
/>
      </div>
      // <div id="chat-container" className="container-fluid" >
      //   <div className="row">
      //       <div className="col-sm col-md-6 col-lg-7">
            
      //       </div>
      //       <div className="col-sm col-md-6 col-lg-7">
            
      //       </div>
      //       <div className="col-sm col-md-6 col-lg-7">
            
      //       </div>
      //   </div>
      //   <div className="row">
            
      //       <div className="col-sm-12 col-md-6 col-lg-6">
      //           <ChatContainer/>
      //       </div>
            
      //   </div>
      //   <div className="row">
      //       <div className="col-sm col-md-6 col-lg-7">
            
      //       </div>
      //       <div className="col-sm col-md-6 col-lg-7">
            
      //       </div>
      //       <div className="col-sm col-md-6 col-lg-7">
            
      //       </div>
      //   </div>
      // </div>
    )
  }
}

export default connect()(Chat);
