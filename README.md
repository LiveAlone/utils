# utils
##### 提供对应的基础Utils 使用的方式。 类似 guava Prediction 校验对应的结果信息。

1.  xml XML 相关的转换方式。
   -  XmlUtils 对应的Xml String Class 之间的转换的方式。
2. zip 
   - ZipUtils 对应的 文件压缩方式。
3. NumberSwitch 通过 Number 操作的转换方式。
4. Arguments 对应的参数 校验方式， 异常 ArgumentsException 方式。（参数的方式）
   - 集合的校验方式
   - 数值， 整数的校验方式
   - 正负的校验方式。
5. BeanMapper， 对应的不同的 Bean 中相同的字段的转换的方式。
   - 集合Bean 转换生成方式
   - Bean Map 转换的方式。
6. Joiners 对应的集合 连接的方式。 枚举对应的Joiner 连接的方式.
7. MapperBuilder 对应的构建Mapper 的构建使用的方式。
8. Params 对应的参数转换方式。
   - Map 空 过滤校验的方式。
9. Splitters 对应的Char 字符序列 分割操作方式。
10. ThreadObjectUtil 生成对应的 Object 方式。
11. ResHelper 对应的 Response 求解方式。
12. EncryptUtil 对应的加密方式
13. Items 对应的操作方式。



##### model 通用的 使用的方式

1. Response  对应的Rpc Service 远程调用的使用方式。
2. PageInfo 对应的页面的 limit， Offset 分页便宜信息的管理方式。
3. Paging 对应的分页的， 结果信息， 用于前段的使用方式。



##### Exception 扩充 类似 Guava中 IllegalStateException , ArgumentsException 异常定义

1. JsonResponseException 对应的Web Controller 异常转换的方式
2. ServiceException 服务的异常信息的产生的处理方式。