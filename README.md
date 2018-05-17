## 使用springboot操作neo4j

* 对应文件夹下边后缀.dump是相应数据库备份
* 
* 导入数据库:
* `./neo4j-admin load --from=~/***.dump --database=graph.db --froce`
* 重启数据库:
* `./neo4j start`
