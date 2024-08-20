package us.lassey.ikamandwrapper;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.webkit.WebViewAssetLoader;
import androidx.webkit.WebViewClientCompat;


import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import us.lassey.ikamandwrapper.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends Activity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;


    private static class LocalContentWebViewClient extends WebViewClientCompat {

        private final WebViewAssetLoader mAssetLoader;

        LocalContentWebViewClient(WebViewAssetLoader assetLoader) {
            mAssetLoader = assetLoader;
        }

        @Override
        @RequiresApi(21)
        public WebResourceResponse shouldInterceptRequest(WebView view,
                                                          WebResourceRequest request) {
            if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
                Log.i("Chrome", "intercepting options");
                return OptionsAllowResponseBuild();
            }

            WebResourceResponse resp =  mAssetLoader.shouldInterceptRequest(request.getUrl());
            Log.i("Chrome", "intercepting " + request.getUrl() + " with " + resp);
            return resp;
        }

        @Override
        @SuppressWarnings("deprecation") // To support API < 21.
        public WebResourceResponse shouldInterceptRequest(WebView view,
                                                          String url) {

            WebResourceResponse resp = mAssetLoader.shouldInterceptRequest(Uri.parse(url));

            Log.i("Chrome", "intercepting " + url + " with " + resp);
            return resp;
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WebView mWebView = new WebView(this);
        setContentView(mWebView);

        final WebViewAssetLoader assetLoader = new WebViewAssetLoader.Builder()
                .setDomain("192.168.10.1")
                .setHttpAllowed(true)
                .addPathHandler("/assets/", new WebViewAssetLoader.AssetsPathHandler(this))
                .addPathHandler("/res/", new WebViewAssetLoader.ResourcesPathHandler(this))
                .build();
        mWebView.setWebViewClient(new LocalContentWebViewClient(assetLoader));
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.d("Console", consoleMessage.message() + " -- From line " +
                        consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
                return true;
            }

            public Bitmap getDefaultVideoPoster() {
                // When not playing, video elements are represented by a 'poster' image.
                return super.getDefaultVideoPoster();
            }

            public View getVideoLoadingProgressView() {
                //            Obtains a View to be displayed while buffering of full screen video is taking place.
                Log.i("Chrome", "                    View	getVideoLoadingProgressView");
                return super.getVideoLoadingProgressView();
            }

            public void getVisitedHistory(ValueCallback<String[]> callback) {

                //Obtains alist of all visited history items, used for link coloring
                super.getVisitedHistory(callback);
            }

            public void onCloseWindow(WebView window) {
                //            Notify the host application to close the given WebView and remove it from the view system if necessary.
                Log.i("Chrome", "onCloseWindow");
                super.onCloseWindow( window);

            }

            public void onConsoleMessage(String message, int lineNumber, String sourceID) {
                //            This method was deprecated in API level 15. Use onConsoleMessage(ConsoleMessage) instead.
                Log.i("Chrome", "onConsoleMessage");
                 super.onConsoleMessage( message,  lineNumber,  sourceID);

            }

            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
                //            Request the host application to create a new window.
                Log.i("Chrome", "onCreateWindow");
                return super.onCreateWindow( view,  isDialog,
                 isUserGesture,  resultMsg);

            }

            public void onExceededDatabaseQuota(String url, String databaseIdentifier, long quota, long estimatedDatabaseSize, long totalQuota, WebStorage.QuotaUpdater quotaUpdater) {
                //            This method was deprecated in API level 19. This method is no longer called; WebView now uses the HTML5 / JavaScript Quota Management API.
                Log.i("Chrome", "onExceededDatabaseQuota");
                 super.onExceededDatabaseQuota( url,  databaseIdentifier,
                 quota,  estimatedDatabaseSize,  totalQuota, quotaUpdater);

            }

            public void onGeolocationPermissionsHidePrompt() {
                //            Notify the host application that a request for Geolocation permissions, made with a previous call to onGeolocationPermissionsShowPrompt() has been canceled.
                Log.i("Chrome", "            void	onGeolocationPermissionsHidePrompt");
                 super.onGeolocationPermissionsHidePrompt();

            }

            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                //            Notify the host application that web content from the specified origin is attempting to use the Geolocation API, but no permission state is currently set for that origin.
                Log.i("Chrome", "onGeolocationPermissionsShowPrompt");
                 super.onGeolocationPermissionsShowPrompt( origin,  callback);

            }

            public void onHideCustomView() {
                //            Notify the host application that the current page has exited full screen mode.
                Log.i("Chrome", "            void	onHideCustomView");
                 super.onHideCustomView ();

            }

            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                //            Notify the host application that the web page wants to display a JavaScript alert() dialog.
                Log.i("Chrome", "onJsAlert");
                return super.onJsAlert( view,  url,  message,  result);

            }

            public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
                //            Notify the host application that the web page wants to confirm navigation from JavaScript onbeforeunload.
                Log.i("Chrome", "onJsBeforeUnload");
                return super.onJsBeforeUnload( view,  url,  message,  result);

            }

            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                //            Notify the host application that the web page wants to display a JavaScript confirm() dialog.
                Log.i("Chrome", "onJsConfirm");
                return super.onJsConfirm( view,  url,  message,  result);

            }

            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                //            Notify the host application that the web page wants to display a JavaScript prompt() dialog.
                Log.i("Chrome", "onJsPrompt");
                return super.onJsPrompt( view,  url,  message,  defaultValue,  result);

            }

            public boolean onJsTimeout() {
                //            This method was deprecated in API level 17. This method is no longer supported and will not be invoked.
                Log.i("Chrome", "            boolean	onJsTimeout");
                return super.onJsTimeout ();

            }

            public void onPermissionRequest(PermissionRequest request) {
                //            Notify the host application that web content is requesting permission to access the specified resources and the permission currently isn't granted or denied.
                Log.i("Chrome", "onPermissionRequest");
                 super.onPermissionRequest( request);

            }

            public void onPermissionRequestCanceled(PermissionRequest request) {
                //            Notify the host application that the given permission request has been canceled.
                Log.i("Chrome", "onPermissionRequestCanceled");
                 super.onPermissionRequestCanceled( request);

            }

            public void onProgressChanged(WebView view, int newProgress) {
                //            Tell the host application the current progress of loading a page.
                Log.i("Chrome", "onProgressChanged");
                 super.onProgressChanged( view,  newProgress);

            }

            public void onReceivedIcon(WebView view, Bitmap icon) {
                //            Notify the host application of a new favicon for the current page.
                Log.i("Chrome", "onReceivedIcon");
                 super.onReceivedIcon( view,  icon);

            }

            public void onReceivedTitle(WebView view, String title) {
                //            Notify the host application of a change in the document title.
                Log.i("Chrome", "onReceivedTitle");
                 super.onReceivedTitle( view,  title);

            }

            public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
                //            Notify the host application of the url for an apple-touch-icon.
                Log.i("Chrome", "onReceivedTouchIconUrl");
                 super.onReceivedTouchIconUrl( view,  url,  precomposed);

            }

            public void onRequestFocus(WebView view) {
                //            Request display and focus for this WebView.
                Log.i("Chrome", "onRequestFocus");
                 super.onRequestFocus( view);

            }

            public void onShowCustomView(View view, int requestedOrientation, WebChromeClient.CustomViewCallback callback) {
                //            This method was deprecated in API level 18. This method supports the obsolete plugin mechanism, and will not be invoked in futur.
                Log.i("Chrome", "onShowCustomView");
                 super.onShowCustomView( view,  requestedOrientation,
                 callback);

            }

            public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {
                //            Notify the host application that the current page has entered full screen mode.
                Log.i("Chrome", "onShowCustomView");
                 super.onShowCustomView( view,  callback);

            }

            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                //            Tell the client to show a file chooser.
                Log.i("Chrome", "onShowFileChooser");
                return super.onShowFileChooser( webView,
                filePathCallback,  fileChooserParams);

            }


        });
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //String host = "http://appassets.androidplatform.net";
         String host = "http://192.168.10.1";
        mWebView.loadUrl(host + "/assets/ikamand.html");


    }

    static final SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy kk:mm:ss", Locale.US);

    static WebResourceResponse OptionsAllowResponseBuild() {
        Date date = new Date();
        final String dateString = formatter.format(date);

        Map<String, String> headers = new HashMap<String, String>() {{
            put("Connection", "close");
            put("Content-Type", "text/plain");
            put("Date", dateString + " GMT");
            put("Access-Control-Allow-Origin", "appassets.androidplatform.net");
            put("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
            put("Access-Control-Max-Age", "600");
            put("Access-Control-Allow-Credentials", "true");
            put("Access-Control-Allow-Headers", "accept, authorization, Content-Type");
            put("Via", "1.1 vegur");
        }};

        return new WebResourceResponse("text/plain", "UTF-8", 200, "OK", headers, null);
    }
}