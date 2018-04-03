import React, {Component} from 'react';
import Dialog from 'material-ui/Dialog';
import { Field,reduxForm } from 'redux-form';
import {FlatButton,AutoComplete} from 'material-ui';
import {TextField, SelectField} from 'redux-form-material-ui';
import MenuItem from 'material-ui/MenuItem';
import axios from 'axios';
import Axios from '../../../util/axiosFunction';
class LoginDialog extends Component{
        constructor(){
            super();
            this.state = {
                nickname:'',
                email:'',
                password:'',
                subjectFromAPI:[],
                typeOfStudyArray:[
                    "UNDERGRADUATE",
                    "POSTGRADUATE",
                    "PHD",
                    "FOUNDATION"
                ],
                subject:'',
                typeOfStudy:''
            }
        }
        componentDidMount(){
            axios({
                method:'get',
                url:'https://kussos-backend.herokuapp.com/getSubjectsNamesAsJson'
            }).then((response)=>{
                //console.log(response.data);
                let responseAsArray = response.data.map(function(obj){
                    return obj.name;
                })
                console.log(responseAsArray);
                this.setState(()=>{
                    return{
                        subjectFromAPI:responseAsArray
                    }
                })
            }).catch((error)=>{
                console.log(error);
            })
        }
        onChange = (e) =>{
            const state = this.state;
            state[e.target.name] = e.target.value;
            this.setState(state);
        }
        onSubmit = (e) =>{
            e.preventDefault();
            const { nickname,email,password,subject,typeOfStudy} = this.state;
            let from_Kingston = true;
            Axios('post',true,'signup',{ nickname, email, password,subject,typeOfStudy,from_Kingston});
        }
        handleUpdateInputSubject = (inputValue) =>{
            const self = this;
            this.setState({
                subject: inputValue
            });
        }
        handleUpdateInputTypeOfStudy = (inputValue) =>{
            const self = this;
            this.setState({
                typeOfStudy: inputValue
            });
        }
        render(){
            const { handleSubmit, open, close } = this.props;
            const { nickname,email,password,subjectFromAPI,typeOfStudyArray } = this.state;
            // console.log(subject);
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
                    title="Sign Up"
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
                                floatingLabelText="Nickname*"
                                onChange={this.onChange}
                                required
                                />
                        </div>
                        <div>
                            <Field 
                                style={fieldStyle}
                                name="email"
                                component={TextField}
                                type="email"
                                hintText="Email"
                                floatingLabelText="Email*"
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
                                floatingLabelText="Password*"
                                onChange={this.onChange}
                                required
                            />
                        </div>
                        <div>
                            <Field
                                style = {fieldStyle}
                                name="subject"
                                component={AutoComplete}
                                hintText = "Subject"
                                dataSource = {this.state.subjectFromAPI}
                                filter = {AutoComplete.fuzzyFilter}
                                // onUpdateInput = {this.handleUpdateInput}
                                floatingLabelText = "Subject*"
                                fullWidth = {true}
                                onUpdateInput = {this.handleUpdateInputSubject}
                                required
                            />
                            
                        </div>
                        <div>
                            <Field
                                style={fieldStyle}
                                name="typeOfStudy"
                                component={AutoComplete}
                                hintText="Type of Study"
                                floatingLabelText="Type of Study*"
                                filter = {AutoComplete.fuzzyFilter}
                                dataSource = {this.state.typeOfStudyArray}
                                fullWidth = {true}
                                onUpdateInput = {this.handleUpdateInputTypeOfStudy}
                                required
                            >      
                            </Field>
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
                                //onClick={this.handleCloseSignUp}
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