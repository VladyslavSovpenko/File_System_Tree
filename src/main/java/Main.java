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
        service.getDirectorySize("root/dir_kz/dir_vl", tree);
        service.getDirectorySize("root/dir_lt/dir_ko/dir_mu", tree);

        System.out.println("--------");

        service.getFileDuplicates(tree);


    }
}
