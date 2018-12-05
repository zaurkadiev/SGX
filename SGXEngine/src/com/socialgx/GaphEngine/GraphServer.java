package com.socialgx.GaphEngine;


import com.socialgx.GaphEngine.Graph.EdgeGX;
import com.socialgx.GaphEngine.Graph.GraphGX;
import com.socialgx.GaphEngine.Graph.VertexGX;
import com.socialgx.GaphEngine.VKApi.CustomVK.CommentXtrPidGX;
import com.socialgx.GaphEngine.VKApi.RUL;
import com.socialgx.GaphEngine.VKApi.VKAccount.VKAccount;
import com.socialgx.GaphEngine.VKApi.VKTaskExecutor;
import com.socialgx.GaphEngine.VKApi.VKTools.Loader;
import com.socialgx.Main;
import com.socialgx.Tools.DataTools;
import com.vk.api.sdk.objects.photos.CommentXtrPid;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by zaurmac on 1/25/17.
 */

public class GraphServer {

    private Logger LOGGER = Logger.getLogger(getClass().getName());

    private List<Thread> mTaskList;

    public GraphServer(){
        mTaskList = new ArrayList<>();
    }


    public void exploreVKTask(int level, int cupID, String accessToken, int userID){

        VKAccount vkAccount = new VKAccount();
        vkAccount.makeUserActor(userID, accessToken);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String key = String.valueOf(vkAccount.getCurrenetUserActor().getId());

                String jsonGraph = VKTaskExecutor.explorePhotoComments(vkAccount, level, cupID);



                mTaskList.remove(Thread.currentThread());
            }
        });
        thread.setName(String.valueOf(vkAccount.getCurrenetUserActor().getId()));
        mTaskList.add(thread);
        thread.start();
    }

}
