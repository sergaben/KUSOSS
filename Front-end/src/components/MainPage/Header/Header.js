import React,{Component} from 'react';
import { AppBar, MuiThemeProvider } from 'material-ui';

class Header extends Component{
    
    render(){
        const { titleHeader, show } = this.props;
        return(
            <MuiThemeProvider>
                <AppBar title={titleHeader} showMenuIconButton={show}/>
            </MuiThemeProvider>
        )
    }
}

export default Header;
