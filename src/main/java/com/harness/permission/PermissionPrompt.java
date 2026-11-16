package com.harness.permission;

public interface PermissionPrompt {

    enum Answer{ ALLOW_ONCE, ALLOW_ALWAYS, DENY }
    Answer ask(String toolName, String target, String reason);
}
