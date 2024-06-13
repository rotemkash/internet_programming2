import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { Row, Col } from 'react-bootstrap';

/**
 * A React component that allows the user to check order details by entering an order code.
 *
 * @returns {JSX.Element} The rendered OrderDetails component.
 */
function OrderDetails() {
    const [orderCode, setOrderCode] = useState('');
    const [orderDetails, setOrderDetails] = useState(null);
    const [error, setError] = useState('');

    /**
     * Handles the action of checking the order details based on the entered order code.
     */
    const handleCheckOrder = () => {
        if (!orderCode) {
            setError('Please enter an order code.');
            return;
        }

        const orders = JSON.parse(localStorage.getItem('orders')) || [];
        const order = orders.find(order => order.code === Number(orderCode));

        if (order) {
            setOrderDetails(order);
            setError('');
        } else {
            setOrderDetails(null);
            setError('Order not found. Please check the order code and try again.');
        }
    };

    return (
        <div>
            <h2>Order Details</h2>
            <div className="form-group">
                <label htmlFor="orderCode">Enter Order Code:</label>
                <input
                    type="text"
                    className="form-control"
                    id="orderCode"
                    value={orderCode}
                    onChange={(e) => setOrderCode(e.target.value)}
                    required
                />
            </div>
            <Row className="mt-3">
                <Col xs="auto">
                    <button onClick={handleCheckOrder} className="btn btn-primary">
                        Check Order
                    </button>
                </Col>
                <Col xs="auto">
                    <Link to="/" className="btn btn-primary">
                        Return to Home
                    </Link>
                </Col>
            </Row>
            {error && <p className="text-danger mt-3">{error}</p>}
            {orderDetails && (
                <div className="mt-3">
                    <h3>Order Summary</h3>
                    <p>Order Code: {orderDetails.code}</p>
                    <p>Name: {orderDetails.userDetails.firstName} {orderDetails.userDetails.lastName}</p>
                    <p>Address: {orderDetails.userDetails.address}</p>
                    <p>Phone: {orderDetails.userDetails.phone}</p>
                    <h4>Items:</h4>
                    <ul>
                        {orderDetails.pizzas.map((pizza, index) => (
                            <li key={index}>
                                <p>Pizza</p>
                                <p>Ingredients:</p>
                                <ul>
                                    {pizza.ingredients.map((ingredient, i) => (
                                        <li key={i} className="d-flex align-items-center">
                                            <img
                                                src={ingredient.imageUrl}
                                                alt={ingredient.name}
                                                style={{ width: '50px', height: '50px', marginRight: '10px' }}
                                            />
                                            <span>{ingredient.name}</span>
                                        </li>
                                    ))}
                                </ul>
                            </li>
                        ))}
                    </ul>
                    <p>Total Price: ${orderDetails.totalPrice}</p>
                </div>
            )}
        </div>
    );
}

/**
 * The default export of the OrderDetails component.
 *
 * @type {React.FunctionComponent}
 */
export default OrderDetails;
