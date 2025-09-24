/**
 * 时间格式化工具函数
 * 用于将秒数转换为人类可读的格式
 */

/**
 * 将秒数转换为人类可读的时间格式
 * @param seconds 秒数
 * @returns 格式化后的时间字符串
 */
export function formatDuration(seconds: number): string {
  if (seconds < 0) {
    return '无效时间';
  }

  if (seconds === 0) {
    return '0秒';
  }

  const days = Math.floor(seconds / (24 * 60 * 60));
  const hours = Math.floor((seconds % (24 * 60 * 60)) / (60 * 60));
  const minutes = Math.floor((seconds % (60 * 60)) / 60);
  const secs = Math.floor(seconds % 60);

  const parts: string[] = [];

  if (days > 0) {
    parts.push(`${days}天`);
  }
  
  if (hours > 0) {
    parts.push(`${hours}小时`);
  }
  
  if (minutes > 0) {
    parts.push(`${minutes}分钟`);
  }
  
  if (secs > 0 && days === 0 && hours === 0) {
    // 只有当天数和小时数都为0时才显示秒数
    parts.push(`${secs}秒`);
  }

  return parts.join('') || '0秒';
}

/**
 * 简化的时间格式化（更紧凑的显示）
 * @param seconds 秒数
 * @returns 简化格式的时间字符串
 */
export function formatDurationShort(seconds: number): string {
  if (seconds < 0) {
    return '无效';
  }

  if (seconds === 0) {
    return '0秒';
  }

  const days = Math.floor(seconds / (24 * 60 * 60));
  const hours = Math.floor((seconds % (24 * 60 * 60)) / (60 * 60));
  const minutes = Math.floor((seconds % (60 * 60)) / 60);

  if (days > 0) {
    return `${days}d ${hours}h`;
  } else if (hours > 0) {
    return `${hours}h ${minutes}m`;
  } else if (minutes > 0) {
    return `${minutes}m`;
  } else {
    return `${seconds}s`;
  }
}

/**
 * 将秒数转换为小时:分钟:秒格式
 * @param seconds 秒数
 * @returns HH:MM:SS 格式的字符串
 */
export function formatDurationHMS(seconds: number): string {
  if (seconds < 0) {
    return '00:00:00';
  }

  const hours = Math.floor(seconds / 3600);
  const minutes = Math.floor((seconds % 3600) / 60);
  const secs = Math.floor(seconds % 60);

  return [
    hours.toString().padStart(2, '0'),
    minutes.toString().padStart(2, '0'),
    secs.toString().padStart(2, '0')
  ].join(':');
}

/**
 * 根据时间长度选择合适的格式化方式
 * @param seconds 秒数
 * @returns 格式化后的时间字符串
 */
export function smartFormatDuration(seconds: number): string {
  if (seconds < 60) {
    // 小于1分钟，显示秒数
    return `${seconds}秒`;
  } else if (seconds < 3600) {
    // 小于1小时，显示分钟和秒数
    const minutes = Math.floor(seconds / 60);
    const secs = Math.floor(seconds % 60);
    return secs > 0 ? `${minutes}分${secs}秒` : `${minutes}分钟`;
  } else if (seconds < 86400) {
    // 小于1天，显示小时和分钟
    const hours = Math.floor(seconds / 3600);
    const minutes = Math.floor((seconds % 3600) / 60);
    return minutes > 0 ? `${hours}小时${minutes}分钟` : `${hours}小时`;
  } else {
    // 大于等于1天，显示天数和小时
    const days = Math.floor(seconds / 86400);
    const hours = Math.floor((seconds % 86400) / 3600);
    return hours > 0 ? `${days}天${hours}小时` : `${days}天`;
  }
}
