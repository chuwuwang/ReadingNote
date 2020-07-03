Paging是Google 2018 IO大会最新发布的Jetpack中的一个组件，主要用于大数据的分页加载。

```
dependencies {
    def paging_version = "2.1.2"

    implementation "androidx.paging:paging-runtime:$paging_version" // For Kotlin use paging-runtime-ktx

    // alternatively - without Android dependencies for testing
    testImplementation "androidx.paging:paging-common:$paging_version" // For Kotlin use paging-common-ktx

    // optional - RxJava support
    implementation "androidx.paging:paging-rxjava2:$paging_version" // For Kotlin use paging-rxjava2-ktx
}
```

## Paging介绍

以前，加载并展示大量数据就已成为各家应用中必不可少的业务场景，分页加载也就成了必不可少的方案。在现有的Android API中也已存在支持分页加载内容的方案， 比如：

* CursorAdapter：它简化了数据库中数据到ListView中Item的映射，仅查询需要展示的数据，但是查询的过程是在UI线程中执行。

* SupportV7包中的AsyncListUtil支持基于position的数据集分页加载到RecyclerView中，但不支持不基于position的数据集，而且它强制一个有限数据集中的null项必须展示Placeholder。

针对现有方案所存在的一些问题，Google推出了Android架构组件中的Paging Library。

Paging主要由三个部分组成：DataSource，PageList，PageListAdapter。

### 特点

Paging主要是用来结合RecyclerView进行使用的。它的作用是能够逐渐地、优雅地加载所需要加载的数据。也就是一种分页方案。

Paging每次只会加载总数据的一小部分。因此它有下面的两个优点：

* 数据加载要求更小的带宽以及更少的系统资源。

* 在资源发生改变的情况下，app依然能够很快的做出响应。

### 使用方式

* LiveData< PagedList< DataBean > > 用LivePagedListBuilder生成。

* LivePagedListBuilder 用 DataSource.Factory<Integer, DataBean> 和 PagedList.Config.Builder 生成。

* DataSource.Factory<Integer, DataBean> 用 PositionalDataSource<DataBean> 生成。

* PositionalDataSource是DataSource的子类，同时还有PageKeyedDataSource和ItemKeyedDataSource。

***

## DataSource

DataSource<Key, Value>从字面意思理解是一个数据源，其中key对应加载数据的条件信息，Value对应加载数据的实体类。

DataSource是一个抽象类，我们不能直接继承它实现它的子类。但是Paging库里提供了它的三个子类供我们继承用于不同场景的实现：

* PageKeyedDataSource<Key, Value>：适用于目标数据根据页信息请求数据的场景，即Key字段是页相关的信息。比如请求的数据的参数中包含类似next/previous页数的信息。

* ItemKeyedDataSource<Key, Value>：适用于目标数据的加载依赖特定item的信息，即Key字段包含的是Item中的信息。比如需要根据第N项的信息加载第N+1项的数据，传参中需要传入第N项的ID时，该场景多出现于论坛类应用评论信息的请求。

* PositionalDataSource<T>：适用于目标数据总数固定，通过特定的位置加载数据，这里Key是Integer类型的位置信息，T即Value。 比如从数据库中的1200条开始加在20条数据。

以上三种Datasource都是抽象类，使用时需实现请求数据的方法。三种Datasource都需要实现loadInitial()方法，各自都封装了请求初始化数据的参数类型LoadInitialParams。

不同的是分页加载数据的方法，PageKeyedDataSource和ItemKeyedDataSource比较相似，需要实现loadBefore()和loadAfter()方法，同样对请求参数做了封装，即LoadParams< Key >。PositionalDataSource需要实现loadRange()，参数的封装类为LoadRangeParams。

如果项目中使用Android架构组件中的Room，Room可以创建一个产出PositionalDataSource的DataSource.Factory：

```
@Query("select * from users WHERE age > : age order by name DESC, id ASC")
DataSource.Factory<Integer, User> usersOlderThan(int age);
```

总的来说，DataSource就像是一个抽水泵，而不是真正的水源，它负责从数据源加载数据，可以看成是Paging与数据源之间的接口。

***

## PageList

如果将DataSource比作抽水泵，那PagedList就像是一个蓄水池，但不仅仅如此。PageList是一个List的子类，支持所有List的操作，除此之外它主要有五个成员：

* Executor mMainThreadExecutor：一个主线程的Excutor，用于将结果post到主线程。

* Executor mBackgroundThreadExecutor：后台线程的Excutor。

* BoundaryCallback< T > mBoundaryCallback：加载Datasource中的数据加载到边界时的回调。

* Config mConfig：配置PagedList从Datasource加载数据的方式，其中包含以下属性：

    * pageSize：设置每页加载的数量。

    * prefetchDistance：预加载的数量，默认为pagesize。

    * initialLoadSizeHint：初始化数据时加载的数量，默认为pageSize*3。

    * enablePlaceholders：当item为null是否使用PlaceHolder展示。

* PagedStorage< T > mStorage：用于存储加载到的数据，它是真正的蓄水池所在，它包含一个ArrayList< List< T > >对象mPages，按页存储数据。

PagedList会从DataSource中加载数据，更准确的说是通过DataSource加载数据，通过Config的配置，可以设置一次加载的数量以及预加载的数量。 除此之外，PagedList还可以向RecyclerView.Adapter发送更新的信号，驱动UI的刷新。

