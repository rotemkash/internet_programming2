# React exercise—Internet Programming Course
This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).
You need to add relevant npm libraries and of course run npm install.
You are given sample images for the project in the public folder. Feel free to use them or replace them with your own images.
## Authors

Name and email:
Rotem Kashani rotemkash@edu.hac.ac.il and David Koplev davidkop@edu.hac.ac.il
## General Information
This is a memory card game built with React.
The game involves flipping cards and finding matching pairs.
The player needs to enter a username and can customize settings like the number of rows and columns,
as well as the delay for revealing card pairs.
The game keeps track of the player's score and moves, and displays a leaderboard at the end.

## Components

- `StartScreen`: Displays the initial screen where the player enters their username and adjusts game settings.
- `GameBoard`: Renders the game board with cards and handles card flipping and matching logic.
- `ResultsScreen`: Shows the player's score, rank, and the leaderboard after the game is over.

## Score Calculation

The player's score is calculated based on the following factors:

- **Base Score**: Determined by the total number of cards in the game.
  Each card adds 10 points to the base score.(`baseScore = totalCards * 10`)
- **Delay Bonus**: A bonus is added based on the selected delay setting for revealing card pairs.
  Longer the delay,the higher the bonus (up to a maximum of 100 points).(`delayBonus = (delay - 0.5) * 100`)
- **Move Penalty**: A penalty is deducted for each move made by the player. 
    Each move subtracts 2 points from the score.(`movePenalty = moves * 2`)

The formula for calculating the final score is:

`score = baseScore + delayBonus - movePenalty`

The score is capped at a minimum of 0 points.

## Card Selection Algorithm

The game uses the Fisher-Yates shuffle algorithm to randomly select and arrange the cards for each new game.
This algorithm ensures that the cards are shuffled in an unbiased manner,
providing a fair distribution of card positions.

The `shuffleArray` function in `src/utils/shuffleArray.js` implements the Fisher-Yates shuffle algorithm.
It takes an array as input and returns a shuffled version of that array.
## Available Scripts

In the project directory, you can run:

### `npm start`

Run the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

The page will reload when you make changes.\
You may also see any lint errors in the console.

### `npm test`

Launch the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified, and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

### `npm run eject`

**Note: this is a one-way operation. Once you `eject`, you can't go back!**

If you aren't satisfied with the build tool and configuration choices, you can `eject` at any time. This command will remove the single build dependency from your project.

Instead,
it will copy all the configuration files and the transitive dependencies 
(webpack, Babel, ESLint, etc.) right into your project, so you have full control over them. 
All of the commands except `eject` will still work, but they will point to the copied scripts, so you can tweak them.
At this point, you're on your own.

You don't have to ever use `eject`. The curated feature set is suitable for small and middle deployments, and you shouldn't feel obligated to use this feature. However, we understand that this tool wouldn't be useful if you couldn't customize it when you are ready for it.

## Learn More

You can learn more in the [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started).

To learn React, check out the [React documentation](https://reactjs.org/).

### Code Splitting

This section has moved here: [https://facebook.github.io/create-react-app/docs/code-splitting](https://facebook.github.io/create-react-app/docs/code-splitting)

### Analyzing the Bundle Size

This section has moved here: [https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size](https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size)

### Making a Progressive Web App

This section has moved here: [https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app](https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app)

### Advanced Configuration

This section has moved here: [https://facebook.github.io/create-react-app/docs/advanced-configuration](https://facebook.github.io/create-react-app/docs/advanced-configuration)

### Deployment

This section has moved here: [https://facebook.github.io/create-react-app/docs/deployment](https://facebook.github.io/create-react-app/docs/deployment)

### `npm run build` fails to minify

This section has moved here: [https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify](https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify)
