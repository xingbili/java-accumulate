代码
1、定义配置文件信息
2、@RequiredArgsConstructor 代替 @Autowired (set注入，构造器注入，注解注入)
3、代码模块化：善于拆分方法
4、抛异常而不是返回
5、减少不必要的db
6、不要返回null 避免别处调用空指针异常
7、不要太多if else
8、controller 业务代码
9、利用好idea
10、 map遍历 for(Map.Entry<String,Strinng> entry:map.entrySet()){} opertional 判空
11、判断元素是否存在 用hashset 而不是list
    HashSet<String> set =  new HashSet<>();
    int index = hash(a);
    return getNode(index)!=null
12、统一管理线程池
13、接口传惨 如果传集合 尽量用Collection<T>
14、@Cacheable 做缓存加上缓存失效时间

