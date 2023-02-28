package entity;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Node {

    public enum NodeType {
        FILE, FOLDER
    }
//todo add private Node predecessor;
    private String name;
    private double size;
    private NodeType type;
    private List<Node> children;

    public double calcSize() {
        return calcSize(this);
    }

    public double calcSize(Node node) {
        if (node.type == NodeType.FILE)
            return node.size;

        var res = 0;
        for (var child : node.children) {
            res += calcSize(child);
        }
        return res;
    }
}
