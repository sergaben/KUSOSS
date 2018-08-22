import React, { Component } from 'react';
import {List, MuiThemeProvider, ListItem, Subheader} from 'material-ui';

class Links extends Component{
    render(){
        return(
            <MuiThemeProvider>
            <List>
                <Subheader> Useful Links </Subheader>
                <ListItem 
                    primaryText="Google link"
                    />
                <ListItem 
                    primaryText="Facebook link"
                    />
                <ListItem 
                    primaryText="Tuenti link"
                />
                <ListItem 
                    primaryText="SDTimes link" 
                 />
            </List>
            </MuiThemeProvider>
        )
    }
}

export default Links;