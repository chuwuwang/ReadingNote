ViewPager2是Google在androidx组件包里增加的一个组件，目前已经发布了1.0.0版本。

谷歌为什么要出这个组件呢？官方是这么说的：

> ViewPager2 replaces ViewPager, addressing most of its predecessor’s pain-points, including right-to-left layout support, vertical orientation, modifiable Fragment collections, etc.

```
implementation 'androidx.viewpager2:viewpager2:1.0.0'
```

### 新功能

* 支持RTL布局。
* 支持竖向滚动。
* 支持关闭预加载offscreenPageLimit。
* 完整支持notifyDataSetChanged（还有局部刷新）。
* 方便启用和禁用用户的滑动 (setUserInputEnabled)。
* 引入了MarginPageTransformer，以提供在页面之间增加空隙。
* 引入CompositePageTransformer来组合多个PageTransformer。
* 因为ViewPager2由Recyclerview支持，所以也支持ItemDecorator、DiffUtil等。
* 完美解决 notifyDataSetChanged（已修复VP1的bug）

***

### API的变动

* FragmentStateAdapter替换了原来的FragmentStatePagerAdapter。
* RecyclerView.Adapter替换了原来的PagerAdapter。
* registerOnPageChangeCallback替换了原来的addPageChangeListener。
* ViewPager2被声明成了final，我们将无法继承ViewPager2。
* 移除了setPargeMargin方法。

**注意：** 不要忘记unregisterOnPageChangeCallback。

***

### 常用API

* void setOrientation（int orientation）设置布局方向
* void setUserInputEnabled（boolean enabled）设置是否允许用户输入/触摸
* int getCurrentItem（）获取当前Item下标
* void setCurrentItem（int item）设置当前Item下标
* setOffscreenPageLimit(int limit) 设置屏幕外加载页面数量
* setPageTransformer(ViewPager2.PageTransformer transformer) 设置页面滑动时的变换效果
* registerOnPageChangeCallback(OnPageChangeCallback) 注册页面改变回调
* unregisterOnPageChangeCallback(ViewPager2.OnPageChangeCallback callback) 解注册页面改变回调

***

### 分析

ViewPager2也是直接继承了ViewGroup，所以并不只是扩展ViewPager，它俩实现方式不一样。

```
private RecyclerView mRecyclerView;
private LinearLayoutManager mLayoutManager;
new PagerSnapHelper().attachToRecyclerView(mRecyclerView)
```

很明显：内部核心是：RecyclerView 、LinearLayoutManager和PagerSnapHelper()，其中PagerSnapHelper的作用是限制ViewPager2一次只能滚动一页，这样就和Viewpager一样的交互方式了。

ViewPager2是基于RecyclerView构建，新增了以下几个属性：
* 可以进行垂直切换页面，只需要设置属性：android:orientation="vertical"
* 从右到左的布局，只需要设置属性：android:layoutDirection="rtl"

***

## 页面滑动事件监听

使用public void registerOnPageChangeCallback (ViewPager2.OnPageChangeCallback callback)

注意ViewPager2.OnPageChangeCallback()有三个可实现方法，因为是一个抽象类所以设置监听事件只需要重写需要的方法即可。

* onPageScrollStateChanged(int state) // 滚动状态更改时调用。

* onPageScrolled(int position, float positionOffset, int positionOffsetPixels) // 当滚动当前页面时（作为程序启动的平滑滚动的一部分或由用户启动的触摸滚动的一部分），将调用此方法。

* onPageSelected(int position) // 当选择新页面时，将调用此方法。

```
private val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {

    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
    }

}
```

## ViewPager2的使用

### 修改gradle.properties

```
android.useAndroidX=true
android.enableJetifier=true
```

android.useAndroidX=true：表示当前项目启用AndroidX。

android.enableJetifier=true：表示将依赖包也迁移到AndroidX。如果取值为false，表示不迁移依赖包到AndroidX，但在使用依赖包中的内容时可能会出现问题。当然了，如果你的项目中没有使用任何三方依赖，那么，此项可以设置为false。

### ViewPager2和Fragment使用

ViewPager2和Fragment结合使用，需要使用FragmentStateAdapter。FragmentStateAdapter继承RecyclerView.Adapter。

```
private val fragmentStateAdapter = object : FragmentStateAdapter(this) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HttpLogsOverviewFragment.create(entry)
            1 -> HttpLogsRequestFragment.create(entry)
            else -> HttpLogsResponseFragment.create(entry)
        }
    }

}
```

### ViewPager2和TabLayout使用

在androidx中，TabLayout没有setupWithViewPager（ViewPager2 viewPager2）方法，而是用TabLayoutMediator将TabLayout和ViewPager2结合。

```
private val tabLayoutMediator by lazy {
    TabLayoutMediator(tab_layout, view_pager2) { tab, position ->
        when (position) {
            0 -> tab.text = "OVERVIEW"
            1 -> tab.text = "REQUEST"
            else -> tab.text = "RESPONSE"
        }
    }
}

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.http_activity_logs_detail)
    initView()
}

private fun initView() {
    http_detail_title.text = entry.requestHost

    view_pager2.adapter = fragmentStateAdapter
    view_pager2.registerOnPageChangeCallback(onPageChangeCallback)

    tabLayoutMediator.attach()
}

override fun onDestroy() {
    super.onDestroy()
    view_pager2.unregisterOnPageChangeCallback(onPageChangeCallback)
    tabLayoutMediator.detach()
}
```

***

> https://www.jianshu.com/p/246e26a4d741

> https://mp.weixin.qq.com/s/rVYIlBX6ZM8ZjZasMejCSQ