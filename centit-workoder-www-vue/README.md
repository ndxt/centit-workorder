请大家注意，项目虚拟机服务VCENTER已经升级完毕，今后使用VM客户端进行登陆管理的话，需要对VM客户端进行升级，升级程序可以到网络共享里面 \\192.168.128.11\Share\常用软件\vSphere-Client 去下载。今后将陆续对项目服务器运行主机和存储服务器进行升级和迁移，项目虚拟机有可能会遇到暂时关机和重启的现象，请大家注意，如果有问题请与顾强联系。

1. 表格日期，建公共过滤器 tr
1. 在提交表单中生效日期和失效日期统一判断 gc
2. 表格中的Code需要转成中文（需要后台配合） gc
3. 前台一些字段没有添加长度校验 (各自负责)
3. 编辑的时候后台唯一性校验需要排除原始值（框架统一处理）tr
4. 新增后中文乱码（后台）gc
5. 后台校验的字符串长度与前台不一致（一起找原因）lsh
6. 一些查询请求后台似乎没有按照排序字段排序 lsh
7
新系统：http://demo.centit.com/smas/framework

老系统API文档：
http://192.168.134.9:11000/framework/system/doc.html

老后台管理系统：http://192.168.134.9:11000/framework/system/mainframe/login

老文件服务系统：
http://192.168.134.9:18000/fileserver/system/mainframe/login

老统计服务系统：
http://192.168.134.9:15000/centit-stat/system/mainframe/login

# centit-ui

> A Vue.js project

## Build Setup

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build

# build for production and view the bundle analyzer report
npm run build --report

# run unit tests
npm run unit

# run e2e tests
npm run e2e

# run all tests
npm test
```
http://192.168.134.9:11000/framework/system/mainframe/index
(http://demo.codedefault.com/demo/ui/material_angular/index.html#/form/form-examples)[http://demo.codedefault.com/demo/ui/material_angular/index.html#/form/form-examples]

For a detailed explanation on how things work, check out the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).
