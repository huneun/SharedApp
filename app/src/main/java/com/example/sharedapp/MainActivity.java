package com.example.sharedapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.v2.KakaoLinkResponse;
import com.kakao.kakaolink.v2.KakaoLinkService;
import com.kakao.message.template.ButtonObject;
import com.kakao.message.template.ContentObject;
import com.kakao.message.template.FeedTemplate;
import com.kakao.message.template.LinkObject;
import com.kakao.message.template.SocialObject;
import com.kakao.message.template.TemplateParams;
import com.kakao.network.ErrorResult;
import com.kakao.network.callback.ResponseCallback;
import com.kakao.util.helper.log.Logger;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.Templates;

public class MainActivity extends AppCompatActivity {

    Button btn_sharing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_sharing = (Button)findViewById(R.id.btn_sharing);

        btn_sharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_custom);
                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setGravity(Gravity.BOTTOM);
                dialog.getWindow().setAttributes((WindowManager.LayoutParams)params);
                dialog.show();

            }
        });


    }
    public void kakaolink() {
        FeedTemplate params = (FeedTemplate) FeedTemplate
                .newBuilder(ContentObject.newBuilder("????????????",
                        "https://recipe1.ezmember.co.kr/cache/recipe/2017/02/03/b3f450689d41986748661de4fe2d47081.png",
                        LinkObject.newBuilder().setWebUrl("https://woodsorrel.imweb.me/").build())
                        .setDescrption("?????? 5???").build())
                .setSocial(SocialObject.newBuilder().setLikeCount(10).setCommentCount(20)
                        .setSharedCount(30).setViewCount(40).build())
                .addButton(new ButtonObject("????????? ??????", LinkObject.newBuilder()
                        .setWebUrl("https://woodsorrel.imweb.me/")
                        .setMobileWebUrl("https://woodsorrel.imweb.me/").build()))
                .addButton(new ButtonObject("????????? ??????", LinkObject.newBuilder()
                        .setWebUrl("'https://woodsorrel.imweb.me/'").setMobileWebUrl("'https://woodsorrel.imweb.me/'")
                        .setAndroidExecutionParams("key1=value1").setIosExecutionParams("key1=value1").build()))
                .build();

        Map<String, String> serverCallbackArgs = new HashMap<String, String>();
        serverCallbackArgs.put("user_id", "${current_user_id}");
        serverCallbackArgs.put("product_id", "${shared_product_id}");

        KakaoLinkService.getInstance().sendDefault(this, params, new ResponseCallback<KakaoLinkResponse>() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                Logger.e(errorResult.toString());
            }

            @Override
            public void onSuccess(KakaoLinkResponse result) {
                // ????????? ?????????????????? ?????? ????????? ??????????????? ??????. ????????? ??????????????? ??????????????? ????????? ??? ??? ??????. ?????? ?????? ????????? ???????????? ????????? ??????????????? ??????.
            }
        });

    }

}