![](https://github.com/chuwuwang/Resources/blob/master/android/1589470230.png)

MVVM与MVP相比最大的区别就是用ViewModel代替了原来的P层。一句话概括它的特点就是对数据状态的持有和维护。换言之，它将原来P层关于数据的逻辑运算与处理统一放到了ViewModel中，而剩余的V层的操作建议使用DataBinding，从而形成最为简洁高效的MVVM架构。

回到ViewModel的特点 - 对数据状态的持有和维护。为什么需要做这些呢？事实上就是为了解决下面两个开发中常见的问题。

* Activity配置更改重建时(比如屏幕旋转)保留数据。

* UI组件(Activity与Fragment、Fragment与Fragment)间实现数据共享。

对于第一条不用ViewModel的情况下只能通过onSaveInstanceState()保存数据，当Activity重建后再通过onCreate()或onRestoreInstanceState()方法的bundle中取出，但如果数据量较大，数据的序列化和反序列化将产生一定的性能开销。

对于第二条如果不用ViewModel，各个UI组件都要持有共享数据的引用，这会带来两个麻烦。第一，如果新增了共享数据，各个UI组件需要再次声明并初始化新增的共享数据。第二，某个UI组件对共享数据修改，无法直接通知其他UI组件，需手动实现观察者模式。而ViewModel结合LiveData就可以很轻松的实现这一点。

LiveData作为数据变化的驱动器，ViewModel借助它可以写出十分简洁的MVVM代码。

ViewModelStore是存储ViewModel的数据单元，存储结构为Map，Fragment/FragmentActivity持有其引用。

ViewModelProvider通过get方法创建一个ViewModel，创建之前会先检查ViewModelStore中是否存在，若不存在则通过反射创建一个VM。

***

## 责任分配

理想情况下，ViewModel应该对Android世界一无所知。这提升了可测试性，内存泄漏安全性，并且便于模块化。通常的做法是保证你的ViewModel中没有导入任何（android.* ，android.arch.*，androidx.lifecycle）包除外。这对 Presenter(MVP) 来说也一样。

**❌ 不要让ViewModel和Presenter接触到Android框架中的类**

条件语句，循环和通用逻辑应该放在应用的ViewModel或者其它层来执行，而不是在Activity和Fragment 中。View通常是不进行单元测试的，除非你使用了Robolectric，所以其中的代码越少越好。View只需要知道如何展示数据以及向ViewModel/Presenter发送用户事件。这叫做Passive View模式。

**✅ 让 Activity/Fragment 中的逻辑尽量精简**

***

## ViewModel中的View引用

![](https://github.com/chuwuwang/Resources/blob/master/android/20200512223324.png)

ViewModel和Activity/Fragment具有不同的作用域。当ViewModel进入alive状态且在运行时，Activity可能位于生命周期状态的任何状态。Activitie和Fragment可以在ViewModel无感知的情况下被销毁和重新创建。

向ViewModel传递 View(Activity/Fragment) 的引用是一个很大的冒险。假设ViewModel请求网络，稍后返回数据。若此时View的引用已经被销毁，或者已经成为一个不可见的Activity。这将导致内存泄漏，甚至crash。

**❌ 避免在ViewModel中持有View的引用**

***

## 观察者模式

在ViewModel和View中通信的建议方式是观察者模式，使用LiveData或者其他类库中的可观察对象。

在Android中设计表示层的一种非常方便的方法是让View观察和订阅ViewModel（中的变化）。由于ViewModel并不知道Android的任何东西，所以它也不知道Android是如何频繁的杀死View的。
这有如下好处：

* ViewModel在配置变化时保持不变，所以当设备旋转时不需要再重新请求资源（数据库或者网络）。

* 当耗时任务执行结束，ViewModel中的可观察数据更新了。这个数据是否被观察并不重要，尝试更新一个不存在的View并不会导致空指针异常。

* ViewModel不持有View的引用，降低了内存泄漏的风险。

**✅ 让UI观察数据的变化，而不是把数据推送给UI**

***

## 保存Activity状态

当Activity被销毁或者进程被杀导致Activity不可见时，重新创建屏幕所需要的信息被称为Activity状态。屏幕旋转就是最明显的例子，如果状态保存在ViewModel中，它就是安全的。

但是，你可能需要在ViewModel也不存在的情况下恢复状态，例如当操作系统由于资源紧张杀掉你的进程时。

为了有效的保存和恢复UI状态，使用 onSaveInstanceState()和ViewModel组合。

详见：ViewModels: Persistence, onSaveInstanceState(), Restoring UI State and Loaders。

***

## ViewModel的泄露

得益于方便的连接UI层和应用的其他层，响应式编程在Android中工作的很高效。LiveData是这个模式的关键组件，你的Activity和Fragment都会观察LiveData实例。

LiveData如何与其他组件通信取决于你，要注意内存泄露和边界情况。如下图所示，视图层（Presentation Layer）使用观察者模式，数据层（Data Layer）使用回调。

![](https://github.com/chuwuwang/Resources/blob/master/android/1589469695.png)

当用户退出应用时，View不可见了，所以ViewModel不需要再被观察。如果数据仓库Repository是单例模式并且和应用同作用域，那么直到应用进程被杀死，数据仓库Repository才会被销毁。 只有当系统资源不足或者用户手动杀掉应用这才会发生。如果数据仓库Repository持有ViewModel的回调的引用，那么ViewModel将会发生内存泄露。

![](https://github.com/chuwuwang/Resources/blob/master/android/1589469768.png)

如果ViewModel很轻量，或者保证操作很快就会结束，这种泄露也不是什么大问题。但是，事实并不总是这样。理想情况下，只要没有被View观察了，ViewModel就应该被释放。

![](https://github.com/chuwuwang/Resources/blob/master/android/1589469792.png)

你可以选择下面几种方式来达成目的：

* 通过ViewModel.onCLeared()通知数据仓库释放ViewModel的回调。

* 在数据仓库Repository中使用弱引用 ，或者 EventBus（两者都容易被误用，甚至被认为是有害的）。

* 通过在View和ViewModel中使用LiveData的方式，在数据仓库和ViewModel之间进程通信。


**✅ 考虑边界情况，内存泄露和耗时任务会如何影响架构中的实例。**

**❌ 不要在ViewModel中进行保存状态或者数据相关的核心逻辑。ViewModel中的每一次调用都可能是最后一次操作。**

***

## 数据仓库中的LiveData

为了避免ViewModel泄露和回调地狱，数据仓库应该被这样观察：

![](https://github.com/chuwuwang/Resources/blob/master/android/1589470249.png)

当ViewModel被清除，或者 View 的生命周期结束，订阅也会被清除：

![](https://github.com/chuwuwang/Resources/blob/master/android/1589470262.png)

如果你尝试这种方式的话会遇到一个问题：如果不访问LifeCycleOwner对象的话，如何通过ViewModel订阅数据仓库？使用Transformations可以很方便的解决这个问题。Transformations.switchMap可以让你根据一个LiveData实例的变化创建新的LiveData。它还允许你通过调用链传递观察者的生命周期信息：

```
LiveData<Repo> repo = Transformations.switchMap(repoIdLiveData, repoId -> {
        return repository.loadRepo(repoId);
    }
);
```

在这个例子中，当触发更新时，这个函数被调用并且结果被分发到下游。如果一个Activity观察了repo，那么同样的LifecycleOwner将被应用在repository.loadRepo(repoId) 的调用上。

**无论什么时候你在ViewModel内部需要一个Lifecycle对象时，Transformation都是一个好方案。**

***

## 思考

首先我们要先了解一下ViewMode的结构：

* ViewModel：抽象类，主要有clear()方法，它是final级，不可修改，clear()方法中包含onClear()钩子，开发者可重写onClear()方法来自定义数据的清空。

* ViewModelStore：内部维护一个HashMap以管理ViewModel。

* ViewModelStoreOwner：接口，ViewModelStore的作用域，实现类为ComponentActivity和Fragment，此外还有FragmentActivity.HostCallbacks。

* ViewModelProvider：用于创建 ViewModel，其构造方法有两个参数，第一个参数传入ViewModelStoreOwner，确定了ViewModelStore的作用域，第二个参数为ViewModelProvider.Factory，用于初始化ViewModel对象，默认为getDefaultViewModelProviderFactory()方法获取的factory。

* 问题1：如何做到Activity重建后ViewModel仍然存在？

* 问题2：如何做到Fragment重建后ViewModel仍然存在？

* 问题3：如何控制作用域？（即保证相同作用域获取的ViewModel实例相同）

我们知道ViewModelStoreOwner代表着作用域，其内部唯一的方法返回ViewModelStore对象，也即不同的作用域对应不同的ViewModelStore ，而ViewModelStore内部维护着ViewModel的 HashMap，因此只要保证相同作用域的ViewModelStore对象相同就能保证相同作用域获取到相同的ViewModel对象，而问题1我们已经解释了重建时如何保证ViewModelStore对象不变。

* 问题4：如何避免内存泄漏？

由于ViewModel的设计，使得Activity/Fragment依赖它，而ViewModel不依赖Activity/Fragment。因此只要不让ViewModel持有Context或View的引用，就不会造成内存泄漏。

***

> https://juejin.im/post/5d5a3cd9f265da03c23ed40a

> https://juejin.im/post/5dadd1c451882504006322d5

> https://juejin.im/post/5e786d415188255e00661a4e