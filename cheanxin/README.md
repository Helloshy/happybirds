## 权限相关参考：[岗位权限.xlsx](https://github.com/hezudaopp/cheanxin/blob/master/%E5%B2%97%E4%BD%8D%E6%9D%83%E9%99%90.xlsx)

## [客户端项目](https://github.com/JimShawn/cheanxinclient)

## 接口统一规范约定：
### 协议：Http
### 风格：Restful
  - 资源名词尽量复数形式
  - PUT 用来更新资源的全部字段 PATCH 用来更新资源的部分字段
  - JSON字段采用驼峰格式
  - 失败响应格式如下:
``` javascript
{
  "errorCode": "Not Found", 
  "errorMessage":"资源未找到"
}
```
### 请求数据格式：JSON
### 响应数据格式：JSON
### 响应代码：
  - 200: 成功
  - 201: 创建成功
  - 400: 失败
  - 401: 用户认证失败或权限不够
  - 404: 资源不存在
  - 405: 请求方法错误
  - 500: 服务端错误

## 基础数据结构：

### 用户信息：USER
``` javascript
{
  "id": 1,
  "username": "jack",
  "password": "93cb4f62ef4b8401225a88aafa6a2cb8ac8eaa5f381dad8411d5fa84059a806472416ed6528ad578",
  "realName": "杰克",
  "deptId": 1,
  "photo": "/1/jack/avatar.jpg",
  "region": "华南",
  "mobileNumber": "14567884433",
  "email": "jack163@163.com",
  "identityNumber": "24313519381214192X",
  "identityPhoto": "/1/jack/identity_0.jpg",
  "address": "福州鼓楼区",
  "emergencyContact": "杰克他爸",
  "emergencyContactMobileNumber": "13452457689",
  "createdTime": 1483432438,
  "authorities": null,
  "accountNonLocked": true,
  "credentialsNonExpired": true,
  "accountNonExpired": true,
  "enabled": true,
  "dept":DEPT结构,
  "posts":[POST结构,  POST结构, ... ]
}
```

### 产品模版信息：PRODUCT_TEMPLATE
``` javascript
{
  "id":2,
  "name":"车安心1号"
  "productType":1, // 产品类型 1: 新车贷款 2: 二手车贷款
  "repaymentMethod":1, // 还款方式 1: 等额本息
  "minAvailableRate":1, // 最小可贷款成数 1表示1成
  "maxAvailableRate":8, // 最大可贷款成数 8表示8成
  "availableTerms":"12,24,36", // 可用期数 用逗号分隔
  "loanPolicy":1, // 贷款政策 1: 过户后放款 2：抵押后放款 
  "loanMonthlyInterestRate":0.81, //贷款月利率 0.81表示0.81%，如果productTemplateId为0，表示参考月利率
  "modifiedTime":1324333455,
  "createdTime":1324333455,
  "creatorUsername":"273" // 创建人
}
```

### 产品信息：PRODUCT
``` javascript
{
  "id":2,
  "productTemplateId":0, // 0表示该产品为模板 非0表示该产品是可用于贷款的产品
  "name":"车安心1号"
  "productType":1, // 产品类型 1: 新车贷款 2: 二手车贷款
  "repaymentMethod":1, // 还款方式 1: 等额本息
  "minAvailableRate":1, // 最小可贷款成数 1表示1成
  "maxAvailableRate":8, // 最大可贷款成数 8表示8成
  "availableTerms":"12,24,36", // 可用期数 用逗号分隔
  "loanPolicy":1, // 贷款政策 1: 过户后放款 2：抵押后放款 
  "loanMonthlyInterestRate":0.81, //贷款月利率 0.81表示0.81%，如果productTemplateId为0，表示参考月利率
  "provinceId": 1, //省份id
  "cityId":2, // 城市id
  "status":1, // 0.待审核 1.审核通过 2. 审核拒绝  （仅当productTemplateId不为0时该字段才有意义）
  "modifiedTime":1324333455,
  "createdTime":1324333455,
  "creatorUsername":"273" // 创建人
}
```

### 岗位信息：POST
``` javascript
{
  "id":1,
  "serialNumber":"002", // 岗位编号,三位数字的字符串
  "name":"金融专员", // 岗位名称
  "postTypeId":1, // 岗位类型 1: 高层领导岗 2: 中层领导岗 3: 执行岗
  "enabled":true, // true: 启用 false: 禁用
  "createdTime":1234567890
}
```

