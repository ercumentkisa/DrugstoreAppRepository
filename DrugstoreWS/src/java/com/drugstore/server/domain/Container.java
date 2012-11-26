package com.drugstore.server.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 13.12.2011
 * Time: 20:50
 * To change this template use File | Settings | File Templates.
 */
public class Container extends Entity{
    private String name;
    private ContainerType containerType;
    private Container parent;

    private List<Container> subContainers = new ArrayList<Container>();

    public Container() {
    }

    public ContainerType getContainerType() {
        return containerType;
    }

    public void setContainerType(ContainerType containerType) {
        this.containerType = containerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Container getParent() {
        return parent;
    }

    public void setParent(Container parent) {
        this.parent = parent;
    }

    public List<Container> getSubContainers() {
        return subContainers;
    }

    public void setSubContainers(List<Container> subContainers) {
        this.subContainers = subContainers;
    }

    public void addSubContainer(Container subContainer) {
        getSubContainers().add(subContainer);
    }
    
    
    
}
