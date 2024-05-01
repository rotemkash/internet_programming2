package src;

/**
 * The File class represents a file in the file system.
 * It implements the FileSystemComponent interface and cannot contain any other components.
 */
class File implements FileSystemComponent {
    public final String name; // The name of the file

    /**
     * Constructs a new File object with the specified name.
     *
     * @param name The name of the file
     */
    public File(String name) {
        this.name = name;
    }

    /**
     * Throws an UnsupportedOperationException because files cannot have components added to them.
     *
     * @param component The FileSystemComponent that cannot be added to this file
     * @throws UnsupportedOperationException Always thrown because files cannot have components added
     */
    @Override
    public void addComponent(FileSystemComponent component) {
        throw new UnsupportedOperationException("Cannot add components to a file");
    }

    /**
     * Prints the path of this file.
     *
     * @param prefix The prefix to be prepended to the path of this file
     */
    @Override
    public void printPath(String prefix) {
        System.out.println("File: " + prefix + "/" + name);
    }

    /**
     * Returns the type of this FileSystemComponent, which is ComponentType.FILE.
     *
     * @return The ComponentType.FILE enum value
     */
    @Override
    public ComponentType getType() {
        return ComponentType.FILE;
    }

    /**
     * Returns the name of this file.
     *
     * @return The name of the file
     */
    @Override
    public String getName() {
        return name;
    }
}