(function() {
    /**
     * Displays a confirmation modal for deleting a chat room and handles the deletion process
     * @param {HTMLElement} button - The button element that triggered the confirmation
     */
    function confirmDelete(button) {
        // Get the room ID from the button's data attribute
        let roomId = button.getAttribute('data-room-id');

        // Select the modal and its confirmation button
        let modal = $('#deleteConfirmationModal');
        let confirmBtn = modal.find('#confirmDeleteBtn');

        // Remove any existing click handlers and add a new one
        confirmBtn.off('click').on('click', function() {
            // Create a form element
            let form = document.createElement('form');
            form.method = 'post';
            form.action = '/admin/delete-chat-room';

            // Create an input element to hold the room ID
            let input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'roomId';
            input.value = roomId;

            // Append the input to the form
            form.appendChild(input);

            // Append the form to the document body
            document.body.appendChild(form);

            // Submit the form to delete the chat room
            form.submit();
        });

        // Show the confirmation modal
        modal.modal('show');
    }

    // Expose the function to the global scope
    window.confirmDelete = confirmDelete;
})();
