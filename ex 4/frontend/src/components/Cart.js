import React, { useState, useEffect } from 'react';
import { usePizzaContext } from '../context/PizzaContext.js';
import PizzaItem from './PizzaItem.js';
import { Link } from "react-router-dom";
import { Container, Card, Alert, Row, Col } from 'react-bootstrap';

/**
 * A React component that renders the cart page showing all pizzas added to the cart, their total price, and options to edit or remove them.
 *
 * @returns {JSX.Element} The rendered Cart component.
 */
function Cart() {
    const { state, dispatch } = usePizzaContext();
    const { cart } = state;
    const [totalPrice, setTotalPrice] = useState(0);

    useEffect(() => {
        /**
         * Calculates the total price of all pizzas in the cart.
         *
         * @returns {number} The calculated total price.
         */
        const calculateTotalPrice = () => {
            return cart.reduce((acc, pizza) => acc + (15 + pizza.ingredients.length * 3), 0);
        };
        setTotalPrice(calculateTotalPrice());
        dispatch({ type: 'SET_TOTAL_PRICE', payload: calculateTotalPrice() });
    }, [cart, dispatch]);

    /**
     * Handles removing a pizza from the cart.
     *
     * @param {number} indexToRemove - The index of the pizza to remove.
     */
    const handleRemovePizza = (indexToRemove) => {
        dispatch({ type: 'REMOVE_PIZZA_FROM_CART', payload: indexToRemove });
    };

    /**
     * Handles editing a pizza in the cart.
     *
     * @param {number} indexToEdit - The index of the pizza to edit.
     * @param {Array<string>} newIngredients - The new list of ingredients for the pizza.
     */
    const handleEditPizza = (indexToEdit, newIngredients) => {
        const updatedCart = cart.map((pizza, index) => {
            if (index === indexToEdit) {
                return { ...pizza, ingredients: newIngredients };
            }
            return pizza;
        });
        dispatch({ type: 'UPDATE_CART', payload: updatedCart });

        const newTotalPrice = updatedCart.reduce((acc, pizza) => acc + (15 + pizza.ingredients.length * 3), 0);
        setTotalPrice(newTotalPrice);
        dispatch({ type: 'SET_TOTAL_PRICE', payload: newTotalPrice });
    };

    return (
        <Container>
            <h2>Cart</h2>
            {cart.length === 0 ? (
                <Alert variant="warning">Your cart is empty.</Alert>
            ) : (
                <div>
                    {cart.map((pizza, index) => (
                        <PizzaItem
                            key={index}
                            index={index}
                            pizza={pizza}
                            ingredients={state.ingredients}
                            onRemove={handleRemovePizza}
                            onEdit={handleEditPizza}
                        />
                    ))}
                    <Card className="mt-3">
                        <Card.Body>
                            <h4>Total Price: ${totalPrice}</h4>
                        </Card.Body>
                    </Card>
                </div>
            )}
            <Row className="mt-3 justify-content-center">
                <Col xs="auto" className="px-1">
                    <Link to={cart.length > 0 ? "/checkout" : "#"} className={`btn btn-success ${cart.length === 0 && 'disabled'}`} onClick={(e) => cart.length === 0 && e.preventDefault()}>
                        Checkout
                    </Link>
                </Col>
                <Col xs="auto" className="px-1">
                    <Link to="/" className="btn btn-secondary">Go to Home</Link>
                </Col>
                <Col xs="auto" className="px-1">
                    <Link to="/pizza-builder" className="btn btn-secondary">Return to Build Pizza</Link>
                </Col>
            </Row>
        </Container>
    );
}

/**
 * The default export of the Cart component.
 *
 * @type {React.FunctionComponent}
 */
export default Cart;
