## 依赖项

```
dependencies {
    def room_version = "2.2.5"

    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version" // For Kotlin use kapt instead of annotationProcessor

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation "androidx.room:room-ktx:$room_version"

    // optional - RxJava support for Room
    implementation "androidx.room:room-rxjava2:$room_version"

    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation "androidx.room:room-guava:$room_version"

    // Test helpers
    testImplementation "androidx.room:room-testing:$room_version"
}
```

Room 具有以下注释处理器选项：

* room.schemaLocation：配置并启用将数据库架构导出到给定目录中的JSON文件的功能。

* room.incremental：启用Gradle增量注释处理器。

* room.expandProjection：配置Room以重新编写查询，使其顶部星形投影在展开后仅包含DAO方法返回类型中定义的列。

```
android {
    ...
    defaultConfig {
        ...
        javaCompileOptions {
            annotationProcessorOptions {
                arguments = [
                    "room.schemaLocation":"$projectDir/schemas".toString(),
                    "room.incremental":"true",
                    "room.expandProjection":"true"]
            }
        }
    }
}
```

***

## 介绍

Room持久性库在SQLite的基础上提供了一个抽象层，允许流利的数据库访问，同时利用的SQLite的全部功能。

**Room主要有以下三个部分组成：**

1. Database：标有 @Database注解的类需要具备以下特征：

    * 继承RoomDatabase的抽象类

    * 在注释中包括与数据库关联的实体列表（ @Database(entities = { } ) ）

    * 包含一个无参的抽象方法并返回一个使用@Dao注解的类

2. Entity：对应数据库中的表

3. DAO：包含访问数据库的方法

### @Database 注解定义一个数据库

* 定义一个抽象类继承RoomDatabase。

* 使用@Database注解这个抽象类，同时使用entities属性配置表，version配置版本号。

* 定义一系列的Dao层的抽象方法。

### @Dao 注解定义一个数据库

* 定义一个接口或抽象类，并使用@Dao注解这个类。

* 定义各种操作表的抽象方法，并使用@Query等注解对应的抽象方法。

以上各部分的依赖关系如下图所示：

