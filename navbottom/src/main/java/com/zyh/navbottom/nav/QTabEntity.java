package com.zyh.navbottom.nav;


import androidx.annotation.DrawableRes;

public interface QTabEntity {
    String getTabTitle();

    @DrawableRes
    int getTabSelectedIcon();

    @DrawableRes
    int getTabUnselectedIcon();

    @DrawableRes
    int getTabCoverIcon();

    String getTabSelectedIcons();

    String getTabUnselectedIcons();

    String getTabCoverIcons();
}