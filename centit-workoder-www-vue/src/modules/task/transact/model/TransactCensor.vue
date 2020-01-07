<template>
  <div style="display: flex; flex: none; flex-direction: column;">
    <Divider orientation="left" style="font-size: 16px;">审批</Divider>

    <zpa-form ref="Form">
      <zpa-form-group>
        <template v-if="value.initData.moduleParam">
          <template v-if="value.initData.moduleParam.hasIdea === 'Y'">
            <zpa-radio-group
              :label="feid.ideaLabel"
              v-model="value.config.ideaCode"
              :values="ideaCodes"
            />
          </template>
          <template v-if="value.initData.moduleParam.assignTeamrole === 'Y'">
            <zpa-textarea
              :label="feid.teamroleName"
              v-model="teamUserCodes"
              readonly
              :span="11.5"
              @focus="openCensor('TeamUserCensor')"
            />

            <EditTransactCensorOperator
              ref="TeamUserCensor"
              v-model="value"
              :type="'teamUserCodes'"
              @changeKey="changeKey"
              style="margin-top: 11px;"
            />
          </template>
          <template v-if="value.initData.moduleParam.assignEnginerole === 'Y'">
            <zpa-textarea
              :label="feid.engineroleName"
              v-model="engineUserCodes"
              readonly
              :span="11.5"
              @focus="openCensor('EngineUserCensor')"
            />

            <EditTransactCensorOperator
              ref="EngineUserCensor"
              v-model="value"
              :type="'engineUserCodes'"
              @changeKey="changeKey"
              style="margin-top: 11px;"
            />
          </template>
          <template v-if="value.initData.moduleParam.hasAttention === 'Y'">
            <zpa-textarea
              :label="feid.attentionLabel"
              v-model="attentionUserCodes"
              readonly
              :span="11.5"
              @focus="openCensor('AttentionUserCensor')"
            />

            <EditTransactCensorOperator
              ref="AttentionUserCensor"
              v-model="value"
              :type="'attentionUserCodes'"
              @changeKey="changeKey"
              style="margin-top: 11px;"
            />
          </template>
          <template v-if="value.initData.moduleParam.hasOrgrole === 'Y'">
            <template v-if="value.initData.moduleParam.zbOrgCode!==''">
              <zpa-textarea
                :label="feid.zbOrgroleName"
                v-model="zbOrgCode"
                readonly
                :span="11.5"
                @focus="openCensor('ZbOrgCensor')"
              />

              <EditTransactCensorOperator
                ref="ZbOrgCensor"
                v-model="value"
                :type="'zbOrgCode'"
                @changeKey="changeKey"
                style="margin-top: 11px;"
              />
            </template>
              <template v-if="value.initData.moduleParam.xbOrgCode!==''">
              <zpa-textarea
                :label="feid.xbOrgroleName"
                v-model="xbOrgCode"
                readonly
                :span="11.5"
                @focus="openCensor('XbOrgCensor')"
              />

              <EditTransactCensorOperator
                ref="XbOrgCensor"
                v-model="value"
                :type="'xbOrgCode'"
                @changeKey="changeKey"
                style="margin-top: 11px;"
              />
            </template>
          </template>
        </template>

        <!--:label="feid.ideaLabel"  label="意见"-->
        <zpa-textarea
          :label="feid.ideaContent"
          v-model="value.config.transContent"
          :max="1000"
        />

      </zpa-form-group>
    </zpa-form>

  </div>
</template>

<script>
import EditTransactCensorOperator from '../operators/EditTransactCensorOperator'

export default {
  name: 'TransactCensor',

  props: {
    value: Object,
    ideaCodes: Array,
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
    EditTransactCensorOperator,
  },

  computed: {
    feid() {
      console.log(this.value.initData.moduleParam);
      return this.value.initData.moduleParam
    }
  },

  methods: {
    openCensor(type) {
      this.$refs[type].$refs['Model'].open()
    },

    changeKey(data) {
      this.value.config[data.type] = data.key.join(',')
      this[data.type] = data.name
    },
  },
}
</script>
