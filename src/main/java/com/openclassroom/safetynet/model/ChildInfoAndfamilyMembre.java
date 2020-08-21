package com.openclassroom.safetynet.model;

import java.util.List;

public class ChildInfoAndfamilyMembre {
    private List<ChildInfo> childInfo;
    private List<Person> family;

    public ChildInfoAndfamilyMembre(List<ChildInfo> childInfo, List<Person> family) {
        this.childInfo = childInfo;
        this.family = family;
    }

    public List<ChildInfo> getChildInfo() {
        return childInfo;
    }

    public void setChildInfo(List<ChildInfo> childInfo) {
        this.childInfo = childInfo;
    }

    public List<Person> getFamily() {
        return family;
    }

    public void setFamily(List<Person> family) {
        this.family = family;
    }
}
