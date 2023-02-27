package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
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

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public List<Node> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }
}
