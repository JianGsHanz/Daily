package com.zyh.navbottom.nav;

public interface OnTabSelectListener {
    void onTabSelect(int position);
    void onTabReselect(int position);
    void onTabPublish(int position);
    void onDoubleClick(int position);
}