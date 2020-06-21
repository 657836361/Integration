package com.camelot.message.dingtalk;

import java.util.ArrayList;
import java.util.List;

import com.camelot.message.buildermsg.dto.RobotDingTalkTextMsgDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.camelot.message.DingTalkMsgBuilderUtil;
import com.camelot.message.enums.DingTalkErrorCodeEnum;
import com.camelot.message.enums.DingTalkMsgTypeEnum;
import com.camelot.message.enums.DingTalkParamEnum;
import com.camelot.message.util.HmacSHA256Util;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiChatSendRequest;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.request.OapiSnsGetuserinfoBycodeRequest;
import com.dingtalk.api.request.OapiUserCreateRequest;
import com.dingtalk.api.request.OapiUserDeleteRequest;
import com.dingtalk.api.request.OapiUserGetByMobileRequest;
import com.dingtalk.api.request.OapiUserGetRequest;
import com.dingtalk.api.request.OapiUserGetUseridByUnionidRequest;
import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
import com.dingtalk.api.request.OapiUserUpdateRequest;
import com.dingtalk.api.response.OapiChatSendResponse;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.dingtalk.api.response.OapiSnsGetuserinfoBycodeResponse;
import com.dingtalk.api.response.OapiUserCreateResponse;
import com.dingtalk.api.response.OapiUserDeleteResponse;
import com.dingtalk.api.response.OapiUserGetByMobileResponse;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.dingtalk.api.response.OapiUserGetUseridByUnionidResponse;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import com.dingtalk.api.response.OapiUserUpdateResponse;
import com.taobao.api.ApiException;
import com.taobao.api.BaseTaobaoRequest;
import com.taobao.api.TaobaoResponse;

/**
 * <p>
 * Description:[钉钉工具]
 * </p>
 * 接口扩展参考用户方法实现，官方API
 * 
 * @author shf
 * @version 1.0
 * @date Created on 2020/6/3 10:10
 */
public class DingTalkUtil {

    private static Logger log = LoggerFactory.getLogger(DingTalkUtil.class);

    /**
     * 功能描述: <br>
     * 〈获取 token〉
     * 
     * @Param: appKey appSecret
     * @Return: token
     * @Author: shihengfei
     * @Date: 2020/6/3 10:27
     */
    public static OapiGettokenResponse getAccessToken(String appKey, String appSecret) {
        DefaultDingTalkClient client = dingTalkClient(DingTalkParamEnum.ACCESS_TOKEN.getUrl());
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(appKey);
        request.setAppsecret(appSecret);
        request.setHttpMethod(DingTalkParamEnum.ACCESS_TOKEN.getHttpsType());
        OapiGettokenResponse response = null;
        try {
            response = client.execute(request);
        } catch (ApiException e) {
            // 异常处理
            log.error("获取 accessToken 异常:", e);
            return null;
        }
        return response;
    }

    /**
     * 功能描述: <br>
     * 〈创建客户端连接〉
     * 
     * @Param: 连接URL
     * @Return: DefaultDingTalkClient
     * @Author: shihengfei
     * @Date: 2020/6/3 10:22
     */
    private static DefaultDingTalkClient dingTalkClient(String url) {
        return new DefaultDingTalkClient(url);
    }

    /**
     * 功能描述: <br>
     * 〈执行请求〉
     * 
     * @Param:
     * @Return:
     * @Author: shihengfei
     * @Date: 2020/6/3 10:46
     */
    public static TaobaoResponse execute(DingTalkParamEnum dingTalkParamEnum, BaseTaobaoRequest request,
        String accessToken) {
        DefaultDingTalkClient client = dingTalkClient(dingTalkParamEnum.getUrl());
        // 请求日志记录
        log.info("{}:请求入参:{}", dingTalkParamEnum.getServerName(), JSON.toJSONString(request));
        TaobaoResponse response = run(client, request, dingTalkParamEnum, accessToken);
        // 响应日志记录
        log.info("{}:请求出参:{}", dingTalkParamEnum.getServerName(), JSON.toJSONString(response));
        return response;
    }

    /**
     * 功能描述: <br>
     * 〈递归重试调用API〉
     * 
     * @Param:
     * @Return:
     * @Author: shihengfei
     * @Date: 2020/6/3 10:52
     */
    private static TaobaoResponse run(DefaultDingTalkClient client, BaseTaobaoRequest request,
        DingTalkParamEnum dingTalkParamEnum, String accessToken) {
        TaobaoResponse response = null;
        try {
            response = client.execute(request, accessToken);
            // 是否调用受限制，是等待5s递归调用
            if (DingTalkErrorCodeEnum.dingTalkErrorCodeEnums.get(response.getErrorCode()) != null) {
                log.info("{}:请求受限,重试:{}", dingTalkParamEnum.getServerName(), response.getMessage());
                Thread.sleep(5000L);
                run(client, request, dingTalkParamEnum, accessToken);
            }
        } catch (ApiException | InterruptedException e) {
            // 异常日志记录
            log.info("{}:API请求异常:", dingTalkParamEnum.getServerName(), e);
        }
        return response;
    }

