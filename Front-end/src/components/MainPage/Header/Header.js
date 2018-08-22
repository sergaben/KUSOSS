import React,{Component} from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { AppBar, MuiThemeProvider,RaisedButton, FlatButton } from 'material-ui';
import { logOut } from '../../../actions/logOutActions';
import { closeConnection } from '../../../actions/closeSSEConnectionAction';
import { browserHistory } from 'react-router';
class Header extends Component{
    
    handleOnlogout = () =>{
        const username = localStorage.getItem("nickname");

        this.props.logOut(username).then(()=>{

            if(this.props.logOutData.data.status === "OK" && this.props.logOutData.data.logout === true){
                localStorage.clear();
                browserHistory.push({
                    pathname: '/'
                });

            }
        })
    }

    render(){
        const { titleHeader, show } = this.props;
        const raisedButtonStyle = {
            marginTop:"10px",
            backgroundColor:"#ff4081",
            color:"white"
        }
        const titleStyleHeader = {
            textAlign:"justify",
            marginLeft:"5%",
            fontSize:"35px"
        }
        return(
            <MuiThemeProvider>
                <AppBar title={titleHeader} titleStyle={titleStyleHeader} showMenuIconButton={show}>
                    <FlatButton
                        label="Log Out"
                        secondary={true}
                        style={raisedButtonStyle}
                        onClick={this.handleOnlogout}
                    />
                </AppBar>
            </MuiThemeProvider>
        )
    }
}

const mapStateToProps=({logOutReducer}) =>({
    logOutData:logOutReducer
});

const mapDispatchToProps=(dispatch) =>bindActionCreators({logOut},dispatch);

export default connect(mapStateToProps,mapDispatchToProps)(Header);
