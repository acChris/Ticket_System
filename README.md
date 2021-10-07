# 火车购票系统 

## 文件目录图

```
Ticket_System_TEST
├─ README.md
├─ docs
│    └─ ticket.sql
├─ pom.xml
├─ src
│    ├─ main
│    │    ├─ java
│    │    └─ resources
│    └─ test
│           └─ java
└─ tree.md
```

## 本售票系统应该具备如下功能： 

1．浏览功能：列出当前数据库文件中车票的所有记录，可选定一项纪录，显示说有域。

 2．查询功能 ：分为对车次信息的查询和客户对已订车票信息的查询。

## 功能缺陷

### 管理员

火车余票管理 ： 

1. 缺少按出发时间和到达时间的搜索功能。（或设计成下拉框，根据用户选择字段搜索相应信息）



用户购票管理：

1. 缺少按出发时间和到达时间的`搜索功能`。（或设计成下拉框，根据用户选择字段搜索相应信息）

2. 缺少`撤销用户订票`功能、`支付过期`功能（定时提醒用户尽快支付功能）、订单状态应添加`退票中`状态。



### 用户

查询火车：

1. 缺少全局搜索功能。

2. 缺少订票功能（订票按钮实现）

购票管理：

1. 缺少全局搜索功能。
2. 缺少撤销订票按钮。
3. 缺少支付按钮。

## 功能测试

待编写。

## 设计报告

待编写。