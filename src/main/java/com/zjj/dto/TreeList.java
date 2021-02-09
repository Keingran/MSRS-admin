package com.zjj.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TreeList树结构实体类
 */
public class TreeList implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 节点ID
     */
    private Long id;

    /**
     * 节点名称
     */
    private String label;

    /**
     * 子节点
     * 属性为 空 ("") 或者为 NULL 都不序列化
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeList> children;

    public TreeList() {
    }

    public TreeList(SysDept dept) {
        this.id = dept.getDeptId();
        this.label = dept.getDeptName();
        this.children = dept.getChildren().stream().map(TreeList::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<TreeList> getChildren() {
        return children;
    }

    public void setChildren(List<TreeList> children) {
        this.children = children;
    }
}
