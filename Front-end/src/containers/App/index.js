import React, { Component } from 'react';
import { connect } from 'react-redux';
import styles from './app.css';

class App extends Component {
  render() {
    return (
      <div id="app-container" className={styles.appContainer}>
        Hello world
      </div>
    )
  }
}

export default connect()(App);
