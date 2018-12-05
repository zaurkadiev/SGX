package com.socialgx.GaphEngine.VKApi.VKTools;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.socialgx.GaphEngine.VKApi.CustomVK.CommentXtrPidGX;
import com.socialgx.GaphEngine.VKApi.CustomVK.LikesInfoGX;
import com.socialgx.GaphEngine.VKApi.VKAccount.VKAccount;
import com.socialgx.GaphEngine.VKApi.VKConfig;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ApiLimitsException;
import com.vk.api.sdk.exceptions.ApiTooManyException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.base.LikesInfo;
import com.vk.api.sdk.objects.photos.CommentXtrPid;
import com.vk.api.sdk.objects.photos.responses.GetAllCommentsResponse;
import com.vk.api.sdk.queries.photos.PhotosGetAllCommentsQuery;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;

/**
 * Created by zaurmac on 1/29/17.
 */
public class Loader {

    private static Logger LOGGER = Logger.getLogger("VK_LOADER");

    public static List<CommentXtrPid> loadAccountPhotoComments(VKAccount vkAccount, int userId){

        List<CommentXtrPid> comments = new ArrayList<>();
        int i = 0;

        while ( i < VKConfig.LOAD_PHOTO_COMMENTS_COUNT){

            try {
                Thread.sleep(333);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                GetAllCommentsResponse commentXtrPids = vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor())
                        .offset(i)
                        .count(100)
                        .ownerId(userId)
                        .execute();

                if (commentXtrPids.getItems().size() == 0) break;

                comments.addAll(commentXtrPids.getItems());

            } catch (ApiException e) {
                e.printStackTrace();
                break;
            } catch (ClientException e) {
                e.printStackTrace();
                break;
            }
            i = i + 100;
        }

        System.out.println("LOADED "+ comments.size() + " comments");
        return comments;
    }

    public static List<CommentXtrPidGX> loadAccountPhotoCommentsExecute(VKAccount vkAccount, int userId){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        List<CommentXtrPidGX> comments = new ArrayList<>();
        JsonArray commentsJson = new JsonArray();
        JsonElement jsonElement = new JsonObject();
        try {
             jsonElement = vkAccount.getVK().execute().batch(vkAccount.getCurrenetUserActor(),
                    vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor()).ownerId(userId).offset(0).count(100),
                    vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor()).ownerId(userId).offset(100).count(100),
                     vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor()).ownerId(userId).offset(200).count(100),
                     vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor()).ownerId(userId).offset(300).count(100),
                     vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor()).ownerId(userId).offset(400).count(100),
                     vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor()).ownerId(userId).offset(500).count(100),
                     vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor()).ownerId(userId).offset(600).count(100),
                     vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor()).ownerId(userId).offset(700).count(100),
                     vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor()).ownerId(userId).offset(800).count(100),
                     vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor()).ownerId(userId).offset(900).count(100),
                     vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor()).ownerId(userId).offset(1000).count(100),
                     vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor()).ownerId(userId).offset(1100).count(100),
                     vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor()).ownerId(userId).offset(1200).count(100),
                     vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor()).ownerId(userId).offset(1300).count(100),
                     vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor()).ownerId(userId).offset(1400).count(100),
                     vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor()).ownerId(userId).offset(1500).count(100),
                     vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor()).ownerId(userId).offset(1600).count(100),
                     vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor()).ownerId(userId).offset(1700).count(100),
                     vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor()).ownerId(userId).offset(1800).count(100),
                     vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor()).ownerId(userId).offset(1900).count(100),
                     vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor()).ownerId(userId).offset(2000).count(100),
                     vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor()).ownerId(userId).offset(2100).count(100),
                     vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor()).ownerId(userId).offset(2200).count(100),
                     vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor()).ownerId(userId).offset(2300).count(100),
                     vkAccount.getVK().photos().getAllComments(vkAccount.getCurrenetUserActor()).ownerId(userId).offset(2400).count(100)
            ).execute();

        } catch (ApiException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }


        for (JsonElement element : jsonElement.getAsJsonArray()) {

            if(!element.isJsonObject()) continue;

            JsonObject jo = element.getAsJsonObject();

            JsonArray commentItems = jo.getAsJsonArray("items");

            if(commentItems.size() != 0)
                commentsJson.addAll(commentItems);
        }

        for (JsonElement element : commentsJson) {
            CommentXtrPidGX commentXtrPid = new CommentXtrPidGX();
            commentXtrPid.setFromId(element.getAsJsonObject().get("from_id").getAsInt());
            commentXtrPid.setId(element.getAsJsonObject().get("id").getAsInt());
            commentXtrPid.setDate(element.getAsJsonObject().get("date").getAsInt());
            commentXtrPid.setText(element.getAsJsonObject().get("text").getAsString());
            commentXtrPid.setPid(element.getAsJsonObject().get("pid").getAsInt());

            if(element.getAsJsonObject().has("likes")){
                JsonObject likes = element.getAsJsonObject().get("likes").getAsJsonObject();
                LikesInfoGX likesInfoGX = new LikesInfoGX();

                if (likes.has("can_like"))
                    likesInfoGX.setCanLike(likes.get("can_like").getAsInt());

                if (likes.has("count"))
                    likesInfoGX.setCount(likes.get("count").getAsInt());

                if (likes.has("user_likes"))
                    likesInfoGX.setUserLikes(likes.get("user_likes").getAsInt());

                if (likes.has("can_publish"))
                    likesInfoGX.setCanPublish(likes.get("can_publish").getAsInt());

                commentXtrPid.setLikes(likesInfoGX);
            }

            if (element.getAsJsonObject().has("reply_to_comment"))
                commentXtrPid.setReplyToComment(element.getAsJsonObject().get("reply_to_comment").getAsInt());

            if (element.getAsJsonObject().has("reply_to_user"))
                commentXtrPid.setReplyToUser(element.getAsJsonObject().get("reply_to_user").getAsInt());

            comments.add(commentXtrPid);
        }
        System.out.println("LOADED "+ comments.size() + " comments");

        return comments;
    }

}
