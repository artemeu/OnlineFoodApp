import React, { Component } from 'react';
import './App.css';
import OnlineFoodApp from "./components/onlinefood/OnlineFoodApp";
import CounterOld from "./components/counter/Counter";
import './bootstrap.css';

class App extends Component {
  render() {
    return (
      <div className="App">
        <OnlineFoodApp />
      </div>
    );
  }
}

export default App;
