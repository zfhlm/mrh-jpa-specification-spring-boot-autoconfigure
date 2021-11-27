# mrh-jpa-spring-boot-autoconfigure

    针对单表动态条件查询，基于 jpa specification、java8 lambda、cglib 进行扩展

### 示例实体

    @Getter
    @Setter
    @Entity(name="t_hello")
    public class HelloEntity {

        @Id
        @Column(name="id", length=36, nullable=false)
        private String id;

        @Column(name="username", length=50, nullable=false)
        private String username;

        @Column(name="gender", length=11, nullable=false)
        private Integer gender;

    }

    public interface HelloRepository extends JpaExampleRepository<HelloEntity, String> {}

### 示例代码

    简单and条件查询：

        JpaExample<HelloEntity> example = JpaExample.from(HelloEntity.class);
        example.where()
            .equal(HelloEntity::getUsername, "张三")
            .equal(HelloEntity::getGender, 1);
        List<HelloEntity> entities = helloRepo.findAll(example);

        select * from t_hello where username='张三' and gender=1

    简单or条件查询：

        JpaExample<HelloEntity> example = JpaExample.from(HelloEntity.class);
        example.whereOr()
            .equal(HelloEntity::getUsername, "张三")
            .equal(HelloEntity::getUsername, "李四");
        List<HelloEntity> entities = helloRepo.findAll(example);

        select * from t_hello where username='张三' or username='李四'

    条件过滤查询：

        JpaExample<HelloEntity> example = JpaExample.from(HelloEntity.class);
        example.where()
            .equalIgnoreBlank(HelloEntity::getUsername, " ")
            .rightLikeIgnoreBlank(HelloEntity::getUsername, "李四")
            .equal(HelloEntity::getGender, 0);
        List<HelloEntity> entities = helloRepo.findAll(example);

        select * from t_hello where username='李四' and gender=0

    and/or条件嵌套查询(可以无限嵌套)：

        JpaExample<HelloEntity> example = JpaExample.from(HelloEntity.class);
        JpaAndPredicates<HelloEntity> predicates = example.where();
        predicates.or()
            .equal(HelloEntity::getUsername, "张三")
            .equal(HelloEntity::getUsername, "李四")
        predicates.or()
            .equal(HelloEntity::getGender, 0)
            .equal(HelloEntity::getGender, 1);
        List<HelloEntity> entities = helloRepo.findAll(example);

        select * from t_hello where ( username='张三' or username='李四' ) and ( gender=0 or gender=1 )

    单个排序查询：

        JpaExample<HelloEntity> example = JpaExample.from(HelloEntity.class);
        example.where()
            .equal(HelloEntity::getUsername, "测试1")
            .equal(HelloEntity::getGender, 1);
        example.asc(HelloEntity::getId);
        List<HelloEntity> entities = helloRepo.findAll(example);

        select * from table_hello where user_name=? and gender=? order by id asc

    多个排序查询：

        JpaExample<HelloEntity> example = JpaExample.from(HelloEntity.class);
        example.where()
            .equal(HelloEntity::getUsername, "测试1")
            .equal(HelloEntity::getGender, 1);
        example.asc(HelloEntity::getId);
        example.desc(HelloEntity::getGender);
        List<HelloEntity> entities = helloRepo.findAll(example);

        select * from table_hello where user_name=? and gender=? order by id asc, gender desc
