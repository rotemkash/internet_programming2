/**
 * Shuffles the elements of an array using the Fisher-Yates shuffle algorithm
 * @param {Array} array - The array to be shuffled
 * @exception none
 * @return {Array} - The shuffled array
 */
function shuffleArray(array) {
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
    }
    return array;
}

export default shuffleArray;