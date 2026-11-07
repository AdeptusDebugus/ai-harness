package com.harness.agent;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties("harness.agent")
public record AgentProperties(
        @DefaultValue("20") int maxTurns,
        @DefaultValue(".")String workSpaceRoot
) {
    public AgentProperties {
        if (maxTurns < 1) {
            throw new IllegalStateException("harness.agent.maxTurns must be >= 1, actual: " + maxTurns);
        }
        if (workSpaceRoot == null || workSpaceRoot.isBlank()) {
            throw new IllegalStateException("harness.agent.workSpaceRoot must not be blank");
        }
    }
}
