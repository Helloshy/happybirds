/**
 * 校验是否为数字 
 * @param nums
 * @returns true:数字 false:非数字
 */
function isNumber(nums){
	return /^[0-9]*$/.test(nums);
}