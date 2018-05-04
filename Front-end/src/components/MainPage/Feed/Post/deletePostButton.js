import React, { Component } from 'react';
import IconButton from 'material-ui/IconButton';
import FontIcon from 'material-ui/FontIcon';
import { MuiThemeProvider } from 'material-ui';

class DeletePostButton extends Component{

    
    render(){
        return(
            <MuiThemeProvider>
                <IconButton tooltip="Font Icon">
                    <FontIcon className="material-icons">
                        delete
                    </FontIcon>
                </IconButton>
            </MuiThemeProvider>
        )
    }
}

export default DeletePostButton;
