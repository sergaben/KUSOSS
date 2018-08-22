import React, { Component } from 'react';
import { Avatar, MuiThemeProvider,Paper } from 'material-ui';

class ProfileViewer extends Component{
    render(){
        const { srcImage, sizeImage, styleUI, username, subject } = this.props;
        return(
             <div className={styleUI.profileViewer}>
                <MuiThemeProvider>
                    <Avatar src={srcImage} size={sizeImage} className={styleUI.mainStyle} style={{'backgroundColor': 'black'}} />
                </MuiThemeProvider>
                <div className={styleUI.profileDetails}>
                    <div className={styleUI.profileUserName}>{username}</div>
                    <div className={styleUI.profileCourse}>{subject}</div>
                </div>
             </div>
        )
    }
}

export default ProfileViewer;