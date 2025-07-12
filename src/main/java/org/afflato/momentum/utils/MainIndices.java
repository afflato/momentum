package org.afflato.momentum.utils;

public enum MainIndices {
    NIFTY50("data", "nifty50.csv"),
    NIFTYNEXT("data", "niftynext50.csv"),
    NIFTYMIDCAP("data", "niftymidcap150.csv"),
    NIFTYSMALLCAP("data", "niftysmallcap250.csv");

    private String path;
    private String file;

    MainIndices(String path, String file) {
        this.path = path;
        this.file = file;
    }

    public String getPath() {
        return path;
    }

    public String getFile() {
        return file;
    }
}
