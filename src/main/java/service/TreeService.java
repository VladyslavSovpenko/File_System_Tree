package service;

import entity.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
* 1. getDirectorySize(path: Path).

Ця функц?я приймає шлях будь-якої директор?ї та повертає її розм?р в байтах. Вкладен? файли на будь-якому р?вн? повинн? також п?дсумовуватись.

У наведеному приклад? результатом виконання ц?єї функц?ї є:
root: 54236 + 35823932 + 2049 + 54236 + 8092 + 54236 + 436346 + 436436 + 54236 = 36923799
root/dir_aa: 54236 + 35823932 + 2049 = 35880217
root/dir_mn: 0 + 436436 + 54236 = 490672
root/dir_mn/dir_bb/dir_bb: 0

2. getFileDuplicates().

Ця функц?я повертає список шлях?в файл?в-дубл?кат?в.
Файли-дубл?кати - це файли, котр? мають однакове ?м'я та розм?р.
Груп ?з файлами-дубл?катами може бути дек?лька.

У наведеному приклад? результатом ц?єї функц?ї є:
root/dir_aa/file_tq
root/dir_cd/file_tq
root/dir_kb/dir_bs/file_tq
root/dir_mn/file_tq

Вам потр?бно над?слати нам код проекта (арх?в або посилання на репозитор?й) та результати виконання вищезазначених функц?й:
getDirectorySize(path: Path) з параметрами:
root
root/dir_kz/dir_vl
root/dir_lt/dir_ko/dir_mu
getFileDuplicates()

* */
public class TreeService {

    public void getDirectorySize(String path, List<Node> tree) {

        String[] pathArray = path.split("/");
        Node mainNode = null;

        for (String step : pathArray) {
            if (mainNode == null) {
                mainNode = tree.get(0);
            } else {
                mainNode = mainNode.getChildren().stream()
                        .filter(node -> node.getName().equals(step))
                        .collect(Collectors.toList()).get(0);
            }
        }
        System.out.println(mainNode.calcSize());
    }

    public void getFileDuplicates(List<Node> tree) {
        List<Node> allFiles = getAllFiles(tree);

        Map<Node, List<Integer>> map = new HashMap<>();

        for (Node node:allFiles){
           map.put(node, new ArrayList<>());
        }
        int a=0;

        for (Node node:allFiles){
            List<Integer> integerList = map.get(node);
            integerList.add(a);
            a++;
        }
        for (Map.Entry<Node, List<Integer>> entry:map.entrySet()){
            if (entry.getValue().size()>1){
                System.out.println((entry.getKey().getName()+":"+entry.getKey().getSize()+"\n").repeat(entry.getValue().size()));
            }
        }
    }

    private List<Node> getAllFiles(List<Node> tree) {
        List<Node> folders = new ArrayList<>();
        List<Node> result = new ArrayList<>();
        folders.add(tree.get(0));
        while (!folders.stream()
                .filter(node -> node.getType().equals(Node.NodeType.FOLDER))
                .collect(Collectors.toList()).isEmpty()) {

            if (folders.get(0).getType() == Node.NodeType.FOLDER) {
                Node node = folders.remove(0);

                node.getChildren().stream()
                        .filter(e -> e.getType().equals(Node.NodeType.FILE))
                        .forEach(node1 -> result.add(node1));

                node.getChildren().stream()
                        .filter(e -> e.getType().equals(Node.NodeType.FOLDER))
                        .forEach(node1 -> folders.add(node1));
            }
        }
        return result;
    }
}
