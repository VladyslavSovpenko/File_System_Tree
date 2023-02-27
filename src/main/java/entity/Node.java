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

    private String name;
    private long size;
    private NodeType type;
    private List<Node> children;

    public long calcSize() {
        return calcSize(this);
    }

    public long calcSize(Node node) {
        if (node.type == NodeType.FILE)
            return node.size;

        var res = 0;
        for (var child : node.children) {
            res += calcSize(child);
        }
        return res;
    }
}
