package com.harness.tool;

import com.harness.agent.AgentProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

@Component
public class WorkspaceGuard {

    private final Path root;

    public WorkspaceGuard(AgentProperties agentProperties) {
        this.root = Path.of(agentProperties.workSpaceRoot()).toAbsolutePath().normalize();
    }

    public Path root() {
        return root;
    }

    public Path resolve(String rawPath) {
        if (rawPath == null || rawPath.isBlank()) {
            throw  new WorkspaceViolationException("Path is blank");
        }
        Path candidate = Path.of(rawPath);
        Path resolved = (candidate.isAbsolute() ? candidate: root.resolve(candidate)).normalize();

        if (!resolved.startsWith(root)) {
            throw  new WorkspaceViolationException(
                    "Путь вне рабочей папки: %s, <текущий путь: %s>".formatted(resolved, root));
        }

        try {
            Path realPath = resolved.toRealPath();
            if (!realPath.startsWith(root.toRealPath())) {
                throw  new WorkspaceViolationException(
                        "Path is not relative to current workspace: " + rawPath);
            }
        }
        catch (NoSuchFileException _) {}
        catch (IOException cannotVerify) {
            throw  new WorkspaceViolationException("Can't verify work directory: " + rawPath);
        }

        return resolved;
    }

    public String relativize(Path path) {
        return root.relativize(path).toString().replace("\\", "/");
    }


    public class WorkspaceViolationException extends RuntimeException {
        public WorkspaceViolationException(String pathIsBlank) {
            super(pathIsBlank);
        }
    }
}
