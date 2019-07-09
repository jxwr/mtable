#### TODO

##### 基本功能
* projection ✔
* aggregate, groupby ✔
* mtable api ✔
* 更新自动类型转换 ✔
* 函数入参类型校验和自动转换 ✔  
* datatype, list map tuple date float
* udf / udaf ✔
  - 在线代码编译类型问题没解决，加载Jar包OK 
* bucket支持一个range, 避免太多map ✔ 
  - 支持动态数据分配
* API简化，使用优化
* ORM，创建Schema，插入Record

##### MQL
* parser, ast
* 默认resolver，到mtable，到DB，到RPC

##### 升级功能
* multi index
* dml
* query on complex type
* db or kv backend
* lru / record evict

##### 优化
* ResultRow和Record合并 ✔ 
* 全字段取FullProjection，短路处理，避免对象创建，Record对应的Immutable版本，可以是个接口？ ✔
* 全字段函数
* 并发控制
* 性能优化
