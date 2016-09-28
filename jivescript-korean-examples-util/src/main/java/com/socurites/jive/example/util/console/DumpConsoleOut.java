package com.socurites.jive.example.util.console;

import java.util.List;

import com.socurites.jive.core.engine.old.TopicManager;

/*-----------------------*/
/*-- Developer Methods --*/
/*-----------------------*/
public class DumpConsoleOut {
	/**
	 * DEVELOPER: Dump the trigger sort buffers to the terminal.
	 */
	public static void dumpSorted(TopicManager topics) {
		for ( String topicId : topics.getTopicIds() ) {
			String[] triggers = topics.addTopic(topicId).listTriggers();

			// Dump.
			System.out.println("Topic: " + topicId);
			for (int i = 0; i < triggers.length; i++) {
				System.out.println("       " + triggers[i]);
			}
		}
	}
	
	
	/**
	 * DEVELOPER: Dump the entire topic/trigger/reply structure to the terminal.
	 */
	public static void dumpTopics (TopicManager topics) {
		System.out.println("{");
		for ( String topicId : topics.getTopicIds() ) {
			String extra = "";

			// Includes? Inherits?
			String[] includes = topics.addTopic(topicId).includes();
			String[] inherits = topics.addTopic(topicId).inherits();
			if (includes.length > 0) {
				extra = "includes ";
				for (int i = 0; i < includes.length; i++) {
					extra += includes[i] + " ";
				}
			}
			if (inherits.length > 0) {
				extra += "inherits ";
				for (int i = 0; i < inherits.length; i++) {
					extra += inherits[i] + " ";
				}
			}
			System.out.println("  '" + topicId + "' " + extra + " => {");

			// Dump the trigger list.
			String[] trigList = topics.addTopic(topicId).listTriggers();
			for (int i = 0; i < trigList.length; i++) {
				String trig = trigList[i];
				System.out.println("    '" + trig + "' => {");

				// Dump the replies.
				List<String> replies = topics.addTopic(topicId).addTrigger(trig).getReplies();
				if (replies.size() > 0) {
					System.out.println("      'reply' => [");
					for ( String reply : replies ) {
						System.out.println("        '" + reply + "',");
					}
					System.out.println("      ],");
				}

				// Dump the conditions.
				List<String> conditionalReplies = topics.addTopic(topicId).addTrigger(trig).getConditionalReplies();
				if (conditionalReplies.size() > 0) {
					System.out.println("      'condition' => [");
					
					for ( String conditionalReply : conditionalReplies ) {
						System.out.println("        '" + conditionalReply + "',");
					}
					System.out.println("      ],");
				}

				// Dump the redirects.
				List<String> redirects = topics.addTopic(topicId).addTrigger(trig).getRedirects();
				if (redirects.size() > 0) {
					System.out.println("      'redirect' => [");
					
					for ( String redirect : redirects ) {
						System.out.println("        '" + redirect + "',");
					}
					System.out.println("      ],");
				}

				System.out.println("    },");
			}

			System.out.println("  },");
		}
	}
}