### 部门组织信息：DEPT
``` javascript
{
  "id": 2,
  "deptCode": "002", // 部门编号，三位数字字符串
  "name": "金融中心", // 部门名称
  "parentDeptId": 1, // 上级部门
  "level": 2, // 部门层级
  "enabled": true, // 是否启用 true: 启用 false: 禁用 
  "createdTime": 1234567890
}
```

### 贷款基本信息：LOAN
``` javascript
{
  "id": 1,
  "vehicleDealPrice": 20000, // 车辆成交价格
  "vehiclePredictPrice": 19000, // 评估师给的车辆价格
  "productId": 2, // 产品id
  "productName": "车安心1号", // 产品名称
  "productType": 1, // 0: 新车贷款 1: 二手车贷款
  "productLoanMonthlyInterestRate": 0.78, // 贷款月利率
  "productMinAvailableRate":3, // 产品最小可贷成数
  "productMaxAvailableRate":7, // 产品最大可贷成数
  "productLoanPolicy":1, // 产品贷款策略
  "applicantLoanRate": 4, // 客户想要的贷款比率
  "applicantLoanPrice": 50000, // 客户想要的贷款金额
  "reviewLoanRate": 3, // 审批核准的最高贷款比率
  "reviewLoanPrice": 40000, // 审批的贷款金额
  "loanTerms": 12, // 贷款期数
  "remark": "备注信息", // 贷款
  "sourceFinancialCommissioner": "魏伟", // 金融专员
  "sourceReceiver": "李四", // 收单人员
  "sourceCityId": 1, // 来源城市
  "sourceChannel": 1, // 来源渠道
  "sourcePersonName": "张三", // 来源联系人姓名
  "sourcePersonTel": "13456789876", // 来源联系人电话
  "applicantName": "蒋一鸣", // 申请人姓名
  "applicantMarriage": 1, // 申请人婚姻状态
  "applicantCertificateType": 1, // 申请人证件类型
  "applicantCertificateNumber": "430426", // 申请人证件号
  "applicantGender": 1, // 申请人性别
  "applicantBirthYearMonth": "199006", // 申请人出生年月，6位
  "applicantQualification": "本科", // 申请人学历
  "applicantMobileNumber": "13078889999", // 申请人手机号码
  "applicantCensusCityId": 1, // 申请人户籍所在城市
  "applicantAddress": "福建厦门", // 现居住地
  "applicantIncomePerMonth": 18999, // 月收入
  "applicantTelephone": "059126131234", // 申请人电话
  "applicantEmployerName": "中国电信", // 工作单位名称
  "applicantEmployerTelephone": "10000", // 单位电话
  "applicantEmployerAddress": "福州市鼓楼区", // 单位所在地
  "applicantEmployerType": "国企", // 工作单位性质(国企、私企、外企等等)
  "applicantEmployerIndustry": "电信", // 工作单位所属行业
  "applicantPost": "客服", // 申请人工作岗位
  "applicantOccupation": "客户服务", // 申请人职业
  "applicantWorkYears": 6, // 申请人工作年限
  "applicantPosition": "客服经理", // 申请人职务
  "applicantJobTitle": "高级客服", // 申请人职称
  "applicantPostAddress": "福州市东大路1号", // 申请人邮寄地址
  "applicantPostCode": "350000", // 申请人邮编
  "applicantFirstEmergencyContact": "蒋介水", // 申请人紧急联系人1
  "applicantFirstEmergencyContactRelationship": "母亲", // 申请人紧急联系人1与申请人的关系
  "applicantFirstEmergencyContactMobileNumber": "18798721123", // 申请人紧急联系人1手机号码
  "applicantFirstEmergencyContactAddress": "福建三明", // 申请人紧急联系人1地址
  "applicantSecondEmergencyContact": "江无忌", // 申请人紧急联系人2
  "applicantSecondEmergencyContactRelationship": "朋友", // 申请人紧急联系人1与申请人的关系
  "applicantSecondEmergencyContactMobileNumber": "13234338867", // 申请人紧急联系人2手机号码
  "applicantSecondEmergencyContactAddress": "江苏无锡", // 申请人紧急联系人2地址
  "applicationPicUrl": "1.jpg,2.jpg,3.jpg", // 申请照片列表
  "applicantCertificateFileIds": "1.jpg,2.jpg,3.jpg", // 申请人证件照列表
  "applicantQualificationFileIds": "1.jpg,2.jpg,3.jpg", // 申请人学历证明照片列表
  "applicantIncomeFileIds": "1.jpg,2.jpg,3.jpg", // 申请人收入证明照片列表
  "applicantEstateFileIds": "1.jpg,2.jpg,3.jpg", // 申请人房产证明照片列表
  "applicantVehicleFileIds": "1.jpg,2.jpg,3.jpg", // 申请人车辆证明照片列表
  "applicantOtherFileIds": "1.jpg,2.jpg,3.jpg", // 申请人其他照片列表
  "coApplicantName": "张家豪", // 共同申请人姓名
  "coApplicantRelationship": "同学", // 共同申请人和申请人关系
  "coApplicantCensusCityId": 2, // 共同申请人户籍所在城市
  "coApplicantCertificateNumber": "350101198802031122", // 共同申请人身份证号码
  "coApplicantMobileNumber": "17001023456", // 共同申请人手机号
  "coApplicantQualification": "硕士", // 共同申请人学历
  "coApplicantAddress": "浙江杭州", // 共同申请人地址
  "coApplicantIncomePerMonth": 20000, // 共同申请人月收入
  "coApplicantTelephone": "079138872234", // 共同申请人电话
  "coApplicantEmployerName": "阿里巴巴集团", // 共同申请人单位名称
  "coApplicantEmployerTelephone": "079188772233", // 共同申请人单位电话
  "coApplicantEmployerAddress": "浙江杭州西湖区", // 共同申请人单位所在地
  "coApplicantCertificateFileIds": "1.jpg,2.jpg,3.jpg", // 共同申请人身份证照片列表
  "coApplicantIncomeFileIds": "1.jpg,2.jpg,3.jpg", // 共同申请人收入证明照片列表
  "coApplicantEstateFileIds": "1.jpg,2.jpg,3.jpg", // 共同申请人房产证明照片列表
  "coApplicantOtherFileIds": "1.jpg,2.jpg,3.jpg", // 共同申请人其他证明照片列表
  "guarantorName": "刘维佳", // 担保人姓名
  "guarantorCensusCityId": 4, // 担保人户籍所在城市
  "guarantorRelationship": "舅舅", // 担保人和申请人关系
  "guarantorCertificateNumber": "350102197001035831", // 担保人身份证号码
  "guarantorRealEstateOwnType": "自有", // 担保人房产权属
  "guarantorIncomePerMonth": 5000, // 担保人月收入
  "guarantorMobileNumber": "18606070809", // 担保人手机号
  "guarantorCertificateFileIds": "1.jpg,2.jpg,3.jpg", // 担保人身份证照片列表
  "guarantorIncomeFileIds": "1.jpg,2.jpg,3.jpg", // 担保人收入证明照片列表
  "guarantorEstateFileIds": "1.jpg,2.jpg,3.jpg", // 担保人房产证明照片列表
  "guarantorOtherFileIds": "1.jpg,2.jpg,3.jpg", // 担保人其他证明照片列表
  "vehicleVin": "12345678645223457", // 车辆识别码
  "vehicleManufacturers": "广汽丰田", // 车辆生产商
  "vehicleBrand": "丰田", // 车辆品牌
  "vehicleSeries": "汉兰达", // 车系
  "vehicleType": "3.5L 四驱豪华版", // 车型
  "vehicleProductionYearMonth": "201605", // 车辆生产年月
  "vehicleRegistrationYearMonth": "201606", // 车辆首次登记年月
  "vehicleKilometers": 1.85, // 车辆公里数，万为单位
  "vehicleUtilityType": "家用", // 车辆使用性质
  "vehicleEmission": "国V", // 车辆排放标准
  "vehicleRegistrationCertificateFileIds": "1.jpg,2.jpg,3.jpg", // 车辆登记证书图片列表
  "vehicleLicenseFileIds": "1.jpg,2.jpg,3.jpg", // 机动车行驶证图片列表
  "vehicleFileIds": "1.jpg,2.jpg,3.jpg", // 车辆图片列表
  "materialsRemark": "补充材料重新申请理由",
  "materialsFileIds": "1.jpg,2.jpg,3.jpg", // 补充材料图片列表
  "contractNumber": "123455667", // 合同编号
  "contractCreatedTime": 1234567890, // 合同签约时间
  "contractFileIds": "1.jpg,2.jpg,3.jpg", // 合同图片列表
  "createdTime": 1487815222, // 贷款创建时间
  "modifiedTime": 1487818250, // 贷款最后修改时间
  "creatorUsername": "273", // 贷款创建人用户名
  "reviewRemark": "This is just a 备注" // 贷款审批备注信息
  "status": 0, // 贷款状态 
}
```

