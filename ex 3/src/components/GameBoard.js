import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import shuffleArray from '../utils/shuffleArray';

/**
 * The Component that displays the game board
 * Manages the state of cards, open and matched pairs, and move count
 */
const GameBoard = () => {
    const location = useLocation();
    const { username, cols, delay,rows,totalCards } = location.state;
    const [cards, setCards] = useState([]);
    const [openCards, setOpenCards] = useState([]);
    const [matchedCards, setMatchedCards] = useState([]);
    const [moves, setMoves] = useState(0);
    const navigate = useNavigate();

    /**
     * Creates the deck of cards based on the game settings
     * Shuffles the deck randomly
     * @param none
     * @exception none
     * @return none
     */
    useEffect(() => {
        const images = [...Array(totalCards/2)].map((_, i) => `images/${i}.jpg`);
        const deck = shuffleArray([...images, ...images]);
        const cards = deck.map((image, i) => ({ id: i, image, matched: false }));
        setCards(cards);
    }, [totalCards]);


    /**
     * Handles the click on a card
     * Opens new cards, marks matching pairs, and closes non-matching pairs after a set delay
     * Keeps track of the move count
     * @param card - The card object that was clicked
     * @exception none
     */
    const handleCardClick = (card) => {
        if (matchedCards.some((c) => c.id === card.id)) {
            return;
        }

        if (openCards.length === 1) {
            const [firstCard] = openCards;
            if (firstCard.image === card.image) {
                setMatchedCards([...matchedCards, firstCard, card]);
                setOpenCards([]);
            } else {
                setOpenCards([...openCards, card]);
                setTimeout(() => {
                    setOpenCards([]);
                }, delay * 1000);
            }
        } else {
            setOpenCards([card]);
        }

        setMoves(moves + 1);
    };


    /**
     * Calculates the player's score based on the number of cards, delay setting, and move count
     * @param moves - The number of moves made by the player
     * @param totalCards - The total number of cards in the game
     * @param delay - The delay setting for revealing cards
     * @exception none
     * @return {number} - The calculated score
     */
    const calculateScore = (moves, totalCards, delay) => {
        // Base score based on the total number of cards
        const baseScore = totalCards * 10;

        // Bonus score based on the delay setting
        const delayBonus = (delay - 0.5) * 100;

        // Penalty for each move
        const movePenalty = moves * 2;

        // Calculate the final score
        const score = baseScore + delayBonus - movePenalty;

        return Math.max(score, 0); // Ensure the score is never negative
    };

    return (
        <div>
            <div>
                <p>Player: {username}</p>
                <p>Moves: {moves}</p>
                <button className="btn btn-danger" onClick={() => navigate('/')}>Quit Game</button>
                {matchedCards.length === cards.length && (
                    <button
                        className="btn btn-success"
                        onClick={() =>
                            navigate('/results', {
                                state: {
                                    username,
                                    score: calculateScore(moves, rows * cols, delay),
                                    moves,
                                    delay,
                                    rows,
                                    cols,
                                },
                            })
                        }
                    >
                        View Results
                    </button>
                )}
            </div>
            <div
                style={{
                    display: 'grid',
                    gridTemplateColumns: `repeat(${cols}, 1fr)`,
                    gap: '10px',
                }}
            >
                {cards.map((card) => (
                    <div
                        key={card.id}
                        onClick={() => handleCardClick(card)}
                        style={{
                            width: '100px',
                            height: '100px',
                            backgroundImage: openCards.some((c) => c.id === card.id) || matchedCards.some((c) => c.id === card.id)
                                ? `url(${card.image})`
                                : 'url(images/card.jpg)',
                            backgroundSize: 'cover',
                            cursor: 'pointer',
                        }}
                    />
                ))}
            </div>
        </div>
    );
};

export default GameBoard;