import React from 'react';
import { Link } from 'react-router-dom';
import { Container, Button } from 'react-bootstrap';

/**
 * A React component that renders the home page of the Pizza Builder application.
 *
 * @returns {JSX.Element} The rendered Home component.
 */
function Home() {
    return (
        <Container className="text-center mt-5">
            <h1>Welcome to Pizza Builder</h1>
            <Link to="/pizza-builder">
                <Button variant="primary" className="m-2">Start a New Order</Button>
            </Link>
            <br />
            <Link to="/order-details">
                <Button variant="secondary" className="m-2">Check an Order</Button>
            </Link>
        </Container>
    );
}

/**
 * The default export of the Home component.
 *
 * @type {React.FunctionComponent}
 */
export default Home;
