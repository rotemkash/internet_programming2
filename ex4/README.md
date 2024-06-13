[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/Ke_DgSzD)
## Authors

Name and email:
Rotem Kashani rotemkash@edu.hac.ac.il and David Koplev davidkop@edu.hac.ac.il

## Backend
This project uses Spring Boot to create a REST API for managing pizza orders and ingredients. The backend is responsible for handling the following operations:

1. Providing a list of predefined pizza ingredients.
2. Placing new pizza orders with user details and a list of pizzas.
3. Retrieving an existing order by its unique code.

### Controllers
- `HomeController`: Handles the root endpoint of the application and returns a welcome message.
- `IngredientController`: Provides a REST API endpoint to retrieve the list of predefined ingredients.
- `OrderController`: Handles REST API endpoints for placing new orders and retrieving existing orders by their unique code.

### Models
- `Address`: A model class representing the address details of an order.
- `Ingredient`: A model class representing a pizza ingredient with its name and image URL.
- `Order`: A model class representing a pizza order with user details and a list of pizzas.
- `Pizza`: A model class representing a pizza with a list of ingredients and its price.

### Application
- `PizzaOrderingSystemApplication`: The main class that runs the Spring Boot application.

The application data is stored in memory and is not persistent. Restarting the server will reset the orders to none.

### Running the Backend
To run the backend, you can use the following command:
`./mvnw spring-boot:run`.

This will start the Spring Boot application on `http://localhost:8080`.

### API Endpoints
- `GET /api/ingredients`: Retrieves the list of predefined ingredients.
- `POST /api/orders`: Places a new order. The request body should be a JSON representation of the `Order` object.
- `GET /api/orders/{code}`: Retrieves an order by its unique code.

## Frontend
The frontend is a React application that provides a user interface for building and ordering pizzas.
It communicates with the backend API to fetch ingredients and place orders.

### Components
- `Cart`: Displays the pizzas added to the cart and allows editing and removing pizzas.
- `Checkout`: Allows the user to enter their details and place the order.
- `Home`: The landing page that provides options to start a new order or check an existing order.
- `IngredientList`: Displays the list of available ingredients and allows selecting or deselecting them.
- `OrderConfirmation`: Displays the details of the placed order.
- `OrderDetails`: Allows the user to enter an order code and retrieve the details of an existing order.
- `PizzaBuilder`: Allows the user to select ingredients and build a pizza. It displays the available ingredients on one 
side and the selected ingredients on the other side. The user can add a pizza to the cart only if they have selected at 
least two ingredients. The component also calculates the price of the pizza based on the selected ingredients.
- `PizzaItem`: Displays a pizza with its ingredients and provides options to edit or remove the pizza.

### Context
The application state is managed using the React Context API and a reducer pattern. The `PizzaContext` provides the 
global state and a dispatch function to update the state to its child components.

The `PizzaContext` maintains the following state:
- `ingredients`: List of available ingredients
- `cart`: List of pizzas added to the cart
- `order`: Details of the current order
- `totalPrice`: Total price of all pizzas in the cart

The `reducer` function handles state updates based on different action types, such as adding/removing pizzas from the
cart, placing an order, and setting ingredients.

The `PizzaProvider` component uses the `useReducer` hook to create the state and dispatch function, which it then 
provides to its child components through the `PizzaContext.Provider`.

Child components can access the state and dispatch function using the `usePizzaContext` custom hook, which internally
uses the `useContext` hook to consume the `PizzaContext`.

This centralized state management approach allows for easy data sharing and consistent state across the application.

### Hooks
- `useFetchIngredients`: A custom hook that fetches the list of ingredients from the backend API and updates the state
in the `PizzaContext`.

### Running the Frontend
To run the frontend, navigate to the `frontend` directory by the command: `cd frontend` and use the following commands:
`npm install` and `npm start`.
This will start the React development server and open the application in your default browser at `http://localhost:3000`.
The backend should be running before starting the frontend, as it relies on the API endpoints provided by the backend.