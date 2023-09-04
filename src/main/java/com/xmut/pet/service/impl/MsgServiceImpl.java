package com.xmut.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmut.pet.Utils.JwtUtil;
import com.xmut.pet.VO.MsgVO;
import com.xmut.pet.entity.Chat;
import com.xmut.pet.entity.Msg;
import com.xmut.pet.entity.Store;
import com.xmut.pet.entity.User;
import com.xmut.pet.mapper.MsgMapper;
import com.xmut.pet.service.ChatService;
import com.xmut.pet.service.MsgService;
import com.xmut.pet.service.StoreService;
import com.xmut.pet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Ji
 * @since 2023-07-30 12:33:55
 */
@Service
public class MsgServiceImpl extends ServiceImpl<MsgMapper, Msg> implements MsgService {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserService userService;
    @Autowired
    private ChatService chatService;
    @Autowired
    private StoreService storeService;

    @Override
    public List<MsgVO> listMsg() {
        Integer userId = JwtUtil.getUserId(request.getHeader("token"));
        QueryWrapper<Msg> msgVOQueryWrapper = new QueryWrapper<>();
        msgVOQueryWrapper.eq("user_id", userId);
        List<Msg> msgs = this.list(msgVOQueryWrapper);
        List<MsgVO> msgVOS = new ArrayList<>();
        for (Msg msg : msgs) {
            MsgVO msgVO = new MsgVO(msg);
            if (msgVO.getType() == 1) {
                //跟人聊天
                User recipient = userService.getByid(msgVO.getRecipient());
                msgVO.setFace(recipient.getImg());
                msgVO.setUserName(recipient.getName());
            } else {
                //跟商店客服聊天
                Store store = storeService.getById(msgVO.getRecipient());
                msgVO.setFace(store.getImg());
                msgVO.setUserName(store.getName());
            }
//            Chat
            Chat chat = this.baseMapper.listNew(msg.getUserId(), msg.getRecipient());
            if (chat == null) {
                msgVO.setMsg("你们还没有开始聊天！");
            } else {
                msgVO.setMsg(chat.getMsg());
                msgVO.setTime(chat.getTime());
            }

            msgVOS.add(msgVO);
        }
        return msgVOS;
    }


    public boolean add(Msg msg) {
        Integer userId = JwtUtil.getUserId(request.getHeader("token"));
        QueryWrapper<Msg> msgQueryWrapper = new QueryWrapper<>();
        msgQueryWrapper.eq("user_id", userId).eq("recipient", msg.getRecipient());
        if (msgQueryWrapper.getEntity() != null) {
            return false;
        }
        msg.setTisNum(0);
        msg.setUserId(userId);
        this.save(msg);
        return true;
    }
}
