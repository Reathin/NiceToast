### NiceToast

![1]
```
compile 'com.rairmmd.nicetoast:library:1.2.0'
```

### 使用
```
//字数不要超过12个
NiceToast.newNiceToast(this)
         .setIcon(R.mipmap.icon_warning)//图标（用圆的）
         .setText("Nice Toast")//文字
         .setBgColor(R.color.divider_color)//背景色
         .setTextColor(R.color.color_dialog_gray)//文字色
         .alignTop(true)//显示在顶部
         .isRound(false)//圆的
         .show();
```
[1]:http://chuantu.biz/t6/124/1509767259x1966967087.gif
