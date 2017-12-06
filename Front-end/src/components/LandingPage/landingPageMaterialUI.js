import React, { Component } from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import { AppBar, ToolbarTitle } from 'material-ui';
import styles from './landingPage.css';
import { Card, CardHeader, CardMedia, CardTitle, CardActions, CardText } from 'material-ui/Card';
import Paper from 'material-ui/Paper';
import FontIcon from 'material-ui/FontIcon';
import { Grid, Row, Col } from 'react-flexbox-grid';
import {BottomNavigation, BottomNavigationItem} from 'material-ui/BottomNavigation';
import FlatButton from 'material-ui/FlatButton';
import chat from '../../../public/images/chat.jpg';
import subject from '../../../public/images/subjects.jpg';
import realTime from '../../../public/images/RealTime.jpg';
import usefulLinks from '../../../public/images/links.png';
import Dialog from 'material-ui/Dialog';
import { Toolbar,ToolbarGroup } from 'material-ui/Toolbar';

class LandingPageMaterialUI extends Component{
    state = {
        openLogIn:false,
        openSignUp:false
    };
    handleOpenLogIn = () =>{
        this.setState({openLogIn:true});    
    };
    handleCloseLogIn = () =>{
        this.setState({openLogIn:false});
    };
    handleOpenSignUp = () =>{
        this.setState({openSignUp:true});    
    };
    handleCloseSignUp = () =>{
        this.setState({openSignUp:false});    
    };
    render(){
        let textAlign = {
            marginTop:'5%',
            marginBottom:'2%',
            fontSize:'4em'    
        }
        let paperStyle = {
            height:'20%',
            width:'25%',
            margin:'50'
        }
        let cardBoxShadow = {
            boxShadow:'rgba(0, 0, 0, 0.16) 0px 1px 10px 6px, rgba(0, 0, 0, 0.23) 0px 3px 10px'
        }
        let gridStyle = {
            padding:0
        }
        let bottomNavigation = {
            backgroundColor:'#f0ad4e'
        }
        let colPaddingStyle = {
            padding:'5%'
        }
        let verticalAlign = {
            marginTop:'24%',
            marginBottom:'2%',
            fontSize:'4em'
        }
        let fontFamily = {
            fontFamily:'Roboto, sans-serif'
        }
        let fontSize = {
            fontSize:'15px'
        }
        let imgStyle ={
            marginBottom:'18%'
        }
        let rowWidth = {
            maxWidth:'100%'
        }
        let signUpText = (
            <div style={fontSize}>
                Sign Up
            </div>
        )
        let logInText = (
            <div style={fontSize}>
                Log In
            </div>
        )
        const actionsLogIn = [
            <FlatButton
                label="Cancel"
                primary={true}
                onClick={this.handleCloseLogIn}
            />,
            <FlatButton
                label="Submit"
                primary={true}
                disabled={true}
                onClick={this.handleCloseLogIn}
            />,
        ];
        const actionsSignUp = [
            <FlatButton
                label="Cancel"
                primary={true}
                onClick={this.handleCloseSignUp}
            />,
            <FlatButton
                label="Submit"
                primary={true}
                disabled={true}
                onClick={this.handleCloseSignUp}
            />,
        ];
        const logInIcon = <FontIcon className="material-icons">lock_open</FontIcon>;
        const signInIcon = <FontIcon className="material-icons">person_add</FontIcon>;
        return(
        <div>
            <Grid fluid style={gridStyle}>
            
                    <MuiThemeProvider>
                        <AppBar title="KUSSOS" 
                            className={styles.textAlign}
                            iconClassNameLeft={styles.displayNone}
                            titleStyle={textAlign}>
                        </AppBar>
                    </MuiThemeProvider>
                
                
                    <MuiThemeProvider>
                        <AppBar title="A social space for learning and exchanging knowledge between students of all career paths" 
                            className={styles.textAlign}
                            iconClassNameLeft={styles.displayNone}
                            style={{backgroundColor:'#005661'}}>
                        </AppBar>
                    </MuiThemeProvider>
                
                            <MuiThemeProvider>
                                    <BottomNavigation style={bottomNavigation}>
                                        <BottomNavigationItem
                                            label={logInText}
                                            icon={logInIcon}
                                            onClick={this.handleOpenLogIn}
                                        />
                                        <BottomNavigationItem
                                            label={signUpText}
                                            icon={signInIcon}
                                            onClick={this.handleOpenSignUp}
                                        />
                                        <Dialog
                                            title="Log In"
                                            actions={actionsLogIn}
                                            modal={true}
                                            open={this.state.openLogIn}
                                        >
                                            Log In Form goes here
                                        </Dialog>
                                        <Dialog
                                            title="Sign Up"
                                            actions={actionsSignUp}
                                            modal={true}
                                            open={this.state.openSignUp}
                                        >
                                            Sign up Form goes here
                                        </Dialog>
                                    </BottomNavigation>
                            </MuiThemeProvider>
                     
        </Grid>
        <Grid fluid>  
                <Row>
                    <Col xs={12} sm={6} md={6} lg={4} style={colPaddingStyle}>
                        <MuiThemeProvider>  
                                    <Card style={cardBoxShadow}>
                                        <CardHeader
                                            title="Create chat rooms"
                                        />
                                        <CardMedia
                                            overlay={<CardTitle title="Create chat rooms" subtitle="Up to 6"/>}
                                        >
                                            <img src={chat} alt="imageOne"/>
                                        </CardMedia>
                                        <CardTitle title="Create chat rooms"/>
                                        <CardText style={{textAlign:'justify'}}>
                                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed iaculis, nibh ut condimentum interdum, 
                                            nibh lorem pretium libero, eleifend dignissim turpis ligula ac sapien. Mauris pharetra elit non augue 
                                            consectetur, eu laoreet ante lacinia. Sed lacus libero, dignissim in metus ac, vulputate viverra velit.
                                            Etiam a nunc quis eros sodales vehicula ac sit amet libero. Ut egestas ex nec commodo euismod.
                                            Suspendisse quis nunc turpis. Morbi urna dui, lobortis vel bibendum at, placerat non magna. 
                                            Duis vel tincidunt nulla. Curabitur rhoncus et arcu et vehicula. Nulla sapien magna,
                                            dignissim eget condimentum sed, blandit vel risus. Phasellus purus odio, volutpat sed blandit et,
                                            malesuada at orci. Aenean consequat urna at pharetra malesuada. Sed a velit ante. Ut vitae arcu congue, 
                                            lobortis eros id, imperdiet risus. Curabitur semper justo ac velit pharetra, quis viverra est rhoncus.
                                            Pellentesque porttitor mauris eu feugiat cursus.
                                        </CardText>
                                        <CardActions>
                                            <FlatButton label="Action1" />
                                            <FlatButton label="Action2" />
                                        </CardActions>
                                    </Card>
                            
                        </MuiThemeProvider>
                    </Col>
                    <Col xs={12} sm={6} md={6} lg={4}  style={colPaddingStyle}>
                        <MuiThemeProvider>
                                <Card style={cardBoxShadow}>
                                    <CardHeader
                                        title="Tailored chats to subjects"
                                    />
                                    <CardMedia
                                        overlay={<CardTitle title="Chat rooms tailored to your subject"/>}
                                    >
                                        <img src={subject} alt="imageTwo"/>
                                    </CardMedia>
                                        <CardTitle title="Create chat rooms depending on the subject that you are doing"/>
                                    <CardText style={{textAlign:'justify'}}>
                                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed iaculis, nibh ut condimentum interdum, 
                                        nibh lorem pretium libero, eleifend dignissim turpis ligula ac sapien. Mauris pharetra elit non augue 
                                        consectetur, eu laoreet ante lacinia. Sed lacus libero, dignissim in metus ac, vulputate viverra velit.
                                        Etiam a nunc quis eros sodales vehicula ac sit amet libero. Ut egestas ex nec commodo euismod.
                                        Suspendisse quis nunc turpis. Morbi urna dui, lobortis vel bibendum at, placerat non magna. 
                                        Duis vel tincidunt nulla. Curabitur rhoncus et arcu et vehicula. Nulla sapien magna,
                                        dignissim eget condimentum sed, blandit vel risus. Phasellus purus odio, volutpat sed blandit et,
                                        malesuada at orci. Aenean consequat urna at pharetra malesuada. Sed a velit ante. Ut vitae arcu congue, 
                                        lobortis eros id, imperdiet risus. Curabitur semper justo ac velit pharetra, quis viverra est rhoncus.
                                        Pellentesque porttitor mauris eu feugiat cursus.
                                    </CardText>
                                    <CardActions>
                                        <FlatButton label="Action 1" />
                                        <FlatButton label="Action 2" />
                                    </CardActions>
                                </Card>
                        </MuiThemeProvider>
                    </Col>
                    <Col xs={12} sm={6} md={6} lg={4} style={colPaddingStyle}>
                        <MuiThemeProvider>
                                <Card style={cardBoxShadow}>
                                    <CardHeader
                                        title="Real time feed"
                                    />
                                    <CardMedia
                                        overlay={<CardTitle title="View real time feed of posts" />}
                                    >
                                        <img src={realTime} alt="imageTwo"/>
                                    </CardMedia>
                                    <CardTitle title="A real time feed of the posts that you created and the posts that you are interest in"/>
                                    <CardText style={{textAlign:'justify'}}>
                                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed iaculis, nibh ut condimentum interdum, 
                                        nibh lorem pretium libero, eleifend dignissim turpis ligula ac sapien. Mauris pharetra elit non augue 
                                        consectetur, eu laoreet ante lacinia. Sed lacus libero, dignissim in metus ac, vulputate viverra velit.
                                        Etiam a nunc quis eros sodales vehicula ac sit amet libero. Ut egestas ex nec commodo euismod.
                                        Suspendisse quis nunc turpis. Morbi urna dui, lobortis vel bibendum at, placerat non magna. 
                                        Duis vel tincidunt nulla. Curabitur rhoncus et arcu et vehicula. Nulla sapien magna,
                                        dignissim eget condimentum sed, blandit vel risus. Phasellus purus odio, volutpat sed blandit et,
                                        malesuada at orci. Aenean consequat urna at pharetra malesuada. Sed a velit ante. Ut vitae arcu congue, 
                                        lobortis eros id, imperdiet risus. Curabitur semper justo ac velit pharetra, quis viverra est rhoncus.
                                        Pellentesque porttitor mauris eu feugiat cursus.
                                    </CardText>
                                    <CardActions>
                                        <FlatButton label="Action 1" />
                                        <FlatButton label="Action 2" />
                                    </CardActions>
                                </Card>
                        </MuiThemeProvider>
                    </Col>
                    <Col xs={12} sm={6} md={6} lg={4}  style={colPaddingStyle}>
                        <MuiThemeProvider>
                        
                                <Card style={cardBoxShadow}>
                                    <CardHeader
                                        title="Useful Links"
                                    />
                                    <CardMedia
                                        overlay={<CardTitle title="Save useful links relevant for your future course" />}
                                    >
                                        <img src={usefulLinks} style={imgStyle} alt="imageTwo"/>
                                    </CardMedia>
                                    <CardTitle title="View resources that are useful to your career and will help you improve in your studies"/>
                                    <CardText style={{textAlign:'justify'}}>
                                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed iaculis, nibh ut condimentum interdum, 
                                        nibh lorem pretium libero, eleifend dignissim turpis ligula ac sapien. Mauris pharetra elit non augue 
                                        consectetur, eu laoreet ante lacinia. Sed lacus libero, dignissim in metus ac, vulputate viverra velit.
                                        Etiam a nunc quis eros sodales vehicula ac sit amet libero. Ut egestas ex nec commodo euismod.
                                        Suspendisse quis nunc turpis. Morbi urna dui, lobortis vel bibendum at, placerat non magna. 
                                        Duis vel tincidunt nulla. Curabitur rhoncus et arcu et vehicula. Nulla sapien magna,
                                        dignissim eget condimentum sed, blandit vel risus. Phasellus purus odio, volutpat sed blandit et,
                                        malesuada at orci. Aenean consequat urna at pharetra malesuada. Sed a velit ante. Ut vitae arcu congue, 
                                        lobortis eros id, imperdiet risus. Curabitur semper justo ac velit pharetra, quis viverra est rhoncus.
                                        Pellentesque porttitor mauris eu feugiat cursus.
                                    </CardText>
                                    <CardActions>
                                        <FlatButton label="Action 1" />
                                        <FlatButton label="Action 2" />
                                    </CardActions>
                                </Card>
                        </MuiThemeProvider>
                    </Col>
                </Row>
            </Grid>
        </div>   
        )

    }
}

export default LandingPageMaterialUI;