### 贷款状态说明
  - 0: 已放弃（初审直接拒绝） 
  - 1: 草稿箱 
  - 2: 待初审 
  - 3: 待定价 
  - 4: 待复审 
  - 5: 材料待补充 
  - 6: 待签约 
  - ~~7: 方案待修改 (弃用)~~
  - 8: 待过户（待补充过户材料，过户后放款）
  - 9: 初审退回 
  - 10: 材料补充后待复审
  - 11: 材料补充后复审被拒绝 
  - 12: 调整方案后待复审 
  - 13: 调整方案后复审被拒绝 
  - ~~14: 已放弃（放弃调整方案，弃用）~~
  - 15: 已放弃（放弃补充材料） 
  - 16: 已放弃（第二次放弃合同签约）
  - 17: 主动放弃签约后待审核状态（防止用户误操作放弃签约）
  - ~~18: 调整方案前的待审核状态（调整方案需要审核，弃用）~~
  - 19: 无法过户待确认（过户后放款）
  - 20: 无法过户放弃贷款（过户后放款，结束）
  - 21: 过户材料退回待补充（过户后放款）
  - 22: 过户材料待审核（过户后放款）
  - ~~23: 待放款（过户后放款，弃用）~~
  - 24: 待抵押（过户后放款）
  - 25: 待过户抵押（抵押后放款）
  - 26: 放弃抵押待确认（抵押后放款）
  - 27: 无法处理抵押放弃贷款（抵押后放款，结束）
  - 28: 抵押材料退回待补充（抵押后放款）
  - 29: 过户抵押材料待审核（抵押后放款）
  - ~~30: 待放款（抵押后放款，弃用）~~
  - 31: 待还款（过户后放款）
  - 32: 补充材料后放款待审核（过户后放款）
  - 33: 补充材料后放款待审核（抵押后放款）
  - 34: 第二次过户材料审核不通过（过户后放款，结束）
  - 35: 第二次过户抵押材料审核不通过（抵押后放款，结束）
  - 36: 待还款（过押后放款）
  - 37: 同意签约待审核状态
  - 38: 抵押材料上传待审核状态（过户后放款）

