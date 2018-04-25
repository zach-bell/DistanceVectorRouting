package core.base;

import java.util.ArrayList;

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
