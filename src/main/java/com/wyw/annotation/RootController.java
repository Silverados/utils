package com.wyw.annotation;

@RequestMapping(value = "/root")
public class RootController {

    @RequestMapping(value = "/test")
    public void test() {

    }

    @RequestMapping(value = "/test2")
    public void test2(int val) {

    }
}