### 贷款日志：LOAN_LOG
``` javascript
{
  "id": 2, // 贷款日志id
  "loanId": 1, // 贷款id
  "operatorUsername": "username1", // 操作用户名
  "fromStatus": 1, // 贷款原状态
  "toStatus": 2, // 贷款目标状态
  "createdTime": 1234567890
}
```



## 接口列表：
### 岗位相关
#### 获取岗位类型列表
  - 请求URI: /post_types
  - 请求Method: GET
  - 请求参数: 
    - enabled: 1 // 是否启用 默认1
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: 
``` javascript
  [POST结构, POST结构, ...]
```

#### 添加岗位
  - 请求URI: /posts
  - 请求Method: POST
  - 请求参数: 无
  - 请求内容: POST结构, id不用传
  - 响应成功代码: 201
  - 响应成功内容: POST结构

#### 删除岗位
  - 请求URI: /posts/{id}
  - 请求Method: DELETE
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: 无

#### 编辑岗位
  - 请求URI: /posts/{id}
  - 请求Method: PUT
  - 请求参数: 无
  - 请求内容: POST结构, id不用传
  - 响应成功代码: 200
  - 响应成功内容: POST结构

#### 岗位列表（分页）
  - 请求URI: /posts
  - 请求Method: GET
  - 请求参数:
    - page 当前页，默认0
    - size 每页大小，默认10
    - enabled 是否启用，默认1
    - name 岗位名称，搜索岗位时模糊匹配岗位名，默认不传
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: 
``` javascript
  {
    "content": [POST结构, ...],
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 20,
    "number": 0,
    "first": true,
    "sort": null,
    "numberOfElements": 1
  }
```

#### 禁用/启用岗位
  - 请求URI: /posts/{id}
  - 请求Method: PATCH
  - 请求参数: 无
  - 请求内容: 
``` javascript
  {
    "enabled":1 // 启用状态
  }
```
  - 响应成功代码: 200
  - 响应成功内容: POST结构

