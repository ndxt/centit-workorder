<template>
  <div style="margin-left: 30px;">
    <div>表达式为itemExp ([或| itemExp][与& itemExp][非! itemExp])的形式</div>
    <div>itemExp为下列形式：D()P()DT()DL()GW()XZ()R()UT()UL()U()RO()</div>
    <br/>
    <div>D 根据机构代码过滤 D(机构表达式)</div>
    <div>P 根据机构代码过滤主要机构</div>
    <div>DT 根据机构类型过滤 DT("角色代码常量" [,"角色代码常量"])</div>
    <div>DL 根据机构标签过滤 DL("角色代码常量" [,"角色代码常量"])</div>
    <div>GW 根据岗位过滤 GW("角色代码常量" [,"角色代码常量"])</div>
    <div>XZ 根据行政职务过滤 XZ("角色代码常量" [,"角色代码常量"])</div>
    <div>R 根据行政职务等级过滤 R(U) / R(U-) / R(U-1) / R(U--) /R(U-1--)</div>
    <div>U 根据用户代码过滤 U(用户变量|"用户代码常量" [,用户变量|"用户代码常量])</div>
    <div>UT 根据用户类型过滤 UT("用户类型常量" [,"用户类型常量"])</div>
    <div>UL 根据用户标签过滤 UL("用户标记常量" [,"用户标记常量"])</div>
    <div>RO 根据用户角色过滤 RO("系统角色代码常量" [,"系统角色代码常量"])</div>
  </div>
</template>

<script>
export default {
  name: 'FlowFormulaExplan',
}
</script>
