package com.srmarlins.vertex.vertex.api;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.srmarlins.vertex.R;
import com.srmarlins.vertex.vertex.api.model.InstagramMedia;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JaredFowler on 7/21/2016.
 */
public class VertexApi {

    private static final String BASE_URL = "http://www.instagram.com";

    public static VertexApi api;

    private WebView webView;

    public static VertexApi getInstance() {
        if (api == null) {
            api = new VertexApi();
        }
        return api;
    }

    private VertexApi() {
    }

    @JavascriptInterface
    public void getInstagramTagUrls(final Context context, final InstagramPhotoListener photoListener, final List<String> tags) {
        webView = (WebView) WebView.inflate(context, R.layout.webview_hack, null);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new WebViewInterface(photoListener), "HTMLOUT");
        webView.setWebViewClient(new WebViewClient() {
            public boolean isRedirected = false;

            @Override
            public void onPageFinished(WebView view, String url) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        webView.loadUrl("javascript:document.getElementsByClassName('_1ooyk')[0].click();window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                    }
                }, 1000);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(BASE_URL + "/explore/tags/vertex2016");
                isRedirected = true;
                return true;
            }
        });
        webView.loadUrl(BASE_URL + "/explore/tags/vertex2016");
    }


    public interface InstagramPhotoListener {
        void onPhotosReceived(List<InstagramMedia> photoUrls);

        void onError(Throwable t);
    }

    class WebViewInterface {
        private InstagramPhotoListener listener;

        public WebViewInterface(InstagramPhotoListener listener){
            this.listener = listener;
        }
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void processHTML(String html) {
            InstagramParser instagramParser = new InstagramParser();
            List<InstagramMedia> tagUrls = instagramParser.parseHtmlForImageUrls(html);
            listener.onPhotosReceived(tagUrls);
        }
    }

}
