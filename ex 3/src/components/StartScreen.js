import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

/**
 * The Component that displays the start screen of the game
 * Includes input fields for username, game settings, and leaderboard display
 */
const StartScreen = () => {
    const [username, setUsername] = useState('');
    const [rows, setRows] = useState(4);
    const [cols, setCols] = useState(4);
    const [delay, setDelay] = useState(1);
    const [showSettings, setShowSettings] = useState(false);
    const [showLeaderboard, setShowLeaderboard] = useState(false);
    const [leaderboard, setLeaderboard] = useState([]);
    const navigate = useNavigate();

    /**
     * Loads the leaderboard from localStorage when the component mounts
     * @param none
     * @exception none
     * @return none
     */
    useEffect(() => {
        const storedLeaderboard = JSON.parse(localStorage.getItem('leaderboard')) || [];
        setLeaderboard(storedLeaderboard);
    }, []);

    /**
     * Updates the username value based on user input
     * Limits the length to 12 characters and trims leading/trailing spaces
     * @param e - The input event object
     * @exception none
     * @return none
     */
    const handleUsernameChange = (e) => {
        setUsername(e.target.value.trim().slice(0, 12));
    };

    /**
     * Starts a new game if the username is entered and the card count is even
     * Passes the game settings to the GameBoard component
     * @exception none
     * @return none
     */
    const handleStartGame = () => {
        if (username && isEvenCardCount()) {
            navigate('/game', { state: { username, rows, cols, delay, totalCards: rows * cols } });
        }
    };

    /**
     * Toggles the display of game settings
     * @exception none
     * @return none
     */
    const handleSettingsToggle = () => {
        setShowSettings(!showSettings);
    };

    /**
     * Checks if the total number of cards is even
     * @exception none
     * @return {boolean} - True if the card count is even, false otherwise
     */
    const isEvenCardCount = () => {
        const totalCards = rows * cols;
        return totalCards % 2 === 0;
    };

    return (
        <div>
            <div className="mb-3">
                <input
                    type="text"
                    placeholder="Enter username"
                    value={username}
                    onChange={handleUsernameChange}
                    className="form-control"
                />
            </div>
            <div className="mb-3 d-flex justify-content-between">
                <button className="btn btn-primary" onClick={handleStartGame} disabled={!username || !isEvenCardCount()}>
                    Start Game
                </button>
                <button className="btn btn-secondary" onClick={handleSettingsToggle}>Settings</button>
                <button className="btn btn-secondary" onClick={() => setShowLeaderboard(true)}>Show Leaderboard</button>
            </div>
            {showSettings && (
                <div>
                    <label>
                        Rows:
                        <input
                            type="number"
                            min="2"
                            max="5"
                            value={rows}
                            onChange={(e) => setRows(parseInt(e.target.value))}
                            className="form-control"
                        />
                    </label>
                    <label>
                        Columns:
                        <input
                            type="number"
                            min="2"
                            max="5"
                            value={cols}
                            onChange={(e) => setCols(parseInt(e.target.value))}
                            className="form-control"
                        />
                    </label>
                    {!isEvenCardCount() && <p style={{ color: 'red' }}>Please choose an even number of cards.</p>}
                    <label>
                        Delay (seconds):
                        <input
                            type="number"
                            min="0.5"
                            max="2"
                            step="0.1"
                            value={delay}
                            onChange={(e) => setDelay(parseFloat(e.target.value))}
                            className="form-control"
                        />
                    </label>
                </div>
            )}

            {showLeaderboard && (
                <div className="modal" style={{ display: 'block' }}>
                    <div className="modal-dialog">
                        <div className="modal-content">
                            <div className="modal-header">
                                <h5 className="modal-title">Leaderboard</h5>
                                <button type="button" className="close" onClick={() => setShowLeaderboard(false)}>
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div className="modal-body">
                                {leaderboard.length > 0 ? (
                                    <table className="table">
                                        <thead>
                                        <tr>
                                            <th>Rank</th>
                                            <th>Username</th>
                                            <th>Score</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        {leaderboard.map((entry, index) => (
                                            <tr key={index}>
                                                <td>{index + 1}</td>
                                                <td>{entry.username}</td>
                                                <td>{entry.score}</td>
                                            </tr>
                                        ))}
                                        </tbody>
                                    </table>
                                ) : (
                                    <p>No scores yet.</p>
                                )}
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-secondary" onClick={() => setShowLeaderboard(false)}>
                                    Close
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default StartScreen;