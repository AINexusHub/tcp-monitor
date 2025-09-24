/**
 * 示例：如何使用生成的 TCP Monitor API 客户端
 * 
 * 这个文件展示了如何配置和使用 swagger-codegen 生成的 axios 客户端
 */

import { Configuration, ConnectionsApi, ConnectionStatsDTO } from './generated';

// 创建 API 配置
const configuration = new Configuration({
  basePath: 'http://localhost:8080', // 开发环境
  // 生产环境: 'https://api.ainexushub.cn'
});

// 创建 API 实例
const connectionsApi = new ConnectionsApi(configuration);

/**
 * 获取 TCP 连接统计数据的示例函数
 */
export async function getConnectionStats(startTime: string, endTime: string): Promise<ConnectionStatsDTO[]> {
  try {
    console.log('正在获取 TCP 连接统计数据...');
    
    // 调用 API - 可以传入时间参数
    const response = await connectionsApi.getConnectionStats(
      startTime,
      endTime,
      {
        timeout: 10000, // 10秒超时
      }
    );

    const stats: ConnectionStatsDTO[] = response.data;
    console.log('获取到的连接统计数据:', stats);
    
    // 处理数据
    stats.forEach((stat: ConnectionStatsDTO) => {
      console.log(`端点: ${stat.localEndpoint}, 持续时间: ${stat.duration}秒`);
    });

    return stats;

  } catch (error: any) {
    console.error('获取连接统计数据时出错:', error);
    
    if (error.response) {
      // 服务器返回了错误响应
      console.error('状态码:', error.response.status);
      console.error('错误信息:', error.response.data);
      throw new Error(`API 错误: ${error.response.status} - ${error.response.data.message}`);
    } else if (error.request) {
      // 请求已发出但没有收到响应
      console.error('网络错误: 没有收到响应');
      throw new Error('网络错误: 无法连接到服务器');
    } else {
      // 其他错误
      console.error('错误:', error.message);
      throw new Error(`请求错误: ${error.message}`);
    }
  }
}


// 导出配置好的 API 实例，方便在其他地方使用
export { connectionsApi, configuration };
