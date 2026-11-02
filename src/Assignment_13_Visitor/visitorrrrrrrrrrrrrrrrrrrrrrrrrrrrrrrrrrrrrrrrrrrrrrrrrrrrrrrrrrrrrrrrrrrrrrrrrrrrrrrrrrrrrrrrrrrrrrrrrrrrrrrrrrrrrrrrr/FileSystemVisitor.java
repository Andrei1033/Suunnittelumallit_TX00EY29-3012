package Assignment_13_Visitor.visitorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr;

import Assignment_13_Visitor.modelllllllllllllllllllllllllll.Directory;
import Assignment_13_Visitor.modelllllllllllllllllllllllllll.File;

public interface FileSystemVisitor {

    void visit(File file);

    void visit(Directory directory);
}
