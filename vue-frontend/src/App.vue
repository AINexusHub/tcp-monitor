<template>
  <div id="app">
    <el-container>
      <el-header>
        <h1>TCP连接监控系统</h1>
      </el-header>
      
      <el-main>
        <!-- 时间范围过滤器 -->
        <el-card class="filter-card">
          <template #header>
            <div class="card-header">
              <span>时间范围筛选</span>
            </div>
          </template>
          
          <el-form :model="filterForm" label-width="120px">
            <el-form-item label="开始时间">
              <el-date-picker
                v-model="filterForm.startTime"
                type="datetime"
                placeholder="选择开始时间"
                value-format="YYYY-MM-DD HH:mm:ss"
              />
            </el-form-item>
            
            <el-form-item label="结束时间">
              <el-date-picker
                v-model="filterForm.endTime"
                type="datetime"
                placeholder="选择结束时间"
                value-format="YYYY-MM-DD HH:mm:ss"
              />
            </el-form-item>
            
            <el-form-item>
              <el-button type="primary" @click="searchConnections" :loading="loading">
                查询
              </el-button>
              <el-button @click="resetFilter">重置</el-button>
            </el-form-item>
          </el-form>
        </el-card>

        <!-- 连接统计表格 -->
        <el-card class="table-card">
          <template #header>
            <div class="card-header">
              <span>TCP连接统计</span>
              <el-button type="primary" @click="refreshData" :loading="loading">
                刷新
              </el-button>
            </div>
          </template>

          <el-table
            :data="connectionStats"
            v-loading="loading"
            stripe
            style="width: 100%"
          >
            <el-table-column prop="localEndpoint" label="本地端点" min-width="120" />
            <el-table-column label="总计时长" width="120">
              <template #default="{ row }">
                {{ smartFormatDuration(row.duration) }}
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { getConnectionStats } from './api/tcp-monitor'
import { smartFormatDuration } from './utils/timeFormatter'

export default {
  name: 'App',
  setup() {
    const filterForm = ref({
      startTime: '',
      endTime: ''
    })
    
    const connectionStats = ref([])
    const loading = ref(false)

    const searchConnections = async () => {
      if (!filterForm.value.startTime || !filterForm.value.endTime) {
        ElMessage.warning('请选择开始时间和结束时间')
        return
      }

      loading.value = true
      try {
        const response = await getConnectionStats(filterForm.value.startTime, filterForm.value.endTime)
        connectionStats.value = response.sort((a, b) => b.duration - a.duration)
        ElMessage.success('查询成功')
      } catch (error) {
        console.error('查询失败:', error)
        ElMessage.error('查询失败，请检查网络连接')
      } finally {
        loading.value = false
      }
    }

    const resetFilter = () => {
      filterForm.value = {
        startTime: '',
        endTime: ''
      }
      connectionStats.value = []
    }

    const refreshData = () => {
      if (filterForm.value.startTime && filterForm.value.endTime) {
        searchConnections()
      } else {
        ElMessage.warning('请先选择时间范围')
      }
    }

    // 设置默认时间范围（最近24小时）
    const setDefaultTimeRange = () => {
      const endTime = new Date()
      const startTime = new Date(endTime.getTime() - 24 * 60 * 60 * 1000)
      
      filterForm.value.startTime = startTime.toISOString().replace('T', ' ').substring(0, 19)
      filterForm.value.endTime = endTime.toISOString().replace('T', ' ').substring(0, 19)
    }

    onMounted(() => {
      setDefaultTimeRange()
    })

    return {
      filterForm,
      connectionStats,
      loading,
      searchConnections,
      resetFilter,
      refreshData,
      smartFormatDuration
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

.el-header {
  background-color: #409EFF;
  color: white;
  text-align: center;
  line-height: 60px;
}

.filter-card {
  margin-bottom: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.table-card {
  margin-top: 20px;
}
</style>