***

## PagedListAdapter

PagedListAdapte是RecyclerView.Adapter的实现，用于展示PagedList的数据。

它本身实现的更多是Adapter的功能，但是它有一个小伙伴PagedListAdapterHelper< T >， PagedListAdapterHelper会负责监听PagedList的更新，Item数量的统计等功能。这样当PagedList中新一页的数据加载完成时，PagedAdapte就会发出加载完成的信号，通知RecyclerView刷新，这样就省略了每次loading后手动调一次notifyDataChanged()。

除此之外，当数据源变动产生新的PagedList，PagedAdapter会在后台线程中比较前后两个PagedList的差异，然后调用notifyItem…()方法更新RecyclerView。

这一过程依赖它的另一个小伙伴ListAdapterConfig，ListAdapterConfig负责主线程和后台线程的调度以及DiffCallback的管理，DiffCallback的接口实现中定义比较的规则，比较的工作则是由PagedStorageDiffHelper来完成。

### 不同点

大致的4个不同如下：

1. Adapter不再继承自RecyclerView.Adapter，改为继承自PagedListAdapter，因为PagedListAdapter就是RecyclerView.Adapter的一个子类。

2. 定义内部回调接口ItemCallbackImp继承自DiffUtil.ItemCallback< VideoInfo >，并且实例化一个父类引用指向子类ItemCallbackImp对象。


3. 重写构造方法，无需参数传入，调用父类构造方法将mDiffCallback传入。

4. 通onBindViewHolder中过调用getItem(position)，获得指定位置的数据对象。因为Adapter中不再需要维护一个数据List了，PagedListAdapter中已经维护有，并且提供getItem()方法访问。

### 在Activity中的使用

在使用Paging后，我们无需向Adapter中在传入数据源List，我们需要构造LiveData。

LiveData需要DataSource.Factory对象和PagedList.Config对象，只是实例化DataSource.Factory对象需要额外两个步骤。

DataSource.Factory是一个抽象类，实例化时需要实现create()函数，这个函数返回值是一个DataSource类对象。

DataSource是一个抽象类，他有三个实现子类。

所以我们捋一下思路。

1. 首先要定义一个MyDataSource继承自DataSource的三个子类之一，

2. 再定义一个MyDataSourceFactory继承自DataSource.Factory，返回值是MyDataSource。

3. 然后实例化PagedList.Config，这个类提供有Builder()，比较简单。

4. 最后将MyDataSourceFactory对象和PagedList.Config对象传入new LivePagedListBuilder()中得到LiveData数据源。将LiveData数据源和Adapter绑定是通过观察者模式实现，调用LiveData.observe()。

***

## 加载数据

使用Paging加载数据主要有两种方式，一种是单一数据源的加载（本地数据或网络数据），另一种是多个数据源的加载（本地数据+网络数据）。

### 加载单一数据源的数据

首先我们可以通过LivePagedListBuilder来创建LiveData<PagedList>为UI层提供数据。

如果数据源是DB，当数据发生变化，DB会推送（Push）一个新的PagedList（这里会依赖LiveData的机制）。如果是网络数据，即客户端无法知道数据源的变化，可以通过诸如滑动刷新的方式将调用DataSource的invalidate()方法来拉取（Pull）新的数据。

### 加载多个数据源的数据

![](https://github.com/chuwuwang/Resources/blob/master/android/paging-library-data-flow.png)

这种场景一般是先加载本地数据，加载完成后再加载网络数据，比较适合需要本地做缓存的业务。比如IM中的聊天消息，当打开聊天界面时先加载本地数据库中的聊天消息，加载完了再加载网络的离线消息。

这种场景需要为PagedList设置BoundaryCallback来监听加载完本地数据的事件，触发加载网络数据，然后入库，此时LiveData< PagedList >会推送一个新的PagedList, 并触发界面刷新。

具体使用案例可以参考Google Sample的PagingWithNetworkSample项目。

***

## 小结

Paging作为Android架构组件库的一员，其特点主要还是在其架构思想上。Paging将分页的业务封装为一条完整的流水线，一个Pattern。其中各个组件之间存在联动的关系：

* 当PagedList创建时会立即从DataSource加载数据，触发loadInitial(), DataSource加载到数据后会更新PagedList，PagedList更新会通知到PagedAdapter并刷新UI。

* UI上的展示会触发PagedAdapter的getItem()，随即触发PagedList的loadAround()方法从DataSource加载周围的数据...

整个过程Paging内部实现了线程的切换，数据的预加载，所有联动的关系都内聚到Paging中，这样使用时只需要关心加载数据的具体实现，并且在用户体验上，将会大大减少等待数据加载的时间和次数。

***

> https://www.jianshu.com/p/ff5c711bb7a1

> https://www.loongwind.com/archives/367.html

> https://www.dazhuanlan.com/2019/10/02/5d93c178580b2/

> https://www.jianshu.com/p/995e82580dbf

> https://mp.weixin.qq.com/s?__biz=MzIwMTAzMTMxMg==&mid=2649492903&idx=1&sn=6040b030d2a8125f38b7c9e7bd8f3054&chksm=8eec8658b99b0f4e07cf1c550096b87c5551a6da124906a67911dcae4a0656814a6e5cc13c84#rd