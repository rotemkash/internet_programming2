import React, { useState, useEffect } from 'react';
import { usePizzaContext } from '../context/PizzaContext.js';
import useFetchIngredients from '../hooks/useFetchIngredients.js';
import IngredientList from './IngredientList.js';
import { Link } from 'react-router-dom';
import { Container, Row, Col, Button, ListGroup } from 'react-bootstrap';

/**
 * A React component that allows the user to build a pizza by selecting ingredients.
 *
 * @returns {JSX.Element} The rendered PizzaBuilder component.
 */
function PizzaBuilder() {
    const [selectedIngredients, setSelectedIngredients] = useState([]);
    const { state, dispatch } = usePizzaContext();
    const { ingredients } = state;

    useFetchIngredients();

    useEffect(() => {
        // Retrieve selected ingredients from localStorage when component mounts
        const storedIngredients = localStorage.getItem('selectedIngredients');
        if (storedIngredients) {
            setSelectedIngredients(JSON.parse(storedIngredients));
        }
    }, []);

    useEffect(() => {
        // Save selected ingredients to localStorage whenever it changes
        localStorage.setItem('selectedIngredients', JSON.stringify(selectedIngredients));
    }, [selectedIngredients]);

    /**
     * Handles the selection or deselection of an ingredient.
     *
     * @param {Object} ingredient - The ingredient object.
     */
    const handleIngredientSelect = (ingredient) => {
        if (selectedIngredients.includes(ingredient)) {
            setSelectedIngredients(selectedIngredients.filter(item => item !== ingredient));
        } else {
            setSelectedIngredients([...selectedIngredients, ingredient]);
        }
    };

    /**
     * Adds the pizza with the selected ingredients to the cart.
     */
    const addPizzaToCart = () => {
        if (selectedIngredients.length >= 2) {
            const price = 15 + (selectedIngredients.length * 5);
            const newPizza = { ingredients: selectedIngredients, price: price };
            dispatch({ type: 'ADD_PIZZA_TO_CART', payload: newPizza });
            setSelectedIngredients([]);
        } else {
            alert('Please select at least 2 ingredients for your pizza.');
        }
    };

    return (
        <Container>
            <h2>Build Your Pizza</h2>
            <Row>
                <Col md={6}>
                    <h4>Available Ingredients</h4>
                    <IngredientList
                        ingredients={ingredients}
                        selectedIngredients={selectedIngredients}
                        onIngredientSelect={handleIngredientSelect}
                    />
                </Col>
                <Col md={6}>
                    <h4>Selected Ingredients</h4>
                    <ListGroup>
                        {selectedIngredients.map((ingredient, index) => (
                            <ListGroup.Item key={index}>
                                <img src={ingredient.imageUrl} alt={ingredient.name} width="30" /> {ingredient.name}
                            </ListGroup.Item>
                        ))}
                    </ListGroup>
                    <Button variant="primary" className="mt-3" onClick={addPizzaToCart}>
                        Add Pizza to Cart
                    </Button>
                    <Link to="/cart" className="btn btn-secondary mt-3 ml-2">
                        Go to Cart
                    </Link>
                </Col>
            </Row>
        </Container>
    );
}

/**
 * The default export of the PizzaBuilder component.
 *
 * @type {React.FunctionComponent}
 */
export default PizzaBuilder;
