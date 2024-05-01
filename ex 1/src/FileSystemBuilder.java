package src;

import java.util.Scanner;

/**
 * The FileSystemBuilder class is responsible for building a file system structure
 * based on user input. It provides a command-line interface for the user to enter
 * file and directory paths, and constructs the corresponding file system hierarchy.
 */
public class FileSystemBuilder {
    private static final Directory ROOT_DIRECTORY = new Directory("root"); // The root directory of the file system

    /**
     * The main entry point of the program.
     *
     * @param args The command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file/directory paths (or 'exit' to quit):");

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            if (input.isEmpty()) {
                continue;
            }
            try {
                addPath(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        ROOT_DIRECTORY.printPath("");
    }

    /**
     * Adds a file or directory path to the file system structure.
     *
     * @param path The path to be added
     */
    private static void addPath(String path) {
        String[] components = path.split("/");
        Directory currentDirectory = ROOT_DIRECTORY;

        for (String component : components) {
            if (component.isEmpty()) {
                continue;
            }
            if (component.contains(".")) {
                addFile(currentDirectory, component, path);
                break;
            } else {
                currentDirectory = addDirectory(currentDirectory, component);
            }
        }
    }

    /**
     * Adds a file to the specified directory.
     *
     * @param directory The directory to which the file should be added
     * @param fileName  The name of the file
     * @param path      The full path of the file
     * @throws IllegalArgumentException If a file with the same name already exists in the directory
     */
    private static void addFile(Directory directory, String fileName, String path) {
        for (FileSystemComponent component : directory.components) {
            if (component.getType() == ComponentType.FILE && component.getName().equals(fileName)) {
                String filePath = path.substring(0, path.lastIndexOf("/") + 1);
                String remainingPath = path.substring(path.lastIndexOf("/") + 1);
                String errorMessage = "Cannot add " + "/root" + filePath + remainingPath + " to " + "/root" + filePath;
                throw new IllegalArgumentException(errorMessage.substring(0, errorMessage.length() - 1));
            }
        }
        directory.addComponent(new File(fileName));
    }

    /**
     * Adds a directory to the specified parent directory.
     *
     * @param parent        The parent directory to which the new directory should be added
     * @param directoryName The name of the new directory
     * @return The newly added directory
     */
    private static Directory addDirectory(Directory parent, String directoryName) {
        for (FileSystemComponent component : parent.components) {
            if (component.getType() == ComponentType.DIRECTORY && component.getName().equals(directoryName)) {
                return (Directory) component;
            }
        }
        Directory newDirectory = new Directory(directoryName);
        parent.addComponent(newDirectory);
        return newDirectory;
    }
}