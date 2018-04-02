import React, { Component } from 'react';
import { Avatar, MuiThemeProvider,Paper } from 'material-ui';

class ProfileViewer extends Component{
    render(){
        const { srcImage, sizeImage, styleUI } = this.props;
        return(
            
            <MuiThemeProvider>
             <div className={styleUI.profileViewer}>
                <Avatar src={srcImage} size={sizeImage} className={styleUI.mainStyle} style={{'background-color': 'black'}} />
                <div className={styleUI.profileDetails}>
                    <div className={styleUI.profileUserName}>Sergaben</div>
                    <div className={styleUI.profileCourse}>Software Engineering</div>
                </div>
             </div>
            </MuiThemeProvider>
        )
    }
}

export default ProfileViewer;