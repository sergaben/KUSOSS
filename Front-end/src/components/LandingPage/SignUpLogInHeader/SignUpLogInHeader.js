import React, { Component } from 'react';
import { MuiThemeProvider, BottomNavigation, BottomNavigationItem, FontIcon} from 'material-ui';
import LoginDialog from '../Dialog/LogIn/loginDialog';
import SignUpDialog from '../Dialog/SignUp/signUpDialog';

class SignUpLogInHeader extends Component {

    render(){
        let bottomNavigation = {
            backgroundColor:'#f0ad4e'
        }
        let fontSize = {
            fontSize:'15px'
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
        const logInIcon = <FontIcon className="material-icons">lock_open</FontIcon>;
        const signInIcon = <FontIcon className="material-icons">person_add</FontIcon>;
        const { handleOpenLogInFunction,handleOpenSignUpFunction, 
                handleCloseSignUpFunction,handleCloseLogInFunction, 
                openLogInState, openSignUpState } = this.props;
        return(
            <MuiThemeProvider>
                <BottomNavigation style={bottomNavigation}>
                    <BottomNavigationItem
                        label={logInText}
                        icon={logInIcon}
                        onClick={handleOpenLogInFunction}
                    />
                    <BottomNavigationItem
                        label={signUpText}
                        icon={signInIcon}
                        onClick={handleOpenSignUpFunction}
                    />
                    <LoginDialog open={openLogInState} close={handleCloseLogInFunction}/>
                    <SignUpDialog open={openSignUpState} close={handleCloseSignUpFunction} visible={false}/>
                </BottomNavigation>
                
            </MuiThemeProvider>
        )
    }
}

export default SignUpLogInHeader;