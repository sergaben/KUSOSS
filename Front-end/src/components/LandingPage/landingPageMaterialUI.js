import React, { Component } from 'react';
import { CardTitle } from 'material-ui';
import { Grid, Row, Col } from 'react-flexbox-grid';
import chat from '../../../public/images/chat.jpg';
import subject from '../../../public/images/subjects.jpg';
import realTime from '../../../public/images/RealTime.jpg';
import usefulLinks from '../../../public/images/links.png';
import CustomCard from './Card/CustomCard';
import Header from './Header/Header';
import SignUpLogInHeader from './SignUpLogInHeader/SignUpLogInHeader';
import { TextCard1, TextCard2, TextCard3, TextCard4 } from '../../util/dummyData';




class LandingPageMaterialUI extends Component{
    state = {
        openLogIn:false,
        openSignUp:false
    };
    handleOpenLogIn = () =>{
        this.setState({openLogIn:true});    
    };
    handleOpenSignUp = () =>{
        this.setState({openSignUp:true});    
    };
    handleCloseLogIn = () =>{
        this.setState({openLogIn:false});
    };
    handleCloseSignUp = () =>{
        this.setState({openSignUp:false});    
    };
    render(){
        let gridStyle = {
            padding:0
        }
        let rowStyle = {
            margin:0
        }
        let colPaddingStyle = {
            padding:'5%'
        }
        return(
        <div>
            <Grid fluid style={gridStyle}>
                <Row style={rowStyle}>
                    <Header titleHeader="KUSSOS" showMenuIcon={false} 
                            subtitle="A social space for learning and exchanging knowledge between students of all career paths"/>
                </Row>
                <Row style={rowStyle}>
                    <SignUpLogInHeader handleOpenLogInFunction={this.handleOpenLogIn} handleOpenSignUpFunction={this.handleOpenSignUp} 
                                       handleCloseSignUpFunction={this.handleCloseSignUp} handleCloseLogInFunction={this.handleCloseLogIn} 
                                       openLogInState={this.state.openLogIn} openSignUpState={this.state.openSignUp}/>
                     </Row>
            </Grid>
            <Grid fluid style={gridStyle}>  
                    <Row style={rowStyle}>
                        <Col xs={12} sm={6} md={6} lg={4} style={colPaddingStyle}>
                            <CustomCard titleHeader="Create chat rooms" overlayHeader={<CardTitle title="Create chat rooms" subtitle="Up to 6"/>} 
                                        srcImage={chat} altImage="imageOne" 
                                        cardTitle="Create chat rooms" cardInsideText={TextCard1} />
                        </Col>
                        <Col xs={12} sm={6} md={6} lg={4}  style={colPaddingStyle}>
                            <CustomCard titleHeader="Tailored chats to subjects" overlayHeader={<CardTitle title="Chat rooms tailored to your subject" />} 
                                        srcImage={subject} altImage="imageTwo" cardTitle="Create chat rooms depending on the subject that you are doing"
                                        cardInsideText={TextCard2} />
                        </Col>
                        <Col xs={12} sm={6} md={6} lg={4} style={colPaddingStyle}>
                            <CustomCard titleHeader="Real time feed" overlayHeader={<CardTitle title="View real time feed of posts" />} 
                                        srcImage={realTime} altImage="imageThree" cardTitle="A real time feed of the posts that you created and the posts that you are interest in"
                                        cardInsideText={TextCard3}/>
                        </Col>
                        <Col xs={12} sm={6} md={6} lg={4}  style={colPaddingStyle}>
                            <CustomCard titleHeader="Useful Links" overlayHeader={<CardTitle title="Save useful links relevant for your future course" />} 
                                        srcImage={usefulLinks} altImage="imageFour" cardTitle="View resources that are useful to your career and will help you improve in your studies"
                                        cardInsideText={TextCard4} />
                        </Col>
                    </Row>
                </Grid>
                
        </div>   
        )

    }
}

export default LandingPageMaterialUI;