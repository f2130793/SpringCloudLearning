package com.moxuran.learning;

import com.alibaba.fastjson.JSONObject;
import com.zoi7.component.core.base.BaseClass;

import java.io.Serializable;

/**
 * @author wuqiang
 */
public abstract class BaseSerializable extends BaseClass implements Serializable {

    private static final long serialVersionUID = -8323936810027022742L;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
