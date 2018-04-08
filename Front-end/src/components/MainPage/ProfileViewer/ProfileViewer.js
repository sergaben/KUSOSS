import React, { Component } from 'react';
import { Avatar, MuiThemeProvider,Paper } from 'material-ui';

class ProfileViewer extends Component{
    render(){
        const { srcImage, sizeImage, styleUI, username, subject } = this.props;
        return(
            
            <MuiThemeProvider>
             <div className={styleUI.profileViewer}>
                <Avatar src={srcImage} size={sizeImage} className={styleUI.mainStyle} style={{'backgroundColor': 'black'}} />
                <div className={styleUI.profileDetails}>
                    <div className={styleUI.profileUserName}>{username}</div>
                    <div className={styleUI.profileCourse}>{subject}</div>
                </div>
             </div>
            </MuiThemeProvider>
        )
    }
}

export default ProfileViewer;