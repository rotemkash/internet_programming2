import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';


/**
 * The Component that displays the game results
 * Shows the player's score, rank, move count, and the leaderboard
 */
const ResultsScreen = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const { username, score, moves, rows, cols } = location.state;
    const [leaderboard, setLeaderboard] = useState([]);

    const totalCards = rows * cols;

    /**
     * Updates the leaderboard with the current player's score,
     * Loads the leaderboard from localStorage and sorts it based on the scores
     * @param none
     * @exception none
     * @return none
     */
    useEffect(() => {
        const storedLeaderboard = JSON.parse(localStorage.getItem('leaderboard')) || [];

        const normalizedUsername = username.toLowerCase();
        const existingIndex = storedLeaderboard.findIndex(entry => entry.username.toLowerCase() === normalizedUsername);

        if (existingIndex !== -1) {
            const updatedEntry = { ...storedLeaderboard[existingIndex] };
            updatedEntry.score = Math.max(updatedEntry.score, score);
            updatedEntry.moves = Math.min(updatedEntry.moves, moves);
            storedLeaderboard[existingIndex] = updatedEntry;
        } else {
            storedLeaderboard.push({ username, score, moves });
        }

        storedLeaderboard.sort((a, b) => b.score - a.score);

        setLeaderboard(storedLeaderboard);
        localStorage.setItem('leaderboard', JSON.stringify(storedLeaderboard));
    }, [username, score, moves, rows, cols]);

    const handlePlayAgain = () => {
        navigate('/');
    };

    const currentRank = leaderboard.findIndex(entry => entry.username.toLowerCase() === username.toLowerCase()) + 1;

    return (
        <div>
            <h2 className="mb-3">Game Results</h2>
            <div className="mb-3">
                <p>Player: {username}</p>
                <p>Score: {score}</p>
                <p>Rank: {currentRank} out of {leaderboard.length}</p>
                <p>Total Cards: {totalCards}</p>
                <p>Moves: {moves}</p>
            </div>

            <h3 className="mb-3">Leaderboard</h3>
            {leaderboard.length > 0 ? (
                <table className="table">
                    <thead>
                    <tr>
                        <th>Rank</th>
                        <th>Username</th>
                        <th>Score</th>
                        <th>Moves</th>
                    </tr>
                    </thead>
                    <tbody>
                    {leaderboard.map((entry, index) => (
                        <tr key={index}>
                            <td>{index + 1}</td>
                            <td>{entry.username}</td>
                            <td>{entry.score}</td>
                            <td>{entry.moves}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            ) : (
                <p>No scores yet.</p>
            )}

            <button className="btn btn-primary" onClick={handlePlayAgain}>Play Again</button>
        </div>
    );
};

export default ResultsScreen;