package com.path;

import java.util.ArrayList;

public class Node {
	String name;
	ArrayList<Node> connections = new ArrayList<>();
	public Node(String name){
		this.name = name;
	}
}
