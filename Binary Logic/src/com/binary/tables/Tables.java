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
		for(char v: values){
			System.out.print(v+"|");
		}
		System.out.print("out");
		System.out.println();
		for(int i = 0; i<values.size()*2+1;i++){
			System.out.print("-");
		}
		System.out.println();
		for(int i = 0; i<Math.pow(2, values.size());i++){
			ArrayList<Integer> subValues = new ArrayList<>();
			String bin = Integer.toBinaryString(i);
			while(bin.length()<values.size()){
				bin = "0"+bin;
			}
			for(int u = 0; u<values.size(); u++){
				subValues.add(Integer.parseInt(bin.charAt(u)+""));
			}
			for(int v: subValues){
				System.out.print(v+"|");
			}
			//System.out.print(subValues);
			//System.out.println(sub(subValues));
			//System.out.println(andPass(sub(subValues)));
			System.out.println(orPass(andPass(sub(subValues))));
		}
	}
	
	public char[] sub(ArrayList<Integer> subValues){
		char[] subbed = new char[input.length];
		for(int i = 0;i<input.length;i++){
			subbed[i]= input[i];
		}
		if(values.indexOf(subbed[0])>=0){
			subbed[0] = subValues.get(values.indexOf(subbed[0])).toString().charAt(0);
		}
		for(int i = 0;i<subbed.length;i++){
					if(i>0 && values.indexOf(subbed[i])>=0){
						if(subbed[i-1] == '�'){
							subbed[i] = subValues.get(values.indexOf(subbed[i]))==1 ? '0':'1';
						}else{
							subbed[i] = subValues.get(values.indexOf(subbed[i])).toString().charAt(0);
						}
				}
		}
		return new String(subbed).replaceAll("�", "").toCharArray();
	}
	
	public char[] andPass(char[] toPass){
		String[] and = new String(toPass).split("\\+");
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
			out += s+"+";
		}
		out = out.substring(0, out.length()-1);
		return out.toCharArray();
	}
	
	public Character orPass(char[] toPass){
		String or = new String(toPass).replaceAll("\\+","");
		boolean on = false;
		for(char s:or.toCharArray()){
			if(s == '1'){
				on = true;
			}
		}
		return on?'1':'0';
	}
}
