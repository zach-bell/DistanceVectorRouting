package core.table;

import java.util.ArrayList;

import core.base.Node;

public class Table {
	
	public ArrayList<Node> destinations;
	public ArrayList<Integer> delays;
	public ArrayList<Node> outgoing;
	
	public Table() {
		destinations = new ArrayList<Node>();
		delays = new ArrayList<Integer>();
		outgoing = new ArrayList<Node>();
	}
}
