## 19-11-24

### 工作内容:

1. 通过BootStrap创建了一个登录的导航条
2. 完成了调用 Github 的第三方授权,并且成功拿到了 Github 的 User 信息
3. [bilibili视频观看至](https://www.bilibili.com/video/av50200264?p=13)

### 资源工具地址:

[Github认证文档](https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/)

[okhttp模拟get/post请求](https://square.github.io/okhttp/)

[BootStrap前端框架](https://v3.bootcss.com/components/)

## 19-11-25

### 内容:

1. 创建application.yml文件, @Value(${github.client.id}}) 将参数注入
2. 使用H2 数据库
3. 一个404的BUG,待解决

## 19-11-26

### 内容:

1. 更改为 MySQL数据库,完成持久化储存
2. 整合 Mybatis 
3. 解决上一个BUG

### 记录一下问题
> java.lang.IllegalArgumentException: Parameter Maps collection does not conta.......
> 这里我之前用的 parameterMap="User" 是会报错的(被官方弃用了),替换成 parameterType="User" 解决

~~~xml
<insert id="insert" parameterType="User">
    insert into t_user(name,account_id,token,gmt_create,gmt_modified) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})
</insert>
~~~
     
## 19-11-27

### 工具
[flywaydb](https://flywaydb.org/getstarted/firststeps/maven)