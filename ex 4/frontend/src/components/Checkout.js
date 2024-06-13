import React, { useState, useEffect } from 'react';
import { usePizzaContext } from '../context/PizzaContext.js';
import { Link } from 'react-router-dom';
import { Container, Form, Button } from 'react-bootstrap';
import Cookies from 'js-cookie';

/**
 * A React component that renders the checkout page for placing an order.
 *
 * @returns {JSX.Element} The rendered Checkout component.
 */
function Checkout() {
    const { state, dispatch } = usePizzaContext();
    const { cart } = state;
    const [userDetails, setUserDetails] = useState({
        firstName: '',
        lastName: '',
        address: '',
        phone: ''
    });
    const [orderPlaced, setOrderPlaced] = useState(false);
    const [showAddressHint, setShowAddressHint] = useState(false);

    useEffect(() => {
        const savedUserDetails = Cookies.get('userDetails');
        if (savedUserDetails) {
            setUserDetails(JSON.parse(savedUserDetails));
        }
    }, []);

    /**
     * Handles the change event for input fields.
     *
     * @param {Object} e - The event object.
     */
    const handleChange = (e) => {
        const { name, value } = e.target;
        setUserDetails(prevDetails => ({ ...prevDetails, [name]: value }));
        setShowAddressHint(!value.includes(','));
    };

    /**
     * Calculates the price of a pizza based on its ingredients.
     *
     * @param {Object} pizza - The pizza object.
     * @returns {number} The calculated price of the pizza.
     */
    const calculatePizzaPrice = (pizza) => {
        return 15 + pizza.ingredients.length * 3;
    };

    /**
     * Handles the submission of the order.
     */
    const handlePlaceOrder = () => {
        if (!userDetails.firstName || !userDetails.lastName || !userDetails.address || !userDetails.phone) {
            alert('Please fill in all the details');
            return;
        }

        const phoneRegex = /^\d{10}$/;
        if (!phoneRegex.test(userDetails.phone)) {
            alert('Please enter a valid 10-digit phone number');
            return;
        }

        if (!userDetails.address.includes(',') || userDetails.address.split(',').length !== 3) {
            alert('Please enter a valid address with street, house number, and city separated by commas');
            return;
        }

        const orderCode = Math.floor(Math.random() * 1000000);

        const order = {
            code: orderCode,
            userDetails,
            pizzas: cart.map(pizza => ({
                ...pizza,
                price: calculatePizzaPrice(pizza)
            })),
            totalPrice: cart.reduce((acc, pizza) => acc + calculatePizzaPrice(pizza), 0)
        };

        dispatch({ type: 'PLACE_ORDER', payload: order });
        dispatch({ type: 'CLEAR_CART' });
        Cookies.set('userDetails', JSON.stringify(userDetails), { expires: 7 });
        setOrderPlaced(true);
    };

    return (
        <Container>
            <h2>Checkout</h2>
            <Form>
                <Form.Group controlId="firstName">
                    <Form.Label>First Name</Form.Label>
                    <Form.Control
                        type="text"
                        name="firstName"
                        value={userDetails.firstName}
                        onChange={handleChange}
                        required
                    />
                </Form.Group>
                <Form.Group controlId="lastName">
                    <Form.Label>Last Name</Form.Label>
                    <Form.Control
                        type="text"
                        name="lastName"
                        value={userDetails.lastName}
                        onChange={handleChange}
                        required
                    />
                </Form.Group>
                <Form.Group controlId="address">
                    <Form.Label>Address</Form.Label>
                    <Form.Control
                        type="text"
                        name="address"
                        value={userDetails.address}
                        onChange={handleChange}
                        required
                    />
                    {showAddressHint && <Form.Text className="text-muted">Please enter the address with street, house number, and city separated by commas.</Form.Text>}
                </Form.Group>
                <Form.Group controlId="phone">
                    <Form.Label>Phone</Form.Label>
                    <Form.Control
                        type="text"
                        name="phone"
                        value={userDetails.phone}
                        onChange={handleChange}
                        required
                    />
                </Form.Group>
                <div className="d-flex mt-3">
                    <Button variant="primary" onClick={handlePlaceOrder}>Place Order</Button>
                    <Link to="/" className="btn btn-secondary ml-2">Go to Home</Link>
                </div>
            </Form>
            {orderPlaced && (
                <div className="mt-3">
                    <Link to="/order-confirmation">
                        <Button variant="success" className="mt-3">Go to Order Confirmation</Button>
                    </Link>
                </div>
            )}
        </Container>
    );
}

/**
 * The default export of the Checkout component.
 *
 * @type {React.FunctionComponent}
 */
export default Checkout;
