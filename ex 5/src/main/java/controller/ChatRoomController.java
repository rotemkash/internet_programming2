package controller;

import jakarta.validation.Valid;
import model.ChatRoom;
import model.Message;
import model.User;
import model.Comment;
import repository.ChatRoomRepository;
import repository.MessageRepository;
import repository.UserRepository;
import repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Controller class for managing forum operations.
 * This class handles various operations related to forums, messages, and comments,
 * including retrieving forum content, sending messages, posting comments, and
 * editing or deleting messages and comments.
 */
@Controller
public class ChatRoomController {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final CommentRepository commentRepository;

    private static final Logger logger = Logger.getLogger(ChatRoomController.class.getName());

    /**
     * Constructs a ChatRoomController with the necessary repositories.
     *
     * @param messageRepository The repository for message operations.
     * @param userRepository The repository for user operations.
     * @param chatRoomRepository The repository for forum operations.
     * @param commentRepository The repository for comment operations.
     */
    @Autowired
    public ChatRoomController(MessageRepository messageRepository, UserRepository userRepository,
                              ChatRoomRepository chatRoomRepository, CommentRepository commentRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.commentRepository = commentRepository;
    }

    /**
     * Handles the request to view a specific forum.
     *
     * @param roomId The ID of the forum to view.
     * @param model The model to add attributes to.
     * @param session The HTTP session containing user information.
     * @return The name of the view to render.
     */
    @GetMapping("/chatroom")
    public String getChatRoom(@RequestParam(required = false) Long roomId, Model model, HttpSession session) {
        if (roomId == null) {
            return "redirect:/chat-rooms";
        }

        Optional<User> optionalCurrentUser = getCurrentUser(session);
        if (optionalCurrentUser.isEmpty()) {
            return "redirect:/login";
        }
        User currentUser = optionalCurrentUser.get();

        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Forum not found"));
        List<Message> messages = chatRoom.getMessages();

        // Prepare messages and comments for display
        messages.forEach(this::prepareForDisplay);

        model.addAttribute("messages", messages);
        model.addAttribute("chatRoom", chatRoom);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("newMessage", new Message());
        model.addAttribute("newComment", new Comment());
        model.addAttribute("username", currentUser.getUsername());

        return "chatroom";
    }

    /**
     * Handles the request to send a new message or edit an existing message in a forum.
     *
     * @param newMessage The new message to be sent or the edited message.
     * @param bindingResult The result of the validation.
     * @param roomId The ID of the forum where the message is being sent.
     * @param messageId The ID of the message being edited (null for new messages).
     * @param session The HTTP session containing user information.
     * @param model The model to add attributes to.
     * @return A string indicating where to redirect after the operation.
     */
    @PostMapping("/sendMessage")
    public String sendMessage(@Valid @ModelAttribute("newMessage") Message newMessage,
                              BindingResult bindingResult,
                              @RequestParam Long roomId,
                              @RequestParam(required = false) Long messageId,
                              HttpSession session,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("messageError", "Message must be between 2 and 250 characters");
            return getChatRoom(roomId, model, session);
        }

        Optional<User> optionalCurrentUser = getCurrentUser(session);
        if (optionalCurrentUser.isEmpty()) {
            return "redirect:/login";
        }
        User currentUser = optionalCurrentUser.get();

        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Forum not found"));

        Message message;
        if (messageId != null) {
            message = messageRepository.findById(messageId)
                    .orElseThrow(() -> new RuntimeException("Message not found"));
            if (!message.getUser().getId().equals(currentUser.getId())) {
                throw new RuntimeException("You are not authorized to edit this message");
            }
            message.setContent(prepareForStorage(newMessage.getContent()));
            message.setEdited(true);
        } else {
            message = new Message();
            message.setContent(prepareForStorage(newMessage.getContent()));
            message.setTimestamp(LocalDateTime.now());
            message.setUser(currentUser);
            message.setChatRoom(chatRoom);
            message.setId(newMessage.getId());
        }

        messageRepository.save(message);

        Long savedMessageId = message.getId();
        logger.info("Message with ID " + savedMessageId + " has been saved successfully.");

        userRepository.save(currentUser);

