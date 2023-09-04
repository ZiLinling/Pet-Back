package com.xmut.pet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xmut.pet.VO.MsgVO;
import com.xmut.pet.entity.Msg;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Ji
 * @since 2023-07-30 12:33:55
 */
public interface MsgService extends IService<Msg> {
    List<MsgVO> listMsg();

    boolean add(Msg msg);
}
