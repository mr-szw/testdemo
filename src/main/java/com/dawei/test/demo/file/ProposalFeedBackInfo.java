package com.dawei.test.demo.file;

import lombok.Data;

/**
 * Create by Dawei on 2019/11/1 提案反馈信息
 */
@Data
public class ProposalFeedBackInfo {

    private String boardName;

    private String proposalId;

    // 提案类型
    private String proposalType;

    private String proposalContent;

    // 关联帖子数
    private Integer linkAnnounceCnt;

    // 赞同帖子人数
    private Integer likeProposalCnt;

    // 提案状态
    private String proposalStatusStr;
    private String proposalStatus;

    // 提案操作人
    private String proposalOperatorName = "";

    // 创建时间
    private String createTime;

    public String getProposalContent() {

        if (proposalContent != null) {
            proposalContent = proposalContent.replace("\"", "'");
        }
        return proposalContent;
    }
}