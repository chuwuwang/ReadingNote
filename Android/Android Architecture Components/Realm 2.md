## 写操作

请注意，写入事务之间会互相阻塞，如果一个写入事务正在进行，那么其他的线程的写入事务就会阻塞它们所在的线程。同时在UI线程和后台线程使用写入事务有可能导致ANR问题。可以使用异步事务（async transactions）以避免阻塞UI线程。

```
// Asynchronously update objects on a background thread
realm.executeTransactionAsync(new Realm.Transaction() {
    @Override
    public void execute(Realm bgRealm) {
        Dog dog = bgRealm.where(Dog.class).equals("age", 1).findFirst();
        dog.setAge(3);
    }
}, new Realm.Transaction.OnSuccess() {
    @Override
    public void onSuccess() {
    	// Original queries and Realm objects are automatically updated.
    	puppies.size(); // => 0 because there are no more puppies younger than 2 years old
    	managedDog.getAge();   // => 3 the dogs age is updated
    }
});
```

Realm数据文件不会因为程序崩溃而损坏。当有异常在事务块中被抛出时，当前事务中所做出的数据修改会被丢弃。如果在该情况下程序需要继续运行，那么请调用cancelTransaction()来中止事务，或者使用executeTransaction()来执行事务。

由但得益于Realm的MVCC架构，当正在进行一个写入事务时读取操作并不会被阻塞！这意味着，除非你需要从多个线程进行并发写入操作，否则，你可以尽量使用更大的写入事务来做更多的事情而不是使用多个更小的写入事务。当写入事务被提交到Realm时，该Realm的所有其他实例都将被通知，读入隐式事务将自动刷新你每个Realm对象。

在Realm中的读写访问是符合ACID的。

**当使用realm.copyToRealm()时，请注意只有返回的对象是由Realm管理的，这非常重要。对原始对象（umanaged Object）的任何改变都不会写入Realm。**

**异步事务（Asynchronous Transactions）**