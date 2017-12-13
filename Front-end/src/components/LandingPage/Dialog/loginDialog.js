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
                
            }
        }
        
        render(){
            const { handleSubmit, open, close } = this.props;
            const formStyle = {
                marginTop:'3%',
                marginRight:'10%',
                marginLeft:'10%'
            }
            const fieldStyle = {
                width:'100%'
            }
            const kuStudent = {
                "nickname":"Sergio",
                "password":"fjadkfjklsd",
                "email":"fasdf@Fadsfsd.com",
                "subject":"fjksladfl",
                "typeOfStudy":"fasfafsfasdfsdf"
            }
            return(
                <Dialog
                    title="Log In"
                    //actions={actionsLogIn}
                    modal={true}
                    open={open}
                >
                    <form onSubmit={handleSubmit} style={formStyle}>
                        <div>
                            <Field
                                style={fieldStyle}
                                name="nickname"
                                component={TextField}
                                hintText="Nickname"   
                                floatingLabelText="Nickname"
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
                                onClick={(e)=>(Axios('POST',true,'registerKStudents',kuStudent))}
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