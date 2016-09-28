package com.socurites.jive.example.util.console;

import com.socurites.jive.core.parser.JiveScriptEntityBuilder;


/**
 * Console pretty printer for Bot standalone execution
 * 
 * @author socurites
 *
 */
public class PrettyConsoleOut {
	public static void printBanner() {
		System.out.println("" + "      .   .       \n"
				+ "     .:...::      JiveScript Java // JSBot\n"
				+ "    .::   ::.     Version: "
				+ JiveScriptEntityBuilder.VERSION + "\n"
				+ " ..:;;. ' .;;:..  \n"
				+ "    .  '''  .     Type '/quit' to quit.\n"
				+ "     :;,:,;:      Type '/help' for more options.\n"
				+ "     :     :      \n");
	}
	
	public static void printInfo(String text) {
		System.out.println(":: " + text);
	}
	
	public static void printInputScreen(String name) {
		System.out.print(name + "> ");
	}
}
