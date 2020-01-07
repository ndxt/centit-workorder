<template>
  <div class="bg">
    <div class="header">
      <div></div>
    </div>

    <div class="center">
      <div class="item item1">
        <div class="h">散煤用户总数</div>
        <div class="t">居民清洁能源替代总数</div>
        <div class="c">
          <div>65149554</div>
          <div>户</div>
        </div>
        <div class="t">2018年前完成</div>
        <div class="c">
          <div>659554</div>
          <div>户</div>
        </div>
        <div class="t">2019年计划</div>
        <div class="c">
          <div>49554</div>
          <div>户</div>
        </div>
        <div class="t">2020年计划</div>
        <div class="c">
          <div>654</div>
          <div>户</div>
        </div>
      </div>

      <div class="item item2">
        <div class="shishi">
          <div>
            <div class="t">2019年完成进度</div>
            <ul>
              <li>2</li>
              <li>3</li>
              <li>6</li>
              <li>8</li>
              <li>2</li>
              <li>3</li>
              <li>1</li>
              <li class="b">户</li>
            </ul>
          </div>
          <div>
            <div class="t">2019年完成比例</div>
            <ul>
              <li>8</li>
              <li>7</li>
              <li class="b">.</li>
              <li>6</li>
              <li class="b">%</li>
            </ul>
          </div>
        </div>

        <div class="map">

        </div>
      </div>

      <div class="item item3">
        <div class="h">清洁能源代替类型</div>
        <div class="c">
          <div>煤改电</div>
          <div>494166</div>
        </div>
        <div class="c">
          <div>煤改气</div>
          <div>466</div>
        </div>
        <div class="c">
          <div>洁净煤</div>
          <div>49416</div>
        </div>
        <div class="c">
          <div>煤改热</div>
          <div>4966</div>
        </div>
        <div class="c">
          <div>集中供暖</div>
          <div>466</div>
        </div>
        <div class="c">
          <div>太阳能</div>
          <div>4166</div>
        </div>
        <div class="c">
          <div>其它</div>
          <div>496</div>
        </div>
      </div>
    </div>

    <div class="footer">
      <ul>
        <li>
          <div></div>
          <div>洁净煤配送打火器</div>
        </li>
        <li>
          <div></div>
          <div>洁净煤配送</div>
        </li>
        <li>
          <div></div>
          <div>洁净煤配送</div>
        </li>
        <li>
          <div></div>
          <div>洁净煤配送</div>
        </li>
        <li>
          <div></div>
          <div>洁净煤配送</div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import $ from 'jquery'

export default {
  methods: {
    reload() {
      const w = document.documentElement.clientWidth

      $('html').css({
        'font-size': `${w / 100}px`
      })
    },
  },

  mounted() {
    $(window).resize(() => {
      this.reload()
    })

    this.reload()
  },
}
</script>

