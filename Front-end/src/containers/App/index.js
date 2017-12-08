import React, { Component } from 'react';
import { connect } from 'react-redux';
import styles from './app.css';
import LandingPageMaterialUI from '../../components/LandingPage/landingPageMaterialUI';

class App extends Component {
  render() {
    return (
      <div id="app-container" className={`${styles.appContainer}`}>
        <LandingPageMaterialUI/>
      </div>
    )
  }
}

export default connect()(App);
