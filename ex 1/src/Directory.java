package src;

import java.util.ArrayList;
import java.util.List;

/**
 * The Directory class represents a directory in the file system.
 * It implements the FileSystemComponent interface and can contain other files and directories.
 */
class Directory implements FileSystemComponent {
    public final String name; // The name of the directory
    public final List<FileSystemComponent> components; // A list of files and directories contained within this directory

    /**
     * Constructs a new Directory object with the specified name.
     *
     * @param name The name of the directory
     */
    public Directory(String name) {
        this.name = name;
        this.components = new ArrayList<>();
    }

    /**
     * Adds a FileSystemComponent (file or directory) to this directory.
     *
     * @param component The FileSystemComponent to be added
     */
    @Override
    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }

    /**
     * Prints the path of this directory and recursively prints the paths of all components (files and directories) contained within it.
     *
     * @param prefix The prefix to be prepended to the path of this directory
     */
    @Override
    public void printPath(String prefix) {
        String currentPath = prefix.isEmpty() ? "/" + name : prefix + "/" + name;
        System.out.println("Directory: " + currentPath);
        for (FileSystemComponent component : components) {
            component.printPath(currentPath);
        }
    }

    /**
     * Returns the type of this FileSystemComponent, which is ComponentType.DIRECTORY.
     *
     * @return The ComponentType.DIRECTORY enum value
     */
    @Override
    public ComponentType getType() {
        return ComponentType.DIRECTORY;
    }

    /**
     * Returns the name of this directory.
     *
     * @return The name of the directory
     */
    @Override
    public String getName() {
        return name;
    }
}