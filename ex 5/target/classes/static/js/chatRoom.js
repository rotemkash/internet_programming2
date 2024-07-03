(function() {
    /**
     * This script handles various functionalities for a messaging system, including:
     * - Character count for message input
     * - Editing and deleting messages and comments
     * - Showing/hiding edit forms and comment sections
     * - Linkifying URLs in messages and comments
     */

    /**
     * Event listener for when the DOM content is loaded
     * Sets up character counting for message input and edit forms
     */
    document.addEventListener('DOMContentLoaded', function() {
        const messageContent = document.getElementById('messageContent');
        const charCount = document.getElementById('charCount');

        function updateCharCount() {
            charCount.textContent = messageContent.value.length;
        }

        messageContent.addEventListener('input', updateCharCount);

        // Initial count
        updateCharCount();

        const editForms = document.querySelectorAll('.edit-form');

        editForms.forEach(form => {
            const textarea = form.querySelector('.edit-message-content');
            const charCount = form.querySelector('.edit-char-count');

            function updateEditCharCount() {
                const remaining = textarea.value.length;
                charCount.textContent = remaining;

                if (remaining < 2 || remaining > 250) {
                    charCount.style.color = 'red';
                } else {
                    charCount.style.color = 'inherit';
                }
            }

            textarea.addEventListener('input', updateEditCharCount);

            // Initial count
            updateEditCharCount();
        });

        // Linkify URLs in existing messages and comments
        let messages = document.querySelectorAll('[id^="message-content-"]');
        let comments = document.querySelectorAll('[id^="comment-content-"]');

        messages.forEach(function(message) {
            message.innerHTML = linkify(message.textContent);
        });

        comments.forEach(function(comment) {
            comment.innerHTML = linkify(comment.textContent);
        });
    });

    /**
     * Shows the edit form for a specific message
     * @param {string} messageId - The ID of the message to edit
     */
    function showEditForm(messageId) {
        document.getElementById('edit-form-' + messageId).style.display = 'block';
        document.getElementById('message-content-' + messageId).style.display = 'none';
    }

    /**
     * Hides the edit form for a specific message
     * @param {string} messageId - The ID of the message being edited
     */
    function hideEditForm(messageId) {
        document.getElementById('edit-form-' + messageId).style.display = 'none';
        document.getElementById('message-content-' + messageId).style.display = 'inline';
    }

    /**
     * Shows the comment form for a specific message
     * @param {string} messageId - The ID of the message to comment on
     */
    function showCommentForm(messageId) {
        document.getElementById('comment-form-' + messageId).style.display = 'block';
    }

    /**
     * Hides the comment form for a specific message
     * @param {string} messageId - The ID of the message being commented on
     */
    function hideCommentForm(messageId) {
        document.getElementById('comment-form-' + messageId).style.display = 'none';
    }

    /**
     * Shows the edit form for a specific comment
     * @param {string} commentId - The ID of the comment to edit
     */
    function showEditCommentForm(commentId) {
        document.getElementById('edit-comment-form-' + commentId).style.display = 'block';
        document.getElementById('comment-content-' + commentId).style.display = 'none';
    }

    /**
     * Hides the edit form for a specific comment
     * @param {string} commentId - The ID of the comment being edited
     */
    function hideEditCommentForm(commentId) {
        document.getElementById('edit-comment-form-' + commentId).style.display = 'none';
        document.getElementById('comment-content-' + commentId).style.display = 'inline';
    }

    /**
     * Toggles the visibility of comments for a specific message
     * @param {string} messageId - The ID of the message whose comments are being toggled
     */
    function toggleComments(messageId) {
        const commentsSection = document.getElementById('comments-section-' + messageId);
        const commentToggle = document.getElementById('comment-toggle-' + messageId);
        const commentCountSpan = commentToggle.querySelector('span');
        const commentCount = commentCountSpan ? commentCountSpan.textContent : '0';

        if (commentsSection.style.display === 'none' || commentsSection.style.display === '') {
            commentsSection.style.display = 'block';
            commentToggle.innerHTML = '<i class="fas fa-chevron-up"></i> Hide comments (<span>' + commentCount + '</span>)';
        } else {
            commentsSection.style.display = 'none';
            commentToggle.innerHTML = '<i class="fas fa-chevron-down"></i> Show comments (<span>' + commentCount + '</span>)';
        }
    }

    /**
     * Converts URLs in text to clickable links
     * @param {string} text - The text to linkify
     * @returns {string} The linkified text
     */
    function linkify(text) {
        let urlRegex = /(https?:\/\/\S+)/g;
        return text.replace(urlRegex, function(url) {
            return '<a href="' + url + '" target="_blank" rel="noopener noreferrer">' + url + '</a>';
        });
    }

    /**
     * Displays a confirmation modal for deleting a message
     * @param {string} messageId - The ID of the message to delete
     */
    function confirmDeleteMessage(messageId) {
        $('#deleteMessageModal').modal('show');
        $('#confirmDeleteMessageBtn').off('click').on('click', function() {
            const form = document.createElement('form');
            form.method = 'post';
            form.action = '/deleteMessage';
            const input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'messageId';
            input.value = messageId;
            form.appendChild(input);
            document.body.appendChild(form);
            form.submit();
        });
    }

    /**
     * Displays a confirmation modal for deleting a comment
     * @param {string} commentId - The ID of the comment to delete
     */
    function confirmDeleteComment(commentId) {
        $('#deleteCommentModal').modal('show');
        $('#confirmDeleteCommentBtn').off('click').on('click', function() {
            const form = document.createElement('form');
            form.method = 'post';
            form.action = '/deleteComment';
            const input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'commentId';
            input.value = commentId;
            form.appendChild(input);
            document.body.appendChild(form);
            form.submit();
        });
    }

    // Expose functions to the global scope
    window.showEditForm = showEditForm;
    window.hideEditForm = hideEditForm;
    window.showCommentForm = showCommentForm;
    window.hideCommentForm = hideCommentForm;
    window.showEditCommentForm = showEditCommentForm;
    window.hideEditCommentForm = hideEditCommentForm;
    window.toggleComments = toggleComments;
    window.confirmDeleteMessage = confirmDeleteMessage;
    window.confirmDeleteComment = confirmDeleteComment;
})();
