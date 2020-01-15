<template>
  <div>
    <AddKnowedge @onSumbit='sumbitTreeDoc' :docVal='docVal'></AddKnowedge>
    <Tree :key='keyVal' slot="aside" :data="treeData" :render='renderContent'></Tree>
  </div>
</template>
<script>
import {
  knowedgeTreeData,
  knowedgeSeach,
  deleteTreeDoc,
  addTreeDoc,
  eidtTreeDoc
} from '@/api/knowedge/knowedge';
import AddKnowedge from './AddKnowedge';
export default {
  name: 'KnowedgeTree',
  data() {
    return {
      treeData: [],
      docVal: { docTitle: '' },
      nowDoc: '',
      keyVal: 0
    };
  },
  components: {
    AddKnowedge
  },
  mounted() {
    this.getTreeData();
  },
  methods: {
    
    search() {
      knowedgeSeach(this.params);
    },
    async getTreeData() {
      let _treeData = await knowedgeTreeData();
      this.treeData = this.treeDataSet(_treeData);
    },
    treeDataSet(data) {
      data.forEach(item => {
        item.text = item.label || item.docTitle;
        item.title = item.label || item.docTitle;
        if (item.children) {
          this.treeDataSet(item.children);
        }
      });
      return data;
    },
    renderContent(h, { root, node, data }) {
      return h(
        'span',
        {
          style: {
            display: 'inline-block',
            width: '90%',
            height: '30px',
            lineHeight: '30px',
            fontSize: '14px',
            fontWeight: 'bold',
            cursor: data.children ? 'auto' : 'pointer'
          },
          on: {
            mouseover: () => {
              this.nowDoc = data.docId;              
            },
            mouseout: () => {
              this.nowDoc = '';
            },
            'click': () => {
              !data.children && console.log(22)
            }
          }
        },
        [
          h('span', [h('span', data.title)]),
          h(
            'span',
            {
              style: {
                display: this.nowDoc === data.docId ? 'inline-block' : 'none',
                float: 'right',
                marginRight: '32px'
              }
            },
            [
              // 添加
              h(AddKnowedge, {
                props: { label: '' },
                style: {
                  display: 'inline-block',
                  padding: '0px'
                },
                on: {
                  onSumbit: parms => {
                    this.sumbitTreeDoc(
                      {
                        ...parms,
                        docLevel: data.docLevel + 1,
                        docPath: data.docId
                      },
                      data
                    );
                  }
                }
              }),
              // 修改
              h(AddKnowedge, {
                props: {
                  docVal: { docTitle: data.docTitle || data.title },
                  label: '',
                  icon: 'md-create'
                },
                style: {
                  display: 'inline-block',
                  padding: '0px 4px'
                },
                on: {
                  onSumbit: parms => {
                    this.editTreeDoc(parms, data, root, node);
                  }
                }
              }),
              // 删除
              h('Button', {
                props: Object.assign({}, this.buttonProps, {
                  icon: 'ios-remove'
                }),
                on: {
                  click: () => {
                    this.remove(root, node, data);
                  }
                }
              })
            ]
          )
        ]
      );
    },
    async sumbitTreeDoc(params, data) {
      let _parms = {
        ...params,
        docLevel: params.docLevel || 1,
        docPath: params.docPath || 0
      };
      let retDocVal = await addTreeDoc(_parms);
      if (data) {
        // 添加子节点
        const children = data.children || [];
        retDocVal.text = retDocVal.docTitle
        retDocVal.title = retDocVal.docTitle 
        children.push(retDocVal);
        this.$set(data, 'children', children);
      } else {
        this.getTreeData();
      }
      this.docVal.docTitle = '';
    },
    async editTreeDoc(params, data, root, node) {
      await eidtTreeDoc({...params, docId: data.docId});
      let parentDoc = this.treeData.find(el => el.docId === data.docId);
      let index = -1;
      if (parentDoc) {
        index = this.treeData.indexOf(parentDoc);
        parentDoc.title = params.docTitle;
        this.treeData.splice(index, 1, parentDoc);
      } else {
        // 子节点修改
        let parentKey = root.find(el => el === node);
        let index = root.indexOf(parentKey);
        parentKey.node.title = params.docTitle;
        root.splice(index, 1, parentKey);
      }
    },
    remove(root, node, data) {
      let _this = this;
      this.$Modal.confirm({
        title: '提示',
        content: `是否确定删除 ‘${data.title}’ 文档`,
        loading: true,
        onOk: async function() {
          await deleteTreeDoc({ docId: data.docId });
          _this.$Modal.remove();
          let parentKey = root.find(el => el === node).parent
            ? root.find(el => el === node).parent
            : root.find(el => el === node);
          let index = -1;
          let parent = {};

          if (typeof parentKey === 'number') {
            // 子节点
            parent = root.find(el => el.nodeKey === parentKey).node;
            index = parent.children.indexOf(data);
            parent.children.splice(index, 1);
          } else {
            // 顶层节点
            // index = root.indexOf(parentKey);
            let treeIndex = _this.treeData.indexOf(parentKey.node);
            _this.treeData.splice(treeIndex, 1);
          }
        }
      });
    }
  }
};
</script>
