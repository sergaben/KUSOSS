import React from 'react';

const Errors = (props) =>{
    const {errors} = props;
    const errorSignUp = {
        color: 'red',
        textAlign:'center'
    } 
    return(
        <div>
                {
                    errors.map(error => {
                        return (
                            <p style={errorSignUp} key={error.time}>{error.body}</p> 
                        );
                    })
                }
        </div>
    )
}

export default Errors;