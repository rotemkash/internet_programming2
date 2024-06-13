import React from 'react';

/**
 * A React component that renders a list of ingredients with the ability to select or deselect them.
 *
 * @param {Object} props - The component props.
 * @param {Array} props.ingredients - The list of ingredients to display.
 * @param {Array} props.selectedIngredients - The list of currently selected ingredients.
 * @param {Function} props.onIngredientSelect - The function to call when an ingredient is selected or deselected.
 * @returns {JSX.Element} The rendered IngredientList component.
 */
function IngredientList({ ingredients, selectedIngredients, onIngredientSelect }) {
    return (
        <ul className="list-group">
            {ingredients.map(ingredient => (
                <li
                    key={ingredient.name}
                    className={`list-group-item ${selectedIngredients.includes(ingredient) ? 'active' : ''}`}
                    onClick={() => onIngredientSelect(ingredient)}
                >
                    <img src={ingredient.imageUrl} alt={ingredient.name} width="30" /> {ingredient.name}
                </li>
            ))}
        </ul>
    );
}

/**
 * The default export of the IngredientList component.
 *
 * @type {React.FunctionComponent}
 */
export default IngredientList;
