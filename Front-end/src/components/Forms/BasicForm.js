import React, {Component} from 'react';
//import {connect} from 'react-redux';
import { Field,reduxForm } from 'redux-form';
//import Styles from './forms.css';
//import { FlatButton } from 'material-ui';
import {TextField, SelectField} from 'redux-form-material-ui';
import MenuItem from 'material-ui/MenuItem';

class BasicForm extends Component{
    // componentDidMount(){
    //     this.refs.name
    //         .getRenderedComponent()
    //         .getRenderedComponent()
    //         .focus();
    // }
    render(){
        const { handleSubmit} = this.props;
        const formStyle = {
            marginTop:'3%',
            marginRight:'10%',
            marginLeft:'10%'
        }
        const fieldStyle = {
            width:'100%'
        }
        return(
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
                        name="email"
                        component={TextField}
                        hintText="Email"
                        floatingLabelText="Email"
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
                        name="subject"
                        component={SelectField}
                        hintText="Subject"
                        floatingLabelText="Subject"
                    >
                        <MenuItem value="Computer Science" primaryText="Computer Science"/>
                        <MenuItem value="Medicine" primaryText="Medicine"/>
                        <MenuItem value="Mathematics" primaryText="Mathematics"/>

                    </Field>
                </div>
                <div>
                    <Field
                        style={fieldStyle}
                        name="typeOfStudy"
                        component={SelectField}
                        hintText="Type of Study"
                        floatingLabelText="Type of Study"
                    >
                        <MenuItem value="undergraduate" primaryText="Undergraduate"/>
                        <MenuItem value="postgraduate" primaryText="Postgraduate"/>
                        <MenuItem value="PhD" primaryText="PhD"/>
                        <MenuItem value="foundation" primaryText="Foundation"/>
                    </Field>
                </div>
                
            </form>

        )
    }
}

BasicForm = reduxForm({
    form:'basicForm'
})(BasicForm) 

export default BasicForm;