        return "redirect:/chatroom?roomId=" + roomId;
    }

    /**
     * Handles the request to delete a message from a forum.
     *
     * @param messageId The ID of the message to be deleted.
     * @param session The HTTP session containing user information.
     * @param model The model to add attributes to.
     * @return A string indicating where to redirect after the operation.
     */
    @PostMapping("/deleteMessage")
    public String deleteMessage(@RequestParam Long messageId, HttpSession session, Model model) {
        Optional<User> optionalCurrentUser = getCurrentUser(session);
        if (optionalCurrentUser.isEmpty()) {
            return "redirect:/login";
        }
        User currentUser = optionalCurrentUser.get();

        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found"));

        if (!message.getUser().getId().equals(currentUser.getId()) && !currentUser.isAdmin()) {
            model.addAttribute("error", "You are not authorized to delete this message");
            return getChatRoom(message.getChatRoom().getId(), model, session);
        }

        messageRepository.delete(message);
        model.addAttribute("success", "Message deleted successfully");

        return "redirect:/chatroom?roomId=" + message.getChatRoom().getId();
    }

    /**
     * Handles the request to post a new comment on a message.
     *
     * @param messageId The ID of the message being commented on.
     * @param newComment The new comment to be posted.
     * @param bindingResult The result of the validation.
     * @param session The HTTP session containing user information.
     * @param model The model to add attributes to.
     * @return A string indicating where to redirect after the operation.
     */
    @PostMapping("/postComment")
    public String postComment(@RequestParam Long messageId,
                              @Valid @ModelAttribute("newComment") Comment newComment,
                              BindingResult bindingResult,
                              HttpSession session,
                              Model model) {
        Optional<User> optionalCurrentUser = getCurrentUser(session);
        if (optionalCurrentUser.isEmpty()) {
            return "redirect:/login";
        }
        User currentUser = optionalCurrentUser.get();

        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found"));

        if (bindingResult.hasErrors()) {
            model.addAttribute("commentError", "Comment must be between 2 and 120 characters");
            model.addAttribute("commentErrorMessageId", messageId);
            return getChatRoom(message.getChatRoom().getId(), model, session);
        }

        Comment comment = new Comment();
        comment.setContent(prepareForStorage(newComment.getContent()));
        comment.setTimestamp(LocalDateTime.now());
        comment.setUser(currentUser);
        comment.setMessage(message);

        List<Comment> comments = message.getComments();
        comments.add(comment);
        message.setComments(comments);

        comment.setId(null);
        commentRepository.save(comment);

        return "redirect:/chatroom?roomId=" + message.getChatRoom().getId();
    }

    /**
     * Handles the request to edit an existing comment.
     *
     * @param commentId The ID of the comment to be edited.
     * @param editComment The edited comment content.
     * @param bindingResult The result of the validation.
     * @param session The HTTP session containing user information.
     * @param model The model to add attributes to.
     * @return A string indicating where to redirect after the operation.
     */
    @PostMapping("/editComment")
    public String editComment(@RequestParam Long commentId,
                              @Valid @ModelAttribute("editComment") Comment editComment,
                              BindingResult bindingResult,
                              HttpSession session,
                              Model model) {
        Optional<User> optionalCurrentUser = getCurrentUser(session);
        if (optionalCurrentUser.isEmpty()) {
            return "redirect:/login";
        }
        User currentUser = optionalCurrentUser.get();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("You are not authorized to edit this comment");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("commentError", "Comment must be between 2 and 120 characters");
            model.addAttribute("commentErrorMessageId", comment.getMessage().getId());
            return getChatRoom(comment.getMessage().getChatRoom().getId(), model, session);
        }

        comment.setContent(prepareForStorage(editComment.getContent()));
        comment.setEdited(true);
        commentRepository.save(comment);

        return "redirect:/chatroom?roomId=" + comment.getMessage().getChatRoom().getId();
    }

    /**
     * Handles the request to delete a comment.
     *
     * @param commentId The ID of the comment to be deleted.
     * @param session The HTTP session containing user information.
     * @param model The model to add attributes to.
     * @return A string indicating where to redirect after the operation.
     */
    @PostMapping("/deleteComment")
    public String deleteComment(@RequestParam Long commentId, HttpSession session, Model model) {
        Optional<User> optionalCurrentUser = getCurrentUser(session);
        if (optionalCurrentUser.isEmpty()) {
            return "redirect:/login";
        }
        User currentUser = optionalCurrentUser.get();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getUser().getId().equals(currentUser.getId()) && !currentUser.isAdmin()) {
            model.addAttribute("error", "You are not authorized to delete this comment");
            return getChatRoom(comment.getMessage().getChatRoom().getId(), model, session);
        }

        Long chatRoomId = comment.getMessage().getChatRoom().getId();
        commentRepository.delete(comment);
        model.addAttribute("success", "Comment deleted successfully");

        return "redirect:/chatroom?roomId=" + chatRoomId;
    }

    /**
     * Provides a new instance of Message for the form.
     *
     * @return A new Message instance.
     */
    @ModelAttribute("newMessage")
    public Message newMessage() {
        return new Message();
    }

    /**
     * Provides a new instance of Comment for the form.
     *
     * @return A new Comment instance.
     */
    @ModelAttribute("newComment")
    public Comment newComment() {
        return new Comment();
    }

    /**
     * Provides a new instance of Comment for editing.
     *
     * @return A new Comment instance.
     */
    @ModelAttribute("editComment")
    public Comment editComment() {
        return new Comment();
    }

    /**
     * Retrieves the current user from the session.
     *
     * @param session The HTTP session containing user information.
     * @return An Optional containing the current User, or empty if not found.
     */
    private Optional<User> getCurrentUser(HttpSession session) {
        return Optional.ofNullable((User) session.getAttribute("currentUser"))
                .or(() -> {
                    Long userId = (Long) session.getAttribute("userId");
                    if (userId != null) {
                        return userRepository.findById(userId);
                    }
                    return Optional.empty();
                });
    }

    /**
     * Prepares a message for display by escaping HTML characters.
     *
     * @param message The message to prepare for display.
     */
    private void prepareForDisplay(Message message) {
        message.setContent(message.getContent().replace("<", "&lt;").replace(">", "&gt;"));
        message.getComments().forEach(comment ->
                comment.setContent(comment.getContent().replace("<", "&lt;").replace(">", "&gt;"))
        );
    }

    /**
     * Prepares content for safe storage by escaping HTML characters.
     *
     * @param content The content to prepare for storage.
     * @return The sanitized content.
     */
    private String prepareForStorage(String content) {
        return content.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}