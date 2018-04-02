import React, { Component } from 'react';
import {List, MuiThemeProvider, ListItem, Subheader} from 'material-ui';

class MainChat extends Component{
    render(){
        return(
            <MuiThemeProvider>
                <List>
                    <Subheader> Chat List </Subheader>
                    <ListItem
                        primaryText="Sergaben"
                    />
                    <ListItem
                        primaryText="Emesmess"
                    />
                </List>
            </MuiThemeProvider>
        )
    }
}

export default MainChat;