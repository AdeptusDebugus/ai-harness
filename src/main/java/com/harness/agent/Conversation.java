package com.harness.agent;

import org.springframework.ai.chat.messages.*;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
    private final String systemPromt;
    private List<Message> messages = new ArrayList<>();

    public Conversation(String systemPromt) {
        this.systemPromt = systemPromt;
    }

    public void addUserMessage(String userMessage){
        messages.add(new UserMessage(userMessage));
    }

    public void addAssistantMessage(AssistantMessage assistantMessage){
        messages.add(assistantMessage);
    }

    public List<Message> toPromptMessages(){
        ArrayList<Message> prompt = new ArrayList<Message>(messages.size() + 1);
        prompt.add(new SystemMessage(systemPromt));
        prompt.addAll(messages);
        return prompt;
    }

    public void addToolResponses(ToolResponseMessage toolResponseMessage) {
        messages.add(toolResponseMessage);
    }
}
