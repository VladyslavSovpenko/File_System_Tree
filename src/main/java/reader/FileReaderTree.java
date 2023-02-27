package reader;

import entity.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderTree {

    private ArrayList<Node> tree;

    public List<Node> read() {
        tree = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("tree.txt"))) {
            String line = reader.readLine();
            if (line.contains("|")) {
                addFolder(line, reader);
            }
        } catch (IOException e) {
            System.out.println("Не найден файл tree.txt. Уточните путь файла.");
            e.printStackTrace();
        }
        return tree;
    }

    private void addFolder(String line, BufferedReader reader) {
        Node folderNode = createFolder(line);
        int fileContains = getFileContains(line);

        if (fileContains == 0) {
            folderNode.setChildren(List.of());
        } else {
            folderNode.setChildren(addFiles(reader, fileContains));
        }
        tree.add(folderNode);
    }

    private int getFileContains(String line) {
        return Integer.valueOf(line.substring(line.indexOf("|") + 1));
    }

    private List<Node> addFiles(BufferedReader reader, int fileContains) {
        List<Node> children = new ArrayList<>();
        for (int i = 0; i < fileContains; i++) {
            String fileLine = null;
            try {
                fileLine = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (fileLine.contains(":")) {
                children.add(Node.builder()
                        .type(Node.NodeType.FILE)
                        .name(fileLine.substring(0, fileLine.indexOf(":")))
                        .size(Long.valueOf(fileLine.substring(fileLine.indexOf(":") + 1)))
                        .build());
            } else if (fileLine.contains("|")) {
                Node folder = createFolder(fileLine);
                folder.setChildren(addFiles(reader, getFileContains(fileLine)));
                children.add(folder);
            }
        }
        return  children;
    }

    private Node createFolder(String line) {
        return Node.builder()
                .type(Node.NodeType.FOLDER)
                .name(line.substring(0, line.indexOf("|")))
                .build();
    }
}
