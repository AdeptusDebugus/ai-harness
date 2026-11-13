package com.harness.demo;

/**
 * Локация источника кода.
 */
public class SourceLocation {
    private final String packageName;
    private final String className;

    public SourceLocation(String packageName, String className) {
        this.packageName = packageName;
        this.className = className;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }
}
