package com.wyw.utils.filter;

import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public enum FilterPattern {
    ONLY_NUMBER(Pattern.compile("^[0-9]*$"), "纯数字匹配"),
    ONLY_ENGLISH(Pattern.compile("^[a-zA-Z]*$"), "纯英文匹配"),
    ONLY_CHINESE(Pattern.compile("^[\u4e00-\u9fa5]*$"), "纯中文匹配"),
    NUMBER_HEAD(Pattern.compile("^[0-9].*$"), "数字开头"),
    SPACE(Pattern.compile("^[\\s]*$"), "空白字符匹配"),
    ;

    public Pattern pattern;
    public String desc;

    FilterPattern(Pattern pattern, String desc) {
        this.pattern = pattern;
        this.desc = desc;
    }
}