![](https://github.com/chuwuwang/Resources/blob/master/android/1591436646.png)

***

## Entity定义数据

默认情况下，Room为实体中定义的每个字段创建一个列。如果实体有不想持久的字段，则可以使用@Ignore来注解它们。必须通过Database类中的entities数组引用实体类。

每个实体必须定义至少1个字段作为主键。即使只有1个字段，仍然需要用@PrimaryKey注解字段。

此外，如果您想Room自动分配IDs给实体，则可以设置@PrimaryKey的autoGenerate属性。如果实体具有复合主键，则可以使用@Entity注解的primaryKeys属性。如下面的代码片段所示：

```
@Entity( primaryKeys = {"firstName", "lastName"} )
public class User {
    public String firstName;
    public String lastName;
}
```

默认情况下，Room使用类名作为数据库表名。如果希望表具有不同的名称，请设置@Entity注解的tableName属性。SQLite中的表名不区分大小写。

与tableName属性类似，Room使用字段名称作为数据库中的列名。如果希望列具有不同的名称，请将@ColumnInfo注解添加到字段中，如下面的代码片段所示：

```
@Entity(tableName = "users")
public class User {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;
}
```

**注意：** Entity可以有一个空的构造函数（如果DAO类可以访问每个持久化字段），或者一个构造函数其参数包含与实体类中的字段匹配的类型和名字。Room还可以使用全部或部分构造函数，比如只接收部分字段的构造函数。

### 索引和唯一约束

根据访问数据的方式，你可能希望对数据库中的某些字段进行索引，以加快查询速度。要向实体添加索引，请在@Entity注解中包含indices属性，列出要包含在索引或组合索引中的列的名字。

有时，数据库中的某些字段或字段组合必须是唯一的。你可以通过设置@Index注解的unique属性为true来强制满足唯一属性。下面代码样例阻止表含有对于firstName和lastName列包含同样的值的两条记录：

```
@Entity( indices = { @Index(value = {"first_name", "last_name"}, unique = true) } )
class User {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @Ignore
    Bitmap picture;
}
```

### 嵌套对象

有时，你希望将一个实体或POJO表达作为数据库逻辑中的一个整体，即使对象包含了多个字段。在这种情况下，你可以使用@Embeded注解来表示要在表中分为为子字段的对象。然后，你可以像其他单独的列一样查询嵌入的字段。

例如，我们的User类可以包含一个类型为Address的字段，其表示了一个字段组合，包含street、city、state和postCode。为了将这些组合列单独的存放到表中，将Address字段加上@Embedde注解，如下代码片段所示：

```
class Address {
    public String street;
    public String state;
    public String city;

    @ColumnInfo(name = "post_code")
    public int postCode;
}

@Entity
class User {
    @PrimaryKey
    public int id;

    public String firstName;

    @Embedded
    public Address address;
}
```

这张表示User对象的表将包含以下名字的列：id，firstName，street，state，city和post_code。

**注意：** 嵌入字段也可以包含其他嵌入字段。

如果实体包含了多个同一类型的嵌入字段，你可以通过设置prefix属性来保持每列的唯一性。Room然后将提供的值添加到嵌入对象的每个列名的开头。

当一个类中嵌套多个类，并且这些类具有相同的字段，则需要调用@Embedded的属性prefix 添加一个前缀，生成的列名为前缀+列名。

```
public class User {
  @PrimaryKey(autoGenerate = true) public int id;
  public String firstName;
  public String lastName;
  @Embedded(prefix = "first")
  Address address;
  @Embedded(prefix = "second")
  AddressTwo addressTwo;
}
```

该例中将会创建firstStreet，firstState...secondStreet，secondState...等列。

***

## 数据访问对象DAO

Room的主要组件是Dao类。DAO以简洁的方式抽象了对于数据库的访问。

DAO既可以是接口，也可以是抽象类。如果是抽象类，它可以有一个构造函数，它把RoomDatabase作为唯一的参数。Room在编译时创建每个DAO实现。

**注意：** Room不允许在主线程中访问数据库，除非你可以builder上调用allowMainThreadQueries()，因为它可能会长时间锁住UI。异步查询（返回LiveData或RxJava Flowable的查询）则不受此影响，因为它们在有需要时异步运行在后台线程上。

### Insert

当您创建一个DAO方法并用@Insert注解时，Room生成一个实现，在一个事务中将所有参数插入到数据库中。

```
@Dao
public interface MyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUsers(User... users);

    @Insert
    public void insertBothUsers(User user1, User user2);

    @Insert
    public void insertUsersAndFriends(User user, List<User> friends);
}
```

如果@Insert方法只接收1个参数，则可以返回一个Long型的值，这是插入项的新rowId。如果参数是数组或集合，则应该返回long[]或者List类型的值。有时插入数据和更新数据会产生冲突，所以就有了冲突之后要怎么解决，SQLite对于事务冲突定义了5个方案。

**OnConflictStrategy**

* REPLACE：见名知意，替换，违反的记录被删除，以新记录代替之

* IGNORE：违反的记录保持原貌，其它记录继续执行

* FAIL：终止命令，违反之前执行的操作得到保存

* ABORT：终止命令，恢复违反之前执行的修改

* ROLLBACK：终止命令和事务，回滚整个事务

### Update

@Update注解在数据库中用于修改一组实体的字段。它使用每个实体的主键来匹配查询。

```
@Dao
public interface MyDao {
    @Update
    public void updateUsers(User... users);
}
```

通常不是必须的，但你可以让此方法返回一个int值，指示数据库中更新的行数。

### Delete

用于从数据库中删除给定参数的一系列实体，它使用主键匹配数据库中相应的行。

```
@Dao
public interface MyDao {
    @Delete
    public void deleteUsers(User... users);
}
```

### Query

@Query是DAO类中使用的主要注解。它允许您在数据库上执行读/写操作。每个@Query方法在编译时被验证，因此，如果存在查询问题，则会发生编译错误而不是运行时故障。

Room还验证查询的返回值，这样如果返回对象中字段的名称与查询响应中的相应列名不匹配，则Room将以以下两种方式之一提醒您：

* 如果只有一些字段名匹配，则发出警告。

* 如果没有字段名匹配，则会出错。

```
@Dao
public interface MyDao {
    @Query("SELECT * FROM user")
    public User[] loadAllUsers();

    @Query("SELECT * FROM user WHERE age > :minAge")
    public User[] loadAllUsersOlderThan(int minAge);

    @Query("SELECT first_name, last_name FROM user WHERE region IN (:regions)")
    public LiveData<List<User>> loadUsersFromRegionsSync(List<String> regions);

    @Query("SELECT * FROM user WHERE age > :minAge LIMIT 5")
    public Cursor loadRawUsersOlderThan(int minAge);
}
```

**Observable查询**

当执行查询时，您经常希望应用程序的UI在数据更改时自动更新。要实现这一点，请在查询方法描述中使用类型LiveData的返回值。当数据库被更新时，Room生成所有必要的代码来更新LiveData。

```
@Dao
public interface MyDao {
    @Query("SELECT first_name, last_name FROM user WHERE region IN (:regions)")
    public LiveData<List<User>> loadUsersFromRegionsSync(List<String> regions);
}
```

**RXJava的响应式查询**

Room还可以从您定义的查询中返回RXJava2 Publisher和Flowable对象。若要使用此功能，请将androidx.room:room-rxjava2库添加到gradle的依赖关系中。

```
@Dao
public interface MyDao {
    @Query("SELECT * from user where id = :id LIMIT 1")
    public Flowable<User> loadUserById(int id);

    // Emits the number of users added to the database.
    @Insert
    public Maybe<Integer> insertLargeNumberOfUsers(List<User> users);

    // Makes sure that the operation finishes successfully.
    @Insert
    public Completable insertLargeNumberOfUsers(User... users);

    /* Emits the number of users removed from the database. Always emits at
       least one user. */
    @Delete
    public Single<Integer> deleteUsers(List<User> users);
}
```

### 返回列的子集

Room允许你从查询中返回任意的java对象，只要结果列集能被映射到返回的对象。比如，你可以创建下面的POJO来拉取用户的first name和last name。

```
public class NameTuple {
    @ColumnInfo(name="first_name")
    public String firstName;

    @ColumnInfo(name="last_name")
    public String lastName;
}

@Dao
public interface MyDao {
    @Query("SELECT first_name, last_name FROM user")
    public List<NameTuple> loadFullName();
}
```

Room理解这个查询是要返回first_name和last_name列的值，并且这些值可以映射成NameTuple类的字段。因此，Room可以生成正确的代码。如果查询返回太多列，或者有列不存在NameTuple类，Room则显示一个警告。

**注意：**这些POJO也可以使用@Embedded注解。

***

## 类型转换器

TypeConverter，它将自定义类转换为Room可以保留的已知类型。例如，如果想要持久化实例Date，可以编写以下内容TypeConverter来在数据库中存储等效的Unix时间戳。

```
public class Converters {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

}

@Database(entities = {User.java}, version = 1)
@TypeConverters( { Converter.class } )
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}

@Entity
public class User {
    ...
    private Date birthday;
}
```

上面的例子中定义了两个函数，一个转换Date对象到Long对象，另一个执行逆变换，从Long到Date。由于Room是知道如何持久化Long对象的，因此它可以使用此转换器来持久保存Date类型的值。接下来，添加@TypeConverters注释到AppDatabase类，这样Room就可以在AppDatabase中的实体和Dao上使用上面的定义的类型转换器。还可以限制@TypeConverters到不同的范围，包括单个实体，DAO和DAO方法。

***

## 数据库迁移

当你添加和更改App功能时，你需要修改实体类来反映这些更改。当用户更新到你的应用最新版本时，你不想要他们丢失所有存在的数据，尤其是你无法从远端服务器恢复数据时。

Room允许你编写Migration类来保留用户数据。每个Migration类指明一个startVersion和endVersion。在运行时，Room运行每个Migration类的migrate()方法，使用正确的顺序来迁移数据库到最新版本。

**警告：** 如果你没有提供需要的迁移类，Room将会重建数据库，也就意味着你会丢掉数据库中的所有数据。

### 如果没有匹配到对应的升级Migration配置怎么办呢？

默认情况下，如果没有匹配到升级策略，则app直接crash。

为了防止crash，可添加fallbackToDestructiveMigration方法配置，直接删除所有的表，重新创建表。

### 数据库降级规则

同理如果没有匹配到降级规则，默认也会crash。

可以通过fallbackToDestructiveMigrationOnDowngrade方法配置删表重建，但不能指定version删表重建。

***

## Repository

### Repository是什么

Repository类用于访问多个数据源。Repository并不是架构组件库的一部分，而是代码解耦和架构比较推荐的方法。Repository类处理数据操作。它为应用程序提供了一个整洁的API。

### 为什么用Repository

Repository管理数据的查询线程，同时，可以使用多个后端。在常规情况下，Repository主要实现从服务端拉取数据还是从本地数据库拉取数据的逻辑。

### 创建ViewModel

ViewModel 主要扮演为UI提供数据的角色，同时，在配置更改后可以继续存在。ViewModel可以看做Repository和UI的通信中心。也可以使用ViewModel共享数据。ViewModel是lifecycle library的一部分。

ViewModel将UI数据和Activity和Fragment类进行分离，更符合单一职责原则：Activity和Fragment负责展示UI，而ViewModel负责持有并处理UI所需要的所有数据。

在ViewModel 中，使用LiveData更新UI的数据。因为LiveData有以下几个优点：

* 可以监听数据，只有当数据更改时才会更新UI。

* 通过ViewModel可以将Repository和UI完全隔离。在ViewModel中不会直接进行数据库调用，这使得代码更方便进行测试。

### 连接数据

ViewModel是UI与Repository的通信中心。即UI更新数据是通过ViewModel进行的。为了显示当前数据的内容，在ViewModel中添加一个观察者，用以监听LiveData的更改。

在MainActivity的onCreate()方法中创建ViewModel实例，并监听数据库数据的更新。

**注意：** 实例化AppDatabase对象时，应该遵循单例模式，因为每个RoomDatabase实例都相当昂贵，而且很少需要访问多个实例。

1. 当一个类由@Entity注解，并且由@Database注解的entities属性引用，Room将在数据库中为其创建一张数据库表。

2. Room不允许在主线程中访问数据库，除非在buid的时候使用allowMainThreadQueries()方法。
```
 Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user.db")
 .allowMainThreadQueries()
 .build();
```

***

> https://www.jianshu.com/p/0ed8b17a199e

> https://juejin.im/post/5d405e1ce51d45620611598c

> https://medium.com/jastzeonic/kotlin-room-%E4%BD%BF%E7%94%A8%E5%88%9D%E9%AB%94%E9%A9%97-f3af4f8ddc80