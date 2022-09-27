package com.moxuanran.learning.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wutao
 * @date 2022/9/27 13:57
 */
public class Building {
    private List<String> buildingComponents = new ArrayList<>();

    public void setBasement(String basement) {
        this.buildingComponents.add(basement);
    }

    public void setWall(String wall) {
        this.buildingComponents.add(wall);
    }

    public void setRoof(String roof) {
        this.buildingComponents.add(roof);
    }

    public String toString() {
        StringBuilder buildStr = new StringBuilder();
        for (int i = buildingComponents.size() - 1; i>=0; i--) {
            buildStr.append(buildingComponents.get(i));
        }

        return buildStr.toString();
    }
}
