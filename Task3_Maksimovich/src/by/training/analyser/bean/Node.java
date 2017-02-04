package by.training.analyser.bean;

public class Node {

	String name;

	String content;

	NodeType type;

	public Node() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public NodeType getType() {
		return type;
	}

	public void setType(NodeType type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (this.getClass() != obj.getClass()) {
			return false;
		}

		Node node = (Node) obj;

		if (this.name == null) {
			if (node.name != null) {
				return false;
			}
		} else {
			if (!this.name.equals(node.name)) {
				return false;
			}
		}

		if (this.content == null) {
			if (node.content != null) {
				return false;
			}
		} else {
			if (!this.content.equals(node.content)) {
				return false;
			}
		}

		if (!this.type.equals(node.type)) {
			return false;
		}

		return true;
	}

	public String toString() {

		return "Node: " + name + ", type= " + type + (content != null ? ", content= " + content + ";" : "");
	}

}
