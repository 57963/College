package com.binary.tables;

import java.util.ArrayList;
import java.util.Scanner;

public class Tables {
	char[] input;
	Scanner keyboard;
	ArrayList<Character> values = new ArrayList<>();
	public static void main(String[] args) {
		new Tables();
	}
	
	public Tables(){
		keyboard = new Scanner(System.in);
		input = keyboard.nextLine().toCharArray();
		for(char c:input){
			boolean alreadyAdded = false;
			if(c!='+' && c!='(' && c!=')' && c!='�'){
				for(char ceval:values){
					if(c == ceval){
						alreadyAdded = true;
					}
				}
				if(!alreadyAdded){
					values.add(c);
				}
			}
		}
		
		for(int i = 0; i<Math.pow(2, values.size());i++){
			ArrayList<Integer> subValues = new ArrayList<>();
			String bin = Integer.toBinaryString(i);
			while(bin.length()<values.size()){
				bin = "0"+bin;
			}
			for(int u = 0; u<values.size(); u++){
				subValues.add(Integer.parseInt(bin.charAt(u)+""));
			}
			System.out.println(pass(sub(subValues)));
		}
	}
	
	public char[] sub(ArrayList<Integer> subValues){
		char[] subbed = new char[input.length];
		for(int i = 0;i<input.length;i++){
			subbed[i]= input[i];
		}
		for(int i = 0;i<subbed.length;i++){
			for(int u = 0;u<values.size();u++){
				if(subbed[i] == values.get(u)){
					if(i>0){
						if(subbed[i-1] == '�'){
							subbed[i] = (char) (subValues.get(u)==1 ? 0:1);
						}
					}
					subbed[i] = subValues.get(u).toString().charAt(0);
					
				}
			}
		}
		return subbed;
	}
	
	public char[] pass(char[] toPass){
		String[] and = new String(toPass).split("\"+");
		for(int i = 0; i<and.length;i++){
			boolean on = true;
			for(char c:and[i].toCharArray()){
				if(c == '0'){
					on = false;
				}
			}
			and[i] = ""+(on?1:0);
		}
		String out = "";
		for(String s: and){
			out += s;
		}
		return out.toCharArray();
	}
}
