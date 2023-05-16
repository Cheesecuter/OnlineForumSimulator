package com.j2eeprac.test;

public class RandomIDTest {
	public static void main(String[] args) {
		System.out.println((int) (10001 + Math.random() * (99999 - 10001 + 1)));
	}
}
