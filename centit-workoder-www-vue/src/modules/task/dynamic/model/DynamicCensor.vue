<template>
  <div>
    <Divider orientation="left" style="font-size: 16px;">审批</Divider>

    <zpa-form>
      <zpa-form-group>
        <template v-if="value.initData.moduleParam">
          <template v-if="value.initData.moduleParam.hasIdea === 'Y'">
        <zpa-radio-group
          label="办理决定"
          v-model="value.config.ideaCode"
          :values="ideaCode"
        />
          </template>

          <template v-if="value.initData.moduleParam.assignTeamrole === 'Y'">
            <zpa-textarea
              label="发送"
              v-model="teamUserCodes"
              readonly
              :span="11.5"
              @focus="openCensor"
            />

            <EditDynamicCensorOperator
              ref="Censor"
              v-model="value"
              :type="'teamUserCodes'"
              @changeKey="changeKey"
              style="margin-top: 11px;"
            />
          </template>
          <template v-if="value.initData.moduleParam.assignEnginerole === 'Y'">
            <zpa-textarea
              label="引擎"
              v-model="engineUserCodes"
              readonly
              :span="11.5"
              @focus="openCensor"
            />

            <EditDynamicCensorOperator
              ref="Censor"
              v-model="value"
              :type="'engineUserCodes'"
              @changeKey="changeKey"
              style="margin-top: 11px;"
            />
          </template>
          <template v-if="value.initData.moduleParam.hasAttention === 'Y'">
            <zpa-textarea
              label="关注"
              v-model="attentionUserCodes"
              readonly
              :span="11.5"
              @focus="openCensor"
            />

            <EditDynamicCensorOperator
              ref="Censor"
              v-model="value"
              :type="'attentionUserCodes'"
              @changeKey="changeKey"
              style="margin-top: 11px;"
            />
          </template>
          <template v-if="value.initData.moduleParam.hasOrgrole === 'Y'">
            <zpa-textarea
              label="主办"
              v-model="zbOrgCode"
              readonly
              :span="11.5"
              @focus="openCensor"
            />

            <EditDynamicCensorOperator
              ref="Censor"
              v-model="value"
              :type="'zbOrgCode'"
              @changeKey="changeKey"
              style="margin-top: 11px;"
            />

            <zpa-textarea
              label="协办"
              v-model="xbOrgCode"
              readonly
              :span="11.5"
              @focus="openCensor"
            />

            <EditDynamicCensorOperator
              ref="Censor"
              v-model="value"
              :type="'xbOrgCode'"
              @changeKey="changeKey"
              style="margin-top: 11px;"
            />
          </template>
        </template>

        <zpa-textarea
          label="办理意见"
          v-model="value.config.transContent"
          :max="85"
        />

      </zpa-form-group>
    </zpa-form>

  </div>
</template>

<script>
  import EditDynamicCensorOperator from '../operators/EditDynamicCensorOperator'

  export default {
  name: 'DynamicCensor',

  props: {
    value: Object,
    ideaCode: Array,
  },

  data() {
    return {
      teamUserCodes: '',
      engineUserCodes: '',
      attentionUserCodes: '',
      zbOrgCode: '',
      xbOrgCode: '',
    }
  },

  components: {
    EditDynamicCensorOperator,
  },

  methods: {
    openCensor() {
      this.$refs['Censor'].$refs['Model'].open()
    },

    changeKey(data) {
      this.value.config[data.type] = data.key.join(',')
      this[data.type] = data.name
    },
  },
}
</script>
