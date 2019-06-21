# 使用aop自定义注解实现验证token

#### 介绍
使用aop防止简单验证token，防止表单重复提交

初始化的时候发送ajax请求获取token
创建test用户
db.createUser({user:"test",pwd:"123456",roles:[{role:"userAdminAnyDatabase",db:"admin"}]})
查看表数据
db.user.find();
删除use表所有数据
 db.user.remove({});
 
 附录：
 
 mongodb和mysql区别
 Mongodb					mysql
 Database					database
 Collection				table
 Bson						二维表
 不支持sql查询			支持sql查询
 _id						主键
 支持GEO索引				不支持GEO索引
 
 Mongodbmsi安装比较方便
 
 Mongo常用指令
 show databases;					查看数据库
  use xiaoming					创建数据库/修改访问数据库为xiaoming
  Db								查看当前数据库
 db.createCollection("bikes")		创建表
  show collections					查看表
 db.col.insert({title: 'MongoDB 教程'})	插入文档
 db.col.update({'title':'MongoDB 教程'},{$set:{'title':'MongoDB'}})	修改文档
 db.collection.remove() 删除满足匹配条件的一个或多个文档
 db.collection.deleteOne() 删除满足匹配条件的最多一个文档(即使有多个文档满足匹配条件),3.2版本支持 
 db.collection.deleteMany() 删除满足匹配条件的所有文档
 db.bikes.find({"aa":122});
 用户设置安全权限
 db.createUser({user: "admin", pwd: "admin123", roles: [{ role: "root", db: "ad
 min" }]})
 
 问题：（虽然能设置权限但是不用密码登录也可以访问），mongodb3.0
 
 
 
 
 