    /**
     * 功能描述: <br>
     * 〈根据authcode获取用户id〉
     * 
     * @Param: authCode 授权码
     * @Return: userId 用户Id
     * @Author: shihengfei
     * @Date: 2020/6/3 17:13
     */
    public static OapiUserGetuserinfoResponse getDingTalkUserIdByAuthCode(String authCode, String accessToken) {
        OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
        request.setCode(authCode);
        request.setHttpMethod(DingTalkParamEnum.GET_USER_ID_AUTH_CODE.getHttpsType());
        OapiUserGetuserinfoResponse response =
            (OapiUserGetuserinfoResponse)execute(DingTalkParamEnum.GET_USER_ID_AUTH_CODE, request, accessToken);
        return response;
    }

    /**
     * 功能描述: <br>
     * 〈根据手机号获取钉钉侧用户id〉
     * 
     * @Param: mobile 手机号
     * @Return: userId
     * @Author: shihengfei
     * @Date: 2020/6/3 17:19
     */
    public static OapiUserGetByMobileResponse getDingTalkUserIdByMobile(String mobile, String accessToken) {
        OapiUserGetByMobileRequest request = new OapiUserGetByMobileRequest();
        request.setMobile(mobile);
        OapiUserGetByMobileResponse response =
            (OapiUserGetByMobileResponse)execute(DingTalkParamEnum.GET_USER_ID, request, accessToken);
        return response;
    }

    /**
     * 功能描述: <br>
     * 〈创建用户,入参随业务自行定义〉
     * 
     * @Param: dto 入参随业务自行定义
     * @Return: 用户id
     * @Author: shihengfei
     * @Date: 2020/6/3 17:31
     */
    public static OapiUserCreateResponse createDingTalkUser(Object dto, String accessToken) {
        OapiUserCreateRequest request = new OapiUserCreateRequest();
        // 以下是必填字段
        request.setMobile("用户手机号");
        request.setName("用户名");
        // 用户所属分组，需要用字符串， "[100,200]" 这种格式
        List<Long> departments = new ArrayList<>();
        request.setDepartment(JSON.toJSONString(departments));
        // 以下是非必须字段
        request.setEmail("邮箱");
        request.setJobnumber("工号");
        OapiUserCreateResponse response =
            (OapiUserCreateResponse)execute(DingTalkParamEnum.CREATE_USER, request, accessToken);
        return response;
    }

    /**
     * 功能描述: <br>
     * 〈修改用户〉
     * 
     * @Param: dto 入参随业务自行定义
     * @Return:
     * @Author: shihengfei
     * @Date: 2020/6/3 17:47
     */
    public static OapiUserUpdateResponse updateDingTalkUser(Object dto, String accessToken) {
        OapiUserUpdateRequest request = new OapiUserUpdateRequest();
        // 这是必填字段
        request.setUserid("更新用户userId必填");
        // 其余是非必填字段，需要其他字段参考钉钉API
        request.setMobile("手机号");
        request.setName("用户名");
        OapiUserUpdateResponse response =
            (OapiUserUpdateResponse)execute(DingTalkParamEnum.UPDATE_USER, request, accessToken);
        return response;
    }

    /**
     * 功能描述: <br>
     * 〈删除用户〉
     * 
     * @Param: userId 用户id
     * @Return: 是否成功
     * @Author: shihengfei
     * @Date: 2020/6/3 17:54
     */
    public static OapiUserDeleteResponse deleteDingTalkUser(String userId, String accessToken) {
        OapiUserDeleteRequest request = new OapiUserDeleteRequest();
        request.setUserid(userId);
        request.setHttpMethod(DingTalkParamEnum.DELETE_USER.getHttpsType());
        OapiUserDeleteResponse response =
            (OapiUserDeleteResponse)execute(DingTalkParamEnum.DELETE_USER, request, accessToken);
        return response;
    }

    /**
     * 功能描述: <br>
     * 〈通过用户id获取详细信息〉
     * 
     * @Param: 用户id
     * @Return: 用户信息
     * @Author: shihengfei
     * @Date: 2020/6/3 17:59
     */
    public static OapiUserGetResponse getUserDetailByUserId(String userId, String accessToken) {
        OapiUserGetRequest request = new OapiUserGetRequest();
        request.setUserid(userId);
        request.setHttpMethod(DingTalkParamEnum.GET_USER.getHttpsType());
        OapiUserGetResponse response = (OapiUserGetResponse)execute(DingTalkParamEnum.GET_USER, request, accessToken);
        return response;
    }

