package controller;

import jakarta.servlet.http.HttpSession;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import repository.ChatRoomRepository;

/**
 * Controller class for handling admin-related operations.
 * This class provides endpoints for administrative tasks such as deleting forums.
 */
@Controller
public class AdminController {

    private final ChatRoomRepository chatRoomRepository;

    /**
     * Constructs an AdminController with the necessary repository.
     *
     * @param chatRoomRepository The repository for forum operations.
     */
    @Autowired
    public AdminController(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    /**
     * Handles the request to delete a forum.
     * This endpoint is accessible only to admin users.
     *
     * @param roomId The ID of the forum to be deleted.
     * @param session The HTTP session containing user information.
     * @return A string indicating where to redirect after the operation.
     */
    @PostMapping("/admin/delete-chat-room")
    public String deleteChatRoom(@RequestParam Long roomId, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null || !currentUser.isAdmin()) {
            return "redirect:/login";
        }

        chatRoomRepository.deleteById(roomId);
        return "redirect:/chat-rooms";
    }
}