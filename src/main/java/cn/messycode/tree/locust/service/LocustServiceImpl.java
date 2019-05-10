package cn.messycode.tree.locust.service;

import cn.messycode.tree.locust.api.LocustService;

public class LocustServiceImpl implements LocustService {

    private String config;

    @Override
    public String[] split(String separatorChar) {
        return this.config.split(separatorChar);
    }
}
