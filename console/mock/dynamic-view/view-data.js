import Mock from 'mockjs'

const users = []
for (let i = 0; i < 40; i++) {
  users.push(Mock.mock({
    id: '@increment',
    name: '@cname',
    birthday: +Mock.Random.date('T'),
    'country|1': ['CN', 'US', 'JP', 'EU'],
    createAt: '@datetime',
    'gender|1': [0,1,2],
    score: '@integer(0, 100)',
    salary: '@float(0, 100, 2, 2)',
    remark: '@title(5, 80)',
    advUser: '@boolean'
  }))
}


const countries = [
  {id: 'CN', text: '中国'},
  {id: 'US', text: '美国'},
  {id: 'JP', text: '日本'},
  {id: 'EU', text: '欧盟'},
]



export default [
  {
    url: '/dynamic-view/view-data/users', 
    type: 'get',
    response: config => {
      return {
        code: 0,
        data: users
      }
    }
  },
  {
    url: '/dynamic-view/view-data/countries', 
    type: 'get',
    response: config => {
      const id = config.query.id
      return {
        code: 0,
        data: !!id ? countries.filter(d => d.id === id) : countries
      }
    }
  },
]