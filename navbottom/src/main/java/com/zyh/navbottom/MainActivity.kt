package com.zyh.navbottom

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.zyh.navbottom.nav.OnTabSelectListener
import com.zyh.navbottom.nav.QTabEntity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : FragmentActivity() {

    private val mTitles =
        arrayOf("首页", "社区", "拍摄", "消息", "发现")
    private val mIconUnselectIds = intArrayOf(
        R.mipmap.tab1, R.mipmap.tab2,
        R.mipmap.tab3, R.mipmap.tab4, R.mipmap.tab5
    )

    private val mIconSelectIds = intArrayOf(
        R.mipmap.tab1_selected, R.mipmap.tab2_selected,
        R.mipmap.tab3_selected, R.mipmap.tab4_selected, R.mipmap.tab5_selected
    )

    private val mCoverImageIds = intArrayOf(
        0, 0,R.mipmap.icon_cover, 0, 0
    )

    private val mIconUnselectIds1 = arrayOf(
        "https://www.easyicon.net/api/resizeApi.php?id=1130900&size=128", "https://www.easyicon.net/api/resizeApi.php?id=1130900&size=128",
        "https://www.easyicon.net/api/resizeApi.php?id=1130900&size=128", "https://www.easyicon.net/api/resizeApi.php?id=1130900&size=128",
        "https://www.easyicon.net/api/resizeApi.php?id=1130900&size=128"
    )

    private val mIconSelectIds1 = arrayOf(
        "https://www.easyicon.net/api/resizeApi.php?id=581164&size=128", "https://www.easyicon.net/api/resizeApi.php?id=506504&size=128",
        "https://www.easyicon.net/api/resizeApi.php?id=581155&size=128", "https://www.easyicon.net/api/resizeApi.php?id=581156&size=128",
        "https://www.easyicon.net/api/resizeApi.php?id=581157&size=128"
    )

    private val mCoverImageIds1 = arrayOf(
        "", "","https://www.easyicon.net/api/resizeApi.php?id=1138954&size=128", "", ""
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //初始化fragment
        val fragments = ArrayList<Fragment>().apply {
            add(MyFragment.instance("首页"))
            add(MyFragment.instance("社区"))
            add(MyFragment.instance("拍摄"))
            add(MyFragment.instance("消息"))
            add(MyFragment.instance("发现"))
        }

        //初始化NavBottom
        val list = mutableListOf<QTabEntity>()

        for (i in mTitles.indices){
            list.add(TabEntity(mTitles[i],mIconSelectIds1[i],mIconUnselectIds1[i],mCoverImageIds1[i]))
        }

        q_ll.apply {
            setTabData(list as ArrayList<QTabEntity>,supportFragmentManager,R.id.frame,fragments)
            currentTab = 1
            showDot(0)
            showMsg(1,33)
            setOnTabSelectListener(object : OnTabSelectListener {
                override fun onDoubleClick(position: Int) {
                }

                override fun onTabSelect(position: Int) {
                    this@apply.mFragmentChangeManager.showCurrentFragment(position)
                    e("onTabSelect - 切换到$position")
                }

                override fun onTabReselect(position: Int) {
                    e("onTabReselect - 再次点击$position")
                }

                override fun onTabPublish(position: Int) {
                    e("onTabPublish$position")
                }
            })
        }
    }


}
