import React, { Component } from 'react';
import customStyles from './landingPage.css';
import { textCenter } from '../../../node_modules/bootstrap-css-modules/css/text.css';
import classNames from 'classnames';
import BasicForm from '../Forms/BasicForm';
import handleResults from '../Forms/handleResults';
import axios from 'axios';
import chat from '../../../public/images/chat.jpg';
import subject from '../../../public/images/subjects.jpg';
import realTime from '../../../public/images/RealTime.jpg';
import usefulLinks from '../../../public/images/links.png';
//import { ajax } from '../../util/ajax';
//import { Promise } from 'es6-promise'
// import $ from 'jquery'
class LandingPage extends Component {
    constructor(){
        super()
        this.handleClick.bind(this.handleClick)
        this.sectionStyle.bind(this.sectionStyle)
    }
    handleClick = (url,type,data) => {    

        //const urlLocalhost = `localhost:9000/${url}`
        const urlRealServer = `https://kussos-backend.herokuapp.com/${url}`

        axios({
            method:type,
            url:urlRealServer,
            data,
            //headers:{'Access-Control-Allow-Origin':'*'}
           })
        .then(function (response){
            console.log(response);
        })
        .catch(function(error){
            console.log(error);
        })
    }
    sectionStyle= (url) => {
        let style = {
            backgroundImage:`url(${url})`,
            backgroundRepeat: 'no-repeat',
            backgroundSize: 'contain',
            padding:'12%'
        }
        return style
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

        // axios({
        //     method:'get',
        //     url:'https://kussos-backend.herokuapp.com/connectionBetweenBackendFrontend',
        //    })
        // .then(function (response){
        //     console.log(response);
        // })
        // .catch(function(error){
        //     console.log(error);
        // })
   
    render() {
        // let student = {
        //     "nickname":"Sergio",
        //     "password":"fasdfsdf",
        //     "email":"alkdfjkl@fasdf.com",
        //     "subject":"fjksladfjl",
        //     "typeOfStudy":"fasdfsadf"
        // }
        
        let oneStyle = {
            display:'table'
        }
        let twoStyle = {
            display:'table-cell',
            verticalAlign:'middle'
        }
        let threeStyle = {
            textAlign:'center',
        }
        let imgStyle = {
            maxWidth:'100%',
            maxHeight:'100%'
        }
        let rowStyle = {
            marginBottom:'5%'
        }
        let widthHundred = {
            width:'100%'
        }
        let centerBlockOverride = {
            marginLeft:'24%',
            marginRight:'24%'
        }
        //let imgBoostrapClass = '.img-thumbnail'
        
        // let sectionStyle = {
        //     padding:'35px',
        //     boxShadow: 'rgba(0, 0, 0, 0.12) 0px 1px 6px, rgba(0, 0, 0, 0.12) 0px 1px 4px',
        //     WebkitTransition: 'box-shadow .1s ease-in-out',
        //     transition: 'box-shadow .1s ease-in-out'
        // }
        return (
            <div className="container">
                <div className="row" style={rowStyle}>
                    <section id="app_header" className={`${customStyles.jumbotron} ${textCenter}`}>
                        <h1 className={customStyles.textSizeJumbotron}>
                            KUSSOS
                        </h1>
                    </section>
                </div>
                <div className="row" id="meaningKUSSOS" style={rowStyle}>
                    <div className="col-sm col-md col-lg">
                        <button className="btn btn-success" style={widthHundred} type="button" data-toggle="collapse" data-target="#collapseKUSSOS" aria-expanded="false" aria-controls="collapseKUSSOS">
                            <h2>What is KUSSOS?</h2>
                        </button>
                        
                        <section className="collapse" id="collapseKUSSOS" style={threeStyle}><h2>KUSSOS is a social e-learning online space for Kingston's Students</h2></section>
                    </div>
                </div>
                <div className="row" id="purposeContent" style={rowStyle}>
                    <div className="col-sm col-md col-lg">
                        <button className="btn btn-success" style={widthHundred} type="button" data-toggle="collapse" data-target="#collapsePurpose" aria-expanded="false" aria-controls="collapsePurpose">
                            <h2>What is the purpose of KUSSOS?</h2>
                        </button>
                        <section className="collapse" id="collapsePurpose" style={threeStyle}><h3>The main purpose of this online space is to allow the communication between students,
                        this will result in getting help from your peers at any time or when they are connected</h3></section>
                    </div>
                </div>
                <div className="row" id="featuresButton" style={rowStyle}>
                    <div className="col-sm col-md col-lg">
                        <button className="btn btn-success" style={widthHundred} type="button" data-toggle="collapse" data-target="#collapseFeatures" aria-expanded="false" aria-controls="collapseFeatures">
                            <h2>What are the main features?</h2>
                        </button>
                    </div>
                </div>
                <div className="row collapse" id="collapseFeatures">
                    <div className="row" style={rowStyle}>
                        <div className="col-sm col-md-4 col-lg-4">
                            <section className={customStyles.imgthumbnail}>
                                <img style={imgStyle} src={chat} alt="chat"/>
                                <div style={oneStyle}>
                                    <div style={twoStyle}>
                                        <div style={threeStyle}><h3>CREATE UP TO 6 CHAT ROOMS AND INVITE YOUR FRIENDS </h3></div>
                                    </div>
                                </div>
                            </section>
                        </div>
                        <div className="col-sm col-md-4 col-lg-4" >
                            <section className={customStyles.imgthumbnail}>
                            <img style={imgStyle} src={subject} alt="subject"/>
                                <div style={oneStyle}>
                                    <div style={twoStyle}>
                                        <div style={threeStyle}><h3>CREATE CHATS DEPENDING ON THE SUBJECT THAT YOU ARE DOING</h3></div>
                                    </div>
                                </div>
                            </section>
                        </div>
                        <div className="col-sm col-md-4 col-lg-4">
                        <section className={customStyles.imgthumbnail}>
                                <img style={imgStyle} src={realTime} alt="realTime"/>
                                <div style={oneStyle}>
                                    <div style={twoStyle}>
                                        <div style={threeStyle}><h3>VIEW REAL-TIME POSTS OF OTHER STUDENTS</h3></div>
                                    </div>
                                </div>
                        </section>
                        </div>
                    </div>
                    <div className="row" style={rowStyle}>
                        <div className="col-sm col-md col-lg">
                            <div className="center-block" style={centerBlockOverride}>
                                <section className={customStyles.imgthumbnail}>
                                    <img style={imgStyle} src={usefulLinks} alt="usefulLinks"/>
                                    <div style={oneStyle}>
                                        <div style={twoStyle}>
                                            <div style={threeStyle}><h3>SAVE RELEVANT LINKS THAT ARE USEFUL TO YOU IN THE FUTURE</h3></div>
                                        </div>
                                    </div>
                                </section>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="row" id="featuresButton" style={rowStyle}>
                    <div className="col-sm col-md col-lg">
                        <button className="btn btn-warning" style={widthHundred} type="button" data-toggle="collapse" data-target="#collapseForm" aria-expanded="false" aria-controls="collapseForm">
                            <h2>Sign Up</h2>
                        </button>
                        <section className="collapse" id="collapseForm" style={threeStyle}>
                                <BasicForm onSubmit={handleResults}/>
                        </section>
                    </div>
                </div>
                
            </div>

        )
    }
}

export default LandingPage;