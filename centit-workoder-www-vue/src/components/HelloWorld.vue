<template>
  <zpa-column no-gutter>
    <zpa-row no-gutter :span="4">
      <zpa-column :span="4">
        <Box>
        </Box>
      </zpa-column>
      <zpa-column :span="8">
        <Box>
        </Box>
      </zpa-column>
    </zpa-row>
    <zpa-row no-gutter :span="4">
      <zpa-column :span="4">
        <Box>
          <Card class="box" title="Options" icon="ios-options" :padding="0" shadow>
            <CellGroup>
              <Cell title="Only show titles"/>
              <Cell title="Display label content" label="label content"/>
              <Cell title="Display right content" extra="details"/>
              <Cell title="Link" extra="details" to="/components/button"/>
            </CellGroup>
          </Card>
        </Box>
      </zpa-column>
      <zpa-column :span="4">
        <Box>
          <Carousel class="box" v-model="value1" loop style="width:500px" height="300px">
            <CarouselItem>
              <div class="demo-carousel">内容一</div>
            </CarouselItem>
            <CarouselItem>
              <div class="demo-carousel">内容二</div>
            </CarouselItem>
            <CarouselItem>
              <div class="demo-carousel">内容三</div>
            </CarouselItem>
            <CarouselItem>
              <div class="demo-carousel">内容四</div>
            </CarouselItem>
          </Carousel>
        </Box>
      </zpa-column>
      <zpa-column :span="4">
        <Box>
        </Box>
      </zpa-column>
    </zpa-row>
  </zpa-column>
</template>

<script>
export default {
  name: 'Dashboard',

  data() {
    return {
      msg: 'Welcome to Your Vue.js App',
      percent: 85,
      value1: 0,
      myCategoryData: {
        title: {
          text: '某楼盘销售情况',
          subtext: '纯属虚构',
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['意向', '预购', '成交']
        },
        toolbox: {
          show: true,
          feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
            restore: {show: true},
            saveAsImage: {show: true},
          }
        },
        calculable: true,
        xAxis: [
          {
            type: 'category',
            boundaryGap: false,
            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: '成交',
            type: 'line',
            smooth: true,
            itemStyle: {normal: {areaStyle: {type: 'default'}}},
            data: [10, 12, 21, 54, 260, 830, 710],
          },
          {
            name: '预购',
            type: 'line',
            smooth: true,
            itemStyle: {normal: {areaStyle: {type: 'default'}}},
            data: [30, 182, 434, 791, 390, 30, 10],
          },
          {
            name: '意向',
            type: 'line',
            smooth: true,
            itemStyle: {normal: {areaStyle: {type: 'default'}}},
            data: [1320, 1132, 601, 234, 120, 90, 20],
          }
        ]
      },
      myBarData: {
        title: {
          text: '某地区蒸发量和降水量',
          subtext: '纯属虚构',
        },
        tooltip: {
          trigger: 'axis'
        },
        legend: {
          data: ['蒸发量', '降水量']
        },
        toolbox: {
          show: true,
          feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true},
          }
        },
        calculable: true,
        xAxis: [
          {
            type: 'category',
            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: '蒸发量',
            type: 'bar',
            data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
            markPoint: {
              data: [
                {type: 'max', name: '最大值'},
                {type: 'min', name: '最小值'}
              ]
            },
            markLine: {
              data: [
                {type: 'average', name: '平均值'}
              ]
            }
          },
          {
            name: '降水量',
            type: 'bar',
            data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
            markPoint: {
              data: [
                {name: '年最高', value: 182.2, xAxis: 7, yAxis: 183, symbolSize: 18},
                {name: '年最低', value: 2.3, xAxis: 11, yAxis: 3}
              ]
            },
            markLine: {
              data: [
                {type: 'average', name: '平均值'}
              ]
            }
          }
        ]
      },
    }
  },
}
</script>
