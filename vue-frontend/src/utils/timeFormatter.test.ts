/**
 * 时间格式化工具函数测试
 */

import { formatDuration, formatDurationShort, formatDurationHMS, smartFormatDuration } from './timeFormatter';

// 测试用例
const testCases = [
  { seconds: 0, expected: '0秒' },
  { seconds: 30, expected: '30秒' },
  { seconds: 60, expected: '1分钟' },
  { seconds: 90, expected: '1分30秒' },
  { seconds: 120, expected: '2分钟' },
  { seconds: 3600, expected: '1小时' },
  { seconds: 3660, expected: '1小时1分钟' },
  { seconds: 7200, expected: '2小时' },
  { seconds: 86400, expected: '1天' },
  { seconds: 90000, expected: '1天1小时' },
  { seconds: 172800, expected: '2天' },
  { seconds: 176400, expected: '2天1小时' },
];

console.log('=== 测试 smartFormatDuration 函数 ===');
testCases.forEach(({ seconds, expected }) => {
  const result = smartFormatDuration(seconds);
  const status = result === expected ? '✓' : '✗';
  console.log(`${status} ${seconds}秒 -> ${result} (期望: ${expected})`);
});

console.log('\n=== 测试 formatDuration 函数 ===');
const detailedTests = [
  { seconds: 3661, expected: '1小时1分钟1秒' },
  { seconds: 90061, expected: '1天1小时1分钟1秒' },
];
detailedTests.forEach(({ seconds, expected }) => {
  const result = formatDuration(seconds);
  const status = result === expected ? '✓' : '✗';
  console.log(`${status} ${seconds}秒 -> ${result} (期望: ${expected})`);
});

console.log('\n=== 测试 formatDurationShort 函数 ===');
const shortTests = [
  { seconds: 30, expected: '30s' },
  { seconds: 90, expected: '1m' },
  { seconds: 3660, expected: '1h 1m' },
  { seconds: 90000, expected: '1d 1h' },
];
shortTests.forEach(({ seconds, expected }) => {
  const result = formatDurationShort(seconds);
  const status = result === expected ? '✓' : '✗';
  console.log(`${status} ${seconds}秒 -> ${result} (期望: ${expected})`);
});

console.log('\n=== 测试 formatDurationHMS 函数 ===');
const hmsTests = [
  { seconds: 30, expected: '00:00:30' },
  { seconds: 90, expected: '00:01:30' },
  { seconds: 3660, expected: '01:01:00' },
  { seconds: 90000, expected: '25:00:00' },
];
hmsTests.forEach(({ seconds, expected }) => {
  const result = formatDurationHMS(seconds);
  const status = result === expected ? '✓' : '✗';
  console.log(`${status} ${seconds}秒 -> ${result} (期望: ${expected})`);
});

// 运行测试
console.log('\n=== 测试完成 ===');
