import React, {Component} from 'react';
import Dialog from 'material-ui/Dialog';
import { Field,reduxForm,SubmissionError } from 'redux-form';
import {FlatButton} from 'material-ui';
import {AutoComplete as MUIAutoComplete} from 'material-ui';
import {TextField,AutoComplete} from 'redux-form-material-ui';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { signUp } from '../../../../actions/signUpDialogActions';
import { getSubjects } from '../../../../actions/subjectActions';
import Errors from '../Errors';
import Messages from '../Messages';
import { typeOfStudyArray } from '../../../../constants/generalConstants';



const renderTextField = (props) => (
    <TextField 
      hintText={props.label}
      floatingLabelText={props.floatinglabel}
      errorText={props.touched && props.error }
      {...props}
    />
  );

class SignUpDialog extends Component{
        constructor(){
            super();
            this.state = {
                nickname:'',
                email:'',
                password:'',
                reEnterPassword:'',
                subjectFromAPI:[],
                subject:'',
                typeOfStudy:'',
                signUpSuccess:'',
                successful:false,
                requesting:false,
                messagesForForm:[],
                errorsFromServer:[],
                visible:false
            }
        }
        
        componentDidMount(){
            this.props.getSubjects().then(()=>{
                // console.log(this.props.subjects);
                let responseAsArray = this.props.subjects.map((subject)=>subject.name);
                this.setState({subjectFromAPI:responseAsArray});
            }); 
        }
        
        handleOnSubmit = ({nickname,email,password, reEnterPassword,subject,typeOfStudy}) =>{

            const { reset } = this.props;
            let error ={};
            let isError = false;
         
            if(isError){
                throw new SubmissionError(error);
            } else {
         
                this.props.signUp(nickname,email,password,subject,typeOfStudy).then(()=>{
                    // console.log('data from server in submit button',this.props.signUpData);

                    if(this.props.signUpData.successful){
                        this.setState({
                            ...this.state,
                            successful: this.props.signUpData.successful,
                            messagesForForm:[{
                                body:`You have successfully signed up, ${nickname}`,
                                time:new Date()
                            }],
                            visible:true
                         });
                         reset();
                    }else{
                        this.setState({
                            ...this.state,
                            successful: false,
                            errorsFromServer:[{
                                body:`There is somethig wrong with the data you supplied, maybe nickname or email is taken`,
                                time:new Date()
                            }],
                            visible:false
                         });  
                    }
                   
                }).catch((error)=>{
                    console.log(error);
                });
            }
        }
        
        handleOnClose = () =>{
            const { close } = this.props;
            this.setState({
                ...this.state,
                visible:false
            });
            return close();
        }

        render() {
            const { handleSubmit, open, close,reset, signUpData, submitting}=this.props;
            const { subjectFromAPI,messagesForForm,successful,errorsFromServer, visible}=this.state;
            const formStyle={
                marginTop:'3%',
                marginRight:'10%',
                marginLeft:'10%'
            }
            const fieldStyle={
                width:'100%'
            }
            return(
                
                <Dialog
                    title="Sign Up"
                    modal={false}
                    open={open}
                    onRequestClose={close}
                >
                {visible && successful && (<Messages messages={messagesForForm}/>) }
                {visible && !successful && (<Errors errors={errorsFromServer}/>)}
                    <form onSubmit={handleSubmit(this.handleOnSubmit)} style={formStyle}>
                    
                        <div>
                            <Field
                                style={fieldStyle}
                                name="nickname"
                                component={renderTextField}
                                label="Nickname"   
                                floatinglabel="Nickname*"
                                required
                                />
                        </div>
                        <div>
                            <Field 
                                style={fieldStyle}
                                name="email"
                                component={renderTextField}
                                type="email"
                                label="Email"
                                floatinglabel="Email*"
                                required
                                />
                        </div>
                        <div>
                            <Field 
                                style={fieldStyle}
                                name="password"
                                component={renderTextField}
                                type="password"
                                label="Password"
                                floatinglabel="Password*"
                                required
                            />
                        </div>
                        <div>
                            <Field 
                                style={fieldStyle}
                                name="reEnterPassword"
                                component={renderTextField}
                                type="password"
                                label="Re-enter password"
                                floatinglabel="Re-enter Password*"
                                required
                            />
                        </div>
                        <div>
                            <Field
                                style={fieldStyle}
                                name="subject"
                                component={AutoComplete}
                                hintText="Subject"
                                dataSource={subjectFromAPI}
                                filter={MUIAutoComplete.fuzzyFilter}
                                floatingLabelText="Subject*"
                                fullWidth={true}
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
                                filter={MUIAutoComplete.fuzzyFilter}
                                dataSource={typeOfStudyArray}
                                fullWidth={true}
                                required
                            >      
                            </Field>
                        </div>
                        <div>
                            <FlatButton
                                label="Close"
                                primary={true}
                                onClick={this.handleOnClose}
                            />
                            <FlatButton
                                label="Sign Up"
                                primary={true}
                                disabled={submitting}
                                type="submit"
                            />
                            <FlatButton
                                label="Clear Values"
                                secondary={true}
                                disabled={false}
                                onClick={reset}
                            />
                        </div>
                    </form>
                    
                </Dialog>
            )
        }
}

const mapStateProps = ({signUpReducer, subjectReducer}) => ({
    signUpData: signUpReducer.data,
    subjects: subjectReducer.data
});

const mapDispatchToProps = (dispatch) =>bindActionCreators({getSubjects,signUp},dispatch);

SignUpDialog = connect(
    mapStateProps,
    mapDispatchToProps
)(SignUpDialog);

export default reduxForm({
    form:'signUpForm',
})(SignUpDialog) ;