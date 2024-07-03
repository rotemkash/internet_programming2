[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/e99KNix9)
# Authors

Name and email:
Rotem Kashani rotemkash@edu.hac.ac.il and David Koplev davidkop@edu.hac.ac.il

# Overview
This is a Q&A forum website built using Spring MVC. The application allows users to create and participate in 
discussions within various forums. Users can post messages, comment on messages, and manage their interactions.
Administrator have additional privileges to manage the forums and content.

# General Functionality
**User Authentication and Authorization**: Users can register, log in, and log out. When registering, users are 
required to choose a unique username and provide a valid email address. The system enforces uniqueness for both 
username and email fields, ensuring that no two users can register with the same credentials. If a username or email
already exists in the system, the registration process will prompt the user to choose another one.

**forum Management**: Users can create, view, and delete forums.

**Message Management**: Users can post, edit, and delete messages within forums.

**Comment Management**: Users can post, edit, and delete comments on messages.

**Admin Privileges**: Admins can manage forums and content, including deleting any forum.

# Known Bugs
**Bug 1**: Changes to sending and editing messages are not reflected directly to all users but only after refreshing
the page if you did not make the change.

**Bug 2**: There is no live feed.

**Bug 3**: When editing a message or a comment that has code in it (decoded message/comment) the message will show as
the decoded message or comment instead of the original (for example for the message `<b>q</b>` in editing we 
see `&lt;b&gt;q&lt;/b&gt;`).

# Setup and Installation
## Prerequisites
* Java 17 or later
* Maven
* MySQL

## How to Run
1. **Clone the repository**: `git clone https://github.com/Solange-s-Courses/ex5-spring-neviim-david-koplev-and-rotem-kashani.git`
`cd ex5`
2. **Build and run the application**: `./mvnw spring-boot:run`
3. **Initialize the database**: Ensure that the database is running and accessible.
The application will automatically create the necessary tables on startup.
4. **Access the application**: Open your browser and navigate to `http://localhost:8080`.

## Admin Credentials
* **Username**: Admin1
* **Password**: Admin1

# Endpoints
## Public Endpoints
* `/`: Home page
* `/login`: Login page
## User Endpoints
* `/chat-rooms`: View all forums
* `/chatroom?roomId={id}`: View a specific forum and its messages
* `/sendMessage`: Post a new message or edit an existing one 
* `/deleteMessage`: Delete a message
* `/postComment`: Post a new comment on a message
* `/editComment`: Edit a comment
* `/deleteComment`: Delete a comment
## Admin Endpoints
* `/admin/delete-chat-room`: Delete a forum

# Project Structure
## Controllers
* `AdminController`: Handles admin-related operations such as deleting forums.
* `ChatRoomController`: Manages chat room operations including viewing, posting, editing, and deleting messages and comments.
* `ChatRoomListController`: Manages the list of forums and the creation of new forums.
* `HomeController`: Handles requests for the home page.
* `LoginController`: Manages user login and logout functionalities.
* `RegistrationController`: Manages user registration.
## Models
* `User`:  Represents a user in the system with attributes like `id`, `username`, `password`, and `email`.
* `ChatRoom`: Represents a forum with attributes like `id`, `name`, and `messages`.
* `Message`: Represents a message posted in a chat room with attributes like `id`, `content`, `timestamp`, and a reference to the user who posted it.
* `Comment`:  Represents a comment on a message with attributes like `id`, `content`, `timestamp`, and  a reference to the user who posted it and the message it belongs to.
## Repositories
* `UserRepository`:  Interface for CRUD operations on `User` entities.
* `ChatRoomRepository`: Interface for CRUD operations on `ChatRoom` entities.
* `MessageRepository`: Interface for CRUD operations on `Message` entities.
* `CommentRepository`: Interface for CRUD operations on `Comment` entities.