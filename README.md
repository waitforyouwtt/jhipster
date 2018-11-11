# jhipster 基本用法
#### 修改api 名称：
application.yml 文件的{swagger}[title]
####项目swagger访问地址：
http://127.0.0.1:8080/swagger-ui/index.html
####使用postman调用方法时去掉过滤：
SecurityConfiguration 类configure 方法加过滤 method
####使用url 生成实体类[带分页]：
https://start.jhipster.tech/jdl-studio/
####生成实体类的途径：
方法1.将下载的jdl文件，放在项目根路径下，用管理员权限运行命令：
cd D:\putong
jhipster import-jdl jhipster-jdl.jh
方法2：将下载的jdl文件，放在项目根路径下，在idea terminal 运行命令：
jhipster import-jdl jhipster-jdl.jh
  
