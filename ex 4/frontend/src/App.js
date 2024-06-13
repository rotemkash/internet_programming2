import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './components/Home.js';
import PizzaBuilder from './components/PizzaBuilder.js';
import Cart from './components/Cart.js';
import Checkout from './components/Checkout.js';
import OrderConfirmation from './components/OrderConfirmation.js';
import { PizzaProvider } from './context/PizzaContext.js';
import OrderDetails from './components/OrderDetails.js'

/**
 * The main component that sets up the routing and provides the PizzaContext to its children.
 *
 * @returns {JSX.Element} The rendered React component.
 */
function App() {
    return (
        <PizzaProvider>
            <Router>
                <div className="container mt-3">
                    <Routes>
                        <Route path="/" element={<Home />} />
                        <Route path="/pizza-builder" element={<PizzaBuilder />} />
                        <Route path="/cart" element={<Cart />} />
                        <Route path="/checkout" element={<Checkout />} />
                        <Route path="/order-confirmation" element={<OrderConfirmation />} />
                        <Route path="/order-details" element={<OrderDetails />} />
                    </Routes>
                </div>
            </Router>
        </PizzaProvider>
    );
}

/**
 * The default export of the App component.
 *
 * @type {React.FunctionComponent}
 */
export default App;
