package com.zyh.zhihu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.FragmentActivity
import com.zyh.zhihu.pager.IContentView
import com.zyh.zhihu.pager.NoteDetailViewPager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = object : NoteDetailViewPager.Adapter() {

            override fun onCreateContentView(parent: ViewGroup, inflater: LayoutInflater): IContentView {
                val itemWebView =
                    inflater.inflate(R.layout.layout_item_web, parent, false) as WebView
                itemWebView.webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                        view?.loadUrl(url)
                        return true
                    }
                }
                return object : IContentView {
                    override fun getView(): View {
                        return itemWebView
                    }

                    override fun canScrollVertically(direction: Int): Boolean {
                        return itemWebView.canScrollVertically(direction)
                    }
                }
            }

            override fun onBindContentView(itemView: View, position: Int) {
                val itemWebView = itemView as WebView
                when (position) {
                    0 -> {
                        itemWebView.loadUrl("http://www.baidu.com")
                    }
                    1 -> {
                        itemWebView.loadUrl("http://www.zhihu.com")
                    }
                    2 -> {
                        itemWebView.loadUrl("http://www.github.com")
                    }
                    3 -> {
                        itemWebView.loadUrl("https://www.jianshu.com/")
                    }
                    4 -> {
                        itemWebView.loadUrl("https://blog.csdn.net/")
                    }
                }

            }

            override fun getItemCount(): Int {
                return 5
            }

        }
        vp.setAdapter(adapter)
    }

}
