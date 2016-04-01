package customtote.motiviga.inrelt.id.co.totebagcustom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    public static final int ABOUT = Menu.FIRST+1;
    public static final int EXIT = Menu.FIRST+2;

    final Activity activity = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_main);
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                activity.setTitle("Loading coy...");
                activity.setProgress(newProgress * 100);
                if (newProgress == 100) {
                    activity.setTheme(R.string.app_name);
                    activity.setTitle("HMTI");
                }
            }
        });
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl("http://103.252.100.236/totebag");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu
                .add(Menu.NONE, ABOUT, Menu.NONE, "About");
//                .setIcon(R.drawable.icon_about);
        menu
                .add(Menu.NONE, EXIT, Menu.NONE, "Exit");
//                .setIcon(R.drawable.icon_exit);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case ABOUT:
                startActivity(new Intent(this, About.class));
                return(true);
            case EXIT:
                finish();
                return(true);
        }
        return(super.onOptionsItemSelected(item));

    }
}
