package com.dawei.test.demo.client;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserVO {
    private String userId;
    private boolean editable; //是否能修改昵称, true --能修改, false --不能修改
    private String oldName; //旧昵称
    private String userName; //昵称
    private String headUrl;// 头像URL
    private Integer sex;// 0女，1男
    private String signature;// 个性签名
    private Long dateOfBirth;// 出生日期
    private String address;// 住址
    private int followerCnt; //粉丝数
    private int followeeCnt; //关注数
    private int boardCnt; //加入版块数
    private int annLikeCnt; //帖子点赞数
    private String label; //标签,如官方团队
    private boolean followedBy; //是否被关注

}