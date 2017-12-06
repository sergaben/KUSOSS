import React, { Component } from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import { AppBar } from 'material-ui';
import styles from './landingPage.css';
import { Card, CardHeader, CardMedia, CardTitle, CardActions, CardText } from 'material-ui/Card';
import Paper from 'material-ui/Paper';
import FontIcon from 'material-ui/FontIcon';
import {BottomNavigation, BottomNavigationItem} from 'material-ui/BottomNavigation';
import FlatButton from 'material-ui/FlatButton';
import chat from '../../../public/images/chat.jpg';
import subject from '../../../public/images/subjects.jpg';
import realTime from '../../../public/images/RealTime.jpg';
import usefulLinks from '../../../public/images/links.png';
import Dialog from 'material-ui/Dialog';

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
        let paperBottomNavigationStyle = {
            backgroundColor:'rgb(0, 92, 212)'
        }
        let bottomNavigation = {
            backgroundColor:'#f0ad4e'
        }
        let displayStyle = {
            display:'-webkit-box'
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
                <MuiThemeProvider>
                    <AppBar title="KUSSOS" 
                        className={styles.textAlign}
                        iconClassNameLeft={styles.displayNone}
                        // iconElementRight={rightButtons}
                        titleStyle={textAlign}>
                    </AppBar>
                </MuiThemeProvider>
                <MuiThemeProvider>
                    <Paper zDepth={2} style={paperBottomNavigationStyle}>
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
                    </Paper>
                </MuiThemeProvider>
                <div style={displayStyle}>
                    <MuiThemeProvider>
                        <Paper style={paperStyle} zDepth={2}>
                                <Card>
                                    <CardHeader
                                        title="Create chat rooms"
                                    />
                                    <CardMedia
                                        overlay={<CardTitle title="Create chat rooms" subtitle="Up to 6"/>}
                                    >
                                        <img src={chat} alt="imageOne"/>
                                    </CardMedia>
                                    <CardTitle title="Create chat rooms"/>
                                    <CardText>
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
                        </Paper>
                    </MuiThemeProvider>
                    <MuiThemeProvider>
                        <Paper style={paperStyle} zDepth={2}>
                                <Card>
                                    <CardHeader
                                        title="Tailored chats to subjects"
                                    />
                                    <CardMedia
                                        overlay={<CardTitle title="Chat rooms tailored to your subject"/>}
                                    >
                                        <img src={subject} alt="imageTwo"/>
                                    </CardMedia>
                                        <CardTitle title="Create chat rooms depending on the subject that you are doing"/>
                                    <CardText>
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
                        </Paper>
                    </MuiThemeProvider>
                    <MuiThemeProvider>
                        <Paper style={paperStyle} zDepth={2}>
                            <Card>
                                <CardHeader
                                    title="Real time feed"
                                />
                                <CardMedia
                                    overlay={<CardTitle title="View real time feed of posts" />}
                                >
                                    <img src={realTime} alt="imageTwo"/>
                                </CardMedia>
                                <CardTitle title="A real time feed of the posts that you created and the posts that you are interest in"/>
                                <CardText>
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
                        </Paper>
                    </MuiThemeProvider>
                </div>
                <div style={displayStyle}>
                    <MuiThemeProvider>
                        <Paper style={paperStyle} zDepth={2}>
                            <Card>
                                <CardHeader
                                    title="Useful Links"
                                />
                                <CardMedia
                                    overlay={<CardTitle title="Save useful links relevant for your future course" />}
                                >
                                    <img src={usefulLinks} style={imgStyle} alt="imageTwo"/>
                                </CardMedia>
                                <CardTitle title="View resources that are useful to your career and will help you improve in your studies"/>
                                <CardText>
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
                        </Paper>
                    </MuiThemeProvider>
                </div>
            </div>
            
        )

    }
}

export default LandingPageMaterialUI;