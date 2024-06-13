import React from 'react';
import { usePizzaContext } from '../context/PizzaContext.js';
import { Link } from 'react-router-dom';
import { Container, Card, ListGroup } from 'react-bootstrap';

/**
 * A React component that displays the order confirmation details.
 *
 * @returns {JSX.Element} The rendered OrderConfirmation component.
 */
function OrderConfirmation() {
    const { state } = usePizzaContext();
    const { order } = state;

    if (!order) {
        return (
            <Container>
                <h2>No Order Found</h2>
                <Link to="/" className="btn btn-primary mt-3">Go to Home</Link>
            </Container>
        );
    }

    return (
        <Container>
            <h2>Order Confirmation</h2>
            <Card className="mt-3">
                <Card.Body>
                    <h4>Order Code: {order.code}</h4>
                    <h4>Customer Details:</h4>
                    <ListGroup>
                        <ListGroup.Item>First Name: {order.userDetails.firstName}</ListGroup.Item>
                        <ListGroup.Item>Last Name: {order.userDetails.lastName}</ListGroup.Item>
                        <ListGroup.Item>Address: {order.userDetails.address}</ListGroup.Item>
                        <ListGroup.Item>Phone: {order.userDetails.phone}</ListGroup.Item>
                    </ListGroup>
                    <h4 className="mt-3">Pizzas:</h4>
                    <ListGroup>
                        {order.pizzas.map((pizza, index) => (
                            <ListGroup.Item key={index}>
                                Ingredients: {pizza.ingredients.map(ing => ing.name).join(', ')} - Price: ${pizza.price}
                            </ListGroup.Item>
                        ))}
                    </ListGroup>
                    <h4 className="mt-3">Total Price: ${order.totalPrice}</h4>
                </Card.Body>
            </Card>
            <Link to="/" className="btn btn-primary mt-3">Go to Home</Link>
        </Container>
    );
}


/**
 * The default export of the OrderConfirmation component.
 *
 * @type {React.FunctionComponent}
 */
export default OrderConfirmation;