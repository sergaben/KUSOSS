import React, { Component } from 'react';
import Styles from './header.css';
class Header extends Component {
    render(){
        return(
            <div>
                <header id="app_header" className= {Styles.headerContainer}>
                    <h1>
                        The title of the website
                    </h1>
                </header>
            </div>
        )
    }
}

export default Header;