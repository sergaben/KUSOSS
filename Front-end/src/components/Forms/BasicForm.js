import React from 'react';
import { Field,reduxForm } from 'redux-form';
import Styles from './forms.css';

const BasicForm = props =>{
    const { handleSubmit, pristine, reset, submitting } = props;
    return(
    <div>
        <form onSubmit={handleSubmit} style={{marginTop:'3%'}}>
            <div>
                <label>Nickname</label>
                <div className={Styles.formGroup}>
                    <Field className="form-control"
                        name="nickname"
                        component="input"
                        type="text"
                        placeholder="Nickname"
                    />
                </div>
            </div>
            <div >
                <label>Email</label>
                <div  className={Styles.formGroup}>
                    <Field className="form-control"
                        name="email"
                        component="input"
                        type="email"
                        placeholder="Email"
                    />
                </div>
            </div>
            <div>
                <label>Password</label>
                <div className={Styles.formGroup}>
                    <Field className="form-control"
                        name="password"
                        component="input"
                        type="password"
                        placeholder="Password"
                    />
                </div>
            </div>
            <div>
                <label>Subject</label>
                <div className={Styles.formGroup}>
                    <Field className="form-control"
                        name="subject"
                        component="input"
                        type="text"
                        placeholder="Subject"
                    />
                </div>
            </div>
            <div>
                <label>Type of study</label>
                <div className={Styles.formGroup}>
                    <Field className="form-control"
                        name="typeOfStudy"
                        component="input"
                        type="text"
                        placeholder="Type of study"
                    />
                </div>
            </div>
            <div>
                <button className="btn btn-success"  style={{marginRight:'3%'}} data-toggle="modal" data-target="#successfulModal" type="button" disabled={pristine || submitting}>Submit</button>
                <button className="btn btn-danger" type="button" disabled={pristine || submitting} onClick={reset}>Clear Values</button>
            </div>
        </form>
        

        
    </div>
    )

}


export default reduxForm({form:'basic'})(BasicForm);