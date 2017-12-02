import React, { Component } from 'react';
import Styles from './circleStyle.css';
import axios from 'axios';
//import { Promise } from 'es6-promise'
// import $ from 'jquery'
class LandingPage extends Component {
    constructor(){
        super()
        //this.handleClick.bind(this.handleClick)
    }
    // componentDidMount(){
    //     $.ajax({
    //         type:'GET',
    //         dataType:'text',
    //         url:'localhost:9000/connectionBetweenBackendFrontend',
    //         success: console.log("the connection is working"),
    //         error:console.log("connection is not working")
    //     })

    // }
   handleClick = () =>{
        axios({
            method:'get',
            url:'https://kussos-backend.herokuapp.com/connectionBetweenBackendFrontend',
           })
        .then(function (response){
            console.log(response);
        })
        .catch(function(error){
            console.log(error);
        })
   }
    render() {
        // let post = {
        //     id:231,
        //     studentId:234,
        //     content:"fajskdf jaksdflkaj ljl",
        //     createdAt: new Date()
        // }
        return (
            <div>
                <section id="app_header" className="jumbotron text-center">
                    <h1>
                        KUSSOS
                    </h1>
                </section>
                <div className="row">
                    <div className="col-sm-7 col-md-4">
                        <div className={Styles.circleMainStyle}>
                            <div className={Styles.wrapContent}>
                                <p>
                                    Create chats depending on the subject that you are doing
                                </p>
                            </div>
                        </div>

                    </div>
                    <div className="col-sm-7 col-md-4" >
                        <div className={Styles.circleMainStyle}>
                            <div className={Styles.wrapContent}>
                                <p>
                                    Create chats depending on the subject that you are doing
                                </p>
                            </div>
                        </div>
                    </div>
                    <div className="col-sm-7 col-md-4">
                        <div className={Styles.circleMainStyle}>
                            <div className={Styles.wrapContent}>
                                <p>
                                    View real-time posts of other students
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="row">
                    <div className="col-sm col-md-4 col-lg-3">
                        <div className={Styles.circleMainStyle}>
                            <div className={Styles.wrapContent}>
                                <p>
                                    Save relevant links that are useful for the future
                                </p>
                            </div>
                        </div>
                    </div>
                    <div className="col-sm col-md-4 col-lg-3">
                        <div className={Styles.circleMainStyle}>
                            <div className={Styles.wrapContent}>
                                <p>
                                    Save relevant links that are useful for the future
                                </p>
                            </div>
                        </div>
                    </div>
                    <div className="col-sm col-md-4 col-lg-3">
                        <div className={Styles.circleMainStyle}>
                            <div className={Styles.wrapContent}>
                                <p>
                                    Save relevant links that are useful for the future
                                </p>
                            </div>
                        </div>
                    </div>
                    <div className="col-sm col-md-4 col-lg-3">
                        <div className={Styles.circleMainStyle}>
                            <div className={Styles.wrapContent}>
                                <p>
                                    Save relevant links that are useful for the future
                                </p>
                            </div>
                        </div>
                    </div>
                    <div className="col-sm col-md-4 col-lg-3">
                        <div className={Styles.circleMainStyle}>
                            <div className={Styles.wrapContent}>
                                <button type="button" className="btn btn-primary" onClick={this.handleClick}>Send data to backend</button>
                            </div>
                        </div>
                </div>
                </div>

            </div>

        )
    }
}

export default LandingPage;