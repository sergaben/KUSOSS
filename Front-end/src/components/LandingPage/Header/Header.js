import React, { Component } from 'react';
import { AppBar, MuiThemeProvider} from 'material-ui';
import styles from '../landingPage.css';

class Header extends Component {
    render(){
        let textAlign = {
            marginTop:'5%',
            marginBottom:'2%',
            fontSize:'4em',
        }
        let subtitleStyle = {
            color: 'white',
        }
        let headerStyle ={
            flexDirection:'column'
        }
        const { titleHeader, showMenuIcon, subtitle } = this.props;
        return(
            <MuiThemeProvider>
                <AppBar title={titleHeader}
                    className={styles.textAlign}
                    style={headerStyle}
                    showMenuIconButton={showMenuIcon}
                    titleStyle={textAlign}>
                    <h3 style={subtitleStyle}>{subtitle}</h3>
                </AppBar>
            </MuiThemeProvider>
        )
    }
}

export default Header;