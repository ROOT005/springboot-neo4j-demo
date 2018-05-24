## relationship使用springboot操作neo4j (The project relationship is show how to deal with neo4j by springboot) 
* 
* relationship文件夹下边后缀.dump是相应数据库备份
* (In document of relationship, '*.dump' is the graph's backup)
* 
* 导入数据库(Load DataSource):
* `./neo4j-admin load --from=备份文件名 --database=graph.db --froce`
*
* 重启数据库(Restart neo4j):
* `./neo4j start`

## personrel使用springboot操作neo4j+mysql,演示多数据源的使用
#
