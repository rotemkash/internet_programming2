import './App.css';
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import GameBoard from './components/GameBoard';
import StartScreen from './components/StartScreen';
import ResultsScreen from './components/ResultsScreen';
import 'bootstrap/dist/css/bootstrap.min.css';

/**
 * The main part of the application
 * Sets up the routing between different components using react-router-dom
 */
function App() {
    return (
        <div className="container">
            <Router>
                <Routes>
                    <Route exact path="/" element={<StartScreen />} />
                    <Route path="/game" element={<GameBoard />} />
                    <Route path="/results" element={<ResultsScreen />} />
                </Routes>
            </Router>
        </div>
    );
}

export default App;