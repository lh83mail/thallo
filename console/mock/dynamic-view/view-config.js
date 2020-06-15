

/**
 * 简单列表配置
 */
const simpleList = {
  /** 视图ID */
  id: 'simpleList',
  /** 配置版本, 用于扩展 */
  version: '1.0',
  
  /**
   * 数据模型配置
   */
  models: [
    {
      id: 'user',
      doc: '用户信息对象',
      idProp: 'id',
      properties: [     // 用户属性
        {
          id: 'id',
          label: 'ID',
          type: 'string',  // 数据类型, 默认string
          primary: true
        },
        {
          id: 'name',
          label: '姓名',
          type: 'string', 
        },
        {
          id: 'birthday',
          label: '生日',
          type: 'date', 
        },
        {
          id: 'country',
          label: '国家',
          type: 'string', 
        },        
        {
          id: 'gender',
          label: '性别',
          type: 'int', 
        },
        {
          id: 'score',
          label: '积分',
          type: 'int', 
        },
        {
          id: 'salary',
          label: '薪水',
          type: 'decimal',
          length: 10,
          decimal: 2 
        },        
        {
          id: 'createAt',
          label: '注册时间',
          type: 'datetime', 
        },
        {
          id: 'remark',
          label: '备注',
          type: 'string', 
          length: 1000
        },
        {
          id: 'advUser',
          label: '高级用户',
          type: 'boolean'
        }
        
      ]
    },
    {
      id: 'generText',
      doc: '性别文本对象',
      idProp: 'id',
      properties: [     // 用户属性
        {
          id: 'id',
          label: 'ID',
          type: 'int',
          primary: true 
        },
        {
          id: 'text',
          label: '文本',
          type: 'string', 
        }
      ]
    }
  ], 
  /**
   * 数据集(DataSet)配置
   */
  ds: [
    // 数据源配置
    {
      id: 'user-list',       // 数据源ID, 数据集范围内唯一
      model: 'user',         // 数据模型ID
      doc: '列表主要数据源',  // 数据源说明
      provider: {            // 数据提供器
        type: 'http',        // http 远程数据源
        method: 'GET',       // http 请求方法，默认GET
        url: '/dynamic-view/view-data/users',              // 数据源地址
        filter: [
          {index: 'name', op: 'contains'},
          {index: 'createAt', op: 'between'},
          {index: 'gender', op: 'eq'},
          {index: 'country', op: 'eq'}
        ]
      }
    },
    {
      id: 'gener-text',
      doc: '性别文本',
      model: 'generText',
      provider: {
        type: 'local',           // 本地提供数据源
        data: [
          {id: 0, text: '保密' },
          {id: 1, text: '男' },
          {id: 3, text: '女' }
        ]
      }
    },
    {
      id: 'country',       
      model: 'user',
      doc: '国家数据源',    
      provider: {         
        type: 'http',        
        method: 'GET',
        url: '/dynamic-view/view-data/countries'
      }
    },
  ],

  actions: [

  ],  
  /**
   * UI 配置
   */
  ui: [
   {
     /** 视图显示设备 */
     device: 'pc',
     id: 'simple-list',  /** 视图模板名称, 不同视图配置参数不一样 */
     version: 1,
    
     /** 视图模板参数配置 */
     templateArgs: {
       ds: 'user-list',   // 目标数据源ID
       pageable: true,    // 分页显示,默认true
       limit: 25,         // 分页大小 

       /** 表格列 */
       columns: [
          {index: 'name'},
          {index: 'birthday', title: '用户生日'},  // 覆盖默认标题
          {index: 'gender'},
       ],

       queryBar: {
          properties: [
            {index:'name'},
            {index:'createAt'},
            {index:'gender'},
            {index:'country'},
          ]
       }
     }
   }
  ]
};



const views = {
  simpleList
}
export default [
  {
    url: '/dynamic-view/view-config/([^/]+)', 
    type: 'get',
    response: config => {
      console.log("RRRR",config.params[0]) 
      
      return {
        code: 0,
        data: views[config.params[0]]
      }
    }
  },
]