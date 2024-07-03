package controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import model.ChatRoom;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import repository.ChatRoomRepository;
import repository.UserRepository;

import java.util.List;

/**
 * Controller class for handling forum list operations.
 * This class provides endpoints for viewing the list of forums and creating new forums.
 */
@Controller
public class ChatRoomListController {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    /**
     * Constructs a ChatRoomListController with the necessary repositories.
     *
     * @param chatRoomRepository The repository for forum operations.
     * @param userRepository The repository for user operations.
     */
    @Autowired
    public ChatRoomListController(ChatRoomRepository chatRoomRepository, UserRepository userRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.userRepository = userRepository;
    }

    /**
     * Handles the request to view the list of forums.
     *
     * @param model The model to add attributes to.
     * @param session The HTTP session containing user information.
     * @return The name of the view to render.
     */
    @GetMapping("/chat-rooms")
    public String getChatRooms(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }

        List<ChatRoom> chatRooms = chatRoomRepository.findAll();
        model.addAttribute("chatRooms", chatRooms);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("newChatRoom", new ChatRoom());
        model.addAttribute("username", currentUser.getUsername());
        return "chat-rooms";
    }


    /**
     * Handles the request to create a new forum.
     *
     * @param newChatRoom The new forum to be created.
     * @param bindingResult The result of the validation.
     * @param model The model to add attributes to.
     * @param session The HTTP session containing user information.
     * @return A string indicating where to redirect after the operation.
     */
    @PostMapping("/create-chat-room")
    public String createChatRoom(@Valid @ModelAttribute("newChatRoom") ChatRoom newChatRoom,
                                 BindingResult bindingResult,
                                 Model model,
                                 HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Question must contain between 2 and 120 characters.");
            return getChatRooms(model, session);
        }

        if (chatRoomRepository.existsByNameIgnoreCase(newChatRoom.getName())) {
            model.addAttribute("error", "A forum with this question already exists.");
            return getChatRooms(model, session);
        }

        newChatRoom.setUser(currentUser);
        ChatRoom savedChatRoom = chatRoomRepository.save(newChatRoom);

        // Example of using setId (though usually managed by JPA)
        newChatRoom.setId(savedChatRoom.getId());

        // Example of using setMessages to initialize the messages list
        newChatRoom.setMessages(savedChatRoom.getMessages());

        currentUser = userRepository.findById(currentUser.getId()).orElse(null);

        if (currentUser != null) {
            List<ChatRoom> userChatRooms = currentUser.getChatRooms();
            userChatRooms.add(newChatRoom);
            currentUser.setChatRooms(userChatRooms);
            userRepository.save(currentUser);
        }

        return "redirect:/chat-rooms";
    }
}