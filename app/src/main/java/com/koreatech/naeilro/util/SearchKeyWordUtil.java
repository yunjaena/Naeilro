package com.koreatech.naeilro.util;

import android.content.Context;
import android.content.Intent;

import com.koreatech.core.activity.WebViewActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SearchKeyWordUtil {
    public static void searchByNaver(String keyword, Context context) {
        try {
            String url = "https://m.search.naver.com/search.naver?query=";
            url += URLEncoder.encode(keyword, StandardCharsets.UTF_8.toString());
            search(context, keyword, url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static void search(Context context, String title, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }
}
