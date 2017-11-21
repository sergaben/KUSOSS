import React, { Component } from 'react';
import { connect } from 'react-redux';
import styles from './app.css';
import Header from '../../components/header';

class App extends Component {
  render() {
    return (
      <div id="app-container" className={styles.appContainer}>
        <Header/>
      </div>
    )
  }
}

export default connect()(App);