<style lang="less">
  * {
    margin: 0;
    padding: 0;
    outline: none;
  }

  html {
    font-size: 20px;

    body {
      height: 100vh;
      font-size: 1rem;

      .bg {
        height: 100%;
        background: url("./img/map.png") no-repeat center center;
        overflow: auto;
        display: flex;
        flex-direction: column;

        .header {
          min-height: 3.8rem;
          display: flex;
          background: url("./img/title-bg.png") no-repeat top center / 100% 100%;

          div {
            flex: auto;
            background: url("./img/title.png") no-repeat center center / 32rem 100%;
          }
        }

        .center {
          display: flex;
          flex: auto;
          min-height: 31rem;
          margin: 2rem;
          color: #0068b7;

          .item {
            width: 22%;
            display: flex;
            padding: 1.2rem 0;
            flex-direction: column;

            &.item1 {
              background: url("./img/bb.png") no-repeat center center / 100% 100%;

              > div {
                flex: auto;
                align-items: center;
                display: flex;

                &.h {
                  text-indent: 2rem;
                  font-weight: bold;
                }
                &.t {
                  padding-left: 4rem;
                  color: #515a6e;
                }
                &.c {
                  font-size: 2.5rem;
                  font-family: "Microsoft YaHei";
                  justify-content: flex-end;
                  padding-right: 3rem;
                  text-shadow: 2px 2px 3px #999;

                  div:first-of-type {
                    letter-spacing: .1rem;
                  }
                  div:last-of-type {
                    font-size: .8rem;
                    margin-left: .5rem;
                  }
                }
              }
            }
            &.item2 {
              width: 56%;
              margin: 1rem;
              padding: 0;

              .shishi {
                height: 8rem;
                display: flex;

                > div {
                  padding: .5rem 1rem;
                  display: flex;
                  flex-direction: column;
                  &:first-of-type {
                    width: 67%;
                    margin-right: 1.5rem;
                    background: url("./img/number-bg1.png") no-repeat center center / 100% 100%;
                  }
                  &:last-of-type {
                    width: 33%;
                    margin-left: 1.5rem;
                    background: url("./img/number-bg2.png") no-repeat center center / 100% 100%;
                  }

                  .t {
                    font-weight: bold;
                    margin-bottom: .3rem;
                  }

                  ul {
                    display: flex;
                    flex: auto;

                    li {
                      flex: auto;
                      background: url("./img/number.png") no-repeat center center / 95% 100%;
                      color: #fff;
                      font-size: 4rem;
                      align-items: center;
                      display: flex;
                      justify-content: center;
                      &.b {
                        background: none;
                        align-items: flex-end;
                        display: flex;
                        margin-left: .3rem;
                        flex: none;
                        font-size: 1.4rem;
                        justify-content: flex-end;
                        color: #0068b7;
                      }
                    }
                  }
                }
              }

              .map {
                flex: auto;
              }
            }
            &.item3 {
              background: url("./img/bb.png") no-repeat center center / 100% 100%;

              > div {
                flex: auto;
                align-items: center;
                display: flex;

                &.h {
                  text-indent: 2rem;
                  font-weight: bold;
                }
                &.c {
                  padding: 0 1.4rem 0 6rem;
                  background: url("./img/11.png") no-repeat 3rem center / 2rem;
                  justify-content: space-between;
                  font-family: "Microsoft YaHei";

                  div:first-of-type {
                    color: #515a6e;
                  }
                  div:last-of-type {
                    font-size: 2rem;
                  }
                  &:nth-child(4) {
                    background-image: url("./img/22.png");
                  }
                  &:nth-child(5) {
                    background-image: url("./img/33.png");
                  }
                  &:nth-child(6) {
                    background-image: url("./img/44.png");
                  }
                  &:nth-child(7) {
                    background-image: url("./img/55.png");
                  }
                  &:nth-child(8) {
                    background-image: url("./img/66.png");
                  }
                  &:nth-child(9) {
                    background-image: url("./img/77.png");
                  }
                }
              }
            }
          }
        }

        .footer {
          display: flex;
          min-height: 8.5rem;
          margin: 0 2rem 1rem 2rem;

          ul {
            display: flex;
            flex: auto;

            li {
              display: flex;
              flex: auto;
              flex-direction: column;
              background: url("./img/1.png") no-repeat center center / 100% 100%;

              div:first-of-type {
                height: 60%;
                background: url("./img/peisong.png") no-repeat bottom center / 4rem;
              }
              div:last-of-type {
                height: 40%;
                padding-top: 0.3rem;
                display: flex;
                justify-content: center;
                color: #fff;
                font-size: 1.3rem;
              }

              &:nth-child(2) {
                background-image: url("./img/2.png");

                div:first-of-type {
                  background-image: url("./img/gl.png");
                }
              }
              &:nth-child(3) {
                background-image: url("./img/3.png");

                div:first-of-type {
                  background-image: url("./img/qi.png");
                }
              }
              &:nth-child(4) {
                background-image: url("./img/4.png");

                div:first-of-type {
                  background-image: url("./img/dapeng.png");
                }
              }
              &:nth-child(5) {
                background-image: url("./img/5.png");

                div:first-of-type {
                  background-image: url("./img/zn.png");
                }
              }
            }
          }
        }
      }
    }
  }
</style>
