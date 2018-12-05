package com.socialgx.GaphEngine.VKApi.CustomVK;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * Created by zaurmac on 2/1/17.
 */
public class LikesInfoGX {
    @SerializedName("count")
    private Integer count;
    @SerializedName("user_likes")
    private Integer userLikes;
    @SerializedName("can_like")
    private Integer canLike;
    @SerializedName("can_publish")
    private Integer canPublish;

    public LikesInfoGX() {
    }

    public Integer getCount() {
        return this.count;
    }

    public Integer getUserLikes() {
        return this.userLikes;
    }

    public Integer getCanLike() {
        return this.canLike;
    }

    public Integer getCanPublish() {
        return this.canPublish;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.canLike, this.count, this.canPublish, this.userLikes});
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(o != null && this.getClass() == o.getClass()) {
            LikesInfoGX likesInfo = (LikesInfoGX)o;
            return Objects.equals(this.count, likesInfo.count) && Objects.equals(this.userLikes, likesInfo.userLikes) && Objects.equals(this.canLike, likesInfo.canLike) && Objects.equals(this.canPublish, likesInfo.canPublish);
        } else {
            return false;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LikesInfo{");
        sb.append("count=").append(this.count);
        sb.append(", userLikes=").append(this.userLikes);
        sb.append(", canLike=").append(this.canLike);
        sb.append(", canPublish=").append(this.canPublish);
        sb.append('}');
        return sb.toString();
    }

    public void setCanLike(Integer canLike) {
        this.canLike = canLike;
    }

    public void setCanPublish(Integer canPublish) {
        this.canPublish = canPublish;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setUserLikes(Integer userLikes) {
        this.userLikes = userLikes;
    }
}