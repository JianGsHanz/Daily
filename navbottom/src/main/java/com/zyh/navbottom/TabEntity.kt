package com.zyh.navbottom

import com.zyh.navbottom.nav.QTabEntity

/**
 *Time:2020/1/10
 *Author:zyh
 *Description:
 */
class TabEntity : QTabEntity {
    var title: String
    var selectedIcon = 0
    var unSelectedIcon = 0
    var coverIcon = 0

    var selectedIcons = ""
    var unSelectedIcons = ""
    var coverIcons = ""

    constructor(title: String) {
        this.title = title
    }

    constructor(title: String, selectedIcon: Int, unSelectedIcon: Int) {
        this.title = title
        this.selectedIcon = selectedIcon
        this.unSelectedIcon = unSelectedIcon
    }

    constructor(title: String, selectedIcon: Int, unSelectedIcon: Int, tabCoverIcon: Int) {
        this.title = title
        this.selectedIcon = selectedIcon
        this.unSelectedIcon = unSelectedIcon
        this.coverIcon = tabCoverIcon
    }

    constructor(title: String, selectedIcon: String, unSelectedIcon: String, tabCoverIcon: String) {
        this.title = title
        this.selectedIcons = selectedIcon
        this.unSelectedIcons = unSelectedIcon
        this.coverIcons = tabCoverIcon
    }


    //本地图片
    override fun getTabTitle(): String = title
    override fun getTabSelectedIcon(): Int =selectedIcon
    override fun getTabUnselectedIcon(): Int = unSelectedIcon
    override fun getTabCoverIcon(): Int = coverIcon

    //网络图片
    override fun getTabSelectedIcons(): String =selectedIcons
    override fun getTabUnselectedIcons(): String = unSelectedIcons
    override fun getTabCoverIcons(): String = coverIcons





}