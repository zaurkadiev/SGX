package com.socialgx.GaphEngine.VKApi.VKAccount;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;

import java.util.Timer;


public class VKAccount {

    private int APP_ID = 5824537;
    private String SECURE_KEY = "IN3iExHYLkBWzGWaq3Bw";
    private String REDIRECT_URL = "localhost:3000/confirm";

    private UserActor mCurrenetUserActor;
    private VkApiClient VK;
    private Timer mTimer;

    public VKAccount(){
        TransportClient transportClient = new HttpTransportClient();
        VK = new VkApiClient(transportClient);
    }

    public void makeUserActor(int userId, String access_token/*, int expires_in*/){

        // SET CURRENT USER ACTOR
        mCurrenetUserActor = new UserActor(userId, access_token);
    }

    public UserActor getCurrenetUserActor() {
        return mCurrenetUserActor;
    }

    public VkApiClient getVK(){
        return VK;
    }
}
