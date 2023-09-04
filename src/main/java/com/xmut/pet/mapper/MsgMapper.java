package com.xmut.pet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmut.pet.VO.MsgVO;
import com.xmut.pet.entity.Chat;
import com.xmut.pet.entity.Msg;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Ji
 * @since 2023-07-30 12:33:55
 */
public interface MsgMapper extends BaseMapper<Msg> {

    //我要得到我id上所有的有添加到msg列表里面的
    @Select("SELECT msg, time FROM msg WHERE user_id = #{ userId } ")
    List<MsgVO> listAll(Integer userId);

    //我要得到对方发来的以后一条消息
    @Select("SELECT msg, time FROM chat WHERE user_id = #{ recipient } AND recipient = #{ userId } ORDER BY time DESC LIMIT 1;")
    Chat listNew(Integer userId, Integer recipient);

}
