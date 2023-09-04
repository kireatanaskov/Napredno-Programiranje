package file_systems;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalLong;

public class Folder extends File implements IFile {
    private List<IFile> files;

    public Folder(String fileName, long size) {
        super(fileName, size);
        this.files = new ArrayList<IFile>();
    }

    public Folder(String filename) {
        super(filename);
        this.files = new ArrayList<IFile>();
    }

    private boolean checkNameExistence(String fileName) {
        return files.stream()
                .map(IFile::getFileName)
                .anyMatch(name -> name.equals(fileName)); // vrakja true ako ima match
    }

    public void addFile(IFile file) throws FileNameExistsException {
        if (checkNameExistence(file.getFileName()))
            throw new FileNameExistsException(file.getFileName(), this.fileName);

        files.add(file);
    }

    @Override
    public String getFileName() {
        return this.fileName;
    }

    @Override
    public long getFileSize() {
        return files.stream()
                .mapToLong(IFile::getFileSize)
                .sum();
    }

    @Override
    public String getFileInfo(int indent) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%sFolderName: %10s Folder size: %10d\n",
                IndentPrinter.printIndent(indent),
                this.fileName,
                this.getFileSize()));

        files.stream()
                .forEach(file -> sb.append(file.getFileInfo(indent + 1)));

        return sb.toString();
    }

    @Override
    public void sortBySize() {
        Comparator<IFile> iFileComparator = Comparator.comparingLong(IFile::getFileSize);
        files.sort(iFileComparator);
        files.forEach(IFile::sortBySize);
    }

    @Override
    public long findLargestFile() {
        OptionalLong largestHere = files.stream()
                .mapToLong(IFile::findLargestFile)
                .max();

        if (largestHere.isPresent())
            return largestHere.getAsLong();
        else
            return 0;
    }
}
