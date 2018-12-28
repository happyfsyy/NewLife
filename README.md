# 新生
眼看千遍，不如手敲一遍。好久没敲代码，手都开始生疏了。其中一部分代码来自郭神的第一行代码，最开始我就是看这本书复习的。
# 文件介绍
* MainActivity主要测试Activity的生命周期，Intent的用法，Menu的使用，onSaveInstanceState()方法的使用。
* SecondActivity测试startActivityForResult()，以及启动Activity的新写法startAct()。
* BaseActivity主要用于测试，显示所有Activity的名字，以及退出所有Activity。
* UiActivity主要用于测试TextView、Button、EditText、ImageView、ProgressBar、Dialog等等各种控件。
* ui_test.xml主要是用于测试ConstraintLayout，以及BorderlessButton和CodeFont等各种style。
* DayListViewActivity主要是用于测试ListView。
* DayRecyclerViewActivity主要是测试RecyclerView。
* OnItemClickListener可以作为之后所有RecyclerView.Adapter<>的监听器。
* ChatActivity实现了一个简单的聊天界面。
* BaseAdapter封装了一个简单的RecyclerView.Adapter的实现。
* FragmentTestActivity测试了Fragment和Activity之间交互时候的生命周期。
* NewsActivity是一个简易版本的新闻应用，兼顾手机和平板。  
![phone](https://raw.githubusercontent.com/happyfsyy/NewLife/master/app/img_folder/img1.png)
![tablet](https://raw.githubusercontent.com/happyfsyy/NewLife/master/app/img_folder/img2.PNG)
* CycleViewPagerAct实现循环滑动ViewPager。
* ViewPagerFragAct实现基本的ViewPager+Fragment。
* SendBroadcastAct发送自定义广播。
* BroadcastLoginActivity实现强制下线以及记住密码功能。
* FileReadWriteAct实现向文件存入数据以及从文件读取数据功能。
* PrefSaveRestoreAct实现采用SharedPreference存储和读取数据的功能。
* DataBaseAct实现数据库基本的CRUD操作。
* RunTimePermissionAct测试一些运行时权限。
* ReadContactsAct通过ContentResolver获取手机通讯录信息。
* MyContentProvider创建了一个内容提供器，可供外界程序访问。
* NotificationAct实现了基本的Notification。
* ReceiveDataAct接收了从DataBaseAct传输过来的数据，测试了Serializable和Parcelable方式。
* AlarmAct实现超简易的闹钟功能，其实只是试验了下Alarm机制。
* CameraAlbumAct实现了拍照和从手机选取照片的功能。
* WebViewAct实现简易的WebView功能。
* NetWorkActivity实现了用HttpURLConnection和OKHttp发送请求，用Pull和SAX解析XML，以及用JSONObject和GSON解析json。

