import entity.Node;
import reader.FileReaderTree;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileReaderTree fileReaderTree = new FileReaderTree();
        List<Node> tree = fileReaderTree.read();

    }
}
