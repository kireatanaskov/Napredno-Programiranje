package file_systems;

public class FileSystem {
    private Folder rootFolder;

    public FileSystem() {
        rootFolder = new Folder("root");
    }

    public void addFile (IFile iFile) throws FileNameExistsException {
        this.rootFolder.addFile(iFile);
    }

    public long findLargestFile() {
        return this.rootFolder.findLargestFile();
    }

    public void sortBySize() {
        this.rootFolder.sortBySize();
    }

    @Override
    public String toString() {
        return this.rootFolder.getFileInfo(0);
    }
}
