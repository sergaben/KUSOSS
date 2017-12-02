import React, { Component } from 'react';
import { connect } from 'react-redux';
import styles from './app.css';
import LandingPage from '../../components/LandingPage/landingPage';

class App extends Component {
  render() {
    return (
      <div id="app-container" className={styles.appContainer}>
        <LandingPage/>
      </div>
    )
  }
}

export default connect()(App);
