import { useEffect } from 'react';
import { usePizzaContext } from '../context/PizzaContext.js';

/**
 * A custom React hook that fetches ingredients from the server and dispatches
 * an action to update the ingredients in the PizzaContext.
 *
 * @returns {void}
 *
 * @exception {Error} Throws an error if the fetch request fails.
 */
const useFetchIngredients = () => {
    const { dispatch } = usePizzaContext();

    useEffect(() => {
        fetch('/api/ingredients')
            .then((response) => response.json())
            .then((data) => dispatch({ type: 'SET_INGREDIENTS', payload: data }))
            .catch((error) => console.error('Error fetching ingredients:', error));
    }, [dispatch]);
};

/**
 * The default export of the useFetchIngredients hook.
 *
 * @type {React.EffectCallback}
 */
export default useFetchIngredients;