import React, {Component} from 'react';
import { Field,reduxForm } from 'redux-form';
import { TextField, FlatButton, Dialog } from 'material-ui';
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
        onSubmit = (e) =>{
            e.preventDefault();
            const { nickname,matchPassword} = this.state;
            Axios('post',true,'login',{password,matchPassword})
        }
        render(){
            const { open, close } = this.props;
            const formStyle = {
                marginTop:'3%',
                marginRight:'10%',
                marginLeft:'10%'
            }
            const fieldStyle = {
                width:'100%'
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
                    modal={true}
                    open={open}
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



}

LoginDialog= reduxForm({
    form:'logInForm'
})(LoginDialog) 

export default LoginDialog;