[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/tvA-HQ9N)
# readme - your personal information is mandatory (email or ID)
<h1>Your Name(s) Rotem Kashani and David Koplev</h1>
<p>Email: rotemkash@edu.hac.ac.il<br/>
  Email: davidkop@edu.hac.ac.il
</p>
<h2>Description:</h2>
<p>This code allows users to create a directory structure by entering file and directory paths. The program creates a 
file system representation using two classes: `File` and `Directory`.
The `FileSystemBuilder` class is the main class that prompts the user to enter file/directory paths. It then processes 
the input and creates the corresponding file system structure by adding files to directories or creating new
subdirectories as needed.
The program uses an interface called `FileSystemComponent` to define the common behavior of files and directories, 
such as adding components and printing their paths.
After the user finishes entering paths, the program prints out the complete file system structure by calling the 
`printPath` method on the root directory.
The code demonstrates the use of interfaces, inheritance, and composition to model a file system hierarchy.
It also handles user input and error cases, such as attempting to add a file with the same name to a directory.
</p>