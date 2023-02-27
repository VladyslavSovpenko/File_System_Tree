import entity.Node;
import reader.FileReaderTree;
import service.TreeService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileReaderTree fileReaderTree = new FileReaderTree();
        TreeService service = new TreeService();

        List<Node> tree = fileReaderTree.read();
        service.getDirectorySize("root", tree);
        service.getDirectorySize("root/dir_aa", tree);
//        service.getDirectorySize("root/dir_mn", tree);
//        service.getDirectorySize("root/dir_mn/dir_bb/dir_bb", tree);

        service.getFileDuplicates(tree);


    }
}
