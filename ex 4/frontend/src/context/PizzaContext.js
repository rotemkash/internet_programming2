import React, { createContext, useReducer, useContext } from 'react';

/**
 * Creates a context for managing the state of the pizza application.
 */
const PizzaContext = createContext();

/**
 * The initial state of the pizza application.
 */
const initialState = {
    ingredients: [],
    cart: [],
    order: {},
    totalPrice: 0,
};


/**
 * The reducer function that updates the state based on the action type.
 *
 * @param {Object} state - The current state of the application.
 * @param {Object} action - The action object containing the type and payload.
 * @returns {Object} The new state after applying the action.
 */
const reducer = (state, action) => {
    switch (action.type) {
        case 'SET_INGREDIENTS':
            return { ...state, ingredients: action.payload };
        case 'PLACE_ORDER':
            const newOrder = action.payload;
            const updatedOrders = [...(JSON.parse(localStorage.getItem('orders')) || []), newOrder];
            localStorage.setItem('orders', JSON.stringify(updatedOrders));
            return { ...state, order: newOrder, cart: [], totalPrice: 0 };
        case 'ADD_PIZZA_TO_CART':
            return { ...state, cart: [...state.cart, action.payload] };
        case 'REMOVE_PIZZA_FROM_CART':
            return {
                ...state,
                cart: state.cart.filter((_, index) => index !== action.payload),
            };
        case 'UPDATE_CART':
            return { ...state, cart: action.payload };
        case 'CLEAR_CART':
            return { ...state, cart: [] };
        case 'SET_TOTAL_PRICE':
            return { ...state, totalPrice: action.payload };
        default:
            return state;
    }
};

/**
 * The PizzaProvider component that provides the state and dispatch function
 * to its children components.
 *
 * @param {Object} props - The props object containing children.
 * @param {React.ReactNode} props.children - The children components to render.
 * @returns {JSX.Element} The rendered PizzaProvider component.
 */
export const PizzaProvider = ({ children }) => {
    const [state, dispatch] = useReducer(reducer, initialState);

    return (
        <PizzaContext.Provider value={{ state, dispatch }}>
            {children}
        </PizzaContext.Provider>
    );
};

/**
 * A custom hook that provides access to the PizzaContext.
 *
 * @returns {Object} An object containing the state and dispatch function from the PizzaContext.
 */
export const usePizzaContext = () => useContext(PizzaContext);
