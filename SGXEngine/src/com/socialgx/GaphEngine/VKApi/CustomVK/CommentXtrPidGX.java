package com.socialgx.GaphEngine.VKApi.CustomVK;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.base.LikesInfo;
import com.vk.api.sdk.objects.photos.CommentXtrPid;
import com.vk.api.sdk.objects.wall.CommentAttachment;

import java.util.List;
import java.util.Objects;

/**
 * Created by zaurmac on 2/1/17.
 */
public class CommentXtrPidGX {
    @SerializedName("id")
    private Integer id;
    @SerializedName("from_id")
    private Integer fromId;
    @SerializedName("date")
    private Integer date;
    @SerializedName("text")
    private String text;
    @SerializedName("likes")
    private LikesInfoGX likes;
    @SerializedName("reply_to_user")
    private Integer replyToUser;
    @SerializedName("reply_to_comment")
    private Integer replyToComment;
    @SerializedName("attachments")
    private List<CommentAttachment> attachments;
    @SerializedName("pid")
    private Integer pid;


    public Integer getId() {
        return this.id;
    }

    public Integer getFromId() {
        return this.fromId;
    }

    public Integer getDate() {
        return this.date;
    }

    public String getText() {
        return this.text;
    }

    public LikesInfoGX getLikes() {
        return this.likes;
    }

    public Integer getReplyToUser() {
        return this.replyToUser;
    }

    public Integer getReplyToComment() {
        return this.replyToComment;
    }

    public List<CommentAttachment> getAttachments() {
        return this.attachments;
    }

    public Integer getPid() {
        return this.pid;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.date, this.attachments, this.pid, this.id, this.text, this.fromId, this.replyToUser, this.replyToComment, this.likes});
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            CommentXtrPidGX commentXtrPid = (CommentXtrPidGX) o;
            return Objects.equals(this.id, commentXtrPid.id) && Objects.equals(this.fromId, commentXtrPid.fromId) && Objects.equals(this.date, commentXtrPid.date) && Objects.equals(this.text, commentXtrPid.text) && Objects.equals(this.likes, commentXtrPid.likes) && Objects.equals(this.replyToUser, commentXtrPid.replyToUser) && Objects.equals(this.replyToComment, commentXtrPid.replyToComment) && Objects.equals(this.attachments, commentXtrPid.attachments) && Objects.equals(this.pid, commentXtrPid.pid);
        } else {
            return false;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("CommentXtrPidGX{");
        sb.append("id=").append(this.id);
        sb.append(", fromId=").append(this.fromId);
        sb.append(", date=").append(this.date);
        sb.append(", text=\'").append(this.text).append("\'");
        sb.append(", likes=").append(this.likes);
        sb.append(", replyToUser=").append(this.replyToUser);
        sb.append(", replyToComment=").append(this.replyToComment);
        sb.append(", attachments=").append(this.attachments);
        sb.append(", pid=").append(this.pid);
        sb.append('}');
        return sb.toString();
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public void setAttachments(List<CommentAttachment> attachments) {
        this.attachments = attachments;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLikes(LikesInfoGX likes) {
        this.likes = likes;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public void setReplyToComment(Integer replyToComment) {
        this.replyToComment = replyToComment;
    }

    public void setReplyToUser(Integer replyToUser) {
        this.replyToUser = replyToUser;
    }

    public void setText(String text) {
        this.text = text;
    }

}