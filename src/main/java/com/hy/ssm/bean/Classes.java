package com.hy.ssm.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @Auther: wangsq
 * @Date: 2019/8/29 14:45
 * @Description:
 */
@TableName("classes")
public class Classes implements Serializable{
    @TableId(value = "cno")
    private Integer cid;
    @TableField("classname")
    private String cname;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "IClasses{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                '}';
    }
}
