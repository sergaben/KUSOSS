import React, {Component} from 'react';
import { Field,reduxForm } from 'redux-form';
import {FlatButton, Dialog } from 'material-ui';
import { TextField } from 'redux-form-material-ui';
import { browserHistory } from 'react-router';
import Axios from '../../../util/axiosFunction';


class LoginDialog extends Component{
        constructor(){
            super();
            this.state = {
                username:'',
                password:'',
                matchPassword:'',
                error:'',
                authed:false
            }
        }
        onChange = (e) =>{
            const state = this.state;
            // if(e.target.data === )
            state[e.target.name] = e.target.value;
            this.setState(state);
        }
        onSubmit= (e) =>{
            e.preventDefault();
            const { username,matchPassword} = this.state;
            console.log(username);
            const headers = {
                'Content-Type':'application/json'
            }
            // console.log(nickname);
            // console.log(matchPassword);
            Axios('post',true,'login',{username,matchPassword},headers).then((response) => {
                console.log(response);
                if(response.data.status === "OK" && response.data.authenticated === true){
                    // this.mainPage(response.data.nickname);
                   localStorage.setItem("token",response.data.token);
                   localStorage.setItem("nickname",response.data.nickname);
                   localStorage.setItem("subject",response.data.subject);
                   this.mainPage(response);
                }else{
                    this.setState({error:'invalid user credentials'});
                }
            }).catch((error)=>{
                console.log(error);
            })

            const { resetForm } = this.props;

        }


        render(){
            const { open, close,reset } = this.props;
            const formStyle = {
                marginTop:'3%',
                marginRight:'10%',
                marginLeft:'10%'
            }
            const fieldStyle = {
                width:'100%'
            }
            const newstate = {
                nickname:'',
                password:'',
                matchPassword:'',   
            }
            return(
                <Dialog
                    title="Log In"
                    //actions={actionsLogIn}
                    modal={false}
                    open={open}
                    onRequestClose={close}
                >
                    <form onSubmit={this.onSubmit} style={formStyle}>
                        <div>
                            <Field
                                style={fieldStyle}
                                name="username"
                                errorText = {this.state.error !== undefined ? this.state.error : ''}
                                component={TextField}
                                hintText="Nickname"   
                                floatingLabelText="Nickname"
                                onChange={this.onChange}
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
                                onChange={this.onChange}
                                required
                            />
                        </div>
                        <div>
                            <Field 
                                style={fieldStyle}
                                name="matchPassword"
                                component={TextField}
                                type="password"
                                hintText="Password"
                                floatingLabelText="Re-enter Password"
                                onChange={this.onChange}
                                required
                            />
                        </div>
                        <div>
                            <FlatButton
                                label="Cancel"
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
                                label="Reset"
                                secondary={true}
                                disabled={false}
                                onClick={()=>this.printResponse}
                            />
                        </div>
                    </form>
                </Dialog>
            )
        }
        mainPage = (response) =>{
            browserHistory.push({
                pathname: '/main'
                // state:{
                //     nickname: response.data.nickname,
                //     subject: response.data.subject        
                //     }
            });
        }

}



LoginDialog= reduxForm({
    form:'logInForm'
})(LoginDialog) 

export default LoginDialog;