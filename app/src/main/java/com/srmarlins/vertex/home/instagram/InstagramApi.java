package com.srmarlins.vertex.home.instagram;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.srmarlins.vertex.R;
import com.srmarlins.vertex.home.instagram.model.InstagramMedia;

import java.util.List;

/**
 * Created by JaredFowler on 7/21/2016.
 */
public class InstagramApi {

    private static final String BASE_URL = "http://www.instagram.com";

    public static InstagramApi api;

    private WebView webView;

    private InstagramApi() {
    }

    public static InstagramApi getInstance() {
        if (api == null) {
            api = new InstagramApi();
        }
        return api;
    }

    @JavascriptInterface
    public void getInstagramTagUrls(final Context context, final InstagramPhotoListener photoListener, final List<String> tags) {
        webView = (WebView) WebView.inflate(context, R.layout.webview_hack, null);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new WebViewInterface(photoListener), "HTMLOUT");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                webView.loadUrl("javascript:document.getElementsByClassName('_1ooyk')[0].click();window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
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

        public WebViewInterface(InstagramPhotoListener listener) {
            this.listener = listener;
        }

        @JavascriptInterface
        @SuppressWarnings("unused")
        public void processHTML(String html) {
            if (listener != null) {
                InstagramParser instagramParser = new InstagramParser();
                List<InstagramMedia> tagUrls = instagramParser.parseHtmlForImageUrls(html);
                listener.onPhotosReceived(tagUrls);
                listener = null;
            }
        }
    }

}
