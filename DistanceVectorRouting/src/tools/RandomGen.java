package tools;

import java.util.Random;

public class RandomGen {
	//	Version 0.5
	Random random = new Random();
	/**
	 * <i><strong>Next Random Character</strong></i><br><br>
	 * Use this to generate a random Character
	 * set of any set length.<br><br>
	 * @param length <br>
	 * The length of the Character set you want generated.
	 * @return String
	 * @author Zach V.
	 */
	public String nextCharSet(long length){
		String sender = "";
		for(long i=0; i < length; i++){
			sender += nextChar();
		}
		return sender;
	}
	/**
	 * <i><strong>Next Random Text</strong></i><br><br>
	 * Use this to generate a random string containing any 
	 * letter upper and lower case, and any number between 0 and 9.
	 * <br><br>
	 * @return char
	 * @author Zach V.
	 */
	public String nextText(long length){
		String sender = "";
		for(long i=0; i < length; i++){
			long number = random.nextInt(2);
			if(number == 1)
				sender += nextInt();
			else
				sender += nextChar();
		}
		return sender;
	}
	/**
	 * <i><strong>Next Random String</strong></i><br><br>
	 * Use this to generate a random string containing any 
	 * key that could be found on your keyboard.<br><br>
	 * @return char
	 * @author Zach V.
	 */
	public String nextString(long length){
		String sender = "";
		for(long i=0; i < length; i++){
			long number = random.nextInt(3);
			if(number == 1)
				sender += nextStringSymbol();
			else
			if(number == 2)
				sender += nextChar();
			else
				sender += nextInt();
		}
		return sender;
	}
	/**
	 * <i><strong>Next Random Number String</strong></i><br><br>
	 * Use this to generate a random string containing any 
	 * number between 0 and 9. This is helpful for displaying
	 * a number that might have 0 in front already a string.<br><br>
	 * @return char
	 * @author Zach V.
	 */
	public String nextNumberString(long length){
		String sender = "";
		for(long i=0; i < length; i++){
			sender += nextInt();
		}
		return sender;
	}
	private String nextInt(){
		String sender = "";
		int number = random.nextInt(11);
		switch(number){
			case 0: sender = "0";break;
			case 1: sender = "1";break;
			case 2: sender = "2";break;
			case 3: sender = "3";break;
			case 4: sender = "4";break;
			case 5: sender = "5";break;
			case 6: sender = "6";break;
			case 7: sender = "7";break;
			case 8: sender = "8";break;
			case 9: sender = "9";break;
		}
		return sender;
	}
	/**
	 * <i><strong>Next Random String Symbol</strong></i><br><br>
	 * Use this to generate a random symbol on your keyboard.<br><br>
	 * @return char
	 * @author Zach V.
	 */
	public String nextStringSymbol(){
		String sender = "";
		int number = random.nextInt(31);
		switch(number){
			case 0: sender = "/";break;
			case 1: sender = "\\";break;
			case 2: sender = "\'";break;
			case 3: sender = "\"";break;
			case 4: sender = "$";break;
			case 5: sender = "!";break;
			case 6: sender = "@";break;
			case 7: sender = "#";break;
			case 8: sender = "%";break;
			case 9: sender = "^";break;
			case 10: sender = "&";break;
			case 11: sender = "*";break;
			case 12: sender = "(";break;
			case 13: sender = ")";break;
			case 14: sender = "-";break;
			case 15: sender = "_";break;
			case 16: sender = "+";break;
			case 17: sender = "=";break;
			case 18: sender = "[";break;
			case 19: sender = "]";break;
			case 20: sender = "{";break;
			case 21: sender = "}";break;
			case 22: sender = "|";break;
			case 23: sender = "<";break;
			case 24: sender = ">";break;
			case 25: sender = ",";break;
			case 26: sender = ".";break;
			case 27: sender = "?";break;
			case 28: sender = ":";break;
			case 29: sender = ";";break;
			case 30: sender = " ";break;
		}
		return sender;
	}
	/**
	 * <i><strong>Next Random Character</strong></i><br><br>
	 * Use this to generate a random character
	 * between A and Z upper and lower case.<br><br>
	 * @return char
	 * @author Zach V.
	 */
	public char nextChar(){
		char sender = 'a';
		int number = random.nextInt(52);
		switch(number){
			case 0: sender = 'a'; break;
			case 1: sender = 'b'; break;
			case 2: sender = 'c'; break;
			case 3: sender = 'd'; break;
			case 4: sender = 'e'; break;
			case 5: sender = 'f'; break;
			case 6: sender = 'g'; break;
			case 7: sender = 'h'; break;
			case 8: sender = 'i'; break;
			case 9: sender = 'j'; break;
			case 10: sender = 'k'; break;
			case 11: sender = 'l'; break;
			case 12: sender = 'm'; break;
			case 13: sender = 'n'; break;
			case 14: sender = 'o'; break;
			case 15: sender = 'p'; break;
			case 16: sender = 'q'; break;
			case 17: sender = 'r'; break;
			case 18: sender = 's'; break;
			case 19: sender = 't'; break;
			case 20: sender = 'u'; break;
			case 21: sender = 'v'; break;
			case 22: sender = 'w'; break;
			case 23: sender = 'x'; break;
			case 24: sender = 'y'; break;
			case 25: sender = 'z'; break;
			case 26: sender = 'A'; break;
			case 27: sender = 'B'; break;
			case 28: sender = 'C'; break;
			case 29: sender = 'D'; break;
			case 30: sender = 'E'; break;
			case 31: sender = 'F'; break;
			case 32: sender = 'G'; break;
			case 33: sender = 'H'; break;
			case 34: sender = 'I'; break;
			case 35: sender = 'J'; break;
			case 36: sender = 'K'; break;
			case 37: sender = 'L'; break;
			case 38: sender = 'M'; break;
			case 39: sender = 'N'; break;
			case 40: sender = 'O'; break;
			case 41: sender = 'P'; break;
			case 42: sender = 'Q'; break;
			case 43: sender = 'R'; break;
			case 44: sender = 'S'; break;
			case 45: sender = 'T'; break;
			case 46: sender = 'U'; break;
			case 47: sender = 'V'; break;
			case 48: sender = 'W'; break;
			case 49: sender = 'X'; break;
			case 50: sender = 'Y'; break;
			case 51: sender = 'Z'; break;
		}
		return sender;
	}
}
