package org.afflato.momentum.utils;

public enum MomentumIndices {
    ALPHA50("alpha50", "alpha.csv"),
    NIFTY500("nifty500", "50_list.csv"),
    MIDCAP150("midcap150", "50_list.csv"),
    VALUE50("value", "50_list.csv");

    private String path;
    private String file;

    MomentumIndices(String path, String file) {
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
