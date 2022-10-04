package com.moxuanran.learning.tile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wutao
 * @date 2022/9/27 15:52
 */
public class TileFactory {
    private final Map<String, Drawable> images;

    public TileFactory() {
        images = new HashMap<>();
    }

    public Drawable getDrawable(String image) {
        if (!images.containsKey(image)) {
            switch (image) {
                case "河流":
                    images.put(image, new River());
                    break;
                case "石头":
                    images.put(image, new Stone());
                    break;
                case "房屋":
                    images.put(image, new House());
                    break;
                case "道路":
                    images.put(image, new Grass());
                    break;
                default:
                    break;
            }
        }

        return images.get(image);
    }
}
