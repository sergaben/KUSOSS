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
                nickname:'',
                password:'',
                matchPassword:'',   
            }
        }
        
        onChange = (e) =>{
            const state = this.state;
            state[e.target.name] = e.target.value;
            this.setState(state);
        }
       
        onSubmit= (e) =>{
            e.preventDefault();
            const { nickname,matchPassword} = this.state;
            const headers = {
                'Content-Type':'application/json'
            }
            Axios('post',true,'login',{nickname,matchPassword},headers);
            const { resetForm } = this.props;
            resetForm();
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
            // const kuStudent = {
            //     "nickname":"Sergio",
            //     "password":"fjadkfjklsd",
            //     "email":"fasdf@Fadsfsd.com",
            //     "subject":"fjksladfl",
            //     "typeOfStudy":"fasfafsfasdfsdf"
            // }
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
                                name="nickname"
                                component={TextField}
                                hintText="Nickname"   
                                floatingLabelText="Nickname"
                                onChange={this.onChange}
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
                            />
                        </div>
                        <div>
                            <FlatButton
                                label="Cancel"
                                primary={true}
                                onClick={close}
                            />
                            <FlatButton
                                label="Submit"
                                primary={true}
                                disabled={false}
                                type="submit"
                                // onClick={this.mainPage}
                                // onClick={(e)=>(Axios('POST',true,'login',kuStudent))}
                            />
                            <FlatButton
                                label="Reset"
                                secondary={true}
                                disabled={false}
                            
                            />
                        </div>
                    </form>
                </Dialog>
            )
        }

        // mainPage = () =>{
        //     browserHistory.push('/main');
        // }

}



LoginDialog= reduxForm({
    form:'logInForm'
})(LoginDialog) 

export default LoginDialog;