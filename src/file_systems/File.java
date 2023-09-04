package file_systems;

public class File implements IFile {
    protected String fileName;
    protected long size;

    public File(String fileName, long size) {
        this.fileName = fileName;
        this.size = size;
    }

    public File(String fileName) {
        this.fileName = fileName;
        this.size = 0L;
    }

    @Override
    public String getFileName() {
        return this.fileName;
    }

    @Override
    public long getFileSize() {
        return this.size;
    }

    @Override
    public String getFileInfo(int indent) {
        return String.format("%sFile name: %10s File size: %10d\n",
                IndentPrinter.printIndent(indent),
                this.getFileName(),
                this.getFileSize());
    }

    @Override
    public void sortBySize() {
        return ;
    }

    @Override
    public long findLargestFile() {
        return this.size;
    }
}