### 部门相关
#### 添加部门
  - 请求URI: /depts
  - 请求Method: POST
  - 请求参数: 无
  - 请求内容: DEPT, id不用传
  - 响应成功代码: 201
  - 响应成功内容: DEPT

#### 删除部门
  - 请求URI: /depts/{id}
  - 请求Method: DELETE
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: 无

#### 编辑部门
  - 请求URI: /depts/{id}
  - 请求Method: PUT
  - 请求参数: 无
  - 请求内容: DEPT, id不用传
  - 响应成功代码: 200
  - 响应成功内容: DEPT

#### 岗位部门
  - 请求URI: /depts
  - 请求Method: GET
  - 请求参数:（level和parentDeptId二选一）
    - enabled 是否启用，默认1
    - level 部门层级，默认为1，当为1时和parentDeptId为0是等效的
    - parentDeptId 上级部门, 默认为0，当为parentDeptId为0时和level为1是等效的
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: 
``` javascript
  [DEPT结构, ...]
```

#### 禁用/启用部门
  - 请求URI: /depts/{id}
  - 请求Method: PATCH
  - 请求参数: 无
  - 请求内容: 
``` javascript
  {
    "enabled":1 // 启用状态
  }
```
  - 响应成功代码: 200
  - 响应成功内容: DEPT结构

### 用户相关
#### 用户登录
  - 接口说明: 目前支持两个方式的登录，password和refresh_token
  - 请求URI: /oauth/token
  - 请求Method: POST
  - 请求参数: 
    - grant_type: password or refresh_token
    - username: 登录用户名 // grant_type为password时需要
    - password: 用户登录密码 // grant_type为password时需要
    - refresh_token: 9caeb2e2-89ac-4438-b029-e0ee1fb9fd4e // grant_type为refresh_token时需要
  - 请求头部: 
    - Authorization: Basic ZW50OmFtcG5 //向服务端开发人员拿
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容:
``` javascript
  {
    "access_token": "ef5979dc-1ca5-48e7-9472-fc4e5094da90",
    "token_type": "bearer",
    "refresh_token": "9caeb2e2-89ac-4438-b029-e0ee1fb9fd4e",
    "expires_in": 14,
    "scope": "read write"
  }
```

#### 用户退出
  - 请求URI: /users/logout
  - 请求Method: DELETE
  - 请求参数: 无
  - 请求头部: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: 无

#### 修改密码
  - 请求URI: /users/password
  - 请求Method: PATCH
  - 请求参数: 无
  - 请求头部: 无
  - 请求内容:
``` javascript
  {
    "oldPassword": "123456",
    "newPassword1": "654321",
    "newPassword2": "654321"
  }
```
  - 响应成功代码: 200
  - 响应成功内容: 无

#### 添加用户
  - 接口说明：
    - username, mobileNumber, email字段必须唯一
  - 请求URI: /users
  - 请求Method: POST
  - 请求参数: 无
  - 请求内容: USER
  - 响应成功代码: 201
  - 响应成功内容: 无

#### 用户列表（分页）
  - 请求URI: /users
  - 请求Method: GET
  - 请求参数:
    - page: 1, 默认0
    - size:10, 默认10
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: 
``` javascript
  {
    "content": [USER, ...],
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 2,
    "number": 0,
    "first": true,
    "sort": null,
    "numberOfElements": 1
  }
```

#### 用户详情
  - 请求URI: /users/{username}
  - 请求Method: GET
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: USER

#### 当前登录用户用户详情
  - 请求URI: /users/me
  - 请求Method: GET
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: USER

### 产品模板相关
#### 添加产品模板
  - 请求URI: /product_templates
  - 请求Method: POST
  - 请求参数: 无
  - 请求内容: PRODUCT_TEMPLATE, id不用传
  - 响应成功代码: 201
  - 响应成功内容: PRODUCT_TEMPLATE

#### 删除产品模板
  - 接口说明：那么该产品模板下必须没有产品
  - 请求URI: /product_templates/{id}
  - 请求Method: DELETE
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: 无

#### 编辑产品模板
  - 请求URI: /product_templates/{id}
  - 请求Method: PUT
  - 请求参数: 无
  - 请求内容: PRODUCT_TEMPLATE
  - 响应成功代码: 200
  - 响应成功内容: PRODUCT_TEMPLATE

