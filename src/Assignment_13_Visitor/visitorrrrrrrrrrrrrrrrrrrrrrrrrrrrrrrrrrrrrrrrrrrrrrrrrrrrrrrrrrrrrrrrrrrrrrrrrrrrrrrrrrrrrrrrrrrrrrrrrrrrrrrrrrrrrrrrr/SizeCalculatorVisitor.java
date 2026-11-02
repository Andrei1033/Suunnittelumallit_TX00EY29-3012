package Assignment_13_Visitor.visitorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr;

import Assignment_13_Visitor.modelllllllllllllllllllllllllll.Directory;
import Assignment_13_Visitor.modelllllllllllllllllllllllllll.File;

public class SizeCalculatorVisitor implements FileSystemVisitor {
    private int totalSize = 0;

    @Override
    public void visit(File file) {
        totalSize += file.getSize();
    }

    @Override
    public void visit(Directory directory) {
        for (var element : directory.getElements()) {
            element.accept(this);
        }

    }

    public int getTotalSize() {
        return totalSize;
    }
}
