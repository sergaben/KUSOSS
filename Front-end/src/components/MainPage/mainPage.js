import React, { Component } from 'react';
import { browserHistory } from 'react-router';
import Header from './Header/Header';
import { AppBar, MuiThemeProvider} from 'material-ui';
import { Grid, Row, Col } from 'react-flexbox-grid';
import ImageProfile from '../../../public/images/ProfileImages/tiger.jpg';
import styles from './mainPage.css';
import Links from './Links/Links';
import FeedTimeLine from './InputFeedTimeLine/InputFeedTimeLine';
import ProfileViewer from './ProfileViewer/ProfileViewer';
import MainChat from './Chat/MainChat';

//TODO - find a way to not let unauthorized user see the main page

class MainPage extends Component {
    constructor(props){
        super(props);
        this.state = {
            nickname:'',
            subject:''
        }
    }
    componentWillMount(){
        if(localStorage.getItem("token") === null){
            browserHistory.push({
                pathname: '/',
            });
        }else{
            const nickname = this.firstLetterUpperCase(localStorage.getItem("nickname"));
            const subject = this.firstLetterUpperCase(localStorage.getItem("subject"));
            this.setState(()=>{
                return {
                    nickname:nickname,
                    subject:subject
                }
            })
        }
    }
    firstLetterUpperCase = (word) =>{
        let wordToLowerCase = word.toLowerCase();
        return wordToLowerCase.charAt(0).toUpperCase() + wordToLowerCase.slice(1);
    }
    render(){
        let gridStyle = {
            padding:0
        }
        let componentStyle = {
            backgroundColor: '#C1CDCD'
         }
        return(
            <div>
                <Grid fluid style={gridStyle}>
                    <Header titleHeader="KUSSOS" show={false}/>
                </Grid>
                <Grid fluid>
                    <Row center="lg" center="xs" center="sm" center="md">
                        <Col xs={12} sm={6} md={6} lg={7}>
                               <ProfileViewer srcImage={ImageProfile} sizeImage={150} styleUI={styles} username={this.state.nickname} subject={this.state.subject}/>
                        </Col>
                    </Row>
                    <Row>
                        <Col xs={12} sm={3} md={3} lg={3}>
                            <div style={componentStyle} className={styles.links}>
                                <Links/>
                            </div>
                        </Col>
                        <Col xs={12} sm={5} md={5} lg={5}>
                            <div style={componentStyle} className={styles.feed}>
                                <FeedTimeLine/>
                            </div>
                        </Col>
                        <Col xs={12} sm={4} md={4} lg={4}>
                            <div style={componentStyle} className={styles.chatList}>
                                <MainChat/>
                            </div>
                        </Col>
                    </Row>
                </Grid>
            </div>  
        )
    }

    // loginPage = () =>{
    //     if(this.state.nickname === undefined){
            
    //     }
    // }
}

export default MainPage;