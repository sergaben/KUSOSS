import React, {Component} from 'react';
import { Field,reduxForm } from 'redux-form';
import { connect } from 'react-redux';
import {FlatButton, Dialog } from 'material-ui';
import { TextField } from 'redux-form-material-ui';
import { bindActionCreators } from 'redux';
import { browserHistory } from 'react-router';
import { logIn } from '../../../../actions/logInDialogActions';


class LoginDialog extends Component{
        constructor(){
            super();
            this.state = {
                username:'',
                password:'',
                error:'',
            }
        }
        
        handleOnSubmit = ({username,password}) =>{
            this.props.logIn(username,password).then(()=>{
               
                if(this.props.logInData.status === "OK" && this.props.logInData.authenticated === true){
                   localStorage.setItem("token",this.props.logInData.token);
                   localStorage.setItem("nickname",this.props.logInData.nickname);
                   localStorage.setItem("subject",this.props.logInData.subject);
                   this.mainPage();
                }else{
                    this.setState({error:'invalid user credentials'});
                }
            }).catch((error)=>{
                console.log(error);
            });
        }
        
        render(){
            const { open, close,reset, handleSubmit } = this.props;
            const formStyle = {
                marginTop:'3%',
                marginRight:'10%',
                marginLeft:'10%'
            }
            const fieldStyle = {
                width:'100%'
            }
            return(
                <Dialog
                    title="Log In"
                    modal={false}
                    open={open}
                    onRequestClose={close}
                >
                    <form onSubmit={handleSubmit(this.handleOnSubmit)} style={formStyle}>
                        <div>
                            <Field
                                style={fieldStyle}
                                name="username"
                                errorText={this.state.error !== undefined ? this.state.error : ''}
                                component={TextField}
                                hintText="Nickname"   
                                floatingLabelText="Nickname"
                                required
                                />
                        </div>
                        <div>
                            <Field 
                                style={fieldStyle}
                                name="password"
                                component={TextField}
                                type="password"
                                hintText="Password"
                                floatingLabelText="Password"
                                required
                            />
                        </div>
                        <div>
                            <FlatButton
                                label="Close"
                                primary={true}
                                onClick={close}
                            />
                            <FlatButton
                                label="Log In"
                                primary={true}
                                disabled={false}
                                type="submit"
                            />
                            <FlatButton
                                label="Clear values"
                                secondary={true}
                                disabled={false}
                                onClick={reset}
                            />
                        </div>
                    </form>
                </Dialog>
            )
        }
        
        mainPage = () =>{
            browserHistory.push({
                pathname: '/main'
            });
        }

}

const mapStateToProps=({logInReducer}) =>({
    logInData:logInReducer.data
});

const mapDispatchToProps=(dispatch) =>bindActionCreators({logIn},dispatch);

LoginDialog= connect(
    mapStateToProps,
    mapDispatchToProps
)(LoginDialog) 

export default reduxForm({
    form:'logInForm'
})(LoginDialog);