    /**
     * 功能描述: <br>
     * 〈根据 unionid 获取用户id〉
     * 
     * @Param: unionid 授权码
     * @Return: 用户id
     * @Author: shihengfei
     * @Date: 2020/6/3 18:24
     */
    public static OapiUserGetUseridByUnionidResponse getUserIdByUnionId(String unionid, String accessToken) {
        OapiUserGetUseridByUnionidRequest request = new OapiUserGetUseridByUnionidRequest();
        request.setUnionid(unionid);
        request.setHttpMethod(DingTalkParamEnum.GET_USERID_BY_UNIONID.getHttpsType());
        OapiUserGetUseridByUnionidResponse response =
            (OapiUserGetUseridByUnionidResponse)execute(DingTalkParamEnum.GET_USERID_BY_UNIONID, request, accessToken);
        return response;
    }

    /**
     * 功能描述: <br>
     * 〈通过扫码授权码获取开放用户ID〉
     * 
     * @Param:
     * @Return:
     * @Author: shihengfei
     * @Date: 2020/6/5 18:12
     */
    public static OapiSnsGetuserinfoBycodeResponse getUserInfoByCode(String code, String accessToken) {
        OapiSnsGetuserinfoBycodeRequest request = new OapiSnsGetuserinfoBycodeRequest();
        request.setTmpAuthCode(code);
        OapiSnsGetuserinfoBycodeResponse response =
            (OapiSnsGetuserinfoBycodeResponse)execute(DingTalkParamEnum.GET_USERINFO_BY_CODE, request, accessToken);
        return response;
    }

    /**
     * 功能描述: <br>
     * 〈发送工作通知消息〉
     * 
     * @Param: 消息入参
     * @Return: 发送结果
     * @Author: shihengfei
     * @Date: 2020/6/4 15:04
     */
    public static OapiMessageCorpconversationAsyncsendV2Response
        sendWorkMsg(OapiMessageCorpconversationAsyncsendV2Request request, String accessToken) {
        OapiMessageCorpconversationAsyncsendV2Response response =
            (OapiMessageCorpconversationAsyncsendV2Response)execute(DingTalkParamEnum.SEND_WORK_MSG, request,
                accessToken);
        return response;
    }

    /**
     * 功能描述: <br>
     * 〈发送群消息〉
     * 
     * @Param: 消息入参
     * @Return: 发送结果
     * @Author: shihengfei
     * @Date: 2020/6/8 11:02
     */
    public static OapiChatSendResponse sendChatMsg(OapiChatSendRequest request, String accessToken) {
        OapiChatSendResponse response =
            (OapiChatSendResponse)execute(DingTalkParamEnum.SEND_CHAT_MSG, request, accessToken);
        return response;
    }

    /**
     * 功能描述: <br>
     * 〈发送群机器人消息〉
     * 
     * @Param: request 构建机器人消息 secret 创建机器人加签的密钥 webhook 机器人地址
     * @Return:
     * @Author: shihengfei
     * @Date: 2020/6/17 11:22
     */
    public static OapiRobotSendResponse sendRobotMsg(OapiRobotSendRequest request, String secret, String webhook) {
        // 构建链接
        Long timestamp = System.currentTimeMillis();
        String sign = HmacSHA256Util.getSign(timestamp, secret);
        StringBuffer sb = new StringBuffer();
        String url =
            sb.append(webhook).append("&timestamp=").append(timestamp).append("&sign=").append(sign).toString();
        DefaultDingTalkClient client = dingTalkClient(url);
        OapiRobotSendResponse response = null;
        try {
            log.info(">>>>> call robotSendMsg before, params: {}", JSON.toJSONString(request));
            response = client.execute(request);
        } catch (ApiException e) {
            log.error(">>>>> call robotSendMsg error, msg: {}", e.getErrMsg());
        }
        log.info("<<<<< call robotSendMsg after, response: {}", JSON.toJSONString(response));
        return response;
    }

    public static void main(String[] args) {
        //
        RobotDingTalkTextMsgDTO robotDingTalkTextMsgDTO = new RobotDingTalkTextMsgDTO();
        robotDingTalkTextMsgDTO.setContent("测试机器人");
        robotDingTalkTextMsgDTO.setMsgType(DingTalkMsgTypeEnum.TEXT_MSG.getMsgType());
        OapiRobotSendRequest oapiRobotSendRequest =
            DingTalkMsgBuilderUtil.buildRobotMsgRequest(robotDingTalkTextMsgDTO);
        DingTalkUtil.sendRobotMsg(oapiRobotSendRequest,
            "SEC77858f84a95b5227951366dd9fdb4f6fc1f0831e24b86eac1da081d0dccc4ebd",
            "https://oapi.dingtalk.com/robot/send?access_token=7187ef5bd050c66a5237356075b9aa301230795799a2378de573b3c28b572e73");

    }
}