#### 产品模版列表（分页）
  - 请求URI: /product_templates
  - 请求Method: GET
  - 请求参数:
    - page: 1, 默认0
    - size:10, 默认10
    - name:"产品模版名", 默认为空字符串，表示不对该字段过滤
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: 
``` javascript
  {
    "content": [PRODUCT_TEMPLATE, ...],
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 2,
    "number": 0,
    "first": true,
    "sort": null,
    "numberOfElements": 1
  }
```

### 产品相关
#### 添加产品
  - 请求URI: /products
  - 请求Method: POST
  - 请求参数: 无
  - 请求内容: PRODUCT, id不用传
  - 响应成功代码: 201
  - 响应成功内容: PRODUCT

#### 编辑产品
  - 接口说明：如果要编辑产品，产品状态必须是待审核
  - 请求URI: /products/{id}
  - 请求Method: PUT
  - 请求参数: 无
  - 请求内容: PRODUCT
  - 响应成功代码: 200
  - 响应成功内容: PRODUCT

#### 产品列表（分页）
  - 请求URI: /products
  - 请求Method: GET
  - 请求参数:
    - page: 1, 默认0
    - size:10, 默认10
    - productTemplateId: 0， 默认-1，表示筛选productTemplateId > 0的产品
    - status:1, 默认0
    - name:"产品名", 默认为空字符串，表示不对该字段过滤
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: 
``` javascript
  {
    "content": [PRODUCT, ...],
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 2,
    "number": 0,
    "first": true,
    "sort": null,
    "numberOfElements": 1
  }
```

#### 审核产品
  - 请求URI: /products/{id}
  - 请求Method: PATCH
  - 请求参数: 无
  - 请求内容: 
``` javascript
  {
    "status":1, // 1: 审核通过 2: 审核拒绝
    "remark":"审核说明"
  }
```
  - 响应成功代码: 200
  - 响应成功内容: PRODUCT

#### 禁用产品
  - 接口说明：产品状态必须是审核通过
  - 请求URI: /products/{id}/disable
  - 请求Method: PATCH
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: PRODUCT

#### 启用产品
  - 接口说明：产品状态必须是审核通过
  - 请求URI: /products/{id}/enable
  - 请求Method: PATCH
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: PRODUCT

### 贷款相关
#### 添加贷款
  - 请求URI: /loans
  - 请求Method: POST
  - 请求参数: 无
  - 请求内容: LOAN, id不用传
  - 响应成功代码: 201
  - 响应成功内容: LOAN

#### 编辑贷款或审批贷款
  - 接口说明：修改贷款信息或审核贷款
  - 请求URI: /loans/{id}
  - 请求Method: PATCH
		  - 请求参数: loanOperate: 1. 修改 2. 提交审批申请 3. 审批通过 4. 审批拒绝 5. 放弃
  - 请求内容: LOAN, id不用传
  - 响应成功代码: 200
  - 响应成功内容: LOAN

#### 贷款列表（分页）
  - 请求URI: /loans
  - 请求Method: GET
  - 请求参数:
    - page: 1, 默认0
    - size:10, 默认10
    - isFilterSelf: 1， 可选，默认0，是否筛选本人创建的贷款
    - sourceFinancialCommissioner: 跟单金融专员用户名 可选
    - applicantName: 申请人姓名 可选
    - applicantMobileNumber: 申请人手机号 可选
    - createdTimeFrom: 最早创建时间 可选
    - createdTimeTo: 最晚创建时间 可选
    - status:1,2,3 可选 默认为空 筛选全部 （多选，也可以只选一种状态，多选的时候用逗号分割）
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: 
``` javascript
  {
    "content": [LOAN, ...],
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 2,
    "number": 0,
    "first": true,
    "sort": null,
    "numberOfElements": 1
  }
```

#### 获取贷款详情
  - 请求URI: /loans/{id}
  - 请求Method: GET
  - 请求参数: 无
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: LOAN

### 贷款日志相关
#### 贷款日志列表
  - 请求URI: /loan_logs
  - 请求Method: GET
  - 请求参数:
    - loanId: 1, // 必选 贷款id
    - fromStatus:1, // 必选 贷款原状态 
    - toStatus: 2, // 必选 贷款目标状态
  - 请求内容: 无
  - 响应成功代码: 200
  - 响应成功内容: 
``` javascript
  [LOAN_LOG, LOAN_LOG, ...]
```
