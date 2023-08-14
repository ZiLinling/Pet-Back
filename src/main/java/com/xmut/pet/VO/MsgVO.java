package com.xmut.pet.VO;

import com.xmut.pet.entity.Msg;
import lombok.Data;

@Data
public class MsgVO {
    private Integer id;
    /**
     * 用户id
     */

    private Integer userId;

    /**
     * 店铺名字或者人名
     */
    private String userName;

    /**
     * 接收者的id分类，0为商店，1为用户
     */
    private Byte type;

    /**
     * 接收者的id
     */
    private Integer recipient;

    /**
     * 店铺简介
     */
    private String msg;

    /**
     * 未读消息数量
     */
    private Integer tisNum;
    /**
     * 头像地址
     */
    private String face;
    /**
     * 时间
     */
    private String time;

    public MsgVO(Msg msg) {
        this.id = msg.getId();
        this.userId = msg.getUserId();
        this.type = msg.getType();
        this.recipient = msg.getRecipient();
        this.tisNum = msg.getTisNum();
    }

}
