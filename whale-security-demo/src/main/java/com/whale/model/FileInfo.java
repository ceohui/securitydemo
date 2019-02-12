package com.whale.model;

import java.io.Serializable;

/**
 * @author ljy
 * @version V1.0
 * @Package com.whale.model
 * @Description: TODO
 * @date 2019/2/12 22:42
 */
public class FileInfo implements Serializable {

    private String path;

    public FileInfo(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
