import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

/**
 * A React component that renders a pizza item with options to edit or remove the pizza.
 *
 * @param {Object} props - The component props.
 * @param {Object} props.pizza - The pizza object containing the ingredients.
 * @param {number} props.index - The index of the pizza in the list.
 * @param {Array} props.ingredients - The list of available ingredients.
 * @param {Function} props.onEdit - The function to call when the pizza is edited.
 * @param {Function} props.onRemove - The function to call when the pizza is removed.
 * @returns {JSX.Element} The rendered PizzaItem component.
 */
function PizzaItem({ pizza, index, ingredients = [], onEdit, onRemove }) {
    const [editMode, setEditMode] = useState(false);
    const [selectedIngredients, setSelectedIngredients] = useState(pizza.ingredients || []);
    const [price, setPrice] = useState(15 + selectedIngredients.length * 3);

    // Update selectedIngredients and price when pizza.ingredients changes
    useEffect(() => {
        setSelectedIngredients(pizza.ingredients || []);
        setPrice(15 + (pizza.ingredients ? pizza.ingredients.length * 3 : 0));
    }, [pizza.ingredients]);

    /**
     * Handles the selection or deselection of an ingredient.
     *
     * @param {Object} ingredient - The ingredient object.
     */
    const handleIngredientSelect = (ingredient) => {
        let newSelectedIngredients;
        if (selectedIngredients.includes(ingredient)) {
            newSelectedIngredients = selectedIngredients.filter(item => item !== ingredient);
        } else {
            newSelectedIngredients = [...selectedIngredients, ingredient];
        }
        setSelectedIngredients(newSelectedIngredients);
        setPrice(15 + newSelectedIngredients.length * 3);
    };

    /**
     * Handles the save action when editing the pizza.
     */
    const handleSaveEdit = () => {
        onEdit(index, selectedIngredients);
        setEditMode(false);
    };

    return (
        <div className="card mb-3">
            <div className="card-body">
                {editMode ? (
                    <div>
                        <h5 className="card-title">Edit Pizza</h5>
                        <div className="d-flex flex-wrap">
                            {ingredients.map(ingredient => (
                                <div
                                    key={ingredient.name}
                                    className={`ingredient-item ${selectedIngredients.includes(ingredient) ? 'selected' : ''}`}
                                    onClick={() => handleIngredientSelect(ingredient)}
                                    style={{
                                        cursor: 'pointer',
                                        border: '1px solid #ccc',
                                        borderRadius: '5px',
                                        margin: '5px',
                                        padding: '10px',
                                        textAlign: 'center',
                                        width: '100px',
                                        backgroundColor: selectedIngredients.includes(ingredient) ? '#d4edda' : '#fff' // Highlight selected ingredient
                                    }}
                                >
                                    <img src={ingredient.imageUrl} alt={ingredient.name} width="50" />
                                    <div>{ingredient.name}</div>
                                </div>
                            ))}
                        </div>
                        <button className="btn btn-primary mr-2 mt-3" onClick={handleSaveEdit}>Save</button>
                        <button className="btn btn-secondary mt-3" onClick={() => setEditMode(false)}>Cancel</button>
                    </div>
                ) : (
                    <div>
                        <h5 className="card-title">Pizza (${price})</h5>
                        <ul className="list-group">
                            {pizza.ingredients && pizza.ingredients.length > 0 ? (
                                pizza.ingredients.map(ingredient => (
                                    <li key={ingredient.name} className="list-group-item">
                                        <img src={ingredient.imageUrl} alt={ingredient.name} width="30" /> {ingredient.name}
                                    </li>
                                ))
                            ) : (
                                <li className="list-group-item">No ingredients selected</li>
                            )}
                        </ul>
                        <button className="btn btn-primary mr-2 mt-3" onClick={() => setEditMode(true)}>Edit</button>
                        <button className="btn btn-danger mt-3" onClick={() => onRemove(index)}>Remove</button>
                    </div>
                )}
            </div>
        </div>
    );
}

/**
 * The default export of the PizzaItem component.
 *
 * @type {React.FunctionComponent}
 */
export default PizzaItem;
