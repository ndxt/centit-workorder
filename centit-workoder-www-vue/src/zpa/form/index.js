import zpaForm from './form'
import zpaFormGroup from './form-group'
import zpaFormItem from './form-item'

import zpaTextInput from './input-text'
import zpaNumberInput from './input-number'
import zpaDateInput from './input-date'
import zpaDateRangeInput from './input-date-range'
import zpaRadioGroup from './radio-group'
import zpaCheckboxGroup from './checkbox-group'
import zpaSwitch from './switch'
import zpaSelect from './select'
import zpaTreeSelect from './tree-select'
import zpaTextarea from './textarea'
import zpaUpload from './upload2'
import zpaAutoComplete from './auto-complete'
import zpaHighlight from './highlight'

const Form = {}

Form.install = function(Vue) {
  Vue.component('zpaForm', zpaForm)
  Vue.component('zpaFormGroup', zpaFormGroup)
  Vue.component('zpaFormItem', zpaFormItem)

  Vue.component('zpaTextInput', zpaTextInput)
  Vue.component('zpaNumberInput', zpaNumberInput)
  Vue.component('zpaDateInput', zpaDateInput)
  Vue.component('zpaDateRangeInput', zpaDateRangeInput)
  Vue.component('zpaRadioGroup', zpaRadioGroup)
  Vue.component('zpaCheckboxGroup', zpaCheckboxGroup)
  Vue.component('zpaSwitch', zpaSwitch)
  Vue.component('zpaSelect', zpaSelect)
  Vue.component('zpaTreeSelect', zpaTreeSelect)
  Vue.component('zpaTextarea', zpaTextarea)
  Vue.component('zpaUpload', zpaUpload)
  Vue.component('zpaDateRangeInput', zpaDateRangeInput)
  Vue.component('zpaAutoComplete', zpaAutoComplete)
  Vue.component('zpaHighlight', zpaHighlight)
}

export default Form
