package src;

/**
 * The FileSystemComponent interface defines the common behavior for file system components,
 * such as files and directories. It provides methods for adding components, printing paths,
 * and retrieving component types and names.
 */
interface FileSystemComponent {
    /**
     * Adds a FileSystemComponent (file or directory) to this component.
     *
     * @param component The FileSystemComponent to be added
     */
    void addComponent(FileSystemComponent component);

    /**
     * Prints the path of this component.
     *
     * @param prefix The prefix to be prepended to the path of this component
     */
    void printPath(String prefix);

    /**
     * Returns the type of this FileSystemComponent (file or directory).
     *
     * @return The ComponentType enum value representing the type of this component
     */
    ComponentType getType();

    /**
     * Returns the name of this component.
     *
     * @return The name of this component
     */
    String getName();
}

/**
 * The ComponentType enum represents the types of file system components.
 * It can be either FILE or DIRECTORY.
 */
enum ComponentType {
    FILE, DIRECTORY
}