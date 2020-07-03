### 为什么要引进LiveData
LiveData是一个可以被观察的数据持有类，它可以感知Activity、Fragment或Service等组件的生命周期。

* 它可以做到在组件处于激活状态的时候才会回调相应的方法，从而刷新相应的UI。
* 不用担心发生内存泄漏
* 当onConfigurationChanged()导致Actiivty重新创建的时候，不需要手动去处理数据的储存和恢复。它已经帮我们封装好了。
* 当Actiivty不是处于激活状态的时候，如果你想LiveData的setValue()之后立即回调Observer的onChange()方法，而不是等到Activity处于激活状态的时候才回调 Obsever的onChange()方法，你可以使用observeForever()方法，但是你必须在onDestroy()的时候removeObserver()。

回想一下，在你的项目中，是不是经常会碰到这样的问题，当网络请求结果回来的时候，你经常需要判断Activity或者Fragment是否已经Destroy，如果不是Destroy，才更新UI。而当你如果使用Livedata的话，因为它是在Activity处于onStart()或者onResume()的状态时，他才会进行相应的回调，因而可以很好得处理这个问题，不必写一大堆的activity.isDestroyed()。

如果一个Observer的生命周期处于STARTED或RESUMED状态，那么LiveData将认为这个Observer处于活跃状态。LiveData仅通知活跃的Observer去更新UI。非活跃状态的Observer，即使订阅了LiveData，也不会收到更新的通知。

**Note：** 您可以使用observeForever(Observer)方法注册一个没有关联LifecycleOwner对象的Observer。在这种情况下，Observer被认为始终处于活动状态，因此当有数据变化时总是会被通知。您可以调用removeObserver(Observer)方法移除这些Observer。

当你更新LiveData对象中存储的数据时，所有注册了的Observer，只要所绑定的LifecycleOwner处于活动状态，就会被触发通知。LiveData允许UI控制器Observer订阅更新。当LiveData对象所保存的数据发生变化时，UI会在响应中自动更新。

**Note：** 确保在ViewModel而不是Activity或Fragment中保存用来更新UI的LiveData对象，原因如下：
* 避免臃肿的Activity和Fragment。这些UI控制器负责显示数据而不是保存数据状态。
* 将LiveData实例与特定Activity或Fragment实例分离，这将使得LiveData对象在配置更改后仍然存活。

***

### 观察LiveData对象
在大多数情况下，出于以下原因，应用程序组件的onCreate()方法是开始观察LiveData对象的最佳位置：

确保系统不会从Activity或Fragment的onResume()方法中进行多余的调用。

确保Activity或Fragment一旦变为活动状态时，就有可展示的数据。 当应用程序组件处于STARTED状态，它就需从它所观察的LiveData对象中接收到最新的值。 所以我们需要在一开始就设置好观察。

通常情况下，LiveData只在数据有变化时，给活跃的Observer进行通知。此行为的一个例外是，Observer在从非活跃状态变为活跃状态时也会收到通知。并且，如果Observer第二次从非活跃状态变为活跃状态，则只有在自上一次变为活跃状态以来该数据发生变化时才会接收到更新。

***

### 更新LiveData对象
MutableLiveData类暴露公用的setValue(T)和postValue(T)方法，如果需要编辑存储在LiveData对象中的值，必须使用这两个方法。

通常在ViewModel中使用MutableLiveData，然后ViewModel仅向Observer公开不可变的LiveData对象。

**Note：** 必须要从主线程调用setValue(T)方法来更新LiveData对象。如果代码在工作线程中执行，你可以使用postValue(T)方法来更新LiveData对象。

***

### LiveData几个比较重要的方法介绍

* **setValue(T value)**

必须在UI Thread中调用，更新数据，同时当Activity处于onStart或onResume状态时会调用Observer的onChanged回调。

* **postValue(T value)**

跟setValue()一样，唯一的区别就是可以非UI Thread中调用，然后通过Handler post在UI Thread中回调。

* **observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer)**

这是一个非常常用的方法, 用于添加一个Observer，监听数据的变化。LifecycleOwner一般是Activity或Fragment对象，使其LiveData可以感知其生命周期，只有在onStart或onResume状态时会调用Observer的onChanged回调, 在onDestroy时removeObserver。

* **observeForever(@NonNull Observer<? super T> observer)**
跟observe()方法差不多，唯一的区别是没办法感知Activity或Fragment生命周期。即只要有数据更新就立马调用Observer的onChanged回调，并且需要手动remove Observer。这个方法对于Transformation（数据转换）非常有用。

* **removeObservers(@NonNull final Observer<? super T> observer)**

这个方法很简单，就是删除某个observer。

* **onActive()**

这是LiveData的一个回调方法，当observer的数量由0变成1的时候，会调用。

* **onInactive()**

这是LiveData的一个回调方法，当observer的数量由1变成0的时候，会调用。

***

### 注意点

* 如果Lifecycle对象不处于活动状态，则即使值发生更改，也不会调用Observer。

* Lifecycle对象被销毁后，Observer被自动删除。

* LiveData对象具有感知生命周期的能力意味着您可以在多个Activity、Fragment和service之间共享它们。为了保持简洁，你可以使用单例模式实现LiveData。

* 带初始值的MutableLiveData，一旦被observe，立即会把已有的数据发送给Observer。不带初始值的MutableLiveData则不会这样。这也就验证了这里提到的：If LiveData already has data set, it will be delivered to the observer.

* Adds the given observer to the observers list within the lifespan of the given owner. The events are dispatched on the main thread. 

* If LiveData already has data set, it will be delivered to the observer. The observer will only receive events if the owner is in Lifecycle.State.STARTED or Lifecycle.State.RESUMED state (active).

* If the owner moves to the Lifecycle.State.DESTROYED state, the observer will automatically be removed.

* When data changes while the owner is not active, it will not receive any updates. If it becomes active again, it will receive the last available data automatically.

* LiveData keeps a strong reference to the observer and the owner as long as the given LifecycleOwner is not destroyed. When it is destroyed, LiveData removes references to the observer & the owner.

* If the given owner is already in Lifecycle.State.DESTROYED state, LiveData ignores the call.

* If the given owner, observer tuple is already in the list, the call is ignored.

* If the observer is already in the list with another owner, LiveData throws an IllegalArgumentException.