import React from 'react';

const Messages = (props) =>{
    const { messages } = props;
    const successSignUp = {
        color: 'green',
        textAlign:'center'
    }    
    // let println = console.log(props.messages[0].body);
    return(
        <div>
                {
                    messages.map(message => {
                        return (
                            <p style={successSignUp} key={message.time}>{message.body}</p> 
                        );
                    })
                }
        </div>
    );
};

export default Messages;